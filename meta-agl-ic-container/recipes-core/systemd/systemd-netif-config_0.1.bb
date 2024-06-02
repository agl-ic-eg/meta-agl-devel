SUMMARY     = "Systemd network interface configuration"
DESCRIPTION = "Systemd network interface configuration \
              "
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI = " \
    file://20-wired.network \
    file://21-wired.network \
    "

do_install() {
	install -D -m0644 ${WORKDIR}/20-wired.network ${D}${sysconfdir}/systemd/network/20-wired.network
	install -D -m0644 ${WORKDIR}/21-wired.network ${D}${sysconfdir}/systemd/network/21-wired.network
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    ${sysconfdir}/systemd/network/* \
"
