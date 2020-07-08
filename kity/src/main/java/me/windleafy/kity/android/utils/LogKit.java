package me.windleafy.kity.android.utils;

import android.util.Log;

import me.windleafy.kity.android.tool.log.SdLog;
import me.windleafy.kity.java.utils.EncryptKit;

/**
 * Log统一管理类
 */
public class LogKit {

    private static String dir;
    private static String file;

    private LogKit() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isEnable = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化

    private static boolean isLog = true;

    private static boolean isPrint = false;

    private static boolean isEncrypt = false;

    private static boolean isSdLog = false;

    private static String encryptPwd = "abc";

    public static void setIsEnable(boolean isEnable) {
        LogKit.isEnable = isEnable;
    }

    public static void setIsLog(boolean isLog) {
        LogKit.isLog = isLog;
    }

    public static void setIsPrint(boolean isPrint) {
        LogKit.isPrint = isPrint;
    }

    public static void setIsEncrypt(boolean isEncrypt) {
        LogKit.isEncrypt = isEncrypt;
    }

    public static void setIsSdLog(boolean isSdLog) {
        LogKit.isSdLog = isSdLog;
    }

    public static void setEncryptPwd(String encryptPwd) {
        LogKit.encryptPwd = encryptPwd;
    }

    private static String PrintSeparator = " : ";

    private static final String TAG = "LogKit";


    public static void i(String msg) {
        LogKit.i(TAG, msg, isEncrypt);
    }

    public static void d(String msg) {
        LogKit.d(TAG, msg, isEncrypt);
    }

    public static void e(String msg) {
        LogKit.e(TAG, msg, isEncrypt);
    }

    public static void w(String msg) {
        LogKit.w(TAG, msg, isEncrypt);
    }

    public static void v(String msg) {
        LogKit.v(TAG, msg, isEncrypt);
    }


    public static void i(String tag, String msg) {
        LogKit.i(tag, msg, isEncrypt);
    }

    public static void d(String tag, String msg) {
        LogKit.d(tag, msg, isEncrypt);
    }

    public static void e(String tag, String msg) {
        LogKit.e(tag, msg, isEncrypt);
    }

    public static void w(String tag, String msg) {
        LogKit.w(tag, msg, isEncrypt);
    }

    public static void v(String tag, String msg) {
        LogKit.v(tag, msg, isEncrypt);
    }


    public static void i(String msg, boolean encrypt) {
        LogKit.i(TAG, msg, encrypt);
    }

    public static void d(String msg, boolean encrypt) {
        LogKit.d(TAG, msg, encrypt);
    }

    public static void e(String msg, boolean encrypt) {
        LogKit.e(TAG, msg, encrypt);
    }

    public static void w(String msg, boolean encrypt) {
        LogKit.w(TAG, msg, encrypt);
    }

    public static void v(String msg, boolean encrypt) {
        LogKit.v(TAG, msg, encrypt);
    }


    public static void i(String tag, String msg, boolean encrypt) {
        if (isEnable) {
            String message = encrypt ? EncryptKit.encryptBase64(msg) : msg;
            if (isLog) Log.i(tag, message);
            if (isPrint) println(tag, msg);
            if (isSdLog) sdlog(tag, msg);
        }
    }


    public static void d(String tag, String msg, boolean encrypt) {
        if (isEnable) {
            String message = encrypt ? EncryptKit.encryptBase64(msg) : msg;
            if (isLog) Log.d(tag, message);
            if (isPrint) println(tag, message);
            if (isSdLog) sdlog(tag, message);
        }
    }

    public static void e(String tag, String msg, boolean encrypt) {
        if (isEnable) {
            String message = encrypt ? EncryptKit.encryptBase64(msg) : msg;
            if (isLog) Log.e(tag, message);
            if (isPrint) println(tag, message);
            if (isSdLog) sdlog(tag, message);
        }
    }

    public static void w(String tag, String msg, boolean encrypt) {
        if (isEnable) {
            String message = encrypt ? EncryptKit.encryptBase64(msg) : msg;
            if (isLog) Log.w(tag, message);
            if (isPrint) println(tag, message);
            if (isSdLog) sdlog(tag, message);
        }
    }

    public static void v(String tag, String msg, boolean encrypt) {
        if (isEnable) {
            String message = encrypt ? EncryptKit.encryptBase64(msg) : msg;
            if (isLog) Log.v(tag, message);
            if (isPrint) println(tag, message);
            if (isSdLog) sdlog(tag, message);
        }
    }


    private static void println(String tag, String message) {
        System.out.println(tag + PrintSeparator + message);
    }

    private static void sdlog(String tag, String message) {
        SdLog.setLog(true);
        if (file == null)
            file = "jhgk.log";
        SdLog.line(file, tag + " -> " + message, true);
    }

}
