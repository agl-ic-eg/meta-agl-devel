SUMMARY = "Virtio-loopback driver"
DESCRIPTION = "Virtio-Loopback kernel driver"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://README.md;md5=a504d51f03528972061035344480790b"

inherit module

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/virtio/virtio-loopback-driver.git;protocol=http;branch=${AGL_BRANCH}"

SRCREV = "8948c9808eded80772de98cd4e8dd0cc71fdbe17"

S = "${WORKDIR}/git"

MAKE_TARGETS = "-C ${STAGING_KERNEL_DIR} M=${WORKDIR}/git"
MODULES_INSTALL_TARGET = "-C ${STAGING_KERNEL_DIR} M=${WORKDIR}/git modules_install"

