#!/bin/bash

while sleep 1 ;do evtest /dev/input/by-id/usb-wch.cn_CH57x-event-kbd ;done | while read line; do
    unset key target_container
    [[ "$line" =~ \((KEY_.)\),\ value\ 1 ]] && key="${BASH_REMATCH[1]}"
    case "$key" in
        KEY_G)
            cmcontrol --change-active-guest-name=agl-flutter-ivi-demo
            cmcontrol --shutdown-guest-role=ivi
            ;;
        KEY_D)
            cmcontrol --change-active-guest-name=agl-qt-ivi-demo
            cmcontrol --shutdown-guest-role=ivi
            ;;
        KEY_A)
            cmcontrol --change-active-guest-name=agl-momi-ivi-demo
            cmcontrol --shutdown-guest-role=ivi
            ;;
        KEY_C)
            cmcontrol --force-reboot-guest-role=ivi
            ;;
    esac
done
