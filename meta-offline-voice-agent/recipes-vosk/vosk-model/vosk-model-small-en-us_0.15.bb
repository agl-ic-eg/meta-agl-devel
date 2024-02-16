SUMMARY = "Lightweight wideband model 'en-us'"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
# LIC_URL = "${COMMON_LICENSE_DIR}/Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=32;endline=32;md5=bb5cd71a409da2b13fa3bcb67a3573d1"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-small-en-us-0.15.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "30f26242c4eb449f948e42cb302dd7a686cb29a3423a8367f99ff41780942498"
SRC_URI[license.sha256sum] = "46220c6d381bf1c230699077e3a693b2474f4cc768a167f25ced5034ab96890b"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-small-en-us-0.15/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-small-en-us-0.15 "

RPROVIDES:${PN} += "vosk-model"
