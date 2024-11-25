SUMMARY = "Tiny window manager for wayland-ivi-extension"
DESCRIPTION = "Tiny window manager for wayland-ivi-extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "jsoncpp wayland-ivi-extension"

PV = "0.1.0+rev${SRCPV}"

SRCREV = "e3a33d47195e4656f7117753d27a0f2d6b21aab9"
SRC_URI = " \
    git://github.com/AGLExport/ilm-manager.git;branch=master;protocol=https \
    file://agl.json.in \
    file://ilm-manager.service \
    "
S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd

DRM_IVI_DEVICE = "HDMI-A-1"
DRM_IVI_DEVICE:qemuall = "Virtual-1"

do_install:append() {
    #install scripts

    sed 's|@DRM_IVI_DEVICE@|${DRM_IVI_DEVICE}|g' \
         ${WORKDIR}/agl.json.in > ${B}/agl.json

    install -d ${D}${sysconfdir}
    install -m 0644 ${B}/agl.json ${D}${sysconfdir}

    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ilm-manager.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += " ${systemd_system_unitdir} ${sysconfdir} "
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "ilm-manager.service"
