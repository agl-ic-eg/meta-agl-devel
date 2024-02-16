SUMMARY = "Lightweight wideband model 'fr'"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=56;endline=56;md5=b49bbab5d6832157cad0e0dbb924a5b2"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-fr-0.22.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "cabf6180e177eb9b3a9a9d43a437bd5e549f3a7d09525e5d69a3fed787be12ad"
SRC_URI[license.sha256sum] = "3fbe0ee1fa914cb4b1cea2f2d7201a155884727cd0afbb51a267b66a73cb3444"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-fr-0.22/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-fr-0.22 "

# RPROVIDES:${PN} += "vosk-model"
