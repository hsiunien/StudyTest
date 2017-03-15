package com.ahsiu.studytest.rx;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.ahsiu.studytest.BaseActivity;
import com.ahsiu.studytest.R;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.ReplayRelay;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

public class RxMainActivity extends BaseActivity {

    private static final String TAG = "RxMainActivity";
    @BindView(R.id.tv_msg)
    TextView mTvMsg;
    @BindView(R.id.btn_rx)
    Button mBtnRx;
    ReplayRelay<Object> relay = ReplayRelay.<Object>create();
    private int count=0;
    private CompositeDisposable mDisposables=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 13; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    relay.accept(count++);
                }

            }
        }).start();
    }

    @OnClick(R.id.btn_rx)
    public void onClick() {
        mDisposables.add(relay.subscribe(createCusumer(count)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposables.clear();
    }

    private Consumer createCusumer(final int id1){
        return new Consumer() {
            private int id=id1;
            private static final String TAG = "RxMainActivity.C";
            @Override
            public void accept(Object o) throws Exception {
                Log.d(TAG,"id:"+id+" result:"+o.toString());
            }
        };
    }
}
