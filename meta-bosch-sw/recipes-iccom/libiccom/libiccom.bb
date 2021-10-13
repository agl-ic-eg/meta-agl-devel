inherit cmake

LICENSE = "MPLv2"
SUMMARY = "Bosch libiccom"

SRC_URI = "git://github.com/agl-ic-eg/libiccom.git;protocol=https;branch=for-linux-v5.10.y"
SRCREV = "abd2e87573894c8593a63e4fe892e08ab13daa2b"
PV = "1.0.0"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=9741c346eef56131163e13b9db1241b3"
