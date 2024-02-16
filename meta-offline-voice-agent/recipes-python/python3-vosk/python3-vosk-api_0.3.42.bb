SUMMARY = "Offline open source speech recognition API based on Kaldi and Vosk"
HOMEPAGE = "https://github.com/alphacep/vosk-api"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../COPYING;md5=d09bbd7a3746b6052fbd78b26a87396b"

SRC_URI = "git://github.com/alphacep/vosk-api;protocol=https;branch=master \
           file://0001-Change-library-search-path-to-usr-lib.patch \
           file://run-ptest \
           "

PV = "0.3.42+git${SRCPV}"
SRCREV = "b1b216d4c87d708935f1601287fe502aa11ee4a9"

S = "${WORKDIR}/git/python"

inherit setuptools3 ptest

DEPENDS += " \
    python3-srt-native \
    python3-tqdm-native \
    python3-requests-native \
    python3-pip-native \
    python3-charset-normalizer-native \
"

RDEPENDS:${PN} += " \
    python3-cffi \
    python3-compression \
    python3-core \
    python3-datetime \
    python3-json \
    python3-logging \
    python3-misc \
    python3-multiprocessing \
    python3-netclient \
    python3-requests \
    python3-tqdm \
    python3-srt \
    python3-charset-normalizer \
    python3-sounddevice \
    vosk-model \
"

do_install_ptest () {
    install -d ${D}${PTEST_PATH}/tests/
    cp ${S}/example/test_simple.py ${D}${PTEST_PATH}/tests/
    cp ${S}/example/test.wav ${D}${PTEST_PATH}/tests/
}
