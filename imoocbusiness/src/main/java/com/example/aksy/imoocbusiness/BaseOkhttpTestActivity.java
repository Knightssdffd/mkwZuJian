package com.example.aksy.imoocbusiness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aksy.imoocbusiness.okhttp.CommonOkhttpClient;
import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataHandle;
import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataListener;
import com.example.aksy.imoocbusiness.okhttp.request.CommonRequest;
import com.example.aksy.imoocbusiness.okhttp.response.CommonJsonCallback;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BaseOkhttpTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void sendRequest() {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("").build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

//    private void test() {
//        CommonOkhttpClient.sendRequest(CommonRequest.createGetRequest("", null),
//                new CommonJsonCallback(new DisposeDataHandle(new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//
//                    }
//                })));
//    }
}
