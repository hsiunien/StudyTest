package com.ahsiu.studytest.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2014/7/14.
 */
public class HorizontalScrollView extends android.widget.HorizontalScrollView {
    private float lastLocationX;
    private Paint paint;
    private boolean isOnTerminus;//是否在终点临界点


    public HorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isOnTerminus) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
          /*  case MotionEvent.ACTION_MOVE:
                if (getScrollX() == 0 && !isFlingLeft(ev.getX()) || getScrollX() == getWidth() && isFlingLeft(ev.getX())) {//内部不可以滑动
                     getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                } else {
                   getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;*/
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(ev);
    }

    private boolean isFlingLeft(float nowX) {
        return nowX - lastLocationX < 0;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (l == oldl) {
//            getParent().requestDisallowInterceptTouchEvent(false);
            isOnTerminus = true;
        } else {
//            getParent().requestDisallowInterceptTouchEvent(false);
            isOnTerminus = false;
        }
    }
}
