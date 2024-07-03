SUMMARY = "Simple, powerful, and fast logging for Python."
HOMEPAGE = "https://github.com/hynek/structlog"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT;md5=0473cdcf9c6ed3f81c08d886870daea5"

PYPI_PACKAGE = "structlog"

SRC_URI += " file://0001-structlog-add-setup-file.patch"
SRC_URI += " file://0001-Structlog-setup.py-fix.patch"
SRC_URI[md5sum] = "bd2218c8799e44a90689bbdeadcab4f3"
SRC_URI[sha256sum] = "270d681dd7d163c11ba500bc914b2472d2b50a8ef00faa999ded5ff83a2f906b"

inherit pypi setuptools3
