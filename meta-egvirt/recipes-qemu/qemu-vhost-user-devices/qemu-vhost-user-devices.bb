BBCLASSEXTEND = ""
SUMMARY = "Fast open source processor emulator"
DESCRIPTION = "Recipe for vhost-user-rng -blk and -input"
HOMEPAGE = "http://qemu.org"
LICENSE = "GPL-2.0-only & LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=8c5efda6cf1e1b03dcfd0e6c0d271c7f"

inherit pkgconfig

DEPENDS = "glib-2.0 zlib pixman bison-native ninja-native"
SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/virtio/qemu;protocol=https;branch=master"
SRCREV = "af1a266670d040d2f4083ff309d732d648afba2a"
S = "${WORKDIR}/git"

UPSTREAM_CHECK_REGEX = "qemu-(?P<pver>\d+(\.\d+)+)\.tar"

# Per https://lists.nongnu.org/archive/html/qemu-devel/2020-09/msg03873.html
# upstream states qemu doesn't work without optimization
DEBUG_BUILD = "0"

export LIBTOOL="${HOST_SYS}-libtool"

B = "${WORKDIR}/build"

do_configure[cleandirs] += "${B}"
do_configure() {
    unset bindir libdir mandir datadir includedir libexecdir
    # Pass empty --cross-prefix to suspend errors due to build configurations
    # on different architectures. CC/CXX and other build variables are set by Yocto
    # itself correct.
    ${S}/configure --disable-werror --cross-prefix=
}

do_install() {
        mkdir ${D}/usr/bin/ -p
        install -m 0755 ${B}/contrib/vhost-user-blk/vhost-user-blk ${D}/usr/bin/
}

FILES:${PN} += "/usr/bin"
RDEPENDS:${PN}:class-target += "bash"

DISABLE_STATIC = ""

