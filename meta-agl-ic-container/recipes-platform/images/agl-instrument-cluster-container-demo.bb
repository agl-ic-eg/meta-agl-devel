SUMMARY = "AGL Instrument Cluster Cotainer Integration demo image"
LICENSE = "MIT"

require lxc-host-image-minimal.bb
require recipes-platform/images/agl-lxc-multi-partition-image.inc

CONTAINER_IMAGES ?= "agl-container-cluster:guest-image-cluster-demo \
                     agl-container-ivi:guest-image-ivi-demo \
                    "

IMAGE_INSTALL += " \
    kernel-modules \
    alsa-utils \
"

# packages required for network bridge settings via lxc-net
IMAGE_INSTALL += " \
    container-manager \
    cm-config-cluster-demo \
    cm-config-agl-momi-ivi-demo \
    lxc-networking \
    iptables-modules \
    dnsmasq \
    systemd-netif-config \
    kernel-module-xt-addrtype \
    kernel-module-xt-multiport \
"

# network manager to use
VIRTUAL-RUNTIME_net_manager = "systemd"


# Under the this line, shall describe machine specific package.
IMAGE_INSTALL:append:rcar-gen3 = " kernel-module-gles gles-user-module-firmware"
