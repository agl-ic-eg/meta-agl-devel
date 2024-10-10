SUMMARY = "Unified HMI Package Groups"
LICENSE = "Apache-2.0"

inherit packagegroup

RDEPENDS:${PN} += " \
    ucl-tools \
    ula-tools \
    uhmi-ivi-wm \
    weston \
    wayland-ivi-extension \
"
