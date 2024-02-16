SUMMARY = "Lightweight wideband model 'en-us' 0.22"
HOMEPAGE = "https://alphacephei.com/vosk/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/models.md;beginline=32;endline=32;md5=bb5cd71a409da2b13fa3bcb67a3573d1"

SRC_URI = " \
           https://alphacephei.com/vosk/models/vosk-model-en-us-0.22.zip;name=model \
           https://raw.githubusercontent.com/alphacep/vosk-space/master/models.md;name=license \
          "
# License listed on https://alphacephei.com/vosk/models

SRC_URI[model.sha256sum] = "47f9a81ebb039dbb0bd319175c36ac393c0893b796c2b6303e64cf58c27b69f6"
SRC_URI[license.sha256sum] = "46220c6d381bf1c230699077e3a693b2474f4cc768a167f25ced5034ab96890b"

do_install() {
    install -d ${D}/usr/share/vosk
    cp -R ${WORKDIR}/vosk-model-en-us-0.22/ ${D}/usr/share/vosk/
}

FILES:${PN} += " /usr/share/vosk  /usr/share/vosk/vosk-model-en-us-0.22 "

RPROVIDES:${PN} += "vosk-model"
