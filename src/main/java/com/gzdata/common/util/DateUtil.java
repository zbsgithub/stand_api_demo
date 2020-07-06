package com.gzdata.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期时间工具类 <br>
 * 提供一些常用的日期时间操作方法，所有方法都为静态，不用实例化该类即可使用。 <br>
 * <br>
 */

public class DateUtil {
	
	private static final Logger logger=LoggerFactory.getLogger(DateUtil.class);

	public static final String DEFAULT_FULLYEAR_FORMAT = "yyyy";

	public static final String DEFAULT_YEARMONTH_FORMAT = "yyyy-MM";

	/**
	 * 缺省的日期显示格式： yyyy-MM-dd
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 缺省的日期显示格式： yyyyMMdd
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	/*
	 * 缺省的日期显示格式：yyyyMMddHHmmss
	 */
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/**
	 * 缺省的时间显示格式： yyyy-MM-dd
	 */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/**
	 * 缺省的日期时间显示格式：yyyy-MM-dd HH:mm:ss
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String[] WEEKDAY = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 缺省的时区GMT+8
	 */
	public static final String DEFAULT_TIMEZONE = "GMT+8";

	/**
	 * 私有构造方法，禁止对该类进行实例化
	 */
	private DateUtil() {
	}

	private static SimpleDateFormat formater = new SimpleDateFormat();

