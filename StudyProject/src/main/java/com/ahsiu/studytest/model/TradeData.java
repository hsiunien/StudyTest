package com.ahsiu.studytest.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by hsiunien on 14-7-15.
 */
public class TradeData implements Serializable {
    private Calendar mCalendar;//保存年月信息
    private float month;//月份 从0开始计算
    private int days;//天数 从1开始计算
    private float[][] tradeData;//交易书第一个是成功交易，第二个是未知交易


}
