REQUIRED_VERSION_libgpiod = "2.1"

FEATURE_PACKAGES_virtio-loopback = " \
    virtio-loopback-driver \
    virtio-can-driver \
    virtio-loopback-adapter \
    vhost-device-rng \
    vhost-device-gpio \
    vhost-device-sound \
    vhost-device-can \
    vhost-device-console \
    vhost-device-input \
    qemu-vhost-user-devices \
    "

EXTRA_IMAGE_FEATURES += "virtio-loopback"
