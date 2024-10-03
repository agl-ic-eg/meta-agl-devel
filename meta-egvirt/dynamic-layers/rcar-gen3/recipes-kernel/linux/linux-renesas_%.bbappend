
FILESEXTRAPATHS:prepend := "${THISDIR}/linux-common/:"

SRC_URI:append = " \
    file://enable-virtio.cfg \
	file://virtio_can.cfg \
	file://virtio_input.cfg \
	file://virtio_loopback.cfg \
	file://virtio_sound.cfg \
"

# virtio sound
SRC_URI += " \
    file://0001-ALSA-virtio-add-virtio-sound-driver.patch \
    file://0002-ALSA-virtio-handling-control-messages.patch \
    file://0003-ALSA-virtio-build-PCM-devices-and-substream-hardware.patch \
    file://0004-ALSA-virtio-handling-control-and-I-O-messages-for-th.patch \
    file://0005-ALSA-virtio-PCM-substream-operators.patch \
    file://0006-ALSA-virtio-introduce-jack-support.patch \
    file://0007-ALSA-virtio-introduce-PCM-channel-map-support.patch \
    file://0008-ALSA-virtio-introduce-device-suspend-resume-support.patch \
    file://0009-Add-virtio-sound-id-in-virtio_ids.h-file.patch \
    file://virtio_sound.cfg \
"

