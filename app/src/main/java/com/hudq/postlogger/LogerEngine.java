package com.hudq.postlogger;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.hudq.postlogger.intern.ExtraParams;
import com.hudq.postlogger.intern.LogLevel;
import com.hudq.postlogger.intern._LogHttp;
import com.hudq.postlogger.intern._LogParams;

/**
 * 日志提交发送控制类<br/>
 * fatal <br/>
 * error <br/>
 * warn <br/>
 * info <br/>
 * debug <br/>
 * log: 自定义
 * <p>
 * Created by hudq on 2016/11/24.
 */
public class LogerEngine {

    /**
     * fatal,默认提交设备参数
     *
     * @param context 上下文环境
     * @param content 日志内容
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void fatal(Context context, String content,
                             ExtraParams extra) {
        fatal(context, content, true, extra);
    }

    /**
     * fatal日志
     *
     * @param context 上下文
     * @param content 日志内容
     * @param hasOS   是否提交设备参数
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void fatal(Context context, String content, boolean hasOS,
                             ExtraParams extra) {
        _LogParams params = new _LogParams.Builder()
                .setContext(context)
                .setContent(content)
                .setAppName(getAppName(context))
                .setLevel(LogLevel.F)
                .setHasOSParams(hasOS) //设置是否提交系统设备参数
                .setExtraParams(extra)//设置是否提交非必传的参数
                .create();
        log(params);
    }

    /**
     * error,默认提交设备参数
     *
     * @param context 上下文环境
     * @param content 日志内容
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void error(Context context, String content,
                             ExtraParams extra) {
        error(context, content, true, extra);
    }

    /**
     * error日志
     *
     * @param context 上下文
     * @param content 日志内容
     * @param hasOS   是否提交设备参数
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void error(Context context, String content, boolean hasOS,
                             ExtraParams extra) {
        _LogParams params = new _LogParams.Builder()
                .setContext(context)
                .setContent(content)
                .setAppName(getAppName(context))
                .setLevel(LogLevel.E)
                .setHasOSParams(hasOS) //设置是否提交系统设备参数
                .setExtraParams(extra)//设置是否提交非必传的参数
                .create();
        log(params);
    }

    /**
     * warn,默认提交设备参数
     *
     * @param context 上下文环境
     * @param content 日志内容
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void warn(Context context, String content,
                            ExtraParams extra) {
        warn(context, content, true, extra);
    }

    /**
     * warn日志
     *
     * @param context 上下文
     * @param content 日志内容
     * @param hasOS   是否提交设备参数
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void warn(Context context, String content, boolean hasOS,
                            ExtraParams extra) {
        _LogParams params = new _LogParams.Builder()
                .setContext(context)
                .setContent(content)
                .setAppName(getAppName(context))
                .setLevel(LogLevel.W)
                .setHasOSParams(hasOS) //设置是否提交系统设备参数
                .setExtraParams(extra)//设置是否提交非必传的参数
                .create();
        log(params);
    }

    /**
     * info，默认提交设备参数
     *
     * @param context 上下文环境
     * @param content 日志内容
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void info(Context context, String content,
                            ExtraParams extra) {
        info(context, content, true, extra);
    }

    /**
     * info
     *
     * @param context 上下文
     * @param content 日志内容
     * @param hasOS   是否提交设备参数
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void info(Context context, String content, boolean hasOS,
                            ExtraParams extra) {
        _LogParams params = new _LogParams.Builder()
                .setContext(context)
                .setContent(content)
                .setAppName(getAppName(context))
                .setLevel(LogLevel.I)
                .setHasOSParams(hasOS) //设置是否提交系统设备参数
                .setExtraParams(extra)//设置是否提交非必传的参数
                .create();
        log(params);
    }

    /**
     * debug，默认提交设备参数
     *
     * @param context 上下文环境
     * @param content 日志内容
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void debug(Context context, String content,
                             ExtraParams extra) {
        debug(context, content, true, extra);
    }

    /**
     * debug
     *
     * @param context 上下文
     * @param content 日志内容
     * @param hasOS   是否提交设备参数
     * @param extra   提交的非必须的参数obj，默认或者设置null则不提交
     */
    public static void debug(Context context, String content, boolean hasOS,
                             ExtraParams extra) {
        _LogParams params = new _LogParams.Builder()
                .setContext(context)
                .setContent(content)
                .setAppName(getAppName(context))
                .setLevel(LogLevel.D)
                .setHasOSParams(hasOS) //设置是否提交系统设备参数
                .setExtraParams(extra)//设置是否提交非必传的参数
                .create();
        log(params);
    }

    /**
     * 日志自定义请求。
     * <p>
     * 调用此方法需要设置_LogParams,参数自定义
     *
     * @param params 参数
     */
    public static void log(_LogParams params) {
        _LogHttp.create().post(params);
    }

    /**
     * shutdownNow ThreadPool.call shutdownNow() from ExecutorService
     */
    public static void destory() {
        _LogHttp.create().shutdownNow();
    }

    /**
     * 根据包名packageName获取设置的App名
     *
     * @param context 上下文
     * @return String
     */
    private static String getAppName(Context context) {
        context = context.getApplicationContext();
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
            return packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "UnKnow";
    }
}
