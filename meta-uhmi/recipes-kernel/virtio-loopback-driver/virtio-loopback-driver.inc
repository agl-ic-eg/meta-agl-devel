PV = "0.0+git${SRCPV}"
SRCREV = "52da5c41c45b7bbc8f042d40d3c2daa3c779e06c"
BRANCH ?= "main"
SRC_URI = " \
   git://github.com/unified-hmi/virtio-loopback-driver.git;protocol=https;branch=${BRANCH} \
"
