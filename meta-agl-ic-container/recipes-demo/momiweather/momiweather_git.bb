SUMMARY     = "Momiyama weather application example."
DESCRIPTION = "AGL sample weather application for container integration."
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = " \
    qttools-native \
    qtbase \
    qtdeclarative \
    qtwayland \
    qtsvg \
    "

PV = "1.0.0"

SRC_URI = "git://github.com/AGLExport/momiweather.git;protocol=https;branch=main \
           file://momiweather.service \
           file://momiweather \
          "
SRCREV = "2f742360975c944c9c9190375ce828b2de185cfb"

S = "${WORKDIR}/git"

inherit cmake qt6-cmake systemd pkgconfig

do_install:append() {
	install -d ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/momiweather.service ${D}/${systemd_unitdir}/system

	install -m 0755 -d ${D}${sysconfdir}/default/
	install -m 0755 ${WORKDIR}/momiweather ${D}${sysconfdir}/default/
}

FILES:${PN} += " \
    ${systemd_unitdir} \
    ${sysconfdir}/*/* \
    "
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "momiweather.service"

RDEPENDS:${PN} = " \
    qtsvg qtsvg-plugins qtsvg-qmlplugins \
    "
