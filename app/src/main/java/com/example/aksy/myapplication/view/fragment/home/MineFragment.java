package com.example.aksy.myapplication.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.view.fragment.BaseFragment;

/**
 *
 */
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

}