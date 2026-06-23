SUMMARY = "Virtio-loopback-adapter application"
DESCRIPTION = "Adapter bridge for virtio-loopback"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/virtio/virtio-loopback-adapter;protocol=http;branch=${AGL_BRANCH}"
SRCREV = "2a557edd3a34e0dba5b9f8fbf9db1b05d7c0b4b4"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	cd ${S}
	make
}

do_install() {
	mkdir ${D}/usr/bin/ -p
	install -m 0755 ${S}/adapter ${D}/usr/bin/virtio-loopback-adapter
}

DEPENDS = ""
FILES:${PN} += "/usr/bin/virtio-loopback-adapter"
