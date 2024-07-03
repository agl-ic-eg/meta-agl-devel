SUMMARY = "OpenAI Whisper tiny model"
HOMEPAGE = "https://github.com/openai/whisper"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${THISDIR}/../../licenses/LICENSE;md5=b1b8ea5cbbe899304ac6566613a3b74e"

SRC_URI = "https://openaipublic.azureedge.net/main/whisper/models/65147644a518d12f04e32d6f3b26facc3f8dd46e5390956a9424a650c0ce22b9/tiny.pt"
SRC_URI[sha256sum] = "65147644a518d12f04e32d6f3b26facc3f8dd46e5390956a9424a650c0ce22b9"

do_install(){
    install -d ${D}/usr/share/whisper
    cp -R ${WORKDIR}/tiny.pt ${D}/usr/share/whisper/
}

FILES:${PN} = " /usr/share/whisper /usr/share/whisper/tiny.pt "

