inherit guest-kernel-module

do_install:aglcontainerguest:append() {
    # Install shared header files to ${includedir}
    install -m 644 ${B}/../include/mmngr_buf_private_cmn.h ${D}/${includedir}/
}
