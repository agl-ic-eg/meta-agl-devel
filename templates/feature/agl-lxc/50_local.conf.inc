BBMULTICONFIG = "agl-container-cluster agl-container-ivi"

# Disable the meta-virtualization configuration support enabled by
# agl-virt.  This should be revisited when upgrading past dunfell,
# as less of the extra configuration may be enabled in the future.
DISTRO_FEATURES:remove = "virtualization"
INHERIT:remove = "sanity-meta-virt"
