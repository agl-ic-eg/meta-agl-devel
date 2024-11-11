DESCRIPTION = "Container Manager config for meta-agl-demo based AGL Qt IVI demo"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"

SRC_URI = "file://agl-qt-ivi-demo.json \
           file://system.conf.agl-qt-ivi-demo.in \
          "

inherit cm-config

do_install:append() {
    install -Dm644 ${WORKDIR}/agl-qt-ivi-demo.json ${D}/opt/container/conf/agl-qt-ivi-demo.json
    install -d ${D}/opt/container/guests/agl-qt-ivi-demo/rootfs
    install -d ${D}/opt/container/guests/agl-qt-ivi-demo/nv
    install -d ${D}/opt/container/guests/agl-qt-ivi-demo/shmounts
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
    /opt/container/conf/* \
    /opt/container/guests/agl-qt-ivi-demo/* \
    "
