SUMMARY = "Unified HMI Package Groups"
LICENSE = "Apache-2.0"

inherit packagegroup

RDEPENDS:${PN} += " \
    remote-virtio-gpu \
    virtio-loopback-driver \
    mesa-virtio \
    rvgpu-wlproxy \
"
