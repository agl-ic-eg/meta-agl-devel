SUMMARY     = "Homescreen for AGL Momi IVI"
DESCRIPTION = "Homescreen for AGL Momi IVI."
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = " \
    qttools-native \
    qtbase \
    qtdeclarative \
    qtwayland \
"

PV = "2.0.0"

SRC_URI = "git://git.automotivelinux.org/apps/momiscreen;protocol=https;branch=${AGL_BRANCH} \
           file://momiscreen.service \
           file://momiscreen \
          "
SRCREV = "bfbac0b1f78962e19ceac8356a6c0c77ccce795b"

S = "${WORKDIR}/git"

inherit cmake qt6-cmake systemd pkgconfig

do_install:append() {
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/momiscreen.service ${D}/${systemd_unitdir}/system

	install -m 0755 -d ${D}${sysconfdir}/default/
	install -m 0755 ${WORKDIR}/momiscreen ${D}${sysconfdir}/default/
}

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "momiscreen.service"

RDEPENDS:${PN} = " \
    qtbase \
    qtdeclarative \
    qtwayland \
"
