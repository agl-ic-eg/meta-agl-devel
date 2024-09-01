SUMMARY = "DRM Lease LXC test guest image"
LICENSE = "MIT"

require guest-image-minimal.bb

IMAGE_INSTALL += " \
    systemd-netif-config \
    weston \
    weston-init-guest \
    weston-ini-conf-drm-lease-test-cluster \
"

IMAGE_INSTALL:append:rpi = " mesa-megadriver"
