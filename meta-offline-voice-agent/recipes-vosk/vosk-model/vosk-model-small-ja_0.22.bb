SUMMARY = "Lightweight wideband model for Japanese"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=105;endline=105;md5=d6585d5b106f271e40f0f6befafc9da9"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-ja-0.22.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "efa092d280153a77615e9e0c7d7283e93e600de3d19d3bec686c57ef19d52eac"
SRC_URI[license.sha256sum] = "3fbe0ee1fa914cb4b1cea2f2d7201a155884727cd0afbb51a267b66a73cb3444"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-ja-0.22/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-ja-0.22 "

# RPROVIDES:${PN} += "vosk-model"
