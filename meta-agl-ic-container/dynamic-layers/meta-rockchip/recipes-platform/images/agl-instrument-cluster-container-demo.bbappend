WKS_FILES:remove = "agl-ic-container-noloader.wks agl-ic-container-noloader-demo.wks"
WKS_FILES:prepend = " \
    ${@oe.utils.conditional('OUT_OF_TREE_CONTAINER_IMAGE_DEPLOY_DIR', '', 'agl-ic-container-rockchip.wks ', 'agl-ic-container-rockchip-demo.wks ', d)} \
"

IMAGE_INSTALL:append = " \
     linux-firmware-rtl8822 \
     linux-firmware-rtl8125 \
     rockchip-libmali-firmware \
"
