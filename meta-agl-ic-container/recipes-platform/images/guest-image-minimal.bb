require recipes-platform/images/agl-image-boot.bb

SUMMARY = "A minimal container guest image"

IMAGE_ROOTFS_EXTRA_SPACE:append = "${@bb.utils.contains("DISTRO_FEATURES", "systemd", " + 4096", "" ,d)}"

IMAGE_INSTALL += " \
    packagegroup-agl-container-feature-logging-guest \
    ${@bb.utils.contains('VIRTUAL-RUNTIME_net_manager', 'systemd', 'systemd-timesyncd-guest', '', d)} \
"

FEATURE_PACKAGES_selinux:remove = " \
    packagegroup-agl-core-selinux \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'packagegroup-agl-core-selinux-devel', '', d)} \
"
FEATURE_PACKAGES_selinux:append = " \
    packagegroup-agl-core-selinux-guest \
"

NO_RECOMMENDATIONS = "1"
