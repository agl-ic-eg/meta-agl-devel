DESCRIPTION = "Panasonic UHMI RVGPU Wayland Proxy"
DEPENDS = " virtual/libgbm libinput libxkbcommon"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=a8a0cf4075753e7d7e8c948171357af0"

SRCREV ="067d46a8977852afb8b503bd7d833b9037673db4"
BRANCH ?= "main"

SRC_URI = " \
        git://github.com/unified-hmi/rvgpu-wlproxy.git;protocol=https;branch=${BRANCH} \
"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit pkgconfig cmake

FILES:${PN} += "/usr/bin/*"
