package ru.kizup.boilerplate.presentation.utils;

import android.util.Log;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public final class LogUtil {

    private static String sTag = "Boilerplate";
    private static boolean isDebug = true;

    public static void setIsDebug(boolean debug) {
        isDebug = debug;
    }

    public static void setTag(String tag) {
        sTag = tag;
    }

    public static void debug(String message) {
        Log.d(sTag, message);
    }

    public static void exception(Throwable throwable) {
//        Crashlytics.logException(throwable);
        localException(throwable);
    }

    public static void localException(Throwable throwable) {
        if (isDebug) {
            Log.e(sTag, "", throwable);
        }
    }

}
