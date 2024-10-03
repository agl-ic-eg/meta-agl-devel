## How to set up Distributed Display Framework

To utilize the Distributed Display Framework, you need to set up each image with a distinct hostname and static IP address, and ensure that ivi-shell is running using the wayland-ivi-extension. Please note that the following configurations are examples and should be adapted to fit your specific network environment.

### Run weston with ivi-shell

Before setting unique hostnames and configuring static IP addresses, it is essential to start Weston with ivi-shell since the Distributed Display Framework controls the layout of applications using ivi-shell. To initialize ivi-shell, follow these steps:

```
ln -sf /etc/xdg/weston/weston_ivi-shell.ini /etc/xdg/weston/weston.ini
systemctl restart weston
```

Now that ivi-shell is properly initialized, you can proceed to set unique hostnames for your images.

### Set Unique Hostnames

Edit the `/etc/hostname` file on each image to set a unique hostname. Here are the suggested hostnames for the first two images:

- **For the first image:**
```
echo "agl-host0" | tee /etc/hostname
```
- **For the second image:**
```
echo "agl-host1" | tee /etc/hostname
```

### Assign Static IP Addresses

Configure a static IP address for each image by editing the `/etc/systemd/network/wired.network` file. If this file does not exist, please create a new one. Use the following template and replace `<IP_address>` and `<Netmask>` with your network's specific settings:

```ini
[Match]
KernelCommandLine=!root=/dev/nfs
Name=eth* en*

[Network]
Address=<IP_address>/<Netmask>
```

Here is how you might configure the static IP addresses for the first two images:

- **For the first image:**
```
[Match]
KernelCommandLine=!root=/dev/nfs
Name=eth* en*

[Network]
Address=192.168.0.100/24
```
- **For the second image:**
```
[Match]
KernelCommandLine=!root=/dev/nfs
Name=eth* en*

[Network]
Address=192.168.0.101/24
```

Once the hostname and IP addresses settings are complete, please reboot to reflect these settings.

### Customizing Virtual Screen Definitions

Adjust the `/etc/uhmi-framework/virtual-screen-def.json` file to match your environment. In the folowing example, we assume an environment where two images, agl-host0 and agl-host1, each have a display output with a resolusion of 1920x1080(FullHD), arranged in order from left to right as agl-host0 and then agl-host1:

```
{
    "virtual_screen_2d": {
        "size": {"virtual_w": 3840, "virtual_h": 1080},
        "virtual_displays": [
            {"vdisplay_id": 0, "disp_name": "AGL_SCREEN0", "virtual_x": 0, "virtual_y": 0, "virtual_w": 1920, "virtual_h": 1080},
            {"vdisplay_id": 1, "disp_name": "AGL_SCREEN1", "virtual_x": 1920, "virtual_y": 0, "virtual_w": 1920, "virtual_h": 1080}
        ]
    },
    "virtual_screen_3d": {},
    "real_displays": [
        {"node_id": 0, "vdisplay_id": 0, "pixel_w": 1920, "pixel_h": 1080, "rdisplay_id": 0},
        {"node_id": 1, "vdisplay_id": 1, "pixel_w": 1920, "pixel_h": 1080, "rdisplay_id": 1}
    ],
    "node": [
        {"node_id": 0, "hostname": "agl-host0", "ip": "192.168.0.100"},
        {"node_id": 1, "hostname": "agl-host1", "ip": "192.168.0.101"}
    ],
    "distributed_window_system": {
        "window_manager": {},
        "framework_node": [
            {"node_id": 0, "ula": {"debug": false, "debug_port": 8080, "port": 10100}, "ucl": {"port": 7654}},
            {"node_id": 1, "ula": {"debug": false, "debug_port": 8080, "port": 10100}, "ucl": {"port": 7654}}
        ]
    }
}
```

Be sure to update the virtual_w, virtual_h, virtual_x, virtual_y, pixel_w, pixel_h, hostname, and ip fields to accurately reflect your specific network configuration.

### Restarting Services
Once you have prepared the virtual-screen-def.json file and configured the system, you need to restart the system or the following services for the changes to take effect:
```
systemctl restart uhmi-ivi-wm
systemctl restart ula-node
systemctl restart ucl-launcher
```

After restarting these services, your system should be ready to use the Distributed Display Framework with the new configuration.

## How to use UCL (Unified Clustering) Framework

The Unified Clustering (UCL) Framework provides a distributed launch feature for applications using remote virtio GPU (rvgpu). By preparing a JSON configuration, you can enable the launch of applications across multiple devices in a distributed environment.

### Setting Up for Application Launch using UCL

