AGL_META_PYTHON = "${METADIR}/external/meta-openembedded/meta-python"
AGL_META_NETWORKING = "${METADIR}/external/meta-openembedded/meta-networking"
AGL_META_FILESYSTEMS = "${METADIR}/external/meta-openembedded/meta-filesystems"
AGL_META_VIRTUALIZATION = "${METADIR}/external/meta-virtualization"
AGL_META_QT6 = "${METADIR}/external/meta-qt6"

BBLAYERS =+ " \
  ${METADIR}/meta-agl-devel/meta-agl-ic-container \
"
