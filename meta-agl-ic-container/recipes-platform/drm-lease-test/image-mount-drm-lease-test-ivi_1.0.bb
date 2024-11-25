SUMMARY = "AGL CI guest image mounter for drm-lease-test-ivi."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://drm-lease-test-ivi.mount \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "var-lib-machines-guest2.mount"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append () {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/drm-lease-test-ivi.mount ${D}${systemd_system_unitdir}/var-lib-machines-guest2.mount
    install -d ${D}/var/lib/machines/guest2
}

FILES:${PN} += " \
    ${systemd_system_unitdir} \
    /var/lib/machines/* \
"
