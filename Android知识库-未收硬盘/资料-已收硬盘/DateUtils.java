package com.chenggua.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 */
public class DateUtils {

	public static List<String> getCalendar(int year, int month) {
		List<String> list = new ArrayList<String>();
		int totaldays = 0;
		int dayofmonth = 0;
		for (int i = 1900; i < year; i++) {
			if (((i % 4 == 0) && (i % 100 != 0)) || (i % 400 == 0)) {
				totaldays = totaldays + 366;
			} else {
				totaldays = totaldays + 365;
			}
		}
		for (int nomonth = 1; nomonth <= month; nomonth++) {
			if (nomonth == 1 || nomonth == 3 || nomonth == 5 || nomonth == 7 || nomonth == 8 || nomonth == 10
					|| nomonth == 12) {
				dayofmonth = 31;
			} else if (nomonth == 2) {
				if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
					dayofmonth = 29;
				} else {
					dayofmonth = 28;
				}
			} else {
				dayofmonth = 30;
			}
			if (nomonth < month) {
				totaldays = totaldays + dayofmonth;
			}
		}

		int temp = ((totaldays + 1) % 7);
		for (int p = 0; p < temp; p++) {
			list.add("**");
		}
		for (int w = 1; w <= dayofmonth; w++) {
			list.add(String.valueOf(w));
		}

		int lastDays = (7 - ((temp + dayofmonth) % 7)) % 7;

		for (int p = 0; p < lastDays; p++) {
			list.add("**");
		}

		return list;

	}

	private final static long minute = 60 * 1000;// 1分钟
	private final static long hour = 60 * minute;// 1小时
	private final static long day = 24 * hour;// 1天
	private final static long month = 31 * day;// 月
	private final static long year = 12 * month;// 年
	/**
	 * 返回文字描述的日期
	 *
	 * @param date1
	 * @return
	 */
	public static String getTimeFormatText1(Object date1) {
		Date date = null;
		if (date1 instanceof String) {
			date = toDate(date1);
		}
		if (date1 instanceof Date) {
			date = (Date) date1;
		}

		if (date == null) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
	   if(diff>day){
		return toDate1(date1);
	  }
		if (diff > hour) {
			r = (diff / hour);
			return r + "小时前";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}
	/**
	 * 返回文字描述的日期
	 * 
	 * @param date1
	 * @return
	 */
	public static String getTimeFormatText(Object date1) {
		Date date = null;
		if (date1 instanceof String) {
			date = toDate(date1);
		}
		if (date1 instanceof Date) {
			date = (Date) date1;
		}

		if (date == null) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
		if (diff > year) {
			r = (diff / year);
			return r + "年前";
		}
		if (diff > month) {
			r = (diff / month);
			return r + "个月前";
		}
		if (diff > day) {
			r = (diff / day);
			if (r == 1) {
				return "昨天";
			} else if (r == 2) {
				return "前天";
			} else {
				return r + "天前";
			}

		}
		if (diff > hour) {
			r = (diff / hour);
			return r + "小时前";
		}
		if (diff > minute) {
			r = (diff / minute);
			return r + "分钟前";
		}
		return "刚刚";
	}
	public static String toTime(int var0) {
		var0 /= 1000;
		int var1 = var0 / 60;
		boolean var2 = false;
		if(var1 >= 60) {
			int var4 = var1 / 60;
			var1 %= 60;
		}

		int var3 = var0 % 60;
		return String.format("%02d:%02d", new Object[]{Integer.valueOf(var1), Integer.valueOf(var3)});
	}
	private static Date toDate(Object date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse((String) date);
		} catch (ParseException e) {
			return null;
		}
	}
	private static String toDate1(Object date1) {
		Date date = null;
		if (date1 instanceof String) {
			date = toDate(date1);
		}
		if (date1 instanceof Date) {
			date = (Date) date1;
		}

		if (date == null) {
			return null;
		}
		long time=date.getTime();
		Date date2=new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(date2);
		} catch (Exception e) {
			return null;
		}
	}
	public static String getStarSeat(String date, String format) {
		try {
			Date d = new SimpleDateFormat(format).parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			return getStarSeat(c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 通过日期来确定星座
	 * 
	 * @param mouth
	 * @param day
	 * @return
	 */
	public static String getStarSeat(int mouth, int day) {
		String starSeat = null;
		mouth += 1;
		if ((mouth == 3 && day >= 21) || (mouth == 4 && day <= 19)) {
			starSeat = "白羊座";
		} else if ((mouth == 4 && day >= 20) || (mouth == 5 && day <= 20)) {
			starSeat = "金牛座";
		} else if ((mouth == 5 && day >= 21) || (mouth == 6 && day <= 21)) {
			starSeat = "双子座";
		} else if ((mouth == 6 && day >= 22) || (mouth == 7 && day <= 22)) {
			starSeat = "巨蟹座";
		} else if ((mouth == 7 && day >= 23) || (mouth == 8 && day <= 22)) {
			starSeat = "狮子座";
		} else if ((mouth == 8 && day >= 23) || (mouth == 9 && day <= 22)) {
			starSeat = "处女座";
		} else if ((mouth == 9 && day >= 23) || (mouth == 10 && day <= 23)) {
			starSeat = "天秤座";
		} else if ((mouth == 10 && day >= 24) || (mouth == 11 && day <= 22)) {
			starSeat = "天蝎座";
		} else if ((mouth == 11 && day >= 23) || (mouth == 12 && day <= 21)) {
			starSeat = "射手座";
		} else if ((mouth == 12 && day >= 22) || (mouth == 1 && day <= 19)) {
			starSeat = "摩羯座";
		} else if ((mouth == 1 && day >= 20) || (mouth == 2 && day <= 18)) {
			starSeat = "水瓶座";
		} else {
			starSeat = "双鱼座";
		}
		return starSeat;
	}
	/**
	 * @Title: getAge
	 * @Description: 获取年龄
	 * @param birthday
	 *
	 * @return
	 */
	public static String getAge(String birthday) {
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		try {
			cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return String.valueOf(age + 1);
	}
	/**
	 * @Title: formatTime
	 * @Description: 将一种时间格式换成另外一种时间格式
	 * @param time
	 * @param format
	 * @param targetFormat
	 * @return
	 */
	public static String format(String time, String format, String targetFormat) {
		try {
			Date d = new SimpleDateFormat(format).parse(time);
			return new SimpleDateFormat(targetFormat).format(d);
		} catch (ParseException e) {
			e.printStackTrace();
			return time;
		}
	}
}
