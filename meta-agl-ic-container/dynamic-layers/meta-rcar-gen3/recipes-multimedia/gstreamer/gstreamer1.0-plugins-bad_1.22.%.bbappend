DEPENDS:remove:aglcontainerguest = "linux-renesas"

EXTRA_OECONF:remove:aglcontainerguest = "--enable-kms"
PACKAGECONFIG:remove:aglcontainerguest = "kms"
