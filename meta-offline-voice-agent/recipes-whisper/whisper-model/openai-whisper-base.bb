SUMMARY = "OpenAI Whisper base model"
HOMEPAGE = "https://github.com/openai/whisper"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/LICENSE;md5=b1b8ea5cbbe899304ac6566613a3b74e"

SRC_URI = "https://openaipublic.azureedge.net/main/whisper/models/ed3a0b6b1c0edf879ad9b11b1af5a0e6ab5db9205f891f668f8b0e6c6326e34e/base.pt"
SRC_URI[sha256sum] = "ed3a0b6b1c0edf879ad9b11b1af5a0e6ab5db9205f891f668f8b0e6c6326e34e"

do_install(){
    install -d ${D}/usr/share/whisper
    cp -R ${WORKDIR}/base.pt ${D}/usr/share/whisper/
}

FILES:${PN} = " /usr/share/whisper /usr/share/whisper/base.pt "
