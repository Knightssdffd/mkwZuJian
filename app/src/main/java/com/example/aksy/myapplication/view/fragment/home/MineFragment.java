package com.example.aksy.myapplication.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.aksy.myapplication.R;
import com.example.aksy.myapplication.view.fragment.BaseFragment;

import org.w3c.dom.Text;

import java.util.List;

/**
 *
 */
public class MineFragment extends BaseFragment {


    private TextView aaa;
    private ListView listView;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mine_layout, container, false);
        return view;
    }

}
