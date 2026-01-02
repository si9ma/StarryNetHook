package com.si9ma.starrynethook;

import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Replace the "add_new_device" string in the target app so "添加新设备" shows as
 * "测试",
 * and override its click action to just show a toast instead of opening a new
 * screen.
 */
public class StarrynetHook implements IXposedHookLoadPackage {
    private static final String TARGET_PACKAGE = "com.upuphone.starrynet";
    private static final String TAG = "StarrynetHook";


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!TARGET_PACKAGE.equals(lpparam.packageName)) {
            log("handleLoadPackage: skip non-target package " + lpparam.packageName);
            return; // avoid ClassNotFound when other apps load
        }

        log("handleLoadPackage: attaching hooks for target package");

        try {
            Class<?> clsK = lpparam.classLoader.loadClass("ag.k");
            XposedHelpers.findAndHookMethod(clsK, "d", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) {
                    Object result = param.getResult();
                    if (result instanceof List) {
                        List list = (List) result;
                        if (!list.contains("com.luna.music")) {
                            list.add("com.luna.music");
                            XposedBridge.log("Added com.luna.music to whiteMusicApps");
                        }
                    }
                }
            });
        } catch (Throwable t) {
            XposedBridge.log("Hook failed: " + t);
        }
    }

    private void log(String message) {
        XposedBridge.log(TAG + ": " + message);
    }

    private void logError(String message, Throwable t) {
        XposedBridge.log(TAG + " ERROR: " + message);
        XposedBridge.log(t);
    }
}
