SUMMARY = "Python wrapper of the snips-nlu-parsers Rust crate"
HOMEPAGE = "https://github.com/snipsco/snips-nlu-parsers"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://../LICENSE-APACHE;md5=1836efb2eb779966696f473ee8540542 \
    file://../LICENSE-MIT;md5=b377b220f43d747efdec40d69fcaa69d \
    "

SRC_URI += " \
    git://github.com/snipsco/snips-nlu-parsers;protocol=https;branch=master \
    file://0001-Update-dependencies-in-snips-nlu-parsers.patch \
    "

PV = "0.4.3+git${SRCPV}"
SRCREV = "8cfdae50437732fcf906af90626c8db196729d30"
S = "${WORKDIR}/git/python"

DEPENDS += "python3-future python3-setuptools-rust-native"

require ${BPN}-crates.inc

inherit cargo python_setuptools3_rust

do_compile[network] = "1"

RDEPENDS:${PN} += "python3-future"
