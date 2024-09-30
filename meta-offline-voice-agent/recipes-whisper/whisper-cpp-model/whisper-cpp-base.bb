SUMMARY = "Whisper-cpp base model"
HOMEPAGE = "https://github.com/ggerganov/whisper.cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/LICENSE;md5=7a3cb84505132167069a95fa683a011c"

SRC_URI = "https://huggingface.co/ggerganov/whisper.cpp/resolve/main/ggml-base.bin"
SRC_URI[sha256sum] = "60ed5bc3dd14eea856493d334349b405782ddcaf0028d4b5df4088345fba2efe"

do_install() {
    # Install the models
    install -d ${D}${datadir}/whisper-cpp/models
    install -m 0644 ${WORKDIR}/ggml-base.bin ${D}${datadir}/whisper-cpp/models/base.bin
}

FILES:${PN} += " /usr /usr/share /usr/share/whisper-cpp/* "
