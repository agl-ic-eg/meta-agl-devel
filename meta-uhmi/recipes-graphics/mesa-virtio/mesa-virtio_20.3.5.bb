SUMMARY = "Mesa library"
SECTION = "graphics"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://docs/license.rst;md5=9aa1bc48c9826ad9fdb16661f6930496"


DEPENDS = "expat makedepend-native flex-native bison-native libxml2-native zlib chrpath-replacement-native python3-mako-native gettext-native"
DEPENDS:append = " libdrm wayland wayland-native wayland-protocols python3-native"

SRC_URI = "https://mesa.freedesktop.org/archive/mesa-${PV}.tar.xz"

SRC_URI[md5] = "224d7576618ef4dd8ac69f30b5b90b38"
SRC_URI[sha256sum] = "909a72df63dfa3c0844ccf44a26ac028d148297ed333ab51560893923d7691ce"

S = "${WORKDIR}/mesa-${PV}"

RDEPENDS:${PN}:append = " libgcc wayland libdrm glibc libstdc++ zlib expat"

inherit meson pkgconfig python3native gettext

EXTRA_OEMESON = " \
    -Dshared-glapi=true \
    -Dgallium-opencl=disabled \
    -Dglx-read-only-text=true \
    -Dplatforms='wayland' \
"

EXTRA_OEMESON += " \
    --prefix=/usr/lib/mesa-virtio \
    --libdir=/usr/lib/mesa-virtio \
    --sysconfdir=/etc/mesa-virtio \
"

EXTRA_OEMESON += " \
    -Ddri-drivers='' \
    -Dgallium-drivers='virgl,swrast' \
    -Dglx=disabled \
    -Dgles1=disabled \
    -Dgles2=enabled \
    -Degl=true \
    -Dgbm=true \
    -Dllvm=disabled \
    -Dvulkan-drivers='[]' \
"

FILES:${PN} = " \
    /usr/lib/mesa-virtio/* \
    /etc/mesa-virtio/drirc \
    /usr/share/mesa-virtio/* \
"

FILES:${PN}-dev += " \
    /usr/lib/mesa-virtio/libglapi.so \
    /usr/lib/mesa-virtio/libEGL.so \
    /usr/lib/mesa-virtio/libgbm.so \
    /usr/lib/mesa-virtio/libGLESv2.so \
    /usr/lib/mesa-virtio/libGLESv1_CM.so \
"
