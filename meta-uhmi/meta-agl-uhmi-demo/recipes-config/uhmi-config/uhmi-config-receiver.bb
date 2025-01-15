SUMMARY     = "Setting files for UHMI receiver"
LICENSE     = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://wired-receiver.config \
    file://uhmi-ivi-wm.conf \
"

do_install() {
    if [ ! -e ${D}/var/lib/connman/wired.config ]; then
        install -d ${D}/var/lib/connman/
        install -m 6444 ${WORKDIR}/wired-receiver.config ${D}/var/lib/connman/wired.config
    fi

    install -d ${D}/etc/systemd/system/uhmi-ivi-wm.service.d/
    install -m 0644 ${WORKDIR}/uhmi-ivi-wm.conf ${D}/etc/systemd/system/uhmi-ivi-wm.service.d/
}

FILES:${PN} += " \
    /var/lib/connman/wired.config \
"
