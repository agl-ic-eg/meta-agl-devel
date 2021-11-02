SUMMARY = "LXC cluster demo guest image crosssdk"
LICENSE = "MIT"

require guest-image-cluster-demo.bb

IMAGE_FEATURES_append = " dev-pkgs"

inherit populate_sdk populate_sdk_qt5
