DESCRIPTION = "Sample key files for the freerdp server"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " file://server.crt file://server.key "

FILES:${PN} = "/etc/xdg/weston/server.crt /etc/xdg/weston/server.key"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {

    install -d ${D}/etc/xdg/weston
    install -m 0644 ${WORKDIR}/server.crt ${D}/etc/xdg/weston/server.crt
    install -m 0644 ${WORKDIR}/server.key ${D}/etc/xdg/weston/server.key

}
