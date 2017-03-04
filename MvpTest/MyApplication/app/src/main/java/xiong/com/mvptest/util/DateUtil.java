package xiong.com.mvptest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	private static final long INTERVAL_IN_MILLISECONDS = 30 * 1000;
	
	public static String READY_TIME = "M月 dd日   HH:mm 开拍";
	public static String BID_TIME = "M月 dd日   HH:mm 结拍";
	public static String END_TIME = "M月 dd日   HH:mm 结拍";
	public static String AUCTION_LOG_TIME = "MM-dd日   HH:mm:ss";
	public static String getTimestampString(Date messageDate) {
		String format = null;
		long messageTime = messageDate.getTime();
		if (isSameDay(messageTime)) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(messageDate);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour > 17) {
				format = "晚上 hh:mm:ss";
			} else if (hour >= 0 && hour <= 6) {
				format = "凌晨 hh:mm:ss";
			} else if (hour > 11 && hour <= 17) {
				format = "下午 hh:mm:ss";
			} else {
				format = "上午 hh:mm:ss";
			}
		} else if (isYesterday(messageTime)) {
			format = "昨天 HH:mm:ss";
		} else {
			format = "yyyy年M月d日 HH:mm";
		}
		return new SimpleDateFormat(format, Locale.CHINA).format(messageDate);
	}

	public static boolean isSameYear(long time) {
		Calendar calendar = GregorianCalendar.getInstance();

		int y = calendar.get(Calendar.YEAR);
		calendar.setTimeInMillis(time);
		int year = calendar.get(Calendar.YEAR);

		return y == year;
	}

	public static String getTimestampString(long time) {
		String format = null;
		Date date = new Date(time);
		if (isSameDay(time)) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			if (hour > 17) {
				format = "晚上 hh:mm";
			} else if (hour >= 0 && hour <= 6) {
				format = "凌晨 hh:mm";
			} else if (hour > 11 && hour <= 17) {
				format = "下午 hh:mm";
			} else {
				format = "上午 hh:mm";
			}
		} else if (isYesterday(time)) {
			format = "昨天 HH:mm";
		} else if (isSameYear(time)) {
			format = "MM月dd日 HH:mm";
		} else {
			format = "yyyy年MM月dd日 HH:mm";
		}
		return new SimpleDateFormat(format, Locale.CHINA).format(date);
	}

	public static String getFocusDateString(long time) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(time);
		return String.format("%4d-%2d-%2d", calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static String getBirthString(long time) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(time);
		return String.format("%4d年%2d月%2d日", calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH) + 1,
				calendar.get(Calendar.DAY_OF_MONTH));
	}

	public static boolean isCloseEnough(long time1, long time2) {
		// long time1 = date1.getTime();
		// long time2 = date2.getTime();
		long delta = time1 - time2;
		if (delta < 0) {
			delta = -delta;
		}
		return delta < INTERVAL_IN_MILLISECONDS;
	}

	private static boolean isSameDay(long inputTime) {

		TimeInfo tStartAndEndTime = getTodayStartAndEndTime();
		if (inputTime > tStartAndEndTime.getStartTime()
				&& inputTime < tStartAndEndTime.getEndTime())
			return true;
		return false;
	}

	private static boolean isYesterday(long inputTime) {
		TimeInfo yStartAndEndTime = getYesterdayStartAndEndTime();
		if (inputTime > yStartAndEndTime.getStartTime()
				&& inputTime < yStartAndEndTime.getEndTime())
			return true;
		return false;
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String formatDate(long time, String format) {
		Date date = new Date(((long)time)*1000);
		return new SimpleDateFormat(format, Locale.CHINA).format(date);
	}

	/**
	 * 
	 * @param timeLength
	 *            Millisecond
	 * @return
	 */
	public static String toTime(int timeLength) {
		timeLength /= 1000;
		int minute = timeLength / 60;
		int hour = 0;
		if (minute >= 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		int second = timeLength % 60;
		// return String.format("%02d:%02d:%02d", hour, minute, second);
		return String.format("%02d:%02d", minute, second);
	}

	/**
	 * 
	 * @param timeLength
	 *            second
	 * @return
	 */
	public static String toTimeBySecond(int timeLength) {
		// timeLength /= 1000;
		int minute = timeLength / 60;
		int hour = 0;
		if (minute >= 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		int second = timeLength % 60;
		// return String.format("%02d:%02d:%02d", hour, minute, second);
		return String.format("%02d:%02d", minute, second);
	}

	public static TimeInfo getYesterdayStartAndEndTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DATE, -1);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);

		Date startDate = calendar1.getTime();
		long startTime = startDate.getTime();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -1);
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		calendar2.set(Calendar.MILLISECOND, 999);
		Date endDate = calendar2.getTime();
		long endTime = endDate.getTime();
		TimeInfo info = new TimeInfo();
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		return info;
	}

	public static TimeInfo getTodayStartAndEndTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar1.getTime();
		long startTime = startDate.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss S");

		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		calendar2.set(Calendar.MILLISECOND, 999);
		Date endDate = calendar2.getTime();
		long endTime = endDate.getTime();
		TimeInfo info = new TimeInfo();
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		return info;
	}

	public static TimeInfo getBeforeYesterdayStartAndEndTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DATE, -2);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar1.getTime();
		long startTime = startDate.getTime();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.DATE, -2);
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		calendar2.set(Calendar.MILLISECOND, 999);
		Date endDate = calendar2.getTime();
		long endTime = endDate.getTime();
		TimeInfo info = new TimeInfo();
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		return info;
	}

	/**
	 * endtime为今天
	 * 
	 * @return
	 */
	public static TimeInfo getCurrentMonthStartAndEndTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.DATE, 1);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar1.getTime();
		long startTime = startDate.getTime();

		Calendar calendar2 = Calendar.getInstance();
		// calendar2.set(Calendar.HOUR_OF_DAY, 23);
		// calendar2.set(Calendar.MINUTE, 59);
		// calendar2.set(Calendar.SECOND, 59);
		// calendar2.set(Calendar.MILLISECOND, 999);
		Date endDate = calendar2.getTime();
		long endTime = endDate.getTime();
		TimeInfo info = new TimeInfo();
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		return info;
	}

	public static TimeInfo getLastMonthStartAndEndTime() {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.MONTH, -1);
		calendar1.set(Calendar.DATE, 1);
		calendar1.set(Calendar.HOUR_OF_DAY, 0);
		calendar1.set(Calendar.MINUTE, 0);
		calendar1.set(Calendar.SECOND, 0);
		calendar1.set(Calendar.MILLISECOND, 0);
		Date startDate = calendar1.getTime();
		long startTime = startDate.getTime();

		Calendar calendar2 = Calendar.getInstance();
		calendar2.add(Calendar.MONTH, -1);
		calendar2.set(Calendar.DATE, 1);
		calendar2.set(Calendar.HOUR_OF_DAY, 23);
		calendar2.set(Calendar.MINUTE, 59);
		calendar2.set(Calendar.SECOND, 59);
		calendar2.set(Calendar.MILLISECOND, 999);
		calendar2.roll(Calendar.DATE, -1);
		Date endDate = calendar2.getTime();
		long endTime = endDate.getTime();
		TimeInfo info = new TimeInfo();
		info.setStartTime(startTime);
		info.setEndTime(endTime);
		return info;
	}

	public static String getTimestampStr() {
		return Long.toString(System.currentTimeMillis());
	}

	public static class TimeInfo {
		private long startTime;
		private long endTime;

		public TimeInfo() {

		}

		public long getStartTime() {
			return startTime;
		}

		public void setStartTime(long startTime) {
			this.startTime = startTime;
		}

		public long getEndTime() {
			return endTime;
		}

		public void setEndTime(long endTime) {
			this.endTime = endTime;
		}
	}
	 
}
