SUMMARY = "Lightweight wideband model 'es'"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=66;endline=66;md5=dafff175a878d17393d30812eac57e87"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-es-0.22.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "97d2b9f062a4d363ad1d71f011cc77e073773d749f1e5cd21a172c8ca28b0a56"
SRC_URI[license.sha256sum] = "3fbe0ee1fa914cb4b1cea2f2d7201a155884727cd0afbb51a267b66a73cb3444"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-es-0.22/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-es-0.22 "

# RPROVIDES:${PN} += "vosk-model"
