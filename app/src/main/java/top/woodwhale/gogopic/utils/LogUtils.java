package top.woodwhale.gogopic.utils;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class LogUtils {
    public static int currentLevel = 4;
    private static final int DEBUG_LEVEL = 4;
    private static final int INFO_LEVEL = 3;
    private static final int WARNING_LEVEL = 2;
    private static final int ERROR_LEVEL = 1;

    public static void d(Object curObject, String log) {
        if (currentLevel >= DEBUG_LEVEL) {
            Log.d(new SimpleDateFormat("HH:mm:ss ").format(new Date())+
                    curObject.getClass().getSimpleName(),log);
        }
    }

    public static void i(Object curObject, String log) {
        if (currentLevel >= INFO_LEVEL) {
            Log.i(new SimpleDateFormat("HH:mm:ss ").format(new Date())+
                    curObject.getClass().getSimpleName(),log);
        }
    }

    public static void w(Object curObject, String log) {
        if (currentLevel >= WARNING_LEVEL) {
            Log.w(new SimpleDateFormat("HH:mm:ss ").format(new Date())+
                    curObject.getClass().getSimpleName(),log);
        }
    }

    public static void e(Object curObject, String log) {
        if (currentLevel >= ERROR_LEVEL) {
            Log.e(new SimpleDateFormat("HH:mm:ss ").format(new Date())+
                    curObject.getClass().getSimpleName(),log);
        }
    }
}
