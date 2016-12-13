package com.ahsiu.studytest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.ahsiu.studytest.util.Log;

import java.util.Calendar;

/**
 * Created by hsiunien on 2014/7/12.
 */
public class HistogramView extends View {
    private Paint paint = new Paint();
    private int sw, sh;
    private float[][] tradeData;//交易数据
    private float maxTrade;//单日最大交易额
    private Calendar calendar;

    public HistogramView(Context context, AttributeSet attrs) {
        super(context, attrs);
        tradeData = new float[][]{{1000, 0}, {1000, 0}, {1000, 0}, {1020, 0}, {1320, 0}, {1000, 0}, {200, 10}};
        Log.d("HistogramView");
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("OnMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(1600, 300);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        HorizontalScrollView c = (HorizontalScrollView) getParent();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = canvas.getHeight();
        int width = canvas.getWidth();
        drawBg(canvas);
        for (int i = 0; i < tradeData.length; i++) {
            canvas.drawRect(100 + 200 * i, 100, 100 + 100 + 200 * i, 200, paint);
            canvas.drawText("test" + i, 100 + 200 * i, 100 - 20, paint);

        }
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        initView();
    }

    private void initView() {
        int days = getMonthOfDays();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = days * 100;
        setLayoutParams(layoutParams);
        setMeasuredDimension(days * 100, 300);
        invalidate();

    }

    //当月需要绘制几条柱子
    private int getMonthOfDays() {
        int days = 0;
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.MONTH) == this.calendar.get(Calendar.MONTH)) {
            days = calendar.get(Calendar.DAY_OF_MONTH);
        } else {
            days = this.calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        Log.d("days=" + days + "  month=" + this.calendar.get(Calendar.MONTH));
        return days;
    }

    private void drawBg(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#ccccce"));
        canvas.drawRect(10, 10, 10 + width - 20, 10 + height - 20, paint);
        paint.setColor(Color.parseColor("#29b318"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }
}
