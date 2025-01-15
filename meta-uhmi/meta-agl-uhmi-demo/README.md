# Unified HMI preconfigured demo images

## Introduction
Unified HMI needs a litle complex environment setup, so here we provide a recipe that allow you to create a pre-configured images.

## Unified HMI frameworks
For a detailed explanation of Unified HMI, please refer to the [AGL Documentation](https://docs.automotivelinux.org/en/master/#06_Component_Documentation/11_Unified_HMI/)

## Demo environment
Demo images supports three boards: qemux-86-64, raspberrypi4, and agl-refhw. 
Please prepare two boards, one is the sender and the other is the receiver, on any boards and build the respective images for each.

* sender
```
IP: 192.168.0.100
HOSTNAME: agl-host0
```

* receiver
```
IP: 192.168.0.101
HOSTNAME: agl-host1
```

## How to build
Follow the [AGL documentation](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/01_Build_Process_Overview/) for the build process, and set up the "[Initializing Your Build Environment](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/04_Initializing_Your_Build_Environment/)" section as described below to enable the AGL feature 'agl-uhmi-demo'.

For example:
```
$ cd $AGL_TOP/master
$ source ./meta-agl/scripts/aglsetup.sh -m qemux86-64 -b qemux86-64 agl-devel agl-uhmi-demo
```

After adding the feature, execute the bitbake command:

for sender
```
$ bitbake agl-uhmi-demo-preconfigured-sender
```

for receiver
```
$ bitbake agl-uhmi-demo-preconfigured-receiver
```

## How to use
After both the sender and receiver have successfully booted, you can execute the following commands on the sender to confirm that the application is displayed across two displays each connected to diferrent boards.

on sender (IP:192.168.0.100, hostname:agl-host0):
```
$ cat /var/local/uhmi-app/glmark2/app.json | ucl-distrib-com /etc/uhmi-framework/virtual-screen-def.json &
$ cat /var/local/uhmi-app/glmark2/initial_vscreen.json | ula-distrib-com
```
