SUMMARY = "Virtio-loopback driver"
DESCRIPTION = "Virtio-Loopback kernel driver"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

inherit module

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/virtio/virtio-loopback-driver.git;protocol=http;branch=${AGL_BRANCH}"

SRCREV = "6dbaa892941dcb7cde05094f29425bc0533a579c"

S = "${WORKDIR}/git"
UNPACKDIR = "${S}"

MAKE_TARGETS = "-C ${STAGING_KERNEL_DIR} M=${S}"
MODULES_INSTALL_TARGET = "-C ${STAGING_KERNEL_DIR} M=${S} modules_install"
