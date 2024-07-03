SUMMARY = "Ultra fast asyncio event loop."
HOMEPAGE = "https://github.com/MagicStack/uvloop"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE-APACHE;md5=bb92739ddad0a2811957bd98bdb90474"

PYPI_PACKAGE = "uvloop"

SRC_URI[md5sum] = "a7679334af9a39dc89f9298088d8d235"
SRC_URI[sha256sum] = "d5d1135beffe9cd95d0350f19e2716bc38be47d5df296d7cc46e3b7557c0d1ff"

DEPENDS += "python3-cython-native"

inherit pypi setuptools3

do_compile:prepend() {
    export LIBUV_CONFIGURE_HOST=${HOST_SYS}
}

do_install:prepend() {
    export LIBUV_CONFIGURE_HOST=${HOST_SYS}
}
