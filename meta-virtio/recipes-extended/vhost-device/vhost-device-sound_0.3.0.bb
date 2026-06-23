SUMMARY = "vhost sound backend device"
DESCRIPTION = "A vhost-user backend that emulates a VirtIO sound device"
HOMEPAGE = "https://github.com/rust-vmm/vhost-device"
LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = "\
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
"

SRC_URI += "crate://crates.io/vhost-device-sound/0.3.0"
SRC_URI[vhost-device-sound-0.3.0.sha256sum] = "d5066471ca6bc631f92b3cd265509d3134ff3483a8768e9f67676923878d7bce"

inherit pkgconfig cargo cargo-update-recipe-crates

PACKAGECONFIG = "${@bb.utils.filter('DISTRO_FEATURES', 'alsa pipewire', d)}"

PACKAGECONFIG[alsa] = "--features alsa-backend,,alsa-lib"
PACKAGECONFIG[pipewire] = "--features pw-backend,,pipewire"
PACKAGECONFIG[gstreamer] = "--features gst-backend,,gstreamer1.0"

TOOLCHAIN = "clang"

export BINDGEN_EXTRA_CLANG_ARGS = "--sysroot=${STAGING_DIR_TARGET}"

include vhost-device-sound-crates.inc
