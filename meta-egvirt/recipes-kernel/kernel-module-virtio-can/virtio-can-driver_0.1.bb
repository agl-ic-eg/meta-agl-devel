SUMMARY = "VIRTIO CAN device driver"
DESCRIPTION = "Virtio-can kernel driver"
LICENSE = "BSD-3-Clause & GPL-2.0-only"
LIC_FILES_CHKSUM = " \
    file://virtio_can.h;endline=4;md5=e9012c23aaa0bab2876b6051c3f836f3 \
    file://virtio_can.c;endline=5;md5=a8857c4c5e172b8a9ff9120ef8064632 \
"

inherit module

SRC_URI = " \
    file://Kbuild \
    file://virtio_can.c \
    file://virtio_can.h \
"

S = "${WORKDIR}"

MAKE_TARGETS = "-C ${STAGING_KERNEL_DIR} M=${WORKDIR}"
MODULES_INSTALL_TARGET = "-C ${STAGING_KERNEL_DIR} M=${WORKDIR} modules_install"
