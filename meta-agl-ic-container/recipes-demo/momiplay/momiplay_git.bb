SUMMARY     = "Momiyama mediaplayer example based on AGL sample app. at CC"
DESCRIPTION = "The momiplay is a mediaplayer example based on AGL sample app. \
               The momiplay is not require agl-appfw."
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://main.cpp;beginline=1;endline=17;md5=24274715d08cb2416c20d2907d19f413"

DEPENDS = " \
    qttools-native \
    qtbase \
    qtdeclarative \
    qtwayland \
    qtsvg \
    qtmultimedia \
    "

PV = "2.0.0"

SRC_URI = "git://git.automotivelinux.org/apps/momiplayer;protocol=https;branch=${AGL_BRANCH} \
           file://momiplay.service \
           file://momiplay \
          "
SRCREV = "e22a2d57ec08bfff591a0bc3494d359e80830791"

S = "${WORKDIR}/git"

inherit cmake qt6-cmake systemd pkgconfig

do_install:append() {
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/momiplay.service ${D}/${systemd_unitdir}/system

	install -m 0755 -d ${D}${sysconfdir}/default/
	install -m 0755 ${WORKDIR}/momiplay ${D}${sysconfdir}/default/
}

FILES:${PN} += " \
    ${systemd_unitdir} \
    ${sysconfdir}/*/* \
    "
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "momiplay.service"

RDEPENDS:${PN} = " \
    qtsvg qtsvg-plugins qtsvg-qmlplugins \
    qtmultimedia qtmultimedia-plugins qtmultimedia-qmlplugins \
    "
