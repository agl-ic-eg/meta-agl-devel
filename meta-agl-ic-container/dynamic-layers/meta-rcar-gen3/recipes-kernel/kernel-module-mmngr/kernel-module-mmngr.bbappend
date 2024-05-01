inherit guest-kernel-module

do_install:aglcontainerguest:append() {
    # Install shared header file to ${includedir}
    install -m 644 ${B}/../include/mmngr_public_cmn.h ${D}/${includedir}/
    install -m 644 ${B}/../include/mmngr_private_cmn.h ${D}/${includedir}/
    install -m 644 ${B}/../include/mmngr_validate.h ${D}/${includedir}/
}
