require recipes-platform/images/agl-image-compositor.bb

SUMMARY = "Example Flutter application debug image for development"
LICENSE = "MIT"

CLANGSDK = "1"

# NOTES:
# - the package-management IMAGE_FEATURE and packagegroup-agl-core-devel
#   are explicitly added here to cover the usecase of building without
#   passing agl-devel to aglsetup.sh.  This is explicitly a debug image
#   where those are are always desired.
# - Getting the debug version of the application requires changing
#   the value of FLUTTER_APP_RUNTIME_MODES to include "debug",
#   which is outside the scope of this image recipe.

IMAGE_FEATURES += "ssh-server-openssh package-management dbg-pkgs"

IMAGE_INSTALL += "\
    packagegroup-agl-core-devel \
    \
    weston-ini-conf-landscape \
    \
    flutter-auto-verbose-logs \
    \
    flutter-engine-sdk-dev \
    \
    flutter-gallery \
"
