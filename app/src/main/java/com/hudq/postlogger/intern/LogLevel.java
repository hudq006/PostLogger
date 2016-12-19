package com.hudq.postlogger.intern;

/**
 * 日志等级
 * Created by hudq on 2016/11/24.
 */
public enum LogLevel {

    /**
     * fatal
     */
    F("FATAL"),
    /**
     * error
     */
    E("ERROR"),
    /**
     * warning
     */
    W("WARN"),
    /**
     * info
     */
    I("INFO"),
    /**
     * debug
     */
    D("DEBUG");

    private String level;

    LogLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }
}
