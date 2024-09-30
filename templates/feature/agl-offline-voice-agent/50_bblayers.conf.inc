# agl-offline-voice-agent

AGL_META_PYTHON = "${METADIR}/external/meta-openembedded/meta-python"

BBLAYERS =+ " \
    ${METADIR}/meta-agl-devel/meta-offline-voice-agent \
    ${METADIR}/external/meta-python-ai \
"

# package in meta-oe is newer
BBMASK += "meta-tensorflow/recipes-devtools/python/python3-gast_0.4.0.bb"

# needed for rasa
#      ${METADIR}/external/meta-tensorflow \
#
