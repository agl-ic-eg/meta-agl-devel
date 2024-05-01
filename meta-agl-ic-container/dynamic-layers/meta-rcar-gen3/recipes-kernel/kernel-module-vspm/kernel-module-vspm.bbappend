inherit guest-kernel-module

do_install:aglcontainerguest:append() {
    # Install shared header filesi
    install -m 644 ${B}/../include/vspm_cmn.h ${D}/${includedir}/
    install -m 644 ${B}/../include/vsp_drv.h ${D}/${includedir}/
    install -m 644 ${B}/../include/fdp_drv.h ${D}/${includedir}/
}
