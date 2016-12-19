package com.hudq.demo;

import android.app.Activity;
import android.os.Bundle;

import com.hudq.postlogger.LogerEngine;
import com.hudq.postlogger.intern.ExtraParams;
import com.hudq.postlogger.intern.LogLevel;
import com.hudq.postlogger.intern.LogerHelp;
import com.hudq.postlogger.intern._LogParams;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogerEngine.debug(this, "MainUI coming...", null);
        LogerEngine.error(this, "exception...", false, null);

        ExtraParams extra = new ExtraParams();
        extra.methodName = LogerHelp.getMethodName();
        LogerEngine.info(this, "first coming...", false, extra);

        //自定义
        _LogParams params = new _LogParams.Builder()
                .setContext(this)
                .setContent("MainUI coming...")
                .setAppName("demo")
                .setLevel(LogLevel.D)
                .setHasOSParams(false) //设置是否提交系统设备参数
                .setExtraParams(null)//设置提交的非必传的自定义参数
                .create();
        LogerEngine.log(params);
    }
}
