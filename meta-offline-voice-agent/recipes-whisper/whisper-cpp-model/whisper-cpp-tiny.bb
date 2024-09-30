SUMMARY = "Whisper-cpp base model"
HOMEPAGE = "https://github.com/ggerganov/whisper.cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/LICENSE;md5=7a3cb84505132167069a95fa683a011c"

SRC_URI = "https://huggingface.co/ggerganov/whisper.cpp/resolve/main/ggml-tiny.en.bin"
SRC_URI[sha256sum] = "921e4cf8686fdd993dcd081a5da5b6c365bfde1162e72b08d75ac75289920b1f"

do_install() {
    # Install the models
    install -d ${D}${datadir}/whisper-cpp/models
    install -m 0644 ${WORKDIR}/ggml-tiny.en.bin ${D}${datadir}/whisper-cpp/models/tiny.en.bin
}

FILES:${PN} += " /usr /usr/share /usr/share/whisper-cpp/* "
