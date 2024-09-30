SUMMARY = "AGL Instrument Cluster Demo Qt Packages"
DESCRIPTION = "This pacage group including Qt packages for AGL Instrument Cluster Demo."
HOMEPAGE = "https://confluence.automotivelinux.org/display/IC"

LICENSE = "Apache-2.0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = "\
    packagegroup-agl-momi-ivi-qt \
"
RDEPENDS:${PN} += "\
    qtbase qtbase-plugins qtbase-qmlplugins \
    qtdeclarative qtdeclarative-qmlplugins \
    qt3d qt3d-plugins qt3d-qmlplugins\
    qtquick3d qtquick3d \
    qtwayland qtwayland-plugins qtwayland-qmlplugins \
    qtlocation qtlocation-plugins qtlocation-qmlplugins \
    qtpositioning qtpositioning-plugins qtpositioning-qmlplugins \
    qtsvg qtsvg-plugins qtsvg-qmlplugins \
    qtmultimedia qtmultimedia-plugins qtmultimedia-qmlplugins \
    \
    gstreamer1.0 \
    gstreamer1.0-plugins-base-meta \
    gstreamer1.0-plugins-good-meta \
    gstreamer1.0-plugins-bad-meta \
    gstreamer1.0-plugins-ugly-meta \
    gstreamer1.0-libav \
    \
    pulseaudio-server pulseaudio-pa-info pulseaudio-misc \
    pulseaudio-module-alsa-sink pulseaudio-module-alsa-source \
    pulseaudio-module-native-protocol-unix pulseaudio-module-loopback \
    pulseaudio-module-null-sink pulseaudio-module-null-source \
"
