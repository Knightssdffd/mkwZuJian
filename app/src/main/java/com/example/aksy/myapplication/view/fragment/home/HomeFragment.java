package com.example.aksy.myapplication.view.fragment.home;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aksy.imoocbusiness.okhttp.listener.DisposeDataListener;
import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.adapter.CourseAdapter;
import com.example.aksy.myapplication.constant.Constant;
import com.example.aksy.myapplication.module.recommand.BaseRecommandModel;
import com.example.aksy.myapplication.network.RequestCenter;
import com.example.aksy.myapplication.util.Util;
import com.example.aksy.myapplication.view.home.HomeHeaderLayout;
import com.example.aksy.myapplication.zxing.app.CaptureActivity;


/**
 *
 */
public class HomeFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final int REQUEST_QRCODE = 0x01;

    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;
    private BaseRecommandModel mRecommandModel;
    private TextView mQRCodeView;

    /**
     * data
     */
    private Activity mContext;
    private CourseAdapter mAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = mContentView.findViewById(R.id.loading_view);
        //启动动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    /**
     * 发送首页列表数据请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommanData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mRecommandModel = (BaseRecommandModel) responseObj;
                Log.e("网络", "onSuccess" + responseObj.toString());
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e("网络", "onFailure: " + reasonObj.toString());
            }
        });
    }

    /**
     * 请求成功执行的方法
     */
    private void showSuccessView() {
        if (mRecommandModel.data.list != null && mRecommandModel.data.list.size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVelocityScale(View.VISIBLE);
            //list添加列表头部
            mListView.addHeaderView(new HomeHeaderLayout(mContext, mRecommandModel.data.head));

            mAdapter = new CourseAdapter(mContext, mRecommandModel.data.list);
            mListView.setAdapter(mAdapter);
        } else {
            showErrorView();
        }
    }

    /**
     * 请求失败执行的方法
     */
    private void showErrorView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.qrcode_view:
//                if (hasPermission(Constant.HARDWEAR_CAMERA_PERMISSION)) {
                doOpenCamera();
//                } else {
//                    requestPermission(Constant.HARDWEAR_CAMERA_CODE, Constant.HARDWEAR_CAMERA_PERMISSION);
//                }
                break;
            case R.id.category_view:
                //与我交谈
//                Intent intent2 = new Intent(Intent.ACTION_VIEW, Util.createQQUrl("277451977"));
//                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent2);
                break;
            case R.id.search_view:
//                Intent searchIntent = new Intent(mContext, SearchActivity.class);
//                mContext.startActivity(searchIntent);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void doOpenCamera() {
        Intent intent = new Intent(mContext, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_QRCODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_QRCODE:

                break;
        }
    }
}
