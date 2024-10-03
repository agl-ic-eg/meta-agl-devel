
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-common/:"

SRC_URI:append = " \
    file://enable-virtio.cfg \
	file://virtio_can.cfg \
	file://virtio_input.cfg \
	file://virtio_loopback.cfg \
	file://virtio_sound.cfg \
"
