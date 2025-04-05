SUMMARY = "DRM lease client support packages"
DESCRIPTION = "This pacage group including drm lease client support packages."
HOMEPAGE = "https://confluence.automotivelinux.org/display/IC"

LICENSE = "Apache-2.0"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = "\
    packagegroup-drm-lease-client-support \
"
RDEPENDS:${PN} += "\
    qt-drm-lease \
"
