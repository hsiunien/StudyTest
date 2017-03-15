package com.ahsiu.studytest;

import android.app.Activity;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;

public  abstract class BaseActivity extends Activity {
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    protected Activity getActivity(){
        return this;
    }
}
