package com.chinaotec.tv.otectv.util;

import android.text.TextUtils;

/**
 * Created by linhao on 2016/9/26.
 */
public final class Logger {

    private static final String TAG = "OtecTV_";

    /**
     * 设置日志开关；
     */
    private static boolean logEnabled_d = true;
    private static boolean logEnabled_i = true;
    private static boolean logEnabled_e = true;

    /**
     * 此方法为debug用于打印调试测试信息；
     */
    public static void d() {
        if (logEnabled_d) {
            android.util.Log.d(TAG, getLocation());
        }
    }

    public static void d(String msg) {
        if (logEnabled_d) {
            android.util.Log.d(TAG, getLocation() + msg);
        }
    }

    /**
     * 此方法为info用于打印log中需要展示的信息，便于记录、查看；
     *
     * @param msg
     */
    public static void i(String msg) {
        if (logEnabled_i) {
            android.util.Log.i(TAG, getLocation() + msg);
        }
    }

    public static void i() {
        if (logEnabled_i) {
            android.util.Log.i(TAG, getLocation());
        }
    }

    /**
     * 此方法为error用于打印log的错误信息；
     * @param msg
     */
    public static void e(String msg) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation() + msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation() + msg, e);
        }
    }

    public static void e(Throwable e) {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation(), e);
        }
    }

    public static void e() {
        if (logEnabled_e) {
            android.util.Log.e(TAG, getLocation());
        }
    }

    private static String getLocation() {
        final String className = Logger.class.getName();
        final StackTraceElement[] traces = Thread.currentThread()
                .getStackTrace();

        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + getClassName(clazz) + ":"
                                + trace.getMethodName() + ":"
                                + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }

        return "[]: ";
    }

    private static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }

            return getClassName(clazz.getEnclosingClass());
        }

        return "";
    }

}