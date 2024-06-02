SUMMARY     = "Momiyama navigation example based on mapbox."
DESCRIPTION = "The mominavi is a navigation example based on mapbox. It's based on aglqtnavigation. \
               The mominavi is not require agl-appfw."
LICENSE = "GPL-3.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = " \
    qtbase \
    qtquickcontrols2 \
    qtlocation \
    qtgraphicaleffects \
    qtsvg \
    qtwebsockets \
    "

PV = "1.0.0"

SRC_URI = "git://git.automotivelinux.org/apps/mominavi;protocol=https;branch=${AGL_BRANCH} \
           file://mominavi.service \
           file://mominavi \
          "
SRCREV = "10c3f7996d4bbe04d237baff2ef16573bc9eb9ed"

S = "${WORKDIR}/git"

inherit qmake5 systemd

MOMIMAP_MAPBOX_ACCESS_TOKEN ??= "YOU_NEED_TO_SET_IT_IN_LOCAL_CONF"
MOMIMAP_MAPBOX_STYLE ??= "mapbox://styles/wata2ki/ckoy853ue11a117nss0uxut76"
MOMIMAP_INITIAL_LATITUDE ??= "36.129"
MOMIMAP_INITIAL_LONGITUDE ??= "-115.1533"
QT_INSTALL_PREFIX = "/usr"

do_configure:prepend() {
	if [ "${MOMIMAP_MAPBOX_ACCESS_TOKEN}" = "YOU_NEED_TO_SET_IT_IN_LOCAL_CONF" ]; then
		bbwarn "WARNING: You should set MapBox development key to MOMIMAP_MAPBOX_ACCESS_TOKEN variable in local.conf."
	fi
}
do_install:append() {
    install -d ${D}/${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/mominavi.service ${D}/${systemd_unitdir}/system

    install -m 0755 -d ${D}${sysconfdir}/default/
    install -m 0755 ${WORKDIR}/mominavi ${D}${sysconfdir}/default/

    echo 'MOMIMAP_MAPBOX_ACCESS_TOKEN=${MOMIMAP_MAPBOX_ACCESS_TOKEN}' >> ${D}${sysconfdir}/default/mominavi
    echo 'MOMIMAP_MAPBOX_STYLE=${MOMIMAP_MAPBOX_STYLE}' >> ${D}${sysconfdir}/default/mominavi
    echo 'MOMIMAP_INITIAL_LATITUDE=${MOMIMAP_INITIAL_LATITUDE}' >> ${D}${sysconfdir}/default/mominavi
    echo 'MOMIMAP_INITIAL_LONGITUDE=${MOMIMAP_INITIAL_LONGITUDE}' >> ${D}${sysconfdir}/default/mominavi
}

FILES:${PN} += " \
    ${systemd_unitdir} \
    ${sysconfdir}/*/* \
    "
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "mominavi.service"

RDEPENDS:${PN} = " \
    qtsvg qtsvg-plugins qtsvg-qmlplugins \
    qtwebsockets qtwebsockets-plugins qtwebsockets-qmlplugins \
    qtlocation qtlocation-plugins qtlocation-qmlplugins \
    "
