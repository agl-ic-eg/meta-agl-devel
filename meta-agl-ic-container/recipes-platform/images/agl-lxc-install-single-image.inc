# Integration type support for install guest root filesystem into host image.

require recipes-platform/images/agl-lxc-multiconfig-build.inc

# Handle modification of IMAGE_LINK_NAME done by ULCB builds with Kingfisher support
MACHINE_SUFFIX = "${@bb.utils.contains('AGL_FEATURES', 'kingfisher', '-kf', '', d)}"

install_container_images() {
    for c in ${CONTAINER_IMAGES}; do
        config=${c%:*}
        image=${c#*:}
        name=${image#guest-image-}
        rm -rf  ${IMAGE_ROOTFS}/var/lib/machines/${name}
        install -m 0755 -d ${IMAGE_ROOTFS}/var/lib/machines/${name}
        src="${TOPDIR}/tmp-${config}/deploy/images/${MACHINE}/${image}-${MACHINE}${MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.tar.bz2"
        bbnote "Installing ${src}"
        tar -C ${IMAGE_ROOTFS}/var/lib/machines/${name} -xf ${src}
    done
}

ROOTFS_POSTPROCESS_COMMAND += "install_container_images; "
