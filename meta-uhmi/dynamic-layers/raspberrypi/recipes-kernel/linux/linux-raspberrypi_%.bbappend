require recipes-kernel/linux/linux-rvgpu.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/linux-common/:"

SRC_URI:append = " \
    file://enable-virtio.cfg \
"

KERNEL_MODULE_AUTOLOAD:append = " virtio-gpu"
