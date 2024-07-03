SUMMARY = "tiktoken is a fast BPE tokeniser for use with OpenAI's models"
HOMEPAGE = "None"
AUTHOR = "Shantanu Jain <shantanu@openai.com>"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3794f392e777e4fdf460d9bad404803"

SRC_URI = "https://files.pythonhosted.org/packages/c4/4a/abaec53e93e3ef37224a4dd9e2fc6bb871e7a538c2b6b9d2a6397271daf4/tiktoken-0.7.0.tar.gz"
SRC_URI[md5sum] = "62b4a9f1953826e61f8e09eb4a51965a"
SRC_URI[sha256sum] = "1077266e949c24e0291f6c350433c6f0971365ece2b173a23bc3b9f9defef6b6"

require ${BPN}-crates.inc

S = "${WORKDIR}/tiktoken-0.7.0"

DEPENDS += "python3-regex"

RDEPENDS_${PN} = "python3-regex python3-requests python3-urllib3"

inherit cargo setuptools3 python_setuptools3_rust cargo-update-recipe-crates


