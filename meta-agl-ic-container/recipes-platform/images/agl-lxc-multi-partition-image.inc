# Integration type support for multi partition with host and guest image.

require recipes-platform/images/agl-lxc-multiconfig-build.inc

# Install out of tree build image support
OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR ??= ""

OUT_OF_TREE_CONTAINER_IMAGES ??= " \
    agl-ivi-demo-qt \
    agl-ivi-demo-flutter \
"

OUT_OF_TREE_CONTAINER_CONFIGS ??= " \
    cm-config-agl-qt-ivi-demo \
    cm-config-agl-flutter-ivi-demo \
"

CONTAINER_EXCHANGE_DEMO_UI ??= "momiweb momikey"

CONTAINER_MULTI_DEMO ??= " \
    ${OUT_OF_TREE_CONTAINER_CONFIGS} \
    ${CONTAINER_EXCHANGE_DEMO_UI} \
"

# Handle modification of IMAGE_LINK_NAME done by ULCB builds with Kingfisher support
MACHINE_SUFFIX = "${@bb.utils.contains('AGL_FEATURES', 'kingfisher', '-kf', '', d)}"

install_container_images() {
    for c in ${CONTAINER_IMAGES}; do
        config=${c%:*}
        image=${c#*:}
        name=${image#guest-image-}
        rm -f "${DEPLOY_DIR_IMAGE}/${image}.ext4"
        src="${TOPDIR}/tmp-${config}/deploy/images/${MACHINE}/${image}-${MACHINE}${MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.ext4"
        bbnote "Installing ${src}"
        ln -s ${src} "${DEPLOY_DIR_IMAGE}/${image}.ext4"
    done

    if ${@oe.utils.conditional('OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR', '', 'false', 'true', d)}; then
        er=0
        for image in ${OUT_OF_TREE_CONTAINER_IMAGES}; do
            rm -f "${DEPLOY_DIR_IMAGE}/${image}.ext4"
            src="${OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR}/images/${MACHINE}/${image}-${MACHINE}${MACHINE_SUFFIX}${IMAGE_NAME_SUFFIX}.ext4"
            if [ ! -e ${src} ]; then
                bberror "File not found at ${src}."
                er=1
            else
                bbnote "Installing ${src}"
            fi
            ln -s ${src} "${DEPLOY_DIR_IMAGE}/${image}.ext4"
        done

        if [ $er -eq 1 ]; then
            bbfatal "AGL Demo IVI images install error.  Please check OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR setting and IVI side build status."
        fi
    fi
}

ROOTFS_POSTPROCESS_COMMAND += "install_container_images; "

IMAGE_INSTALL:append = " \
    ${@oe.utils.conditional('OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR', '', '', '${CONTAINER_MULTI_DEMO}', d)} \
"

WKS_FILES:prepend = " \
    ${@oe.utils.conditional('OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR', '', 'agl-ic-container-noloader.wks ', 'agl-ic-container-noloader-demo.wks ', d)} \
"
