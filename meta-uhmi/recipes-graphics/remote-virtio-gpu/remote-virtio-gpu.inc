PV = "0.0+git${SRCPV}"
SRCREV = "f9e5d64f61a646849a835232998f6cf079176fb3"
BRANCH ?= "agl"
SRC_URI = " \
    git://github.com/unified-hmi/remote-virtio-gpu.git;protocol=https;branch=${BRANCH} \
"