To facilitate the distributed launch of an application with UCL, you need to create a JSON configuration file that specifies the details of the application and how it should be executed on the sender and receiver nodes. Here's an example of such a JSON configuration named `app.json`:
```
{
    "format_v1": {
        "command_type" : "remote_virtio_gpu",
        "appli_name" : "glmark2-es2-wayland",
        "sender" : {
            "launcher" : "agl-host0",
            "command" : "/usr/bin/ucl-virtio-gpu-wl-send",
            "frontend_params" : {
                "scanout_x" : 0,
                "scanout_y" : 0,
                "scanout_w" : 1920,
                "scanout_h" : 1080,
                "server_port" : 33445
            },
            "appli" : "/usr/bin/glmark2-es2-wayland -s 1920x1080",
            "env" : "LD_LIBRARY_PATH=/usr/lib/mesa-virtio"
        },
        "receivers" : [
            {
                "launcher" : "agl-host0",
                "command" : "/usr/bin/ucl-virtio-gpu-wl-recv",
                "backend_params" : {
                    "ivi_surface_id" : 101000,
                    "scanout_x" : 0,
                    "scanout_y" : 0,
                    "scanout_w" : 1920,
                    "scanout_h" : 1080,
                    "listen_port" : 33445,
                    "initial_screen_color" : "0x33333333"
                },
                "env" : "XDG_RUNTIME_DIR=/run/user/200 WAYLAND_DISPLAY=wayland-1"
            },
            {
                "launcher" : "agl-host1",
                "command" : "/usr/bin/ucl-virtio-gpu-wl-recv",
                "backend_params" : {
                    "ivi_surface_id" : 101000,
                    "scanout_x" : 0,
                    "scanout_y" : 0,
                    "scanout_w" : 1920,
                    "scanout_h" : 1080,
                    "listen_port" : 33445,
                    "initial_screen_color" : "0x33333333"
                },
                "env" : "XDG_RUNTIME_DIR=/run/user/200 WAYLAND_DISPLAY=wayland-1"
            }
        ]
    }
}
```

In this example, the application glmark2-es2-wayland is configured to launch on the sender node `agl-host0` and display its output on the receiver nodes `agl-host0` and `agl-host1`. The `scanout_x`, `scanout_y`, `scanout_w`, `scanout_h` parameters define the size of the window, while `server_port` and `listen_port` ensure the communication between sender and receivers.

### Launching the Application

Once the JSON configuration file is ready, you can execute the application across the distributed system by piping the JSON content to the `ucl-distrib-com` command along with the path to the `virtual-screen-def.json` file:

```
cat app.json | ucl-distrib-com /etc/uhmi-framework/virtual-screen-def.json
```

This command will read the configuration and initiate the application launch process, distributing the workload as defined in the JSON file.


Please ensure that the JSON configuration file you create (`app.json` in the example) is correctly formatted and contains the appropriate parameters for your specific use case.

**Note**: Please be aware that when using ivi-shell, applications will not be displayed unless layout configuration is specified as described later in this document. If you wish to display applications without specific layout configuration, you should run weston with desktop-shell. This distinction is crucial to ensure that your applications are visible in your chosen environment.

## How to use ULA (Unified Layout) Framework

The Unified Layout (ULA) Framework allows for the definition of physical displays on a virtual screen and provides the ability to apply layout settings such as position and size to applications launched using the UCL Framework.

### Creating a Layout Configuration File

To define the layout for your applications, you need to create a JSON file, such as `initial_vscreen.json`, with the necessary configuration details. This file will contain the layout settings that specify how applications should be positioned and sized within the virtual screen. Here is an example of what the contents of `initial_vscreen.json` file might look like:

```
{
  "command": "initial_vscreen",
  "vlayer": [
    {
      "VID": 1010000,
      "coord": "global",
      "virtual_w": 1920, "virtual_h": 1080,
      "vsrc_x": 0, "vsrc_y": 0, "vsrc_w": 1920, "vsrc_h": 1080,
      "vdst_x": 960, "vdst_y": 0, "vdst_w": 1920, "vdst_h": 1080,
      "vsurface": [
        {
          "VID": 101000,
          "pixel_w": 1920, "pixel_h": 1080,
          "psrc_x": 0, "psrc_y": 0, "psrc_w": 1920, "psrc_h": 1080,
          "vdst_x": 0, "vdst_y": 0, "vdst_w": 1920, "vdst_h": 1080
        }
      ]
    }
  ]
}
```

In this example, the application is renderd across the right half of the agl-host0 and the left half of agl-host1 display.

In this configuration:

- **vlayer** defines a virtual layer that represents a group of surfaces within the virtual screen. Each layer has a unique Virtual ID (VID) and can contain multiple surfaces. The layer's source (`vsrc_x`, `vsrc_y`, `vsrc_w`, `vsrc_h`) and destination (`vdst_x`, `vdst_y`, `vdst_w`, `vdst_h`) coordinates determine where and how large the layer appears on the virtual screen.

- **vsurface**  defines individual surfaces within the virtual layer. Each surface also has a VID, and its pixel dimensions (`pixel_w`, `pixel_h`) represent the actual size of the content. The source (`psrc_x`, `psrc_y`, `psrc_w`, `psrc_h`) and destination (`vdst_x`, `vdst_y`, `vdst_w`, `vdst_h`) coordinates determine the portion of the content to display and its location within the layer.

### Applying the Layout Configuration

Once you have created the `initial_vscreen.json` file with your layout configuration, you can apply it to your system using the following command:

```
cat initial_vscreen.json | ula-distrib-com
```

Executing this command will process the configuration from the JSON file and apply the layout settings to the virtual screen. As a result, the applications will appear in the specified positions and sizes according to the layout defined in the file.

Ensure that `initial_vscreen.json` file you create accurately reflects the desired layout for your applications and display setup.

**Note**: Due to the specifications of ivi_shell, when an application is stopped after being displayed, the image that was shown just before stopping remains on the screen. To avoid this, please eigther restart weston or display another image on ivi_shell to update the displayed content.