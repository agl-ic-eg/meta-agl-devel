SUMMARY     = "Systemd network interface configuration"
DESCRIPTION = "Systemd network interface configuration \
              "
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://20-wired.network \
    file://21-wired.network \
    file://21-usb.network \
    "

do_install() {
	install -D -m0644 ${WORKDIR}/20-wired.network ${D}${sysconfdir}/systemd/network/20-wired.network
	install -D -m0644 ${WORKDIR}/21-wired.network ${D}${sysconfdir}/systemd/network/21-wired.network
	install -D -m0644 ${WORKDIR}/21-usb.network ${D}${sysconfdir}/systemd/network/21-usb.network
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    ${sysconfdir}/systemd/network/* \
"
