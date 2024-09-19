LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/drm-lease-manager;protocol=https;branch=${AGL_BRANCH} \
           file://drm-lease-manager.service \
           file://run-ptest \
           "

PV = "0.2+git${SRCPV}"
SRCREV = "8ec09a65cd5b390b3d24bfb6c7a73993a3d98a56"

S = "${WORKDIR}/git"

inherit meson pkgconfig
inherit systemd
inherit ptest

DEPENDS = "libdrm libcheck fff tomlc99"

do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/drm-lease-manager.service ${D}/${systemd_unitdir}/system
    rm -rf ${D}/${localstatedir}
}

SYSTEMD_SERVICE:${PN} = "drm-lease-manager.service"
RDEPENDS:${PN} = "drm-lease-manager-init"

PACKAGECONFIG ??= "${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)}"
PACKAGECONFIG[systemd] = "-Denable-systemd=true,-Denable-systemd=false,systemd"

EXTRA_OEMESON += "${@bb.utils.contains('PTEST_ENABLED', '1', '-Denable-tests=true', '', d)}"
RDEPENDS:${PN}-ptest = "libcheck"

do_install_ptest() {
    install ${B}/libdlmclient/test/libdlmclient-test ${D}${PTEST_PATH}
    install ${B}/drm-lease-manager/test/lease-server-test ${D}${PTEST_PATH}
    install ${B}/drm-lease-manager/test/lease-manager-test ${D}${PTEST_PATH}
}

PACKAGES =+ "libdlmclient"
FILES:libdlmclient = "${libdir}/libdlmclient${SOLIBS}"
