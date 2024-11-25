SUMMARY = "Configuration file for the Weston and AGL Wayland compositors for guest container"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
    file://weston.default.ini \
    file://weston.cluster-guest.ini \
    file://weston.ivi-guest.ini \
"

S = "${WORKDIR}"

inherit allarch

# Default weston.ini
WESTON_INI_FILE ??= "weston.default.ini"

# Set container specific weston.ini
WESTON_INI_FILE:aglcontainercluster ?= "weston.cluster-guest.ini"
WESTON_INI_FILE:aglcontainerivi ?= "weston.ivi-guest.ini"

do_install() {
    install -D -p -m0644 ${WORKDIR}/${WESTON_INI_FILE} ${D}${sysconfdir}/xdg/weston/weston.ini
}

FILES:${PN} += " \
    ${sysconfdir}/xdg/weston/weston.ini \
    "
CONFFILES:${PN} += " \
    ${sysconfdir}/xdg/weston/weston.ini \
    "
RDEPENDS:${PN} = "weston-init-guest"
RPROVIDES:${PN} = "weston-ini"
RCONFLICTS:${PN} = "weston-ini-conf"
