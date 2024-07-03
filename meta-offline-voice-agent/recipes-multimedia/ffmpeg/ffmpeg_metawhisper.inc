FILESEXTRAPATHS:prepend := "${THISDIR}/ffmpeg:"

SRC_URI += " \
    file://av1_ordering_info.patch \
    file://vulkan_av1_stable_API.patch \
"

