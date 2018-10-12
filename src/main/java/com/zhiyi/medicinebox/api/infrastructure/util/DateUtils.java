package com.zhiyi.medicinebox.api.infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 日期类操作工具类
 * 
 * @author Cary.Liu
 * @create date 2014-07-16
 * 
 */
public class DateUtils {

	private DateUtils(){}

	public static String getDateStr(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取当前时间往后n分钟的时间
	 * 
	 * @param afterMunute
	 * @return
	 */
	public static String getAfterMinuteTime(String afterMunute) {

		Calendar can = Calendar.getInstance();
		can.add(Calendar.MINUTE, Integer.parseInt(afterMunute));
		Date afterTime = can.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(afterTime);
	}

	/**
	 * 获取两个时间之间相差的天数
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return 0L;
		}
		long time1 = startDate.getTime();
		long time2 = endDate.getTime();
		long diff = time2 - time1;
		return diff / (24 * 60 * 60 * 1000);
	}


	public static String date2String(Date date, String fromat) {
		SimpleDateFormat format = new SimpleDateFormat(fromat);
		return format.format(date);
	}

	public static String Date2TimeString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(date);

	}
}
