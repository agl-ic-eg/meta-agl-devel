SUMMARY = "AGL Unified HMI demo preconfigured weston sender image"
LICENSE = "MIT"

DEPENDS = "uhmi-ivi-wm"

require recipes-platform/images/agl-image-weston.bb

IMAGE_FEATURES += "ssh-server-openssh"

# Add packages for Unified HMI demo platform here
IMAGE_INSTALL += " \
    packagegroup-rvgpu \
    packagegroup-ddfw \
    uhmi-config-sender \
    glmark2 \
"

UHMI_HOSTNAME ?= "agl-host0"
require recipes-config/uhmi-config/set-hostname.inc
