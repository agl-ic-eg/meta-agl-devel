FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"

SRC_URI += "file://0001-can-virtio-Add-virtio-CAN-driver.patch"

AGL_KCONFIG_FRAGMENTS += " \
    virtio_can.cfg \
    virtio_input.cfg \
    virtio_sound.cfg \
"
