# Flyme Link LSPosed helper

This is a self-contained LSPosed/Xposed module that replaces the `add_new_device` string in the target app (`com.flyme.linkUnion`) so the UI shows “测试” instead of “添加新设备”，并拦截点击后仅弹出“测试一下”Toast，不再跳转新页面。

## Build (standalone inside `lsp/`)
1. Open the `lsp` folder in Android Studio.
2. Sync Gradle (uses the Android Gradle Plugin 8.10.1 and SDK 33).
3. Build the APK. It produces an LSPosed/Xposed module APK you can install.

## Deploy
1. Install the built APK on a device with LSPosed installed.
2. In LSPosed, enable the module and scope it to `com.flyme.linkUnion`.
3. Force-stop and reopen the target app; the label should now display “测试”.

## Notes
- `lsp/settings.gradle` declares plugin resolution and repositories, so the project can be built standalone without the parent project.
- Dependency on the Xposed API is declared as `compileOnly` and resolved from `https://api.xposed.info/`.
