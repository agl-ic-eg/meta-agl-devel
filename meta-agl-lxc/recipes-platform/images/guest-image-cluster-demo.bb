SUMMARY = "LXC cluster demo guest image"
LICENSE = "MIT"

require guest-image-minimal.bb

IMAGE_INSTALL += " \
    weston \
    weston-init-guest \
    weston-ini-conf-guest \
    cluster-refgui \
    libiccom \
"
