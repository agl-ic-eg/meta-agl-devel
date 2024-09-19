do_install:append() {
    # Disable network IF renaming
    ln -s /dev/null ${D}${sysconfdir}/systemd/network/99-default.link
}

FILES:udev:append = " \
               ${sysconfdir}/systemd/network/99-default.link \
              "
