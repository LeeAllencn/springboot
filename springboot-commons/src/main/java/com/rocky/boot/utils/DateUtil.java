package com.rocky.boot.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Rocky on 2017-10-10.
 */
public class DateUtil {
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIEM_FORMAT = "HH:mm:ss";

    /**
     * 功能：自定义日期的格式
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date,String format){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 功能：按照地理位置来自定义日期的格式
     * @param date
     * @param format
     * @param locale
     * @return
     */
    public static String format(Date date,String format,Locale locale){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        return sdf.format(date);
    }

    /**
     * 功能：格式化DateTime
     * @param date
     * @return
     */
    public static String formatDateTime(Date date){
        return format(date,DATE_TIME_FORMAT);
    }

    /**
     * 功能：格式化Date
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        return format(date,DATE_FORMAT);
    }

    /**
     * 功能：格式化Time
     * @param date
     * @return
     */
    public static String formatTime(Date date){
        return format(date,TIEM_FORMAT);
    }

    /**
     * 功能：自定义解析日期字符串（转化成Date类型）
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parse(String dateStr,String format){
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 功能：按照地理位置来自定义解析日期字符串（转化成Date类型）
     * @param dateStr
     * @param format
     * @param locale
     * @return
     */
    public static Date parse(String dateStr,String format,Locale locale){
        if(StringUtils.isBlank(dateStr)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, locale);
        Date date = new Date();
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 功能：从数据库里获取创建时间，来显示更新时间
     * @param date
     * @return
     */
    public static String getInterval(Date date){//传入的时间格式必须类似于2012-8-21 17:53:20这样的格式(数据库里时间的格式)
        if(date == null){
            return "";
        }
        String interval = null;
        //用现在距离1970年的时间间隔new Date().getTime()减去以前的时间距离1970年的时间间隔date.getTime()得出的就是以前的时间与现在时间的时间间隔
        Date now = new Date();
        long time = now.getTime() - date.getTime();	//得出的时间间隔的单位是毫秒
        if(time/1000 < 10 && time/1000 >= 0){
            //如果时间间隔小于10秒则显示“刚刚”
            interval = "刚刚";
        }else if(time/1000 < 60 && time/1000 >= 10){
            //如果时间间隔大于10秒、小于60秒则显示多少秒前
            int second = (int)((time%60000)/1000);	//得出的时间间隔的单位是秒
            interval = second + "秒前";
        }else if(time/60000 < 60 && time/60000 >= 0){
            //如果时间间隔小于60分钟则显示多少分钟前
            int minute = (int)((time%3600000)/60000);	//得出的时间间隔的单位是分钟
            interval = minute + "分钟前";
        }else if(time/3600000 < 24 && time/3600000 >= 0){
            //如果时间间隔小于24小时则显示多少小时前
            int hour = (int)(time/3600000);	//得出的时间间隔的单位是小时
            interval = hour + "小时前";
        }else if(time/3600000 >= 24){
            //如果时间间隔大于24小时，则显示正常的时间，但是不显示秒
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            interval = sdf.format(date);
        }
        return interval;
    }

    /**
     * 功能：将毫秒时间转化为特定的时间格式
     * @param time
     * @return
     */
    public static String secToTime(Integer time) {
        if(time == null){
            return "0秒";
        }
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time < 0)
            return "0秒";
        else if(time == 0){
            return "小于1秒";
        } else {
            minute = time / 60;
            if (minute == 0 ) {
                second = time % 60;
                timeStr = "小于1秒";
                if(second >= 1) {
                    timeStr = second + "秒";
                }
            } else if (minute < 60) {
                second = time % 60;
                timeStr = minute + "分" + second + "秒";
            } else {
                hour = minute / 60;
                if (hour > 24)
                    return "大于1天";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = hour + "时" + minute + "分" + second + "秒";
            }
        }
        return timeStr;
    }

    public static void main(String[] args) {

    }
}
