SUMMARY = "Dataset and a pre-trained model for the Automotive Grade Linux's Snips NLU intent engine."
HOMEPAGE = "https://github.com/malik727/snips-model-agl"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ecab3ce0771d366e0f8e4ca058eb48e7"

SRC_URI = " \
    git://gerrit.automotivelinux.org/gerrit/src/snips-model-agl;protocol=https;branch=${AGL_BRANCH} \
"

SRCREV = "2758be6cfabe4a8e1bb784dd5fb7e87a6a500c7c"
S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/usr/share/nlu/snips
    cp -R ${WORKDIR}/git/* ${D}/usr/share/nlu/snips/
}

FILES:${PN} += " /usr/share/nlu/snips"
