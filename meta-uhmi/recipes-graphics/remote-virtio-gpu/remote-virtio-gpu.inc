PV = "0.0+git${SRCPV}"
SRCREV = "bce1f77f189dacf3e57d2f4edc37be4ff63b9e4b"
BRANCH ?= "main"
SRC_URI = " \
    git://github.com/unified-hmi/remote-virtio-gpu.git;protocol=https;branch=${BRANCH} \
"
