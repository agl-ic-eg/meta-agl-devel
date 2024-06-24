FILESEXTRAPATHS:prepend := "${THISDIR}/flutter-gallery:"

SRC_URI += "file://flutter-gallery.service"

inherit systemd

do_install:append() {
    install -D -m 0644 ${WORKDIR}/${BPN}.service ${D}${systemd_system_unitdir}/${BPN}.service
}

RDEPENDS:${PN} += "agl-flutter-env"

SYSTEMD_SERVICE:${PN} = "flutter-gallery.service"
