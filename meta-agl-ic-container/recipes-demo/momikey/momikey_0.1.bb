SUMMARY = "CH57x keyboard based UI for container demo"
DESCRIPTION = "Container exchange user interface using CH57x keyboard. \
               This UI based on mock container manager for AGL CES2023 demo."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI += " \
    file://momikey.sh \
    file://momikey.service \
    file://momikey.rules \
"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "momikey.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

do_install:append () {
    install -d ${D}/usr/bin
    install -m 0755 ${WORKDIR}/momikey.sh ${D}/usr/bin/momikey.sh
    
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/momikey.service ${D}${systemd_system_unitdir}
    
    install -d ${D}${sysconfdir}/udev/rules.d
    install -m 644 ${WORKDIR}/momikey.rules ${D}${sysconfdir}/udev/rules.d/
}

FILES:${PN} += " \
    /usr/bin/momikey.sh \
    ${systemd_system_unitdir} \
    ${sysconfdir}/udev/rules.d/ \
"

RDEPENDS:${PN} := " \
    bash \
    evtest \
"
