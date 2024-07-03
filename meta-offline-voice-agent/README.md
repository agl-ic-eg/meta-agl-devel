# Offline Speech Recognition and Command Execution
The `meta-offline-voice-agent` is an AGL Layer that enables Offline Speech Recognition and Command Execution capabilities for Automotive Grade Linux.

## Table of Contents
- [Introduction](#introduction)
- [Layer Status](#layer-status)
- [Working Features](#working-features)
- [Testing Features on AGL](#testing-features-on-agl)
    - [Build Layer](#build-layer)
    - [Test Vosk](#test-vosk)
    - [Test Snips](#test-snips)
    - [Test RASA](#test-rasa)
- [Supported Targets](#supported-targets)
- [Maintainers](#maintainers)

## Introduction
The `meta-offline-voice-agent` layer integrates the Vosk API, Snips (Inference Only), and RASA to provide offline speech recognition and command execution for Automotive Grade Linux. The layer is based on the Kaldi ASR Toolkit, which allows for accurate and efficient speech recognition in the AGL platform, Snips which provides us with a lightweight Natural Language Intent Engine, and RASA a complete end-to-end ML framework for developing and creating chatbots and voice assistants.

## Layer Status
**Status**: *WIP (Work In Progress)*

This layer is currently in development and integrates the Vosk, Snips (Inference Only), and RASA libraries. Speech recognition has been verified using test scripts from the [vosk-api python examples](https://github.com/alphacep/vosk-api/tree/master/python/example). Additionally, both Snips and RASA have undergone testing and verification to ensure their functionality. Currently, work on command execution is still in progress.

## External Dependencies
This layer depends on the following external layers:
- [meta-tensorflow](https://git.yoctoproject.org/meta-tensorflow)
- [meta-python-ai](https://github.com/zboszor/meta-python-ai/tree/scarthgap)

## Working Features
The following features are currently working in the `meta-offline-voice-agent` layer:
- [Vosk API (Python)](https://github.com/alphacep/vosk-api/tree/master/python)
- [Vosk Websocket Server](https://github.com/alphacep/vosk-server/tree/master/websocket)
- [Snips Inference](https://github.com/malik727/snips-inference-agl)
- [RASA](https://github.com/RasaHQ/rasa)

## Testing Features on AGL
    
### Build Layer
In order to test the features of this layer you first need to build it as part of your final AGL image. First of all ensure that you have all the external layer dependencies included. Then you can use the following set of commands to initialize and build this layer into the `agl-demo-platform` qemux86_64 image:
```shell
$ source master/meta-agl/scripts/aglsetup.sh -m qemux86-64 -b build-master agl-demo agl-devel agl-offline-voice-agent
$ source agl-init-build-env
$ bitbake agl-demo-platform
```

The build can take anywhere from 6 hours to 24 hours or even more depending upon compute power of your machine. After the build completes you can use the following command to boot into your AGL image: (you need to install QEMU if not already for the command to work)
```shell
$ runqemu tmp/deploy/images/qemux86-64/agl-demo-platform-qemux86-64.qemuboot.conf kvm serialstdio slirp publicvnc audio
```

### Test Vosk
(**Not Recommended**) The simplest way to test Vosk API is by using the following command: 
```shell
$ ptest-runner python3-vosk-api
```

In order for the above command to work you need to turn on `ptests` by adding the following lines to your `local.conf` that can be found at `meta-agl-devel/templates/feature/agl-offline-voice-agent/50_local.conf.inc`:
```shell
DISTRO_FEATURES:append = " ptest"
EXTRA_IMAGE_FEATURES += "ptest-pkgs"
```

The above method may be the easiest one but it's not recommended because `ptests` increase the image build times by a substantial amount. You can look into the official [vosk-api docs](https://alphacephei.com/vosk/install) for usage and other ways of testing.

### Test Snips
In order to test the Snips NLU Intent Engine you can use the sample [pre-trained model](https://github.com/malik727/snips-model-agl), by default it automatically gets built into the target image when you include this layer. To perform inference using this model you can run the following command inside your target image:
```shell
$ snips-inference parse /usr/share/nlu/snips/model/ -q "your command here"
```

This is just a sample model and may not be able to handle all types of commands. You can always train your own intent engine model using your custom dataset, for more details on how to do that you can look into the README files of [snips-sdk-agl](https://github.com/malik727/snips-sdk-agl), [snips-model-agl](https://github.com/malik727/snips-model-agl), and [snips-inference-agl](https://github.com/malik727/snips-inference-agl).

### Test RASA
In order to test the RASA NLU Intent Engine you can use the sample [pre-trained model](https://github.com/malik727/rasa-model-agl), by default it automatically gets built into the target image when you include this layer. To perform inference using this model you can run the following command inside your target image:
```shell
$ rasa shell -m /usr/share/nlu/rasa/models/
```

This will open an interactive shell where you can issue commands and get related results. This is just a sample model and may not be able to handle all types of commands. You can always train your own RASA intent engine model using your custom dataset by following the official [RASA docs](https://rasa.com/docs/rasa/).

## Supported Targets
Currently, the following targets are fully supported:
- QEMU x86-64 (work in progress)

## Maintainers
- Anuj Solanki <anuj603362@gmail.com>
- Malik Talha <talhamalik727x@gmail.com>
- Aman Arora <aman.arora9848@gmail.com>

