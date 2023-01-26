# TFlite-FTPcamera
Android Kotlin and Java codes object detection on phone Camera using tflite interpreter, And take FTP action on human detection (Send detected person image to FTP server).

It is most simple and up to date AI network Android project after `https://github.com/marzban2030/TFLiteObjectDetection` project which has few codes to understanding and developing. Just clone the repository and change everything easily that you want.

More advanced higher accuracy app with less codes and components is in below repository:

https://github.com/marzban2030/TFLiteObjectDetection

More advanced higher accuracy app with more codes and components is in below repository:

https://github.com/marzban2030/Camera-tflite-FTP

# Build

Run below commands in Colab:
```
!wget https://dl.google.com/android/repository/commandlinetools-linux-9123335_latest.zip
!mkdir -p sdk
!unzip commandlinetools-linux-9123335_latest.zip -d sdk
!yes | ./sdk/cmdline-tools/bin/sdkmanager --sdk_root=/content/sdk "tools"
!git clone https://github.com/marzban2030/TFlite-FTPcamera
!chmod -c 755 /content/TFlite-FTPcamera/gradlew
!export ANDROID_HOME=/content/sdk && cd /content/TFlite-FTPcamera && ./gradlew assembleDebug
from google.colab import files
files.download('/content/TFlite-FTPcamera/tflite/build/outputs/apk/debug/tflite-debug.apk')
```

# Usage

Change FTP Host, Username and Password in "CameraActivity.kt" file, Also change detection case according to tflite labels which you want take an action on this.
