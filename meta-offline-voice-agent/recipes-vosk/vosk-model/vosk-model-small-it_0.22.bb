SUMMARY = "Lightweight wideband model 'en-us'"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=77;endline=77;md5=5738be531d1fac9b1088702e7d1f9499"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-it-0.22.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "9ec65e75861d1c6c2e457cccd932705340dcdf233f5b239f00733b4de0bf3267"
SRC_URI[license.sha256sum] = "3fbe0ee1fa914cb4b1cea2f2d7201a155884727cd0afbb51a267b66a73cb3444"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-it-0.22/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-it-0.22 "

# RPROVIDES:${PN} += "vosk-model"
