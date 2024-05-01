FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://0001-Remove-container-automatic-rebooting.patch \
    file://lxc.service \
    file://lxc-net.service \
    "

PACKAGECONFIG:remove = "templates"

do_install:append () {
    for service in lxc.service lxc-net.service; do
        install -D -m 0644 ${WORKDIR}/$service ${D}${systemd_system_unitdir}/$service
        sed -i -e 's,@LIBEXECDIR@,${libexecdir},g' ${D}${systemd_system_unitdir}/$service
    done
}

# Divide lxc autostart from main package.
SYSTEMD_PACKAGES = "${PN}-autostart ${PN}-monitord ${PN}-networking"
SYSTEMD_SERVICE:${PN} = ""
SYSTEMD_SERVICE:${PN}-autostart = "lxc.service"
SYSTEMD_AUTO_ENABLE:${PN}-autostart = "enable"
SYSTEMD_SERVICE:${PN}-monitord = "lxc-monitord.service"
SYSTEMD_AUTO_ENABLE:${PN}-monitord = "enable"

PACKAGES =+ "${PN}-autostart ${PN}-monitord"

FILES:${PN}-autostart += " \
    ${sysconfdir}/default/lxc \
    ${sysconfdir}/default/volatiles/99_lxc \
"
FILES:${PN}-doc += " \
    ${datadir}/doc/lxc/examples \
"
FILES:${PN}-monitord += " \
    ${libexexdir}/lxc/lxc-monitord \
"
FILES:${PN}-networking += " \
    ${sysconfdir}/dnsmasq.d \
"

# NOTE:
# This needs to be replaced with a rework of the upstream packaging
# to do a proper split of core from the template support.
RDEPENDS:${PN} = ""
