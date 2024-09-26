# DRM lease support for Qt 6.x

The meta-agl-drm-lease/dynamic-layers/meta-qt6 is a DRM lease support for Qt 6.x eglfs kms backend.

This patch tested in Qt6.7.

## Usage

Qt application shall configured to use eglfs/kms backend.

In addition, there are two environment variable shall be configured.

### QT_QPA_EGLFS_DRMLEASE

When this variable is set, drm-lease-manager client support is enable.  
Shall set drm lease device such as  `card0-LVDS-1`, `card0-HDMI-A-1 ` and etc.  

#### Example

```
  $ export QT_QPA_EGLFS_DRMLEASE=card0-HDMI-A-1
```

### DLM_RUNTIME_PATH

DLM_RUNTIME_PATH is required from libdlmclient.  This environment variable shall be set.

#### Example

```
  $ export DLM_RUNTIME_PATH=/var/display/drm-lease-manager
```

