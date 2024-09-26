DESCRIPTION = "AGL Cluster Reference GUI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5335066555b14d832335aa4660d6c376"

DEPENDS = " \
    qttools-native \
    qtmultimedia \
    cluster-service \
"

BRANCH = "master"
SRC_URI = "git://git.automotivelinux.org/src/cluster-refgui;protocol=https;branch=${BRANCH} \
           file://cluster.service \
           file://cluster \
"
SRCREV = "5c8f09d2c3c99f621b467ed5c1be4fac3a708e85"

S = "${WORKDIR}/git"

inherit cmake qt6-cmake systemd pkgconfig

# NOTE:
# The app currently assumes the mp4 video file is in the same
# directory, so changing this to ${bindir} to better match FHS
# requires code changes.
APP_DIR = "/opt/apps"
EXTRA_OECMAKE = "-DAPPS_INST_DIR=${APP_DIR}"

SYSTEMD_SERVICE:${PN} = "cluster.service"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/cluster.service ${D}${systemd_unitdir}/system/
    
    install -m 0755 -d ${D}${sysconfdir}/default/
    install -m 0755 ${WORKDIR}/cluster ${D}${sysconfdir}/default/
}

FILES:${PN} += "${APP_DIR}/"

RDEPENDS:${PN} = " \
    qtbase \
    qtdeclarative \
    qt3d \
    qtmultimedia \
    qtwayland \
"
