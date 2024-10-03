SUMMARY = "vhost Console backend device"
DESCRIPTION = "A vhost-user backend that emulates a VirtIO console device"
HOMEPAGE = "https://gerrit.automotivelinux.org"

FILESEXTRAPATHS:prepend := "${THISDIR}:" 
EXTRAPATHS:prepend := "${THISDIR}:" 

SRC_URI = " file://. "

LICENSE = "Apache-2.0 | BSD-3-Clause"

LIC_FILES_CHKSUM = "\
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
"

inherit cargo
inherit pkgconfig
inherit cargo-update-recipe-crates

include vhost-device-console-crates.inc

CARGO_BUILD_FLAGS = "-v --offline --target ${RUST_HOST_SYS} ${BUILD_MODE} --manifest-path=${CARGO_MANIFEST_PATH}"
