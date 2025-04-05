SUMMARY = "Momi IVI demo guest image"
LICENSE = "MIT"

require guest-image-minimal.bb

IMAGE_INSTALL += " \
    packagegroup-drm-lease-client-support \
    packagegroup-agl-momi-ivi-qt \
    qtwayland-setting \
    momiscreen \
    mominavi \
    momiplay \
    momiweather \
    systemd-netif-config \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-common \
    ca-certificates \
    pre-install-video-data \
"

IMAGE_INSTALL:append:rpi = " mesa-megadriver"

IMAGE_OVERHEAD_FACTOR = "0"
EXTRA_IMAGECMD:append = " -L agl-momi-ivi"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_SIZE = "2097152"
