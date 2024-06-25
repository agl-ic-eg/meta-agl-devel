## Introduction

The `meta-agl-devel` layer contains components that are being tested or
still in development.
The layer also contains software packages that Original Equipment
Manufacturers (OEMs) need but are not included in the AGL software.

## Sub-Layers

The `meta-agl-devel` layer contains the following files and sub-layers:

```
.
├── docs
├── LICENSE
├── LICENSE.GPL-2.0-only
├── LICENSE.MIT
├── meta-agl-devel.md
├── meta-agl-jailhouse
├── meta-agl-lxc
├── meta-agl-ros2
├── meta-oem-production-readiness
├── meta-speech-framework
├── README.md
└── templates
```

The following list provides a summary of these sub-layers:

* `meta-speech-framework`: Provides libraries and software packages needed by
  for speech recognition.

* `meta-agl-jailhouse`: Provides Jailhouse partitioning hypervisor and
  supporting packages.

* `meta-agl-lxc`: Provides LXC container support and example images.

* `meta-agl-ros2`: Provides ROS2 and additional YDLIDAR support.

* `meta-oem-production-readiness`: Provides libraries and software packages of
  IVI product readiness

* `templates`: Feature templates that support the `meta-agl-devel` layer.

