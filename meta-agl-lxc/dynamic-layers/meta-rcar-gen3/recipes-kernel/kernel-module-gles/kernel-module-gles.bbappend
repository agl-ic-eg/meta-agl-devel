FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"

SRC_URI:append = " file://0001-Fix-fw-download-issue.patch"

RDEPENDS:${PN}:append = " gles-user-module-firmware"
