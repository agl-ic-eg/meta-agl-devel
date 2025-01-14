DESCRIPTION = "Panasonic UHMI ivi-window-manager"
DEPENDS = " weston wayland-ivi-extension jansson"

RDEPENDS:${PN} = " jansson"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=e789951aab02a3028d2e58b90fc933ba"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PN="uhmi-ivi-wm"

SRCREV = "ae86a2803f7a56bbc24d13756a2de78d58da854e"
BRANCH ?= "main"
SRC_URI = " \
    git://github.com/unified-hmi/uhmi-ivi-wm.git;protocol=https;branch=${BRANCH} \
"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

inherit cmake systemd features_check

SRC_URI += " file://uhmi-ivi-wm.service"
REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "uhmi-ivi-wm.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
FILES:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_system_unitdir}/${SYSTEMD_SERVICE}', '', d)} \
    "

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
	install -d ${D}${systemd_system_unitdir}
	install -m 644 ${WORKDIR}/*.service ${D}/${systemd_system_unitdir}
    fi
}

FILES:${PN} += " /usr/bin/uhmi-ivi-wm"
FILES:${PN} += " /usr/share/*"
