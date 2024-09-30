SUMMARY = "Demo video data for AGL demo"
DESCRIPTION = "AGL demo video data aim to show demo information by video."
HOMEPAGE = "https://github.com/agl-ic-eg/ic-demo-video"
SECTION = "Multimedia"
LICENSE = "CC-BY-NC-ND-4.0"
LIC_FILES_CHKSUM = "file://CC-BY-NC-ND-4.0;md5=afe664d64109562c3fa9c309bd7f73bc"

SRC_URI = " \
    git://github.com/agl-ic-eg/ic-demo-video.git;branch=master;protocol=https \
"
SRCREV = "a58624c3e721330d11d389a68c58bfe8f2e05a41"

inherit allarch

S = "${WORKDIR}/git"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_compile[noexec] = "1"
do_install() {
    install -d ${D}/home/root/Music
    cp ${S}/ic-container-ces2024.mp4 ${D}/home/root/Music/
}

PACKAGES = "\
    ${PN} \
"

FILES:${PN} = " \
    /home/root/Music/* \
"
