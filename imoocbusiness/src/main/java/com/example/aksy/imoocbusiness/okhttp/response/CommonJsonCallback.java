package com.example.aksy.imoocbusiness.okhttp.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.aksy.imoocbusiness.okhttp.exception.OkHttpException;
import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataHandle;
import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataListener;
import com.example.aksy.imoocbusiness.util.ResponseEntityToModule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CommonJsonCallback implements Callback {
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    /**
     * 自定义异常类型
     */
    protected final int NETWORK_ERROR = -1; //the network relative error
    protected final int JSON_ERROR = -2;  //the JSON relative error
    protected final int OTHER_ERROR = -3;//the  unknow error

    private Handler mDeliveryHandler; //进行消息的转发
    private DisposeDataListener mListener;
    private Class<?> mClass;

    public CommonJsonCallback(DisposeDataHandle handler) {
        this.mListener = handler.mListener;
        this.mClass = handler.mClass;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
    }

    //请求失败处理
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.e("qqcw","aaaaaaaaaaaaa");
                mListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });

    }

    //真正的响应处理函数
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        Log.e("qqcw",result);
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });

    }

    /**
     * 处理服务器返回的响应的数据
     */
    private void handleResponse(Object responseObj) {
        //为了代码的健壮性
        if (responseObj == null && responseObj.toString().trim().equals("")) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject reuslt = new JSONObject(responseObj.toString());
            if (reuslt.has(RESULT_CODE)) {
                //从JOSN对象中取出我们的响应码，若为0，则是正常的响应
                if (reuslt.getInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (mClass == null) {
                        mListener.onSuccess(responseObj);
                    } else {
                        //需要将josn对象转换成实体对象
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(reuslt, mClass);
                        if (obj != null) {
                            mListener.onSuccess(obj);
                        } else {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        }
                    }
                } else {
                    mListener.onFailure(new OkHttpException(OTHER_ERROR, reuslt.get(RESULT_CODE)));
                }
            }
        } catch (Exception e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }



}
