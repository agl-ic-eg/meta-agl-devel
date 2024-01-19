REQUIRED_VERSION_libgpiod = "2.1"

FEATURE_PACKAGES_virtio-loopback = " \
     kernel-module-virtio-can \
     kernel-module-virtio-loopback \
     virtio-loopback-adapter \
     vhost-device-rng \
     vhost-device-gpio \
     vhost-device-can \
     vhost-device-console \
     "

EXTRA_IMAGE_FEATURES += "virtio-loopback"

