SUMMARY = "vhost console backend device"
DESCRIPTION = "A vhost-user backend that emulates a VirtIO console device"
HOMEPAGE = "https://github.com/rust-vmm/vhost-device"
LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = "\
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
"

SRC_URI += "crate://crates.io/vhost-device-console/0.1.0"
SRC_URI[vhost-device-console-0.1.0.sha256sum] = "2d11f467a1030d035980f56daa4bed32fb26f933a6bca84bed04c3146201dcc1"

inherit cargo cargo-update-recipe-crates

include vhost-device-console-crates.inc
