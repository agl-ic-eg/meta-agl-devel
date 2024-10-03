SUMMARY = "vhost CAN backend device"
DESCRIPTION = "A vhost-user backend that emulates a VirtIO CAN device"
HOMEPAGE = "https://gerrit.automotivelinux.org"

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
EXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI = "\
    file://vhost-device-can-0.1.0/ \
    git://github.com/socketcan-rs/socketcan-rs.git;protocol=https;branch=master \
"
SRCREV = "f004ee91e142a37fea36c5d719a57852c7076e87"

SRC_URI[sha256sum] = "f8a0826ee8082e8f6f3549eaa86406ee22187a5d1e3ad940d3788b072cf18991"

LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = "\
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
"

RDEPENDS:${PN}:append = " can-utils"

inherit cargo
inherit pkgconfig
inherit cargo-update-recipe-crates

include vhost-device-can-crates.inc
include socketcan-crates.inc

CARGO_BUILD_FLAGS = "-v --offline --target ${RUST_HOST_SYS} ${BUILD_MODE} --manifest-path=${CARGO_MANIFEST_PATH}"

S = "${WORKDIR}/vhost-device-can-0.1.0"
