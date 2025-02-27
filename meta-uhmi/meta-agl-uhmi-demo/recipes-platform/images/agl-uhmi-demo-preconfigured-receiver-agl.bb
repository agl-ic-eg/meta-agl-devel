SUMMARY = "AGL Unified HMI demo preconfigured AGL receiver image"
LICENSE = "MIT"

DEPENDS = "uhmi-agl-wm"

require recipes-platform/images/agl-image-compositor.bb
require recipes-platform/images/agl-demo-features.inc

IMAGE_FEATURES += "ssh-server-openssh"

# Add packages for Unified HMI demo platform here
IMAGE_INSTALL += " \
    packagegroup-rvgpu \
    packagegroup-ddfw-agl \
    uhmi-config-receiver \
    agl-compositor \
    native-shell-client \
    uhmi-udev-conf \
"

UHMI_HOSTNAME ?= "agl-host1"
require recipes-config/uhmi-config/set-hostname.inc
