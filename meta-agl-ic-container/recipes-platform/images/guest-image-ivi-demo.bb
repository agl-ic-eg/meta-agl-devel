SUMMARY = "LXC ivi demo guest image"
LICENSE = "MIT"

require guest-image-minimal.bb

IMAGE_INSTALL += " \
    weston \
    weston-init-guest \
    weston-ini-conf-guest \
    wayland-ivi-extension \
    ilm-manager \
    mominavi \
    momiplay \
    momiscreen \
    packagegroup-agl-ic-qt \
    systemd-netif-config \
    ttf-dejavu-sans \
    ttf-dejavu-sans-mono \
    ttf-dejavu-sans-condensed \
    ttf-dejavu-serif \
    ttf-dejavu-serif-condensed \
    ttf-dejavu-mathtexgyre \
    ttf-dejavu-common \
    ca-certificates \
    wireplumber \
    packagegroup-pipewire-base \
    wireplumber-policy-config-agl \
"

IMAGE_INSTALL:append:rpi = " mesa-megadriver"

IMAGE_OVERHEAD_FACTOR = "0"
EXTRA_IMAGECMD:append = " -L agl-momi-ivi"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_ROOTFS_SIZE = "1048576"
