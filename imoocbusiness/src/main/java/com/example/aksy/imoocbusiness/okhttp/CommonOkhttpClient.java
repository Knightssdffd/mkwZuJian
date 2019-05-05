package com.example.aksy.imoocbusiness.okhttp;

import com.example.aksy.imoocbusiness.okhttp.https.HttpsUtils;
import com.example.aksy.imoocbusiness.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @function 请求的发送，请求的参数的配置,https支持。
 */
public class CommonOkhttpClient {
    private static final int TIEE_OUT = 30;//超时参数。
    private static OkHttpClient mOkHttpClient;

    //为我们的client去配置参数了
    static {
        //创建我们client对象的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //是为构建者填充
        okHttpBuilder.connectTimeout(TIEE_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIEE_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIEE_OUT, TimeUnit.SECONDS);

        okHttpBuilder.followRedirects(true);

        //https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {


                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        //生成client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 发送具体的http/https请求
     *
     * @param request
     * @param commCallback
     * @return Call
     */
    public static Call sendRequest(Request request, CommonJsonCallback commCallback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallback);
        return call;
    }

}
