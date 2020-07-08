package me.windleafy.kity.android.bug;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BugKit {

    private BugKit() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void crash() {
        String str = null;
        Log.d("bug", Integer.valueOf(str) + "");
    }

    /**
     * How to handle :java.util.concurrent.TimeoutException: android.os.BinderProxy.finalize() timed out after 10 seconds errors?
     * <p>
     * https://stackoverflow.com/questions/24021609/how-to-handle-java-util-concurrent-timeoutexception-android-os-binderproxy-fin
     *
     * https://www.sohu.com/a/283393871_611601
     */
    public static void fixTimeoutExceptions() {
        try {
            Class clazz = Class.forName("java.lang.Daemons$FinalizerWatchdogDaemon");

            Method method = clazz.getSuperclass().getDeclaredMethod("stop");
            method.setAccessible(true);

            Field field = clazz.getDeclaredField("INSTANCE");
            field.setAccessible(true);

            method.invoke(field.get(null));

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
