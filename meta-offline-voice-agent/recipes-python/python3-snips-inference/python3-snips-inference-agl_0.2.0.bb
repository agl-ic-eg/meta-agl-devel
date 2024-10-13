SUMMARY = "Inference only module of Snips NLU library"
HOMEPAGE = "https://github.com/malik727/snips-inference-agl"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ecab3ce0771d366e0f8e4ca058eb48e7"

SRC_URI += " \
    git://gerrit.automotivelinux.org/gerrit/src/snips-inference-agl;protocol=https;branch=${AGL_BRANCH} \
    "

SRCREV = "6eee72109470897ce1e5446b2b6a0a5e579e942a"
S = "${WORKDIR}/git"

DEPENDS += " \
    python3-setuptools-native \
    python3-numpy-native \
    python3-numpy \
    python3-num2words \
    python3-pyaml \
    python3-six \
    python3-requests \
    python3-deprecation \
    python3-future \
    python3-scipy \
    python3-scikit-learn \
    python3-threadpoolctl \
    python3-sklearn-crfsuite \
    python3-tqdm \
    python3-packaging \
    python3-snips-nlu-utils \
    python3-snips-nlu-parsers \
    "

inherit setuptools3

RDEPENDS:${PN} += " \
    python3-numpy \
    python3-num2words \
    python3-pyaml \
    python3-six \
    python3-requests \
    python3-deprecation \
    python3-future \
    python3-scipy \
    python3-scikit-learn \
    python3-threadpoolctl \
    python3-sklearn-crfsuite \
    python3-tqdm \
    python3-packaging \
    python3-snips-nlu-utils \
    python3-snips-nlu-parsers \
    "
