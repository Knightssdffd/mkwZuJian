package com.example.aksy.myapplication.network;


import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataHandle;
import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataListener;
import com.example.aksy.imoocbusiness.okhttp.request.RequestParams;
import com.example.aksy.myapplication.module.recommand.BaseRecommandModel;

import static com.example.aksy.imoocbusiness.okhttp.CommonOkhttpClient.get;
import static com.example.aksy.imoocbusiness.okhttp.request.CommonRequest.createGetRequest;


/**
 * @function 存放应用中所有的请求
 */
public class RequestCenter {
    //根据参数发送所有post请求
    private static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        get(createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }

    /**
     * 真正的发送首页请求
     *
     * @param listener
     */
    public static void requestRecommanData(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, BaseRecommandModel.class);
    }


}
