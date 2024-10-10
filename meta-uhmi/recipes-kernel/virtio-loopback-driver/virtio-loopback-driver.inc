PV = "0.0+git${SRCPV}"
SRCREV = "cf40e4d295c75567cb7d1d03f9fe291e9593e882"
BRANCH ?= "main"
SRC_URI = " \
   git://github.com/unified-hmi/virtio-loopback-driver.git;protocol=https;branch=${BRANCH} \
"
