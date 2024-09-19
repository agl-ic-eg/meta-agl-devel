PACKAGES:prepend = "\
    ${PN}-firmware \
"

FILES:${PN}-firmware = " \
    ${nonarch_base_libdir}/firmware/* \
"
