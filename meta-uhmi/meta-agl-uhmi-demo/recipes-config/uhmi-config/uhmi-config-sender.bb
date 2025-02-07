SUMMARY     = "Setting files for UHMI sender"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://app.json \
    file://initial_vscreen.json \
    file://uhmi-ivi-wm.conf \
"

do_install() {
    install -d ${D}/var/local/uhmi-app/glmark2
    install -m 644 ${WORKDIR}/app.json ${D}/var/local/uhmi-app/glmark2/
    install -m 644 ${WORKDIR}/initial_vscreen.json ${D}/var/local/uhmi-app/glmark2/

    install -d ${D}/etc/systemd/system/uhmi-ivi-wm.service.d/
    install -m 0644 ${WORKDIR}/uhmi-ivi-wm.conf ${D}/etc/systemd/system/uhmi-ivi-wm.service.d/
}

FILES:${PN} += " \
    /var/local/uhmi-app/glmark2 \
"
