FILESEXTRAPATHS:prepend := "${THISDIR}/pulseaudio:"

SRC_URI:append = " \
    file://pulseaudio.service \
    file://system.pa \
    file://daemon.conf \
    "

do_install:append() {
    install -Dm644 ${WORKDIR}/system.pa ${D}${sysconfdir}/pulse/system.pa
    install -Dm644 ${WORKDIR}/daemon.conf ${D}${sysconfdir}/pulse/daemon.conf
    rm ${D}${sysconfdir}/pulse/default.pa

    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/pulseaudio.service ${D}/${systemd_unitdir}/system
    rm ${D}/${systemd_unitdir}/user/*
}

FILES:${PN}-server:append = " ${systemd_unitdir}/*"
CONFFILES:pulseaudio-server = "\ 
    ${sysconfdir}/pulse/daemon.conf \
    ${sysconfdir}/pulse/system.pa \
"

SYSTEMD_PACKAGES:append = " ${PN}-server"
SYSTEMD_SERVICE:${PN}-server = "pulseaudio.service"
SYSTEMD_AUTO_ENABLE:${PN}-server = "enable"
