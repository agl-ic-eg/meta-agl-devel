DESCRIPTION = "Environment variable setting for Qt eslfs drm lease support"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"


SRC_URI = "file://qt-drm-lease \
          "

do_install() {
    install -Dm644 ${WORKDIR}/qt-drm-lease ${D}${sysconfdir}/default/qt-drm-lease
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
    ${sysconfdir}/default/* \
    "

