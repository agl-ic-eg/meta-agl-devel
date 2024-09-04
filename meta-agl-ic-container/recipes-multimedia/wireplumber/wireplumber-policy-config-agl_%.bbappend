do_install:append() {
    config_dir="${D}${sysconfdir}/wireplumber/"
    systemd_dir="${D}${sysconfdir}/systemd/system/sockets.target.wants"

    # enable additional systemd services
    install -d ${systemd_dir}
    ln -s ${systemd_system_unitdir}/wireplumber@.service ${systemd_dir}/wireplumber@policy.service
}
