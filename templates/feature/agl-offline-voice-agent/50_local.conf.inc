# agl-offline-voice-agent
FORTRAN:forcevariable = ",fortran"
RUNTIMETARGET:append:pn-gcc-runtime = " libquadmath"

# bad practice but required to build scikit-learn
HOSTTOOLS += "gfortran" 

# FIXME check if needed and/or submit upstream
BBCLASSEXTEND:pn-python3-charset-normalizer = "native nativesdk"

PACKAGECONFIG:pn-openblas := "cblas lapack lapack-deprecated"

FEATURE_PACKAGES_agl-offline-voice-agent:append = " \
    python3-snips-inference-agl \ 
    snips-model-agl \
    vosk \
    vosk-kaldi \
    vosk-server \
    python3-vosk-api \ 
    python3-vosk-websocket-server \
    python3-sounddevice \
    agl-service-voiceagent \
    whisper-cpp \
    whisper-cpp-tiny \
    "

EXTRA_IMAGE_FEATURES += "agl-offline-voice-agent"

PREFERRED_RPROVIDER_vosk-model ?= "vosk-model-small-en-us"
PREFERRED_RPROVIDER_whisper-model ?= "whisper-cpp-tiny"

# bump bazel limits
BAZEL_JOBS = "HOST_CPUS*.4"
BAZEL_MEM = "HOST_RAM*.4"

LICENSE_FLAGS_ACCEPTED += "commercial_ffmpeg"

# no rasa support currently
#      python3-rasa \
#      rasa-model-agl \
#
