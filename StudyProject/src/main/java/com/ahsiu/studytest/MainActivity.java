package com.ahsiu.studytest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ahsiu.studytest.rx.RxMainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_rx)
    Button mBtnRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn_rx)
    public void onClick() {
        Intent intent=new Intent(getActivity(),RxMainActivity.class);
        startActivity(intent);
    }

}
