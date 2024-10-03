## Unified HMI support layer (meta-uhmi)
"Unified HMI" is a common platform technology for UX innovation in integrated cockpit by flexible information display on multiple displays of various applications. Applications can be rendered to any display via a unified virtual display.

Unified HMI consists of two main components.

* Remote Virtio GPU Device(RVGPU): Render applications remotely in different SoCs/VMs.
* Distributed Display Framework: Flexible layout control of applications across multiple displays.

The meta-uhmi currently supports x86, Raspberry Pi4, and Renesas Boards platforms.
There are two layers under meta-uhmi to support these two frameworks.

## RVGPU support layer(meta-rvgpu)
RVGPU rendering engine, operating on a client-server model, creates 3D rendering commands on a client device and transmits them to a server device via the network, where the server-side device performs GPU-based rendering and displays the results.

The meta-rvgpu currently supports `agl-image-weston`, `agl-ivi-demo-qt`, `agl-ivi-demo-flutter` as build images.

RVGPU is OSS. For more details, visit the following URL: [Unified HMI - Remote Virtio GPU](https://github.com/unified-hmi/remote-virtio-gpu)

## Distributed Display Framework support layer(meta-distributed-display-fw)
Distributed Display Framework is an open-source software layer that provides essential services for managing distributed display applications. This framework is designed to work with a variety of hardware and software configurations, making it a versatile choice for developers looking to create scalable and robust display solutions.

Now, the meta-distributed-display-fw only supports build images that run on weston and for which the wayland-ivi-extension is available like `agl-image-weston`.

Distributed Display Framework consists of three main components.

* [ucl-tools](https://github.com/unified-hmi/ucl-tools): Unified Clustering Tools (UCL), launch applications for multiple platforms and manage execution order and survival.

* [ula-tools](https://github.com/unified-hmi/ula-tools): Unified Layout Tools (ULA), application layout for virtual displays on virtual screen (mapped from physical displays).

* [uhmi-ivi-wm](https://github.com/unified-hmi/uhmi-ivi-wm): apply ivi-layer and ivi-surface layouts to the screen using the layout design output by ula-tools.

For more details about these frameworks, please visit our GitHub repository at the attached URL.

## How to build
Follow the [AGL documentation](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/01_Build_Process_Overview/) for the build process, and set up the "[Initializing Your Build Environment](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/04_Initializing_Your_Build_Environment/)" section as described below to enable the AGL feature 'agl-uhmi'. For example:
```
$ cd $AGL_TOP/master
$ source ./meta-agl/scripts/aglsetup.sh -m qemux86-64 -b qemux86-64 agl-demo agl-devel agl-uhmi
```
After adding the feature, execute the command:
```
$ bitbake <image_name>
```
Replace the `<image_name>` with the appropriate values you want. We have confirmed the operation with the **agl-image-weston**.

## How to setup and boot
For Environment setup instructions for each platform, refer to the following link in the AGL Documentation.
[Building for x86(Emulation and Hardware)](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/07_Building_for_x86_%28Emulation_and_Hardware%29/)
[Building for Raspberry Pi 4](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/08_Building_for_Raspberry_Pi_4/)
[Building for Supported Renesas Boards](https://docs.automotivelinux.org/en/master/#01_Getting_Started/02_Building_AGL_Image/09_Building_for_Supported_Renesas_Boards/)

For Raspberry Pi 4 and Supported Renesas Boards, refer to the above URL for boot methods.
For x86 emulation (qemu), network bridge is required to enable communication with other devices when using RVGPU. Here’s an example procedure for your reference.
```
$ sudo ip link add <bridge_name> type bridge
$ sudo ip addr add <IP address> dev <bridge_name>
$ sudo ip link set dev <interface> master <bridge_name>
$ sudo ip link set dev <bridge_name> up
```
Replace the placeholders with the appropriate values:
- `<bridge_name>`: You can assign any name, for example: `br0`
- `<IP_address>`: Enter an available IP address, for example: `192.168.0.100/24`
- `<interface>`: Specify the network interface, for example: `eth0`

To enable the use of the bridge, create or append /etc/qemu directory and /etc/qemu/bridge.conf file.
```
allow <bridge_name>
```
Make sure /etc/qemu/ has 755 permissions.
Create the following bash file named **run_qemu_bridge.sh** in any `<WORKDIR>`.
```
#!/bin/bash

KERNEL_PATH=$1
DRIVE_PATH=$2
BRIDGE_NAME="<bridge_name>"

printf -v macaddr "52:54:%02x:%02x:%02x:%02x" $(( $RANDOM & 0xff)) $(( $RANDOM & 0xff )) $(( $RANDOM & 0xff)) $(( $RANDOM & 0xff ))

qemu-system-x86_64 -enable-kvm -m 2048 \
    -kernel ${KERNEL_PATH} \
    -drive file=${DRIVE_PATH},if=virtio,format=raw \
    -cpu kvm64 -cpu qemu64,+ssse3,+sse4.1,+sse4.2,+popcnt \
    -vga virtio -show-cursor \
    -device virtio-net-pci,netdev=net0,mac=$macaddr \
    -netdev bridge,br=$BRIDGE_NAME,id=net0 \
    -serial mon:stdio -serial null \
    -soundhw hda \
    -append 'root=/dev/vda swiotlb=65536 rw console=ttyS0,115200n8 fstab=no'
```
Save the file and run the following to start QEMU.
```
sudo <WORKDIR>/run_qemu_bridge.sh <build_directory>/tmp/deploy/images/qemux86-64/bzImage <build_directory>/tmp/deploy/images/qemux86-64/agl-image-weston-qemux86-64.rootfs.ext4
```
When QEMU boot, you can log in to qemu on the terminal where you executed above command, so please assign an IP address there.

For example:
```
ifconfig <your environment> 192.168.0.100 netmask 255.255.255.0
```
