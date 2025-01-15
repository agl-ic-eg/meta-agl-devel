set_hostname() {
    UHMI_HOSTNAME=${UHMI_HOSTNAME:-agl-host}
    echo "${UHMI_HOSTNAME}" > ${IMAGE_ROOTFS}/${sysconfdir}/hostname
}
ROOTFS_POSTPROCESS_COMMAND:append = " set_hostname;"
