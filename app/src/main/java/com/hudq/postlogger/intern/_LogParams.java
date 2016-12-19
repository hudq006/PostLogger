package com.hudq.postlogger.intern;

import android.content.Context;
import android.os.Build;

/**
 * 日志所传参数（必传参数，系统设备参数，额外扩展参数）
 * Created by hudq on 2016/11/24.
 */

public class _LogParams {
    private String level;                       //必传，日志等级
    private String application_name;            //必传，应用名
    private String content;                     //必传，内容
    private String sdk_version;                 //必传，SDK版本
    private String system;                      //必传，系统

    private _OSParams osParams;
    private ExtraParams extraParams;

    private _LogParams(Builder builder) {
        this.level = builder.level;
        this.application_name = builder.application_name;
        this.content = builder.content;
        this.sdk_version = Build.VERSION.SDK_INT + "";
        this.system = "Android";
        this.extraParams = builder.extraParams;

        if (builder.hasOS) {
            _OSParams _osParams = new _OSParams(builder.context);
            this.osParams = _osParams;
        }
    }

    public _OSParams getOsParams() {
        return osParams;
    }

    public ExtraParams getExtraParams() {
        return extraParams;
    }

    public static class Builder {
        private Context context;
        private String level;                       //必传，日志等级
        private String application_name;            //必传，应用名
        private String content;                     //必传，内容

        private boolean hasOS;
        private ExtraParams extraParams;

        public Builder() {
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        /**
         * 参数
         *
         * @param level log等级
         * @return _LogParams.Builder
         */
        public Builder setLevel(LogLevel level) {
            this.level = level.toString();
            return this;
        }

        /**
         * 参数
         *
         * @param application_name 应用名称
         * @return _LogParams.Builder
         */
        public Builder setAppName(String application_name) {
            this.application_name = application_name;
            return this;
        }

        /**
         * 参数（必传）
         *
         * @param content log内容
         * @return _LogParams.Builder
         */
        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        /**
         * 是否传递系统设备的参数信息
         *
         * @param hasOS true 传递，反之false,默认值false
         * @return _LogParams.Builder
         */
        public Builder setHasOSParams(boolean hasOS) {
            this.hasOS = hasOS;
            return this;
        }

        public Builder setExtraParams(ExtraParams extraParams) {
            this.extraParams = extraParams;
            return this;
        }

        public _LogParams create() {
            return new _LogParams(this);
        }
    }
}
