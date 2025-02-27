SUMMARY = "AGL Unified HMI demo preconfigured AGL sender image"
LICENSE = "MIT"

DEPENDS = "uhmi-agl-wm"

require recipes-platform/images/agl-image-compositor.bb
require recipes-platform/images/agl-demo-features.inc

IMAGE_FEATURES += "ssh-server-openssh package-management"

# Add packages for Unified HMI demo platform here
IMAGE_INSTALL += " \
    packagegroup-rvgpu \
    packagegroup-ddfw-agl \
    uhmi-config-sender \
    agl-compositor \
    native-shell-client \
    glmark2 \
    uhmi-udev-conf \
"

UHMI_HOSTNAME ?= "agl-host0"
require recipes-config/uhmi-config/set-hostname.inc
