DESCRIPTION = "WebSocket, gRPC and WebRTC speech recognition server based on Vosk and Kaldi libraries"
SUMMARY = "This is a server for highly accurate offline speech recognition using Kaldi and Vosk-API."
HOMEPAGE = "https://github.com/alphacep/vosk-server"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=13e8863a069f2c314d9251336e4afcbf"

SRC_URI = "git://github.com/amanarora9848/voicerecognizer-gst-grpc;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "d28da4cd89a5dfff823f1358816d0ff4791f8d28"

S = "${WORKDIR}/git/python-server"

RDEPENDS:${PN} += " \
    vosk \
    python3-vosk-api \
    python3-grpcio \
    python3-protobuf \
    vosk-model \
    python3-pycairo \
    python3-pygobject \
"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}${libdir}/vosk-grpc-server
    cp ${S}/record_server_grpc.py ${D}${libdir}/vosk-grpc-server/
    cp ${S}/recognize_pb2_grpc.py ${D}${libdir}/vosk-grpc-server/
    cp ${S}/recognize.proto ${D}${libdir}/vosk-grpc-server/
    cp ${S}/recognize_pb2.py ${D}${libdir}/vosk-grpc-server/
    chmod a+x ${D}${libdir}/vosk-grpc-server/record_server_grpc.py
}

FILES:${PN} += " /usr/lib/vosk-grpc-server "