package com.zhanghanlun.domain;

import javax.sql.DataSource;
import java.text.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description 专用
 * @Author 11084850
 * @Create 2018-06-27 16:35
 **/
public class Main {
    private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String getExchangeNumber(long num){
        if(num < 10000){
            return String.valueOf(num);
        }
        else if(num < 1000000){
            double result= Math.round(num/1000.0)/10.0;
            int resultInt = (int)result;
            if(resultInt == result){
                return String.valueOf(resultInt)+"万";
            }
            else{
                return String.format("%.1f",result)+"万";
            }

        }
        else{
            return String.valueOf(Math.round(num/10000.0))+"万";
        }
    }

    public static String getNewDate(String strDate){
        Date dateNow = new Date();
        Calendar calendarNow = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        calendarNow.setTime(dateNow);
        Date date;
        try {
             date = sdf.parse(strDate);
             calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int nowYear = calendarNow.get(Calendar.YEAR);
        int nowMonth = calendarNow.get(Calendar.MONTH);
        int nowDay = calendarNow.get(Calendar.DATE);
        int nowHour = calendarNow.get(Calendar.HOUR_OF_DAY);
        int nowMinute = calendarNow.get(Calendar.MINUTE);
        System.out.println(sdf.format(dateNow));
        if(year < nowYear){
            return String.valueOf(nowYear - year)+"年前";
        }
        else if(month < nowMonth){
            return String.valueOf(nowMonth - month)+"月前";
        }
        else if(day < nowDay){
            if(nowDay - day == 1){
                return "昨天";
            }else{
                return String.valueOf(nowDay - day)+"天前";
            }
        }
        else if(hour < nowHour){
            return String.valueOf(nowHour - hour)+"小时前";
        }
        else if(minute < nowMinute){
            return String.valueOf(nowMinute - minute)+"分钟前";
        }
        else{
            return "刚刚";
        }
    }
    public static void main(String[] args){
        String datestr = "2018-06-26 14:32:08";
        System.out.println(getNewDate(datestr));

//        System.out.println(getExchangeNumber(a));
//        System.out.println(getExchangeNumber(b));
//        System.out.println(getExchangeNumber(b1));
//        System.out.println(getExchangeNumber(c));
    }
}
