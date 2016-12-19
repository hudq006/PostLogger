package com.hudq.postlogger.intern;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;

/**
 * 日志http管理类
 * Created by hudq on 2016/11/24.
 */

public class _LogHttp {

    private static final String TAG = "_LogHttp";
    //TODO: 设置url
    private static final String sUrl = "http://www.testserver.com.cn/addlog";
    private static final ExecutorService sExecutors = Executors.newCachedThreadPool();

    private _LogHttp() {
    }

    private static class _LogHttpInner {
        private static final _LogHttp sINSTANCE = new _LogHttp();
    }

    public static _LogHttp create() {
        return _LogHttpInner.sINSTANCE;
    }

    /**
     * post请求
     *
     * @param params _LogParams
     */
    public void post(_LogParams params) {
        sExecutors.execute(new Request(params));
    }

    public void shutdownNow() {
        sExecutors.shutdownNow();
    }


    private static class Request extends Thread {
        private _LogParams params;

        private Request(_LogParams params) {
            this.params = params;
        }

        @Override
        public void run() {
            super.run();
            if (isInterrupted()) return;

            doPost(sUrl, params);
        }
    }

    /**
     * post请求request
     *
     * @param url    URL
     * @param params _LogParams
     * @return result String
     */
    private static String doPost(String url, _LogParams params) {
        HttpURLConnection conn = null;
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String mParams = createPostParams(params);
        if (mParams == null) return null;
        try {
            URL mURL = new URL(url);
            conn = (HttpURLConnection) mURL.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("contentType", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("Accept-Encoding", "gzip");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(60 * 1000); //设置从主机读取数据超时（单位：毫秒）
            conn.setConnectTimeout(60 * 1000); //设置连接主机超时（单位：毫秒）

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream());
            // 发送请求参数
            out.write(mParams);
            // flush输出流的缓冲
            out.flush();
            //得到服务器相应
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream ism = conn.getInputStream();
                String encoding = conn.getContentEncoding();
                if (encoding != null && encoding.contains("gzip")) {
                    //首先判断服务器返回的数据是否支持gzip压缩，如果支持则应该使用GZIPInputStream解压，否则会出现乱码无效数据
                    ism = new GZIPInputStream(conn.getInputStream());
                }
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(ism));
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = in.readLine()) != null) {
                    builder.append(line);
                }
                Log.d(TAG, "Loger success: " + builder.toString());
                return builder.toString();
            } else {
                Log.e(TAG, "Loger failed: code = " + conn.getResponseCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Loger response failed: " + e.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 创建post请求参数
     *
     * @param params _LogParams
     * @return String
     */
    private static String createPostParams(_LogParams params) {
        if (params == null) return null;
        String totalParams = "";
        try {
            //必传参数
            totalParams = createParams(params);
            _OSParams osParams = params.getOsParams();
            if (osParams != null) {
                //设备信息参数
                totalParams += "&" + createParams(osParams);
            }
            ExtraParams extraParams = params.getExtraParams();
            if (extraParams != null) {
                //非必须的参数
                totalParams += "&" + createParams(extraParams);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Params: " + totalParams);
        return totalParams;
    }

    private static String createParams(Object obj) throws Exception {
        String params = "";
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class fieldType = field.getType();              //类型
            if (fieldType.equals(String.class) || fieldType.equals(int.class)) {
                String fieldName = field.getName();         //名称
                Object fieldValue = field.get(obj);         //值
                if (fieldValue != null) {
                    params += ("".equals(params) ? "" : "&") + fieldName + "=" + String.valueOf
                            (fieldValue);
                }
            }
        }
        return params;
    }
}
