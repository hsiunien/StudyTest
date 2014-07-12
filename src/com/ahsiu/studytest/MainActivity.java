package com.ahsiu.studytest;

import android.app.Activity;
import android.os.Bundle;

import com.ahsiu.studytest.util.Tools;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

public class MainActivity extends Activity {
    private ViewFlow viewFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_layout);
        viewFlow = (ViewFlow) findViewById(R.id.viewflow);
        AsyncAdapter adapter = new AsyncAdapter(this);
        viewFlow.setAdapter(adapter, adapter.getTodayId());
        TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
        indicator.setTitleProvider(adapter);
        viewFlow.setFlowIndicator(indicator);
    }

    @Override
    protected void onResume() {
        Tools.setScreenLandscape(this);
        super.onResume();
    }
}
