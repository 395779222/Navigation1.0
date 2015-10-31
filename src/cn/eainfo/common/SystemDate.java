package cn.eainfo.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 定义系统中对日期的处理工作 作者 金贤敏 日期 2010-07-20 版本 1.0
 * 
 */
public class SystemDate {
	
	public static String getMonth(String month){
		Map map = new HashMap();
		map.put("01", "一月");
		map.put("02", "二月");
		map.put("03", "三月");
		map.put("04", "四月");
		map.put("05", "五月");
		map.put("06", "六月");
		map.put("07", "七月");
		map.put("08", "八月");
		map.put("09", "九月");
		map.put("10", "十月");
		map.put("11", "十一月");
		map.put("12", "十二月");
		return (String) map.get(month);
	}
	/**
	 * 获取当前系统日期，如：“yyyy-MM-dd”
	 * 
	 * @return 日期字符串
	 */
	public static String formatDate(Date date) {
		String datevalue = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (date != null) {
			datevalue = sdf.format(date);
		}

		return datevalue;
	}
	
	public static String formatDateymn(Date date) {
		String datevalue = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if (date != null) {
			datevalue = sdf.format(date);
		}

		return datevalue;
	}
	/**
	 * 获取当前系统日期，如：“yyyy-MM-dd HH:mm:ss”
	 * 
	 * @return 日期字符串
	 */
	public static String formatDates(Date date) {
		String datevalue = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			datevalue = sdf.format(date);
		}

