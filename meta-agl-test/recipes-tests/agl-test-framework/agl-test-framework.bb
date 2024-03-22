SUMMARY = "Agl Test Framework"
HOMEPAGE = "https://git.automotivelinux.org/src/agl-test-framework/"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PN = 'agl-test-framework'
PV = '1'

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/agl-test-framework;protocol=https;branch=master"
SRCREV = "8a35e659dba8117eb0bb403cc0d2464bdd6fa052"

S = "${WORKDIR}/git"

# Notice:
# This is the list of all installed tests
#       On the installing board, if you get dirpath like:
#         /usr/AGL/agl-test/tests/bc/
#           Then, the test name here you should write down: bc
#       If you are installing LTP related files:
#         /usr/AGL/agl-test/tests/LTP/syscalls/
#           Then, the test name here you should write down: LTP/syscalls
#
FRAMEWORK_INSTALL_LIST = " \
    acl \
    aio_stress \
    attr \
    babeltrace \
    babeltrace2 \
    bash \
    bc \
    bluez5 \
    busybox \
    bzip2 \
    coreutils \
    cpio \
    crashme \
    curl \
    diffutils \
    e2fsprogs \
    expat \
    flex \
    fontconfig \
    gawk \
    gdbm \
    gdk_pixbuf \
    glib2 \
    gstreamer \
    gzip \
    json_glib \
    kbd \
    libpam \
    libxml2 \
    linus_stress \
    lua \
    lz4 \
    LTP/math \
    LTP/cve \
    LTP/posix_conformance_tests \
    LTP/syscalls \
    openssl \
    popt \
    python3 \
    sed \
    slang \
    stress_ng \
    tar \
    xz \
    zlib \
"

