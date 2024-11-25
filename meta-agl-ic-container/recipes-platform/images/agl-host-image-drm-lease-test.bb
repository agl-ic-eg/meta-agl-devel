SUMMARY = "DRM Lease LXC test host image"
LICENSE = "MIT"

require lxc-host-image-minimal.bb
require recipes-platform/images/agl-lxc-multiconfig-build.inc

CONTAINER_IMAGES ?= "agl-container-ivi:guest-image-drm-lease-test-ivi \
                     agl-container-cluster:guest-image-drm-lease-test-cluster \
                    "

IMAGE_INSTALL += " \
    kernel-modules \
"

# packages required for network bridge settings via lxc-net
IMAGE_INSTALL += " \
    container-manager \
    cm-config-drm-lease-test-cluster image-mount-drm-lease-test-cluster \
    cm-config-drm-lease-test-ivi image-mount-drm-lease-test-ivi \
    lxc-networking \
    iptables-modules \
    dnsmasq \
    systemd-netif-config \
    kernel-module-xt-addrtype \
    kernel-module-xt-multiport \
"

install_container_images() {
    for c in ${CONTAINER_IMAGES}; do
        config=${c%:*}
        image=${c#*:}
        name=${image#guest-image-}
        src="${TOPDIR}/tmp-${config}/deploy/images/${MACHINE}/${image}-${MACHINE}${MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.ext4"
        bbnote "Installing ${src}"
        install -Dm644 ${src} ${IMAGE_ROOTFS}/var/lib/machines/${image}.ext4
    done
}

ROOTFS_POSTPROCESS_COMMAND += "install_container_images; "


# Under the this line, shall describe machine specific package.
IMAGE_INSTALL:append:rcar-gen3 = " kernel-module-gles gles-user-module-firmware"
