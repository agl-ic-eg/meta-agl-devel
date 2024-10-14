SUMMARY = "Voice Assistant"
DESCRIPTION = "Offline voice assistant app designed for Automotive Grade Linux (AGL)."
HOMEPAGE = "https://github.com/malik727/agl-flutter-voiceassistant"
BUGTRACKER = "https://github.com/malik727/agl-flutter-voiceassistant/issues"
SECTION = "graphics"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4202492ed9afcab3aaecc4a9ec32adb2"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/apps/flutter-speechrecognition-demo;protocol=https;branch=${AGL_BRANCH} \
           file://agl-app-flutter@flutter_voiceassistant.service \
           "

SRCREV = "53d2b7ba70f9d6cb7033981853fb37a3028b0b5a"
S = "${WORKDIR}/git"

inherit agl-app flutter-app 

# flutter-app
#############
PUBSPEC_APPNAME = "flutter_voiceassistant"
FLUTTER_BUILD_ARGS = "bundle -v"

# agl-app
#########
AGL_APP_TEMPLATE = "agl-app-flutter"
AGL_APP_ID = "flutter_voiceassistant"
AGL_APP_NAME = "Voice Assistant"

do_install:append() {
    install -D -m 0644 ${WORKDIR}/agl-app-flutter@flutter_voiceassistant.service ${D}${systemd_system_unitdir}/agl-app-flutter@flutter_voiceassistant.service
}

do_compile[network] = "1"

FILES:${PN} += "${datadir} "
