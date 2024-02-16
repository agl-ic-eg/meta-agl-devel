DESCRIPTION = "WebSocket, gRPC and WebRTC speech recognition server based on Vosk and Kaldi libraries"
SUMMARY = "This is a server for highly accurate offline speech recognition using Kaldi and Vosk-API."
HOMEPAGE = "https://github.com/alphacep/vosk-server"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d09bbd7a3746b6052fbd78b26a87396b"

SRC_URI = "git://github.com/alphacep/vosk-server;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "70f3d5321a40f2f5dffe9c833bc1fac4b3b451e7"

S = "${WORKDIR}/git"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install () {
    install -d ${D}${bindir}
    cp ${S}/websocket/asr_server.py ${D}${bindir}/vosk-websocket-python.py
    chmod a+x ${D}${bindir}/vosk-websocket-python.py
}

RDEPENDS:${PN} += " \
    vosk \
    python3-vosk-api \
    python3-websockets \
    vosk-model \
"