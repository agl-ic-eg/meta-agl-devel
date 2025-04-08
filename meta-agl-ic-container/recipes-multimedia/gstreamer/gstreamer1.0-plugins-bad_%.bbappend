PACKAGECONFIG:append = " \
    ${@bb.utils.contains('LICENSE_FLAGS_ACCEPTED', 'commercial', 'openh264', '', d)} \
"
