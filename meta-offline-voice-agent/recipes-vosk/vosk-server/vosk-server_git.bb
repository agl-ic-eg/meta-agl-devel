DESCRIPTION = "WebSocket, gRPC and WebRTC speech recognition server based on Vosk and Kaldi libraries"
SUMMARY = "This is a server for highly accurate offline speech recognition using Kaldi and Vosk-API."
HOMEPAGE = "https://github.com/alphacep/vosk-server"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=d09bbd7a3746b6052fbd78b26a87396b"

SRC_URI = "git://github.com/alphacep/vosk-server;protocol=https;branch=master"

PV = "1.0+git${SRCPV}"
SRCREV = "70f3d5321a40f2f5dffe9c833bc1fac4b3b451e7"

S = "${WORKDIR}/git"

DEPENDS = "python3-vosk-api openblas vosk boost"

do_configure () {
	:
}

do_compile () {
	# websocket-cpp
	cd websocket-cpp
	${CXX} -I${STAGING_INCDIR}/vosk -lvosk ${LDFLAGS} -o vosk-websocket-cpp asr_server.cpp
}

do_install () {
	# websocket-cpp
	install -d ${D}${bindir}
	cp websocket-cpp/vosk-websocket-cpp ${D}${bindir}
}

RDEPENDS:${PN} += "vosk-model"
