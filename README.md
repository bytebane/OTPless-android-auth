# OTPless Auth

An Android implementation of OTPless Authentication with Kotlin.

## Getting Started

This project is a starting point for OTPless Integration with Kotlin Android.

### OTPless Integration Points

- Add ```implementation 'io.github.otpless-tech:otpless-android-sdk:2.1.8'``` in your [build.gradle:app](./app/build.gradle) (**Line** *60*)
- [Android Manifest](/app/src/main/AndroidManifest.xml) (**Lines** *17,18 & 21-28*) for your `SignIn/SignUp` Activity
- [Authenticate.kt](/app/src/main/java/com/bytebane/otpless/Authenticate.kt)
- ***Thats it***. Add your own logic in `onOtplessCallback()` to deal with the authenticated user data or error data in response.

> Note: OTPless Auth Page won't work unless you whitelist your App by adding App's BundleId in [OTPless dashboard](https://otpless.com/platforms/flutter).
