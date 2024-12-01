SUMMARY = "AGL Container Manager"
DESCRIPTION = "AGL Container Manager for AGL Instrument Cluster."
AUTHOR = "Naoto Yamaguchi/ AGL Instrument Cluster Expert Group"
HOMEPAGE = "https://github.com/AGLExport/container-manager"
BUGTRACKER = "https://github.com/AGLExport/container-manager/issues"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "systemd libmnl cjson lxc util-linux"

PV = "0.1.0+rev${SRCPV}"

SRC_URI = " \
    git://github.com/AGLExport/container-manager.git;branch=staging2;protocol=https \
    file://container-manager.service \
    file://container-manager.json \
    "
SRCREV = "665d8985e07688e8e8ec9dbaec63acd3ccd2d715"

S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd features_check

REQUIRED_DISTRO_FEATURES = "systemd"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "container-manager.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    install -d ${D}/opt/container/conf/
    install -d ${D}/opt/container/guests/

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/container-manager.service ${D}${systemd_system_unitdir}/

    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/container-manager.json ${D}${sysconfdir}/
}

FILES:${PN} += " \
    ${systemd_system_unitdir}/* \
    ${sysconfdir}/* \
    /opt/container/conf \
    /opt/container/guests \
    "
