SUMMARY = "Virtio-loopback-adapter application"
DESCRIPTION = "Adapter bridge for virtio-loopback"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=570a9b3749dd0463a1778803b12a6dce"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/virtio/virtio-loopback-adapter;protocol=http;branch=${AGL_BRANCH}"
SRCREV = "4bf3f656b9d58b4b99f6ddbe5ca27f4a39876ed0"

S = "${WORKDIR}/git"
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
