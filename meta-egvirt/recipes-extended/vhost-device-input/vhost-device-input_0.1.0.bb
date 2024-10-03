SUMMARY = "vhost-user input backend device"
DESCRIPTION = "A vhost-user backend that emulates a VirtIO input device"
HOMEPAGE = "https://github.com/rust-vmm/vhost-device"
LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
"

SRC_URI += "git://github.com/rust-vmm/vhost-device.git;protocol=http;branch=main"
SRCREV = "cec57c943354e938cde08ba984f8b1efb6cfa99a"

inherit cargo
inherit pkgconfig
inherit cargo-update-recipe-crates

S = "${WORKDIR}/git"

CARGO_BUILD_FLAGS = "--bin vhost-device-input -v --offline --target ${RUST_HOST_SYS} ${BUILD_MODE} --manifest-path=${CARGO_MANIFEST_PATH}"

include vhost-device-input-crates.inc
