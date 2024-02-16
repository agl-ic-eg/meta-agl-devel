# agl-offline-voice-agent
FORTRAN:forcevariable = ",fortran"
RUNTIMETARGET:append:pn-gcc-runtime = " libquadmath"

# bad practice but required to build scikit-learn
HOSTTOOLS += "gfortran" 

# FIXME check if needed and/or submit upstream
BBCLASSEXTEND:pn-python3-charset-normalizer = "native nativesdk"

IMAGE_INSTALL:append = " \
    python3-snips-inference-agl \ 
    snips-model-agl \
    python3-rasa \
    rasa-model-agl \
    vosk \
    vosk-kaldi \
    vosk-server \
    python3-vosk-api \ 
    python3-vosk-websocket-server \
    python3-sounddevice \
    flutter-voiceassistant \
    agl-service-voiceagent \
    "

PREFERRED_RPROVIDER_vosk-model ?= "vosk-model-small-en-us"


# bump bazel limits
BAZEL_JOBS = "HOST_CPUS*.4"
BAZEL_MEM = "HOST_RAM*.4"
