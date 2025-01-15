FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

WESTON_INIT_IVI_SHELL ?= "weston_ivi-shell.ini"

SRC_URI:append = " \
	  file://${WESTON_INIT_IVI_SHELL} \
"

do_install:append () {
    install -d ${D}/${sysconfdir}/xdg/weston
    install -m 644 ${WORKDIR}/${WESTON_INIT_IVI_SHELL} ${D}/${sysconfdir}/xdg/weston/
	  ln -nfs ${sysconfdir}/xdg/weston/${WESTON_INIT_IVI_SHELL} ${D}/${sysconfdir}/xdg/weston/weston.ini
}
