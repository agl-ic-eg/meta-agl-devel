DESCRIPTION = "Whisper.cpp - Port of OpenAI's Whisper model in C++ for faster and smaller inference"
HOMEPAGE = "https://github.com/ggerganov/whisper.cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1539dadbedb60aa18519febfeab70632"

SRC_URI = "git://github.com/ggerganov/whisper.cpp.git;protocol=https;branch=master"

SRCREV = "81c999fe0a25c4ebbfef10ed8a1a96df9cfc10fd"

DEPENDS = "ffmpeg openblas"

S = "${WORKDIR}/git"

inherit cmake

do_configure:prepend() {
    sed -i 's/-march=native//g' ${S}/Makefile
    sed -i 's/-mtune=native//g' ${S}/Makefile
}


# Specify the model you want to download
WHISPER_MODEL ?= "base.en"

do_compile() {
    export CXXFLAGS="${CXXFLAGS} -I${STAGING_INCDIR}/openblas"
    export LDFLAGS="${LDFLAGS} -lopenblas"
    cd ${S}
    oe_runmake GGML_OPENBLAS=1
}

do_install() {
    # Install the main binary
    install -d ${D}${bindir}
    install -m 0755 ${S}/main ${D}${bindir}/whisper-cpp
}

FILES_${PN} += "${datadir}/whisper-cpp/models/* ${datadir}/* ${bindir}/* /usr/share "
RDEPENDS:${PN} += "openblas ffmpeg"
