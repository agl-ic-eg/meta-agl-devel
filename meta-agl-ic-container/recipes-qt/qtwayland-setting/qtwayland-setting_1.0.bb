DESCRIPTION = "Environment variable setting for Qt Wayland"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/BSD-3-Clause;md5=550794465ba0ec5312d6919e203a55f9"


SRC_URI = "file://qtwayland-common \
          "

do_install() {
    install -Dm644 ${WORKDIR}/qtwayland-common ${D}${sysconfdir}/default/qtwayland-common
}

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += " \
    ${sysconfdir}/default/* \
    "

