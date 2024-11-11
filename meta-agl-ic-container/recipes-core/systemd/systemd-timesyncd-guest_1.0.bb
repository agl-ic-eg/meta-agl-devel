SUMMARY     = "Container guest configuration for systemd-timesyncd."
DESCRIPTION = "Container guest configuration for systemd-timesyncd. \
               It enables systemd-timesyncd in container guest."
LICENSE = "MIT"
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
