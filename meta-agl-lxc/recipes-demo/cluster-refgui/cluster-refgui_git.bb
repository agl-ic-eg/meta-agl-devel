DESCRIPTION = "AGL Cluster Reference GUI"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5335066555b14d832335aa4660d6c376"

DEPENDS = " \
    qttools-native \
    qtmultimedia \
    libclusterapi \
"

BRANCH = "master"
SRC_URI = "git://git.automotivelinux.org/src/cluster-refgui;protocol=https;branch=${BRANCH} \
           file://cluster.service \
"
SRCREV = "a16c6201be94a57eb8151a91699084d99694877c"

S = "${WORKDIR}/git"

inherit cmake cmake_qt5 systemd

# NOTE:
# The app currently assumes the mp4 video file is in the same
# directory, so changing this to ${bindir} to better match FHS
# requires code changes.
APP_DIR = "/opt/apps"
EXTRA_OECMAKE = "-DAPPS_INST_DIR=${APP_DIR}"

SYSTEMD_SERVICE_${PN} = "cluster.service"

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/cluster.service ${D}${systemd_unitdir}/system/
}

FILES_${PN} += "${APP_DIR}/"

RDEPENDS_${PN} = " \
    qtbase \
    qtdeclarative \
    qt3d \
    qtgraphicaleffects \
    qtmultimedia \
    qtquickcontrols \
    qtquickcontrols2 \
    qtwayland \
"
