package com.ahsiu.studytest.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ahsiu.studytest.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by hsiunien on 14-7-17.
 */
public class ViewAdapter extends BaseAdapter {
    private static final DateFormat dfTitle = new SimpleDateFormat("MMM");

    private static final int daysDepth = 30;
    private static final int daysSize = daysDepth /** 2 + 1*/
            ;

    private static Date[] dates = new Date[daysSize];
    private static String[] content = new String[daysSize];

    private LayoutInflater mInflater;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = mInflater.inflate(R.layout.layout_histogram_view, null);
        HistogramView historyView = (HistogramView) view.findViewById(R.id.histogram_view);
        ((HorizontalScrollView) historyView.getParent()).fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates[position]);
        historyView.setCalendar(calendar);

        return null;
    }

    private void prepareDates() {
        Date today = new Date();
        Calendar calPast = Calendar.getInstance();
        calPast.setTime(today);
        for (int i = 1; i <= daysDepth; i++) {
            dates[daysDepth - i] = calPast.getTime();
            calPast.add(Calendar.MONTH, -1);
        }
    }


}
