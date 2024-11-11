DESCRIPTION = "Container Manager config for cluster demo"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"


SRC_URI = "file://cluster-demo.json \
           file://system.conf.cluster-demo.in \
          "

inherit cm-config

do_install:append() {
    install -Dm644 ${WORKDIR}/cluster-demo.json ${D}/opt/container/conf/cluster-demo.json
    install -d ${D}/opt/container/guests/cluster-demo/rootfs
    install -d ${D}/opt/container/guests/cluster-demo/nv
    install -d ${D}/opt/container/guests/cluster-demo/shmounts
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
    /opt/container/conf/* \
    /opt/container/guests/cluster-demo/* \
    "

