SUMMARY = "Unified HMI Layout Tools"
SECTION = "graphics"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/ula-tools/LICENSE.md;md5=e789951aab02a3028d2e58b90fc933ba"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

PN="ula-tools"
PROVIDES += "ula-tools"

SRCREV = "4403efd88fd4e79b092787ac727afe996dd7ca12"
BRANCH ?= "main"
SRC_URI = " \
    git://github.com/unified-hmi/ula-tools.git;protocol=https;branch=${BRANCH} \
"
PV = "0.0+git${SRCPV}"

S = "${WORKDIR}/git"

export GO111MODULE="auto"

GO_IMPORT = "ula-tools"
GO_INSTALL = " ${GO_IMPORT}/cmd/ula-distrib-com  ${GO_IMPORT}/cmd/ula-node"

inherit go
RDEPENDS:${PN}  = "jq bash"
RDEPENDS:${PN}-dev  = "bash"

inherit systemd features_check

SRC_URI += " \
	file://ula-node.service \
	file://virtual-screen-def.json \
	"

REQUIRED_DISTRO_FEATURES = "systemd"
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "ula-node.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"
FILES:${PN} += " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', '${systemd_system_unitdir}/${SYSTEMD_SERVICE}', '', d)} \
    /etc/uhmi-framework/virtual-screen-def.json \
    "

do_compile:append() {
    export CGO_ENABLED="1"
    export GOFLAGS="-mod=vendor -trimpath"
    ${GO} build  -buildmode=c-shared -o ${GOPATH}/pkg/libulaclient.so -v -ldflags '-extldflags "-Wl,-soname=libulaclient.so"' ${GO_IMPORT}/pkg/ula-client-lib
}

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES','systemd','true','false',d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 644 ${WORKDIR}/*.service ${D}/${systemd_system_unitdir}
    fi

    install -d ${D}/etc/uhmi-framework
    install -m 644 ${WORKDIR}/virtual-screen-def.json ${D}/etc/uhmi-framework

    install -d ${D}${libdir}
    install -m 0755 ${GOPATH}/pkg/libulaclient.so ${D}${libdir}

    install -d ${D}${includedir}
    install -m 644 ${GOPATH}/pkg/libulaclient.h ${D}${includedir}
}

FILES:${PN} += "${libdir}"
FILES:${PN} += "${includedir}"
