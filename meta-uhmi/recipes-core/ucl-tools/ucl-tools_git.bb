SUMMARY = "Unified HMI Clustering Tools"
SECTION = "graphics"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/ucl-tools/LICENSE.md;md5=e789951aab02a3028d2e58b90fc933ba"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PN="ucl-tools"
PROVIDES += "ucl-tools"

SRCREV = "80e83132834b5ab62875bc294332bd00cd559d5d"
BRANCH ?= "main"
SRC_URI = " \
    git://github.com/unified-hmi/ucl-tools.git;protocol=https;branch=${BRANCH} \
"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

export GO111MODULE="auto"

GO_IMPORT = "ucl-tools"
GO_INSTALL = " ${GO_IMPORT}/cmd/ucl-launcher ${GO_IMPORT}/cmd/ucl-distrib-com ${GO_IMPORT}/cmd/ucl-consistency-keeper ${GO_IMPORT}/cmd/ucl-ncount-master ${GO_IMPORT}/cmd/ucl-ncount-worker ${GO_IMPORT}/cmd/ucl-nkeep-master ${GO_IMPORT}/cmd/ucl-nkeep-worker ${GO_IMPORT}/cmd/ucl-timing-wrapper ${GO_IMPORT}/cmd/ucl-virtio-gpu-wl-send ${GO_IMPORT}/cmd/ucl-virtio-gpu-wl-recv"

inherit go

RDEPENDS:${PN}  = "bash"
RDEPENDS:${PN}-dev = "bash"

inherit systemd features_check
SRC_URI += " file://ucl-launcher.service"

REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "ucl-launcher.service"
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
