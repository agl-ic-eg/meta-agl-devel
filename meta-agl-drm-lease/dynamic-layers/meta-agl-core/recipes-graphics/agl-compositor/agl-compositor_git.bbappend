FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://0001-Add-drm-lease-support.patch"

PACKAGECONFIG[drm-lease] = "-Ddrm-lease=true,-Ddrm-lease=false,drm-lease-manager"
PACKAGECONFIG:append = " drm-lease"
