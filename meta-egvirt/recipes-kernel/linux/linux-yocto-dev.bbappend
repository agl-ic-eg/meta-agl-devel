FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto:"
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-yocto-5.18:"

# virtio video
SRC_URI:append = " \
    file://0001-drivers-media-Add-config-option-for-virtio-video.patch \
    file://virtio_video.cfg \
"

# virtio BT
SRC_URI:append = " \
    file://virtio_bt.cfg \
    file://0002-Bluetooth-virtio_bt-fix-device-removal.patch \
"

# virtio loopback
SRC_URI += " \
    file://virtio_loopback.cfg \
"

# virtio can
SRC_URI += " \
    file://virtio_can.cfg \
"

# virtio input
SRC_URI += " \
    file://virtio_input.cfg \
"

# virtio sound
SRC_URI += " \
    file://virtio_sound.cfg \
"
