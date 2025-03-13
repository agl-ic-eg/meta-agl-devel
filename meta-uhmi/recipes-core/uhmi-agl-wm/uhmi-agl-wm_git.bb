DESCRIPTION = "UHMI AGL window-manager"
DEPENDS = " agl-compositor grpc grpc-native jansson"

RDEPENDS:${PN} = " jansson"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=fb8ec92f12228c45a207d99abba9d6c9"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PN="uhmi-agl-wm"

SRCREV = "20c1228834f141bbbb6c0ee71d4f3c4149f87b66"
BRANCH ?= "master"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/uhmi-agl-wm.git;protocol=https;branch=${AGL_BRANCH}"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit meson pkgconfig systemd features_check

SRC_URI += " file://uhmi-agl-wm.service"

REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "uhmi-agl-wm.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

FILES:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_system_unitdir}/${SYSTEMD_SERVICE}', '', d)} \
    "

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
	install -d ${D}${systemd_system_unitdir}
	install -m 644 ${WORKDIR}/*.service ${D}/${systemd_system_unitdir}
    fi
}

FILES:${PN} += " /usr/bin/uhmi-agl-wm"
FILES:${PN} += " /usr/share/*"
