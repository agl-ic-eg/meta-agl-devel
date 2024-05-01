inherit guest-kernel-module

do_install:aglcontainerguest:append() {
    # Install shared header file
    install -m 644 ${B}/../include/vspm_if.h ${D}/${includedir}/
}