		return datevalue;
	}
	
	public static Date getNowDateShort() {  
		    Date currentTime = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		    String dateString = formatter.format(currentTime);  
		    ParsePosition pos = new ParsePosition(8);  
		    Date currentTime_2 = formatter.parse(dateString, pos);  
		    return currentTime_2;  
	}


	/**
	 * 获取日期，如：“2006-08-01”
	 * 
	 * @return 日期字符串
	 */
	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(new Date());
		date = date.substring(0, 10);
		return date;
	}
	public static String getNewDate(Date date,int length) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date2 = sdf.format(date);
		date2 = date2.substring(0, length);
		return date2;
	}

	/**
	 * 获取日期date的下一天，如：date为“2006-08-01”，方法返回结果为“2006-08-02”
	 * 
	 * @param date
	 * @return 下一天的日期
	 */
	public static String getNextDate(String date) {
		String[] dateArr = date.split("-");
		int day = Integer.parseInt(dateArr[2]);
		if (day < 28) {
			day++;
			if (day < 10) {
				date = dateArr[0] + "-" + dateArr[1] + "-0" + day;
			} else {
				date = dateArr[0] + "-" + dateArr[1] + "-" + day;
			}
		} else if (day == 28) {
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]);
			if (month == 2) {
				if (year % 4 == 0 && year % 100 == 0) {
					day++;
					date = dateArr[0] + "-" + dateArr[1] + "-" + day;
				} else {
					date = dateArr[0] + "-03-01";
				}
			} else {
				day++;
				date = dateArr[0] + "-" + dateArr[1] + "-" + day;
			}
		} else if (day == 29) {
			int month = Integer.parseInt(dateArr[1]);
			if (month == 2) {
				date = dateArr[0] + "-03-01";
			} else {
				day++;
				date = dateArr[0] + "-" + dateArr[1] + "-" + day;
			}
		} else if (day == 30) {
			int month = Integer.parseInt(dateArr[1]);
			if (month == 1 || month == 3 || month == 5 || month == 7
					|| month == 8 || month == 10 || month == 12) {
				day++;
				date = dateArr[0] + "-" + dateArr[1] + "-" + day;
			} else {
				month++;
				if (month < 10) {
					date = dateArr[0] + "-0" + month + "-01";
				} else {
					date = dateArr[0] + "-" + month + "-01";
				}
			}
		} else {
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1]);
			if (month == 12) {
				year++;
				date = year + "-01-01";
			} else {
				month++;
				if (month < 10) {
					date = dateArr[0] + "-0" + month + "-01";
				} else {
					date = dateArr[0] + "-" + month + "-01";
				}
			}
		}
		return date;
	}

	/**
	 * 格式化日期字符串,格式化后如:YYYY-MM-DD
	 * 
	 * @param date
	 * @return String
	 */
	public static String formateDate(String date) {
		StringBuffer str = new StringBuffer();
		if (date != null) {
			String[] dateArr = date.split("-");
			if (dateArr.length == 3) {
				str.append(dateArr[0]);
				str.append('-');
				if (dateArr[1].length() == 1) {
					str.append('0');
				}
				str.append(dateArr[1]);
				str.append('-');
				if (dateArr[2].length() == 1) {
					str.append('0');
				}
				str.append(dateArr[2]);
			} else {
				str.append(date);
			}
		}
		return str.toString();
	}

	/**
	 * 获取日期date的前一天，如：date为“2006-08-01”，方法返回结果为“2006-07-31”
	 * 
	 * @param date
	 * @return 前一天的日期
	 */
	public static String getPreDate(String date) {
		String[] dateArr = date.split("-");
		int day = Integer.parseInt(dateArr[2]);
		if (day > 1) {
			day--;
			if (day < 10) {
				date = dateArr[0] + "-" + dateArr[1] + "-0" + day;
			} else {
				date = dateArr[0] + "-" + dateArr[1] + "-" + day;
			}
		} else {
			int month = Integer.parseInt(dateArr[1]);
			if (month == 3) {
				int year = Integer.parseInt(dateArr[0]);

				if (year % 4 == 0 && year % 100 == 0) {
					date = year + "-02-29";
				} else {
					date = year + "-02-28";
				}
			} else if (month == 2 || month == 4 || month == 6 || month == 8
					|| month == 9 || month == 11) {
				month--;
				if (month < 10) {
					date = dateArr[0] + "-0" + month + "-31";
				} else {
					date = dateArr[0] + "-" + month + "-31";
				}
			} else if (month == 1) {
				int year = Integer.parseInt(dateArr[0]);
				year--;
				date = year + "-12-31";
			} else {
				month--;
				if (month < 10) {
					date = dateArr[0] + "-0" + month + "-30";
				} else {
					date = dateArr[0] + "-" + month + "-30";
				}
			}
		}
		return date;
	}

	/**
	 * 获取当前系统时间，如：“11:01:23”
	 * 
	 * @return 时间字符串
	 */
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		time = time.substring(11);
		return time;
	}

	/**
	 * 获取当前系统年份
	 * 
	 * @return 年份的整型数
	 */
	public static int getYear() {
		String date =getDate();
		int n = date.indexOf("-");
		String year = "";
		if (n >= 0) {
			year = date.substring(0, n);
		}
		return Integer.parseInt(year);
	}

	/**
	 * 获取当前系统的月份
	 * 
	 * @return 月份的整型数
	 */
	public static int getMonth() {
		String date =getDate();
		int n1 = date.indexOf("-");
		int n2 = date.lastIndexOf("-");
		String month = "";
		if (n1 >= 0 && n2 >= 0 && n1 < n2) {
			month = date.substring(n1 + 1, n2);
		}
		return Integer.parseInt(month);
	}

	/**
	 * 获取当前系统小时
	 * 
	 * @return 小时点
	 */
	public static String getHour() {
		String time = getTime();
		int n = time.indexOf(':');
		String hour = "00";
		if (n >= 0) {
			hour = time.substring(0, n);
		}
		return hour;
	}

	/**
	 * 获取当前系统分钟
	 * 
	 * @return 分钟点
	 */
	public static String getMinute() {
		String time = getTime();
		int n1 = time.indexOf(':');
		int n2 = time.lastIndexOf(':');
		String minute = "00";
		if (n1 >= 0 && n2 >= 0 && n1 < n2) {
			minute = time.substring(n1 + 1, n2);
		}
		return minute;
	}

	/**
	 * 获取当前系统秒钟
	 * 
	 * @return 秒钟点
	 */
	public static String getSecond() {
		String time = getTime();
		int n = time.lastIndexOf(':');
		String second = "1";
		if (n >= 0) {
			second = time.substring(n + 1);
		}
		return second;
	}

	/**
	 * 获取当前系统时间，只精确到分钟，不包含秒数
	 * 
	 * @return 小时和分钟
	 */
	public static String getHourAndMinute() {
		String time = getTime();
		int n = time.lastIndexOf(':');
		String str = "00:00";
		if (n >= 0) {
			str = time.substring(0, n);
		}
		return str;
	}

	/**
	 * 获取当前系统日期和时间组合，如："20000101010101"，"20010501123912"
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 获取当前系统的某一天
	 * 
	 * @return 具体天的整型数
	 */
	public static int getDay() {
		String date =getDate();
		int n = date.lastIndexOf("-");
		String day = "";
		if (n >= 0) {
			day = date.substring(n + 1);
		}
		return Integer.parseInt(day);
	}

	public static String getLastDayOfLastMonth(String d) {
		String date = d.substring(0, 10);
		String[] dateArr = date.split("-");
		Calendar calendar = new GregorianCalendar();
		calendar.set(Integer.parseInt(dateArr[0].trim()),
				Integer.parseInt(dateArr[1].trim()) - 1,
				Integer.parseInt(dateArr[2].trim()));
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long mils = calendar.getTime().getTime();
		mils -= 24 * 60 * 60 * 1000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date(mils));
	}

	public static String getLastDayOfMonth(String d) {
		Calendar cDay1 = Calendar.getInstance();
		String date = d.substring(0, 10);
		String[] dateArr = date.split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(dateArr[0].trim()),
				Integer.parseInt(dateArr[1].trim()) - 1,
				Integer.parseInt(dateArr[2].trim()));
		cDay1.setTime(cal.getTime());
		final int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = cDay1.getTime();
		lastDate.setDate(lastDay);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(lastDate);

	}

	public static int getDayOfWeek(String d) {
		Calendar cal = Calendar.getInstance();
		String[] dateArr = d.split("-");
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Integer.parseInt(dateArr[0].trim()),
				Integer.parseInt(dateArr[1].trim()) - 1,
				Integer.parseInt(dateArr[2].trim()));

		int posOfWeek = cal1.get(Calendar.DAY_OF_WEEK);
		posOfWeek--;
		return posOfWeek;
	}

	public static String getDateOffsetDate(String d, int offset) {
		Calendar c = Calendar.getInstance();
		String[] dateArr = d.split("-");
		int month = Integer.parseInt(dateArr[1].trim()) - 1;
		c.set(Integer.parseInt(dateArr[0].trim()), month,
				Integer.parseInt(dateArr[2].trim()));
		c.add(Calendar.DATE, offset);
		Date old = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(old);
	}

	public static int compareToDate(String date1, String date2) {
		String[] d1 = date1.split("-");
		String[] d2 = date2.split("-");
		int n = 0;
		int d11;
		int d22;
		for (int i = 0; i < d1.length; i++) {
			d11 = Integer.parseInt(d1[i]);
			d22 = Integer.parseInt(d2[i]);
			if (d11 > d22) {
				n = 1;
				break;
			} else if (d11 < d22) {
				n = -1;
				break;
			}
		}
		return n;
	}

	public static int compareToTime(String time1, String time2) {
		String[] t1 = time1.split(":");
		String[] t2 = time2.split(":");
		int n = 0;
		int t11;
		int t22;
		for (int i = 0; i < t1.length; i++) {
			t11 = Integer.parseInt(t1[i]);
			t22 = Integer.parseInt(t2[i]);
			if (t11 > t22) {
				n = 1;
				break;
			} else if (t11 < t22) {
				n = -1;
				break;
			}
		}
		return n;
	}

	public static String getCHNDayOfWeek(String date_str) {
		SystemDate sysdate = new SystemDate();
		int xingQi = sysdate.getDayOfWeek(date_str);
		String week = "";
		switch (xingQi) {
		case 0:
			week = "星期天";
			break;
		case 1:
			week = "星期一";
			break;
		case 2:
			week = "星期二";
			break;
		case 3:
			week = "星期三";
			break;
		case 4:
			week = "星期四";
			break;
		case 5:
			week = "星期五";
			break;
		case 6:
			week = "星期六";
			break;
		default:
			week = "    ";
			break;
		}
		return week;
	}

	/**
	 * 计算日期求和
	 * 
	 * @crrDate 初始日期
	 * @count 计算值
	 * @type 计算类型 1按天 2按月
	 * @return String
	 */
	public static String sumDate(String crrDate, int count, int type) {
		String returnString = "";
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			Date date = simpleDateFormat.parse(crrDate);
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			if (type == 1) {
				calender.add(Calendar.DATE, count);
			} else {
				calender.add(Calendar.MONTH, count);
			}
			simpleDateFormat.format(calender.getTime());
			returnString = simpleDateFormat.format(calender.getTime())
					.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnString;
	}

	/**
	 * 计算当前年份月份的天数
	 * 
	 * @year 年份
	 * @month 月份
	 * @return int天数
	 */
	public static int dateCount(int year, int month) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			if (month == 2) {
				return 29;
			}
		}
		Map dayMap = new HashMap();
		dayMap.put(1, 31);
		dayMap.put(2, 28);
		dayMap.put(3, 31);
		dayMap.put(4, 30);
		dayMap.put(5, 31);
		dayMap.put(6, 30);
		dayMap.put(7, 31);
		dayMap.put(8, 31);
		dayMap.put(9, 30);
		dayMap.put(10, 31);
		dayMap.put(11, 30);
		dayMap.put(12, 31);
		return (Integer) dayMap.get(month);
	}

	public static void main(String[] args) {

		System.out.println(getNowDateShort());
	}

}
