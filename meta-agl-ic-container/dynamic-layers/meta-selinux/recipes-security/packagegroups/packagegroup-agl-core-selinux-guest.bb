SUMMARY = "SELinux packages for container guest"
DESCRIPTION = "SELinux packages required for AGL"
LICENSE = "MIT"

inherit features_check

REQUIRED_DISTRO_FEATURES = "selinux"

PACKAGES = " \
    packagegroup-agl-core-selinux-guest \
"

ALLOW_EMPTY:${PN} = "1"

# The packagegroup-agl-core-selinux is including auditd.
# But it shall run in host, shall not run in guest.
# This package group remove from host only package from packagegroup-agl-core-selinux

RDEPENDS:${PN} = " \
    coreutils \
    libsepol \
    libselinux \
    libselinux-bin \
    libsemanage \
    refpolicy \
"