	/**
	 * 得到系统当前日期时间
	 * 
	 * @return 当前日期时间
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 得到用缺省方式格式化的当前日期
	 * 
	 * @return 当前日期字符串
	 */
	public static String getDate() {

		return getDateTime(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到用缺省方式格式化的给定日期
	 * 
	 * @return 给定日期字符串
	 */
	public static String getDate(Date date) {
		return getDate(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 得到系统当前日期及时间，并用指定的方式格式化
	 * 
	 * @param pattern
	 *            显示格式
	 * @return 当前日期字符串
	 */
	public static String getDate(String pattern) {
		return getDateTime(pattern);
	}

	/**
	 * 得到用指定方式格式化的日期
	 * 
	 * @param date
	 *            需要进行格式化的日期
	 * @param pattern
	 *            显示格式
	 * @return 日期字符串
	 */
	public static String getDate(Date date, String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATE_FORMAT;
		}
		formater.applyPattern(pattern);
		return formater.format(date);
	}

	/**
	 * 得到用缺省方式格式化的当前日期及时间
	 * 
	 * @return 当前日期及时间字符串
	 */
	public static String getDateTime() {
		return getDateTime(DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 得到用缺省方式格式化的给定日期时间
	 * 
	 * @return 给定的日期时间字符串
	 */
	public static String getDateTime(Date date) {
		return getDateTime(date, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 得到系统当前日期及时间，并用指定的方式格式化
	 * 
	 * @param pattern
	 *            显示格式
	 * @return 当前日期及时间字符串
	 */
	public static String getDateTime(String pattern) {
		// Date datetime = Calendar.getInstance().getTime();
		return getDateTime(getNow(), pattern);
	}

	/**
	 * 得到用指定方式格式化的日期
	 * 
	 * @param date
	 *            需要进行格式化的日期
	 * @param pattern
	 *            显示格式
	 * @return 日期时间字符串
	 */
	public static String getDateTime(Date date, String pattern) {
		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATETIME_FORMAT;
		}
		formater.applyPattern(pattern);
		return formater.format(date);
	}

	/**
	 * 得到当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getCurrentMonth() {
		// 用get得到的月份数比实际的小1，需要加上
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @return 当前日
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
	 * 
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(int days) {
		return add(getNow(), days, Calendar.DATE);
	}

	/**
	 * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(Date date, int days) {
		return add(date, days, Calendar.DATE);
	}

	/**
	 * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
	 * 
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date addMonths(int months) {
		return add(getNow(), months, Calendar.MONTH);
	}

	/**
	 * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数。 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
	 * 
	 * @param date
	 *            基准日期
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date addMonths(Date date, int months) {
		return add(date, months, Calendar.MONTH);
	}

	/**
	 * 内部方法。为指定日期增加相应的天数或月数
	 * 
	 * @param date
	 *            基准日期
	 * @param amount
	 *            增加的数量
	 * @param field
	 *            增加的单位，年，月或者日
	 * @return 增加以后的日期
	 */
	private static Date add(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(field, amount);

		return calendar.getTime();
	}

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差天数
	 */
	public static long diffDays(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
	 * 
	 * @param one
	 *            第一个日期数，作为基准
	 * @param two
	 *            第二个日期数，作为比较
	 * @return 两个日期相差月份数
	 */
	public static int diffMonths(Date one, Date two) {

		Calendar calendar = Calendar.getInstance();

		// 得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);

		// 得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
	}

	/**
	 * 将一个字符串用给定的格式转换为日期类型。 <br>
	 * 注意：如果返回null，则表示解析失败
	 * 
	 * @param datestr
	 *            需要解析的日期字符串
	 * @param pattern
	 *            日期字符串的格式，默认为“yyyy-MM-dd”的形式
	 * @return 解析后的日期
	 */
	public static Date parse(String datestr, String pattern) {
		Date date = null;

		if (null == pattern || "".equals(pattern)) {
			pattern = DEFAULT_DATE_FORMAT;
		}

		try {
			formater.applyPattern(pattern);
			date = formater.parse(datestr);
		} catch (ParseException e) {
			logger.error("datetime parse arise exception:",e);
		}

		return date;
	}

	/**
	 * 将一个字符串用4种基本格式转换为日期类型。 <br>
	 * 注意：如果返回null，则表示解析失败
	 * 
	 * @param dateString
	 *            需要解析的日期字符串
	 * 
	 * @param dateString
	 * @return 解析后的日期
	 */
	public static Date parseDate(String dateString) {
		Date date = parse(dateString, "yyyy-MM-dd HH:mm:ss");
		if (date == null) {
			date = parse(dateString, "yyyy-MM-dd");
			if (date == null) {
				date = parse(dateString, "yyyy-MM");
				if (date == null) {
					date = parse(dateString, "yyyy");
				}
			}
		}
		return date;
	}

	/**
	 * 返回本月的最后一天
	 * 
	 * @return 本月最后一天的日期
	 */
	public static Date getMonthLastDay() {
		return getMonthLastDay(getNow());
	}

	/**
	 * 返回给定日期中的月份中的最后一天
	 * 
	 * @param date
	 *            基准日期
	 * @return 该月最后一天的日期
	 */
	public static Date getMonthLastDay(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 将日期设置为下一月第一天
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);

		// 减去1天，得到的即本月的最后一天
		calendar.add(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * 
	 * 功能描述：判断第一个日期是否大于第二个日期，大于返回true,否则返回false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * 
	 * @author 耿沫然
	 * 
	 * @since 2015年11月19日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isGreaterThan(Date date1, Date date2) {
		return date1.getTime() > date2.getTime();
	}

	/**
	 * 
	 * 功能描述：判断时间是否已是过去时
	 * 
	 * @param time
	 * @return
	 * 
	 * @author 耿沫然
	 * 
	 * @since 2015年11月19日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static boolean isExpiredTime(Date time) {
		return isGreaterThan(getNow(), time);
	}

	/*public static void main(String[] args) {
		String test = "2003-1-31";
		Date date;
		try {
			date = parse(test, "");

			System.out.println("得到当前日期 － getDate():" + DateUtil.getDate());
			System.out.println("得到当前日期时间 － getDateTime():" + DateUtil.getDateTime());

			System.out.println("得到当前年份 － getCurrentYear():" + DateUtil.getCurrentYear());
			System.out.println("得到当前月份 － getCurrentMonth():" + DateUtil.getCurrentMonth());
			System.out.println("得到当前日子 － getCurrentDay():" + DateUtil.getCurrentDay());

			System.out.println("解析 － parse(" + test + "):" + getDateTime(date, "yyyy-MM-dd"));

			System.out.println("自增月份 － addMonths(3):" + getDateTime(addMonths(3), "yyyy-MM-dd"));
			System.out.println("增加月份 － addMonths(" + test + ",3):" + getDateTime(addMonths(date, 3), "yyyy-MM-dd"));
			System.out.println("增加日期 － addDays(" + test + ",3):" + getDateTime(addDays(date, 3), "yyyy-MM-dd"));
			System.out.println("自增日期 － addDays(3):" + getDateTime(addDays(3), "yyyy-MM-dd"));

			System.out.println("比较日期 － diffDays():" + DateUtil.diffDays(DateUtil.getNow(), date));
			System.out.println("比较月份 － diffMonths():" + DateUtil.diffMonths(DateUtil.getNow(), date));

			System.out.println("得到" + test + "所在月份的最后一天:" + getDateTime(getMonthLastDay(date), "yyyy-MM-dd"));
			System.out.println(("" + DateUtil.getCurrentYear()).substring(2) + "000001");

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}*/

	/**
	 * 
	 * 功能描述：获得 yyyyMMdd这种格式的当天时间
	 * 
	 * @return String yyyyMMdd
	 * 
	 * @author 张小平
	 * 
	 * @since 2015年9月30日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getYYYYMMDD() {
		formater.applyPattern(YYYYMMDD);
		return formater.format(getNow());
	}

	/**
	 * 
	 * 功能描述：获得 YYYYMMDDHHMMSS这种格式的当天时间
	 * 
	 * @return String YYYYMMDDHHMMSS
	 * 
	 * @author 张小平
	 * 
	 * @since 2015年9月30日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getYYYYMMDDHHMMSS() {
		formater.applyPattern(YYYYMMDDHHMMSS);
		return formater.format(getNow());
	}

	/**
	 * 
	 * 功能描述：获取明日日期
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2016年6月6日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getTomorrowStr() {
		return getDate(getYesterday());
	}

	/**
	 * 
	 * 功能描述：获取明日日期
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2016年6月6日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@SuppressWarnings("static-access")
	public static Date getTomorrow() {
		Calendar calendar = getCalendar();
		calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * 
	 * 功能描述：获取昨日日期
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2016年6月6日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getYesterdayStr() {
		return getDate(getYesterday());
	}

	/**
	 * 
	 * 功能描述：获取昨日日期
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2016年6月6日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	@SuppressWarnings("static-access")
	public static Date getYesterday() {
		Calendar calendar = getCalendar();
		calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		return calendar.getTime();
	}

	/**
	 * 
	 * 功能描述：获得当天日历对象
	 * 
	 * @return
	 * 
	 * @author 张小平
	 * 
	 * @since 2016年6月6日
	 * 
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static Calendar getCalendar() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(getNow());
		return calendar;
	}

	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
}
