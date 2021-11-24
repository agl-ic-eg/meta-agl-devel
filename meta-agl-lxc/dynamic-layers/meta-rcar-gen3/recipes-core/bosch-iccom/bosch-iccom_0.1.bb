SUMMARY = "Bosch iccom drivers"
inherit module

LICENSE = "GPLv2"

SRC_URI = "\
    git://github.com/agl-ic-eg/linux-iccom.git;protocol=https;branch=iccom_across_namespace;name=iccom \
    git://github.com/agl-ic-eg/linux-full-duplex-interface.git;destsuffix=git/linux-full-duplex-interface;branch=main;protocol=https;name=header \
    file://Kbuild \
    file://Makefile \
    "

LIC_FILES_CHKSUM = "\
    file://LICENSE.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    file://linux-full-duplex-interface/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
    "

SRCREV_iccom = "6fe0e381d30a21100547dd7989430d5a056159e5"
SRCREV_header = "29cab9e103367d86ed728696eebd06502ae55530"

S = "${WORKDIR}/git"

do_compile_prepend() {
    cp -r src iccom
    cp -r linux-full-duplex-interface/headers full_duplex_interface
    cp -r ${WORKDIR}/Kbuild .
    cp -r ${WORKDIR}/Makefile .
}
