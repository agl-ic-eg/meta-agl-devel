SUMMARY = "vhost-device sound"
DESCRIPTION = "A virtio-sound device using the vhost-user protocol."
HOMEPAGE = "https://github.com/rust-vmm/vhost-device"
LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
" 
SRC_URI += "crate://crates.io/vhost-device-sound/0.1.0"
SRC_URI[vhost-device-sound-0.1.0.sha256sum] = "8d89731cfe36eb3b57b6eec55b7ed0f79e34b34274fd4f672035b548417c7f09"

DEPENDS = "alsa-lib pipewire clang-native"

inherit cargo
inherit pkgconfig
include vhost-device-sound-crates.inc

