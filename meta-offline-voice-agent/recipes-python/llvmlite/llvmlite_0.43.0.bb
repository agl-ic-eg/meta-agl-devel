DESCRIPTION = "llvmlite prebuilt binary"
HOMEPAGE = "https://github.com/numba/llvmlite"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=a15ea9843f27327e08f3c5fbf8043a2b"

SRCURIWHEEL:aarch64 += "https://files.pythonhosted.org/packages/bf/f1/4c205a48488e574ee9f6505d50e84370a978c90f08dab41a42d8f2c576b6/llvmlite-0.43.0-cp312-cp312-manylinux_2_17_aarch64.manylinux2014_aarch64.whl"
SRCURIWHEEL:x86-64  += "https://files.pythonhosted.org/packages/00/5f/323c4d56e8401c50185fd0e875fcf06b71bf825a863699be1eb10aa2a9cb/llvmlite-0.43.0-cp312-cp312-manylinux_2_17_x86_64.manylinux2014_x86_64.whl"
SRC_URI = " ${SRCURIWHEEL} "
SRC_URI:append = " file://LICENSE "

SRCURICHECKSUM:x86-64 = "df6509e1507ca0760787a199d19439cc887bfd82226f5af746d6977bd9f66844"
SRCURICHECKSUM:aarch64 = "eccce86bba940bae0d8d48ed925f21dbb813519169246e2ab292b5092aba121f"
SRC_URI[sha256sum] = "${SRCURICHECKSUM}"

COMPATIBLE_MACHINE = "null"
COMPATIBLE_MACHINE:aarch64 = "(.*)"
COMPATIBLE_MACHINE:x86-64 = "(.*)"

DEPENDS += " unzip-native"

RDEPENDS:${PN} += " zlib"

do_install() {
    mkdir -p ${WORKDIR}/llvmlite-0.43.0
    unzip ${WORKDIR}/$(basename ${SRC_URI}) -d ${WORKDIR}/llvmlite-0.43.0
    install -d ${D}/usr/lib/python3.12/site-packages
    cp -R ${WORKDIR}/llvmlite-0.43.0/* ${D}/usr/lib/python3.12/site-packages/
}

do_configure:prepend() {
    export LD_LIBRARY_PATH="${D}/usr/lib/python3.12/site-packages/llvmlite/binding:${LD_LIBRARY_PATH}"
}

FILES:${PN} += "/usr/lib/python3.12/site-packages/* "
