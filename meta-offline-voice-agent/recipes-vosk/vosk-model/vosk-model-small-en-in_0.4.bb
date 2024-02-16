SUMMARY = "Lightweight Indian English model for mobile applications"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=44;endline=44;md5=a9131c3e00accfcddf94bff9e8b43018"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-en-in-0.4.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "20663dcac4d5cb783a579c54d98339344a688e4ec6e1b4a4b059fd1235454cc7"
SRC_URI[license.sha256sum] = "3fbe0ee1fa914cb4b1cea2f2d7201a155884727cd0afbb51a267b66a73cb3444"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-en-in-0.4/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-en-in-0.4 "

# RPROVIDES:${PN} += "vosk-model"
