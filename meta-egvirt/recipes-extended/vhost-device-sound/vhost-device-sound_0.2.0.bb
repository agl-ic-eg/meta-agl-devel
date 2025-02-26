SUMMARY = "vhost-device sound"
DESCRIPTION = "A virtio-sound device using the vhost-user protocol."
HOMEPAGE = "https://github.com/rust-vmm/vhost-device"
LICENSE = "Apache-2.0 | BSD-3-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE-APACHE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://LICENSE-BSD-3-Clause;md5=2489db1359f496fff34bd393df63947e \
" 
SRC_URI += "crate://crates.io/vhost-device-sound/0.2.0"
SRC_URI[vhost-device-sound-0.2.0.sha256sum] = "36c278126b6a9c144b2c0f5a3d11cbf2e2e1fa672c61834a98fe049540810acd"

DEPENDS = "alsa-lib pipewire clang-native"

# Add alsa-utils, pipewire, wireplumber for vhost-device-sound testing
RDEPENDS:${PN}:append = " \
    alsa-utils \
    packagegroup-pipewire \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'packagegroup-pipewire-tools alsa-utils', '', d)} \
    wireplumber-config-agl \
    wireplumber-policy-config-agl \
    packagegroup-pipewire \
    pipewire \
    wireplumber \
"

inherit cargo
inherit cargo-update-recipe-crates
inherit pkgconfig

export BINDGEN_EXTRA_CLANG_ARGS= "--sysroot=${STAGING_DIR_TARGET}"

include vhost-device-sound-crates.inc
