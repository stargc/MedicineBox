package com.zhiyi.medicinebox.util.tools;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = LogManager.getLogger(DateUtil.class.getName());

    public static long dateString2Long(String dateString, boolean isFull) {
        SimpleDateFormat format = null;
        if (isFull) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            if (dateString.trim().length() == 7) {
                dateString = dateString.trim() + "-01";
                format = new SimpleDateFormat("yyyy-MM-dd");
            }
        }
        Long result = null;
        try {
            Date date = format.parse(dateString);
            result = date.getTime();
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            result = 0L;
        }
        return result;
    }

    public static String Long2DateString(Long l) {
        Long beginDate = new Long(l);
        Date date = new Date(beginDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    /***
     *
     * @param dateString
     *            要转成date类型的字符串 . isfull 为true时 格式为 "YYYY-MM-DD hh:mm:ss"；
     *            isfull为false 格式为" YYYY-MM-DD"
     * @param isfull
     *            是否是 YYYY-MM-DD hh:mm:ss 格式
     * @return
     */
    public static Date String2Date(String dateString, boolean isfull) {
        SimpleDateFormat format;
        if (isfull) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd");
        }
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return date;
    }

    /***
     *
     * @param timeString
     *            要转成date类型的字符串 格式为 HH:mm：ss
     * @return
     */
    public static Date StringTime2Date(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(timeString);
        } catch (ParseException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return date;
    }

    public static String date2String(Date date, String fromat) {
        SimpleDateFormat format = new SimpleDateFormat(fromat);
        return format.format(date);
    }

    /****
     * 格式 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String Date2TimeString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);

    }
}
