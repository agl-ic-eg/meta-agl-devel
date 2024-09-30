SUMMARY = "A Python MPD client library"
HOMEPAGE = "https://github.com/Mic92/python-mpd2"
AUTHOR = "Joerg Thalheim <joerg@thalheim.io>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRC_URI = "https://files.pythonhosted.org/packages/53/be/e77206eb35eb37ccd3506fba237e1431431d04c482707730ce2a6802e95c/python-mpd2-3.1.1.tar.gz"
SRC_URI[md5sum] = "b218d6f233c23da0bc82c372308bbf8d"
SRC_URI[sha256sum] = "4baec3584cc43ed9948d5559079fafc2679b06b2ade273e909b3582654b2b3f5"

S = "${WORKDIR}/python-mpd2-3.1.1"

RDEPENDS_${PN} = ""

inherit setuptools3
