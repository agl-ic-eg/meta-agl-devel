DESCRIPTION = "Enable linux virtio-loopback driver"

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-common/:"

SRC_URI:append = " \
    file://virtio-gpu.cfg \
    file://uinput.cfg \
"
