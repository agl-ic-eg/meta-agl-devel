DESCRIPTION = "Whisper AI - General-purpose speech recognition model"
HOMEPAGE = "https://github.com/openai/whisper"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b1b8ea5cbbe899304ac6566613a3b74e"

SRC_URI = "git://github.com/openai/whisper.git;protocol=https;branch=main"
SRCREV = "ba3f3cd54b0e5b8ce1ab3de13e32122d0d5f98ab"

S = "${WORKDIR}/git"


DEPENDS += "python3-wheel-native ffmpeg python3-numpy python3-pytorch python3-tqdm python3-tiktoken python3-more-itertools"

inherit setuptools3 python3native

RDEPENDS:${PN} += "ffmpeg python3-numba python3-regex python3-pytorch python3-tqdm python3-tiktoken"


