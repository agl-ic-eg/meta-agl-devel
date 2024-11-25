SUMMARY     = "Container guest configuration for systemd-timesyncd."
DESCRIPTION = "Container guest configuration for systemd-timesyncd. \
               It enables systemd-timesyncd in container guest."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://timesyncd-run-guest.conf \
    "

do_install() {
    install -D -m644 ${WORKDIR}/timesyncd-run-guest.conf ${D}/${systemd_system_unitdir}/systemd-timesyncd.service.d/timesyncd-run-guest.conf
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} = "\
    ${systemd_system_unitdir}/systemd-timesyncd.service.d/* \
"
