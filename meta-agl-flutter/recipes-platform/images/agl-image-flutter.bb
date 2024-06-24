require recipes-platform/images/agl-image-compositor.bb

SUMMARY = "Example Flutter application image"
LICENSE = "MIT"

CLANGSDK = "1"

IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_INSTALL += "\
    weston-ini-conf-landscape \
    \
    flutter-auto \
    \
    flutter-gallery \
"
