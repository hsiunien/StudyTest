package com.ahsiu.studytest.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.ahsiu.studytest.util.Log;

/**
 * Created by Administrator on 2014/7/14.
 */
public class HorizontalScrollView extends android.widget.HorizontalScrollView {
    private float lastLocationX;
    private Paint paint;

    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("Horizontal onInterceptTouchEvent=" + ev.getAction() + "  scrollx" + getScrollX());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("Horizontal dispatchTouchEvent=" + ev.getAction() + "  scrollx" + getScrollX());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("Horizontal ev=" + ev.getAction() + "  scrollx" + getScrollX());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastLocationX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (getScrollX() == 0 && !isFlingLeft(ev.getX()) || getScrollX() == getWidth() && isFlingLeft(ev.getX())) {//内部不可以滑动
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_UP:
                lastLocationX = ev.getX();
                break;
        }
        return super.onTouchEvent(ev);
    }

    private boolean isFlingLeft(float nowX) {
        return nowX - lastLocationX < 0;
    }

}
