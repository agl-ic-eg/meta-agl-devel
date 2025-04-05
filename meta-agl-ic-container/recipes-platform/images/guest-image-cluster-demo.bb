SUMMARY = "LXC cluster demo guest image"
LICENSE = "MIT"

require guest-image-minimal.bb

IMAGE_INSTALL += " \
    packagegroup-agl-ic-core \
    packagegroup-drm-lease-client-support \
    packagegroup-agl-ic-qt \
    cluster-refgui \
"

IMAGE_INSTALL:append:rpi = " mesa-megadriver"

IMAGE_OVERHEAD_FACTOR = "0"
EXTRA_IMAGECMD:append = " -L agl-cluster"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_SIZE = "1048576"
