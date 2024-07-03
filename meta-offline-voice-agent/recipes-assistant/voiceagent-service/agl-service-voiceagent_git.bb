SUMMARY = "A gRPC-based voice agent service designed for Automotive Grade Linux (AGL)."
HOMEPAGE = "https://github.com/malik727/agl-service-voiceagent"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4202492ed9afcab3aaecc4a9ec32adb2"

SRC_URI = " \
    file://agl-service-voiceagent.service \
    git://gerrit.automotivelinux.org/gerrit/apps/agl-service-voiceagent;protocol=https;branch=${AGL_BRANCH} \
"

SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

# Speech to Text Model Name, use 'vosk-model-en-us-0.22' for better performance
VOSK_STT_MODEL_NAME ?= "vosk-model-small-en-us-0.15" 
# Wake Word Detection Model Name
VOSK_WWD_MODEL_NAME ?= "vosk-model-small-en-us-0.15" 
WAKE_WORD ?= "hello auto"

DEPENDS += " \
    python3 \
    python3-setuptools-native \
    python3-grpcio-tools-native \
    "

inherit setuptools3 systemd

SYSTEMD_SERVICE:${PN} = "agl-service-voiceagent.service"

do_compile:prepend() {
    # Generate proto files and move them to 'generated/' directory
    python3 -m grpc_tools.protoc -I${S}/agl_service_voiceagent/protos --python_out=${S}/agl_service_voiceagent/generated --grpc_python_out=${S}/agl_service_voiceagent/generated voice_agent.proto
    python3 -m grpc_tools.protoc -I${S}/agl_service_voiceagent/protos --python_out=${S}/agl_service_voiceagent/generated --grpc_python_out=${S}/agl_service_voiceagent/generated audio_processing.proto

    # Replace all placeholders with actual values
    sed -i 's|PYTHON_DIR|${PYTHON_SITEPACKAGES_DIR}|g' ${S}/agl_service_voiceagent/config.ini
    sed -i 's|VOSK_STT_MODEL_NAME|${VOSK_STT_MODEL_NAME}|g' ${S}/agl_service_voiceagent/config.ini
    sed -i 's|VOSK_WWD_MODEL_NAME|${VOSK_WWD_MODEL_NAME}|g' ${S}/agl_service_voiceagent/config.ini
    sed -i 's|WAKE_WORD_VALUE|${WAKE_WORD}|g' ${S}/agl_service_voiceagent/config.ini

    # Keep Note that if you make any change to the value of above variables, you'll need to perform:
    # 'bitbake -c cleanall agl-service-voiceagent'
    # and compile this package again to see your changes reflected. 
}

do_install:append() {
    # Create the destination directory if it doesn't exist
    install -d ${D}/usr/share/nlu/
    
    # Copy the 'mappings' folder to the destination directory
    cp -R ${WORKDIR}/git/mappings ${D}/usr/share/nlu/

    # Initialize our service definition
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${systemd_system_unitdir}
        install -m 0644 ${WORKDIR}/agl-service-voiceagent.service ${D}${systemd_system_unitdir}
    fi
}

RDEPENDS:${PN} += " \
    python3-numpy \
    python3-grpcio \
    python3-grpcio-tools \
    python3-pygobject \
    kuksa-client \
    python3-snips-inference-agl \
    vosk \
    whisper \
    "

FILES:${PN} += "/usr/share/nlu/"
