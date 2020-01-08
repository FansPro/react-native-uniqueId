
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

     private ReactApplicationContext Context;
     private Promise promise;
     private static final String CACHE_DEVICES_DIR = "com.android.sec/devices";
            //保存的文件 采用隐藏文件的形式进行保存
     private static final String DEVICES_FILE_NAME = ".DEVICES";

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
            String guid = getDeviceId(Context);
            callback.invoke(guid);
        } catch (Exception ex) {
            ex.printStackTrace();
            callback.invoke("");
        }
    }

    public static String getDeviceId(Context context) {
        //读取保存的在sd卡中的唯一标识符
        String deviceId = readDeviceID(context);

        //判断是否已经生成过,
        if (deviceId != null && !"".equals(deviceId)) {
            return deviceId;
        }
        UUID uuid = UUID.randomUUID();
        deviceId = uuid.toString().replace("-", "");
        return saveDeviceID(deviceId, context);
    }

    /**
      * 读取固定的文件中的内容,这里就是读取sd卡中保存的设备唯一标识符
      *
      * @param context
      * @return
      */
    private static String readDeviceID(Context context) {
        File file = getDevicesDir(context);
        StringBuffer buffer = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            Reader in = new BufferedReader(isr);
            int i;
            while ((i = in.read()) > -1) {
                buffer.append((char) i);
            }
            in.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

        /**
         * 保存 内容到 SD卡中,  这里保存的就是 设备唯一标识符
         *
         * @param str
         * @param context
         */
        private static String saveDeviceID(String str, Context context) {
            File file = getDevicesDir(context);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                Writer out = new OutputStreamWriter(fos, "UTF-8");
                out.write(str);
                out.close();
                return str;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }

        /**
         * 对挺特定的 内容进行 md5 加密
         *
         * @param message   加密明文
         * @param upperCase 加密以后的字符串是是大写还是小写  true 大写  false 小写
         * @return
         */
        private static String getMD5(String message, boolean upperCase) {
            String md5str = "";
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");

                byte[] input = message.getBytes();

                byte[] buff = md.digest(input);

                md5str = bytesToHex(buff, upperCase);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return md5str;
        }


        private static String bytesToHex(byte[] bytes, boolean upperCase) {
            StringBuffer md5str = new StringBuffer();
            int digital;
            for (int i = 0; i < bytes.length; i++) {
                digital = bytes[i];

                if (digital < 0) {
                    digital += 256;
                }
                if (digital < 16) {
                    md5str.append("0");
                }
                md5str.append(Integer.toHexString(digital));
            }
            if (upperCase) {
                return md5str.toString().toUpperCase();
            }
            return md5str.toString().toLowerCase();
        }

        /**
         * 统一处理设备唯一标识 保存的文件的地址
         *
         * @param context
         * @return
         */
        private static File getDevicesDir(Context context) {
            File mCropFile = null;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File cropdir = new File(Environment.getExternalStorageDirectory(), CACHE_DEVICES_DIR);
                if (!cropdir.exists()) {
                    cropdir.mkdirs();
                }
                mCropFile = new File(cropdir, DEVICES_FILE_NAME);
            } else {
                File cropdir = new File(context.getFilesDir(), CACHE_DEVICES_DIR);
                if (!cropdir.exists()) {
                    cropdir.mkdirs();
                }
                mCropFile = new File(cropdir, DEVICES_FILE_NAME);
            }
            return mCropFile;
        }

}
