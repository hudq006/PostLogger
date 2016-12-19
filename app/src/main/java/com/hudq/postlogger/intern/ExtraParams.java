package com.hudq.postlogger.intern;

/**
 * 额外扩展字段，非必须，可自定义设置
 * <p>
 * 使用：<br/>
 * ExtraParams params = new ExtraParams();<br/>
 * params.methodName = “login”;<br/>
 * params.lineNumber = 21;<br/>
 * ......
 * <p>
 * Created by hudq on 2016/11/24.
 */
public class ExtraParams {

    public String fileName;     //文件名称
    public String methodName;   //方法名称
    public int lineNumber;      //行数

    public ExtraParams() {
    }

}
