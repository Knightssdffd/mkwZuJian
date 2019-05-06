package com.example.aksy.myapplication.network;


import android.widget.Adapter;

/**
 * @funtion:定义所有请求地址
 */
public class HttpConstants {

    private static final String ROOT_URL = "http://imooc.com/api";
    /**
     * 首页产品请求接口
     */
//    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_recommand.php";
    public static String HOME_RECOMMAND = "http://192.168.0.105:8080/getHomeData";


}
