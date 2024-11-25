DESCRIPTION = "Container Manager config for drm lease test cluster"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"


SRC_URI = "file://drm-lease-test-cluster.json \
           file://system.conf.drm-lease-test-cluster.in \
          "

inherit cm-config

do_install:append() {
    install -Dm644 ${WORKDIR}/drm-lease-test-cluster.json ${D}/opt/container/conf/drm-lease-test-cluster.json
    install -d ${D}/opt/container/guests/drm-lease-test-cluster/rootfs
    install -d ${D}/opt/container/guests/drm-lease-test-cluster/nv
    install -d ${D}/opt/container/guests/drm-lease-test-cluster/shmounts
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
    /opt/container/conf/* \
    /opt/container/guests/drm-lease-test-cluster/* \
    "

