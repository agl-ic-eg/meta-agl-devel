SUMMARY = "Configuration files for the Weston compositors for DRM lease testing"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://weston.ini.ivi \
    file://weston.ini.cluster \
"

S = "${WORKDIR}"

inherit allarch update-alternatives

# On-target weston.ini directory
weston_ini_dir = "${sysconfdir}/xdg/weston"

do_install() {
    install -d ${D}${weston_ini_dir}
    install -m 0644 ${WORKDIR}/weston.ini.ivi ${D}${weston_ini_dir}/
    install -m 0644 ${WORKDIR}/weston.ini.cluster ${D}${weston_ini_dir}/
}

ALTERNATIVE_LINK_NAME[weston.ini] = "${weston_ini_dir}/weston.ini"

RDEPENDS:${PN} = "${BPN}-ivi"
ALLOW_EMPTY:${PN} = "1"

PACKAGE_BEFORE_PN += "${PN}-ivi"

FILES:${PN}-ivi = "${weston_ini_dir}/weston.ini.ivi"

RPROVIDES:${PN}-ivi = "weston-ini"
ALTERNATIVE:${PN}-ivi = "weston.ini"
ALTERNATIVE_TARGET_${PN}-ivi = "${weston_ini_dir}/weston.ini.ivi"

PACKAGE_BEFORE_PN += "${PN}-cluster"

FILES:${PN}-cluster = "${weston_ini_dir}/weston.ini.cluster"

RPROVIDES:${PN}-cluster = "weston-ini"
RCONFLICTS:${PN}-cluster = "${PN}-ivi"
ALTERNATIVE:${PN}-cluster = "weston.ini"
ALTERNATIVE_TARGET_${PN}-cluster = "${weston_ini_dir}/weston.ini.cluster"
