package com.pangfeng.frame.utils;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeUtil implements Serializable {

	private static final long serialVersionUID = -1844761923580220290L;

	private static final SimpleDateFormat longDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static String getWeek(String strDate) {
		Date date = null;
		try {
			date = shortDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return new SimpleDateFormat("EEEE").format(calendar.getTime());
	}

	public static String getShortToday() {
		Date today = new Date();
		return shortDateFormat.format(today);
	}

	public static String getLongToday() {
		Date today = new Date();
		return longDateFormat.format(today);
	}

	@SuppressWarnings("unused")
	private static String getTodayStart() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date date = new Date(calendar.getTimeInMillis());
		return longDateFormat.format(date);
	}

	@SuppressWarnings("unused")
	private static String getTodayEnd() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date date = new Date(calendar.getTimeInMillis());
		return longDateFormat.format(date);
	}

	@SuppressWarnings("unused")
	private static String getDateStart(String strDate) {
		if (null == strDate || "".equals(strDate.trim())) {
			return "";
		}
		Date date = null;
		try {
			date = shortDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return longDateFormat.format(date);
	}

	@SuppressWarnings("unused")
	private static String getDateEnd(String strDate) {
		if (null == strDate || "".equals(strDate.trim())) {
			return "";
		}
		Date date = null;
		try {
			date = shortDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);

		return longDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getMondayOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);

		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getSundayOfThisWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, 6);
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getMondayOfPreviousWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, -7);
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getSundayOfPreviousWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1);
		calendar.add(Calendar.DATE, -1);
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getMondayOfNextWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1); // ������һ
		calendar.add(Calendar.DATE, 7);
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getSundayOfNextWeek() {
		Calendar calendar = Calendar.getInstance();
		int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		calendar.add(Calendar.DATE, -day_of_week + 1); // ������һ
		calendar.add(Calendar.DATE, 13);
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getFirstDayOfThisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1); // ��Ϊ��ǰ�µ�1��
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getLastDayOfThisMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1); // ��Ϊ��ǰ�µ�1��
		calendar.add(Calendar.MONTH, 1); // ��һ���£���Ϊ���µ�1��
		calendar.add(Calendar.DATE, -1); // ��ȥһ�죬��Ϊ�������һ��
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getFirstDayOfPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1); // ��Ϊ��ǰ�µ�1��
		calendar.add(Calendar.MONTH, -1); // ��һ���£���Ϊ���µ�1��
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getLastDayOfPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1); // ��Ϊ��ǰ�µ�1��
		calendar.add(Calendar.DATE, -1); // ��ȥһ�죬��Ϊ�������һ��
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static String getFirstDayOfNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1); // ��һ����
		calendar.set(Calendar.DATE, 1); // ����������Ϊ���µ�һ��
		return shortDateFormat.format(calendar.getTime());
	}

	/**
	 * ȡ�������һ��
	 */
	@SuppressWarnings("unused")
	private static String getLastDayOfNextMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);// ��һ����
		calendar.set(Calendar.DATE, 1);// ����������Ϊ���µ�һ��
		calendar.roll(Calendar.DATE, -1);// ���ڻع�һ�죬Ҳ���Ǳ������һ��
		return shortDateFormat.format(calendar.getTime());
	}

	@SuppressWarnings("unused")
	private static int getTotalDaysOfThisYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, 1);// ��������Ϊ�����һ��
		calendar.roll(Calendar.DAY_OF_YEAR, -1);// �����ڻع�һ�졣
		int totalDays = calendar.get(Calendar.DAY_OF_YEAR);
		return totalDays;
	}

	@SuppressWarnings("unused")
	private static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static String getFirstDayOfThisYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		Date date = new Date(calendar.getTimeInMillis());
		return shortDateFormat.format(date);
	}

	public static String getLastDayOfThisYear() {
		Date date = new Date();
		String years = new SimpleDateFormat("yyyy").format(date);
		return years + "-12-31";
	}

	public static String getFirstDayOfPreviousYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1); // ���� �����Ϊ������1����
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_YEAR, 1);
		Date date = new Date(calendar.getTimeInMillis());
		return shortDateFormat.format(date);
	}

	public static long getDays(String date1, String date2) {
		if (date1 == null || "".equals(date1.trim())) {
			return 0;
		}
		if (date2 == null || "".equals(date2.trim())) {
			return 0;
		}
		Date date = null;
		Date mydate = null;
		try {
			date = shortDateFormat.parse(date1);
			mydate = shortDateFormat.parse(date2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long day = 0;
		if (date.before(mydate)) {
			day = (mydate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
		} else {
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		}
		return day;
	}

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat DATE_FORMAT_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 
	 * long time to string
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */

	public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
		return dateFormat.format(new Date(timeInMillis));
	}

	/**
	 * 
	 * String time to date
	 * 
	 * @param timeInMillis
	 * @param dateFormat
	 * @return
	 */

	public static Date getTime(String timeInMillis, SimpleDateFormat dateFormat) {
		Date date = null;
		try {
			date = dateFormat.parse(timeInMillis);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 
	 * long time to string, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */

	public static String getTime(long timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 
	 * String time to date, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @param timeInMillis
	 * @return
	 */

	public static Date getTime(String timeInMillis) {
		return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 
	 * get current time in milliseconds
	 * 
	 * @return
	 */

	public static long getCurrentTimeInLong() {
		return System.currentTimeMillis();
	}

	/**
	 * 
	 * get current time in milliseconds, format is {@link #DEFAULT_DATE_FORMAT}
	 * 
	 * @return
	 */

	public static String getCurrentTimeInString() {
		return getTime(getCurrentTimeInLong());
	}

	/**
	 * 
	 * get current time in milliseconds
	 * 
	 * @return
	 */

	public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
		return getTime(getCurrentTimeInLong(), dateFormat);
	}
}
