<!-- Insert more Badges here -->
[![Kotlin](https://img.shields.io/badge/kotlin-2.1.10-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Release](https://img.shields.io/github/v/release/dkluske/dekay)](https://github.com/dkluske/dekay/releases)

<div align="center">

  <picture>
    <img alt="dekay logo" src="/statics/dekay_pill.png">
  </picture>

</div>

# Welcome to `dekay`!

`dekay` is an open source Kotlin Multiplatform application for various use cases in the normal everyday life, especially helping you live a healthy life.
The general purpose of this project is to become independent on paid subscriptions for mobile apps, that basically give you statistics about your health status and habits. 

## Key features

With `dekay` you will be able to track your:
- health statistics with some neat charts and raw data
- habits for your everyday life
- meals and calories
- workouts

## Tech Stack

| Tech                  | Description                                                    |
|-----------------------|----------------------------------------------------------------|
| Kotlin                | The programming language.                                      |
| Compose Multiplatform | A framework for kotlin multiplatform based on Jetpack Compose. |
| SQLDelight            | A multiplatform framework for database handling.               |
| Compose Charts        | A compose multiplatform framework for displaying nice graphs.  |

## Usage

To deploy the app on your phone, you will need to download the specific app-file for your operating system from the latest actions workflow.

After you obtained the file, you will have to proceed with the individual steps, that are needed to deploy an application to your operating system.

**Note:** This procedure is required, because this project has no independent - project specific - organization currently. Therefore there are no credentials for a general deployment available currently. Might change in the future.

### iOS

- Sign the built `.ipa` file with your AppleId.
- Deploy the signed file to an app distribution service of your desire.
  - For Example: Google Firebase App Distribution
- Download and install the app from the distribution on your iPhone.

### Android

- Download the `.apk` file on your android device.
- Install the application by executing the `.apk` file on the device. 

### IDE

- Open this project in your favourable IDE like Android Studio or Intellij IDEA.
- Run the JVM implementation for testing by executing the following command as a gradle run
  configuration:

```shell
  jvmRun -DmainClass=io.dkluske.dekay.MainKt -q
```

- Or directly in console:

```shell
  ./gradlew jvmRun -DmainClass=io.dkluske.dekay.MainKt -q
```

## Authors

- Dominik Kluske - [@dkluske](https://github.com/dkluske) - 2025
