
package com.reactlibrary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;
public class RNUniqueIdPackage implements ReactPackage {
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      return Arrays.<NativeModule>asList(new RNUniqueIdModule(reactContext));
    }

    // Deprecated from RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
      return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Collections.emptyList();
    }

    @ReactMethod
        public void getUniqueId(Callback callback) {
            try {
                String dev = "3883756" +
                        Build.BOARD.length() % 10 +
                        Build.BRAND.length() % 10 +
                        Build.DEVICE.length() % 10 +
                        Build.HARDWARE.length() % 10 +
                        Build.ID.length() % 10 +
                        Build.MODEL.length() % 10 +
                        Build.PRODUCT.length() % 10 +
                        Build.SERIAL.length() % 10;
                String uuid = new UUID(dev.hashCode(),
                        Build.SERIAL.hashCode()).toString();
                callback.invoke(uuid);
            } catch (Exception ex) {
                ex.printStackTrace();
                callback.invoke("02385FFE906944189BA2CC63DFB08700");
            }
        }
}