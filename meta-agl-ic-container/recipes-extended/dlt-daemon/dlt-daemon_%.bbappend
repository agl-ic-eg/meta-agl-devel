# DLT for Linux container integration
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

# NOTE:
# file://0001-Disable-noisy-log-from-dlt-daemon.patch removed
# until someone can review its status with upstream and rebase
# it if it is still required.
SRC_URI:append = " \
    file://dlt.conf \
    file://dlt-system.conf \
    file://dlt_logstorage.conf \
"

# Mandatory require to systemd feature
inherit features_check
REQUIRED_DISTRO_FEATURES = "systemd"

PACKAGECONFIG = "systemd systemd-watchdog systemd-journal dlt-system unixsocket"

# General Options
PACKAGECONFIG[unixsocket] = "-DDLT_IPC=UNIX_SOCKET -DDLT_USER_IPC_PATH=${DLT_COMMON_IPC_PATH},-DDLT_IPC=FIFO"

# Common DLT log transfer path between host and guest
DLT_COMMON_IPC_PATH = "/run/dlt/"

# Breakdown each package from all in one
PACKAGE_BEFORE_PN:append = "libdlt ${PN}-command ${PN}-system ${PN}-gateway ${PN}-dbus ${PN}-example"

SYSTEMD_PACKAGES:append = " \
    ${@bb.utils.contains('PACKAGECONFIG', 'dlt-system', '${PN}-system', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'dlt-dbus', ' ${PN}-dbus', '', d)} \
"
SYSTEMD_SERVICE:${PN}-system = "dlt-system.service"
SYSTEMD_AUTO_ENABLE:${PN}-system = "enable"
SYSTEMD_SERVICE:${PN}-dbus = "dlt-dbus.service"
SYSTEMD_AUTO_ENABLE:${PN}-dbus = "enable"
SYSTEMD_SERVICE:${PN} = "dlt.service"

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/dlt.conf ${D}${sysconfdir}/
    install -m 0644 ${WORKDIR}/dlt-system.conf ${D}${sysconfdir}/

    install -d ${D}/var/nvlog/dlt
    install -m 0644 ${WORKDIR}/dlt_logstorage.conf ${D}/var/nvlog/dlt
}

FILES:libdlt = "${libdir}/libdlt${SOLIBS}"
FILES:${PN}-command = " \
    ${bindir}/dlt-receive \
    ${bindir}/dlt-control \
    ${bindir}/dlt-convert \
    ${bindir}/dlt-logstorage-ctrl \
    ${bindir}/dlt-sortbytimestamp \
    ${bindir}/dlt-adaptor-stdin \
"
FILES:${PN}-system = " \
    ${bindir}/dlt-system \
    ${sysconfdir}/dlt-system.conf \
    ${systemd_system_unitdir}/dlt-system.service \
"
FILES:${PN}-gateway = " \
    ${bindir}/dlt-passive-node-ctrl \
    ${sysconfdir}/dlt_gateway.conf \
"
FILES:${PN}-dbus = " \
    ${bindir}/dlt-dbus \
    ${sysconfdir}/dlt-dbus.conf \
"
FILES:${PN}-example = " \
    ${bindir}/dlt-example* \
"
FILES:${PN}:append = " \
    /var/nvlog/dlt/dlt_logstorage.conf \
"