# Function of the structure installation
install_framework () {
    # basic essential pytest structure
    install -d ${D}/usr/bin/
    install -m 0755 ${WORKDIR}/git/agl-test ${D}/usr/bin/
    install -d ${D}/usr/AGL/agl-test/plugins/
    install -m 0644 ${WORKDIR}/git/pytest.ini ${D}/usr/AGL/agl-test/
    install -m 0644 ${WORKDIR}/git/plugins/* ${D}/usr/AGL/agl-test/plugins/
    install -d ${D}/usr/AGL/agl-test/template/
    install -m 0644 ${WORKDIR}/git/template/* ${D}/usr/AGL/agl-test/template/
    install -d ${D}/usr/AGL/agl-test/tests/
    install -m 0644 ${WORKDIR}/git/tests/__init__.py ${D}/usr/AGL/agl-test/tests/
    install -d ${D}/usr/AGL/agl-test/tests/LTP/
    install -m 0644 ${WORKDIR}/git/tests/LTP/*py ${D}/usr/AGL/agl-test/tests/LTP/
}

# Function of the test file installation
install_test_files () {
    for test_name in ${FRAMEWORK_INSTALL_LIST}; do
        # Step 1 : install basic python files (no check, this is common installation)
        install -d ${D}/usr/AGL/agl-test/tests/${test_name}
        install -m 0644 ${WORKDIR}/git/tests/${test_name}/*.py ${D}/usr/AGL/agl-test/tests/${test_name}/

        # Step 2 : install spec.json (check first, not common)
        if [ -f "${WORKDIR}/git/tests/${test_name}/spec.json" ];then
            install -m 0644 ${WORKDIR}/git/tests/${test_name}/spec.json ${D}/usr/AGL/agl-test/tests/${test_name}
        fi

        # Step 3 : install the resource folder (check first, not common)
        if [ -d "${WORKDIR}/git/tests/${test_name}/resource/" ];then
            install -d ${D}/usr/AGL/agl-test/tests/${test_name}/resource
            install -m 0644 ${WORKDIR}/git/tests/${test_name}/resource/* ${D}/usr/AGL/agl-test/tests/${test_name}/resource/
        fi
    done
}

# install agl-test-framework
do_install() {
    install_framework
    install_test_files
}

# Override PACKAGES
# base: basic structure of the agl-test-framework along with pytest modules
# fast: fast mode packages
# standard: standard mode packages
# extreme: extreme mode packages
#
PACKAGES = " \
    ${PN}-base \
    ${PN}-fast \
    ${PN}-standard \
    ${PN}-extreme \
"

# Filter for basic structure
FILES:${PN}-base += " \
    /usr/AGL/agl-test/plugins/* \
    /usr/AGL/agl-test/pytest.ini \
    /usr/AGL/agl-test/template/* \
    /usr/AGL/agl-test/tests/LTP/agl_test_ltp_base.py \
    /usr/AGL/agl-test/tests/LTP/__init__.py \
    /usr/AGL/agl-test/tests/__init__.py \
    /usr/bin/agl-test \
"

# Filter for fast mode
FILES:${PN}-fast = " \
     /usr/AGL/agl-test/tests/aio_stress/* \
     /usr/AGL/agl-test/tests/attr/* \
     /usr/AGL/agl-test/tests/bc/* \
     /usr/AGL/agl-test/tests/diffutils/* \
     /usr/AGL/agl-test/tests/expat/* \
     /usr/AGL/agl-test/tests/flex/* \
     /usr/AGL/agl-test/tests/fontconfig/* \
     /usr/AGL/agl-test/tests/gdk_pixbuf/* \
     /usr/AGL/agl-test/tests/json_glib/* \
     /usr/AGL/agl-test/tests/kbd/* \
     /usr/AGL/agl-test/tests/libpam/* \
     /usr/AGL/agl-test/tests/lua/* \
     /usr/AGL/agl-test/tests/LTP/math/* \
     /usr/AGL/agl-test/tests/popt* \
     /usr/AGL/agl-test/tests/stress_ng/* \
     /usr/AGL/agl-test/tests/xz/* \
     /usr/AGL/agl-test/tests/zlib/* \
"

# Filter for standard mode
FILES:${PN}-standard = " \
     /usr/AGL/agl-test/tests/acl/* \
     /usr/AGL/agl-test/tests/babeltrace/* \
     /usr/AGL/agl-test/tests/babeltrace2/* \
     /usr/AGL/agl-test/tests/bash/* \
     /usr/AGL/agl-test/tests/bluez5/* \
     /usr/AGL/agl-test/tests/busybox/* \
     /usr/AGL/agl-test/tests/bzip2/* \
     /usr/AGL/agl-test/tests/coreutils/* \
     /usr/AGL/agl-test/tests/cpio/* \
     /usr/AGL/agl-test/tests/crashme/* \
     /usr/AGL/agl-test/tests/curl/* \
     /usr/AGL/agl-test/tests/e2fsprogs/* \
     /usr/AGL/agl-test/tests/gawk/* \
     /usr/AGL/agl-test/tests/gdbm/* \
     /usr/AGL/agl-test/tests/glib2/* \
     /usr/AGL/agl-test/tests/gstreamer/* \
     /usr/AGL/agl-test/tests/gzip/* \
     /usr/AGL/agl-test/tests/libxml2/* \
     /usr/AGL/agl-test/tests/linus_stress/* \
     /usr/AGL/agl-test/tests/LTP/cve/* \
     /usr/AGL/agl-test/tests/LTP/posix_conformance_tests/* \
     /usr/AGL/agl-test/tests/openssl/* \
     /usr/AGL/agl-test/tests/sed/* \
     /usr/AGL/agl-test/tests/slang/* \
     /usr/AGL/agl-test/tests/tar/* \
"

# Filter for extreme mode
FILES:${PN}-extreme = " \
     /usr/AGL/agl-test/tests/python3/* \
     /usr/AGL/agl-test/tests/lz4/* \
     /usr/AGL/agl-test/tests/LTP/syscalls/* \
"

# Runtime dependency for basic structure
RDEPENDS:${PN}-base += " \
    python3-jinja2 \
    python3-pytest \
    python3-pytest-order \
"

# Runtime dependency for fast mode package
RDEPENDS:${PN}-fast += " \
    ltp \
    aio-stress \
    stress-ng \
"

# Runtime dependency for standard mode packages
RDEPENDS:${PN}-standard += " \
    linus-stress \
"

# Runtime dependency for extreme mode packages
RDEPENDS:${PN}-extreme += " \
    \
"
