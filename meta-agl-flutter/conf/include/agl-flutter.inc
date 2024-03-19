AGL_FEATURES += "agl-flutter"

FLUTTER_SDK_TAG = "3.13.9"

# Mask libstd-rs bbappend out since the change it makes does not
# build with newer versions of Rust.
BBMASK += "meta-flutter/recipes-devtools/rust/libstd-rs_%.bbappend"
