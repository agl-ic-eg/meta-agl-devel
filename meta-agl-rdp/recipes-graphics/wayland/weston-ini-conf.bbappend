FILESEXTRAPATHS:prepend:agl-rdp := "${THISDIR}/${PN}:"

SRC_URI:prepend:agl-rdp = " file://rdp-standalone.cfg "

WESTON_DISPLAYS:agl-rdp = " "
WESTON_FRAGMENTS:agl-rdp = " rdp-standalone "

# sample keys
AGL_FREERDP_DEFAULT_KEYS ?= "agl-freerdp-sample-server-key"
AGL_FREERDP_DEPENDENCY ?= "${AGL_FREERDP_DEFAULT_KEYS}"
RDEPENDS:${PN}:append:agl-rdp = " ${AGL_FREERDP_DEPENDENCY} "

# the key tools need to be on-target (for now)
RDEPENDS:${PN}:agl-rdp += " freerdp "

