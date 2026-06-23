AGL_FEATURES += "agl-virtio-loopback"

# Feature definition, layer users can add virtio-loopback to
# IMAGE_FEATURES to install the packages.
FEATURE_PACKAGES_virtio-loopback = " \
    virtio-loopback-driver \
    virtio-loopback-adapter \
    "
