package com.mapway;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

import com.mapway.database2java.database.IConnectionPool;
import com.mapway.database2java.database.MyPool;

// TODO: Auto-generated Javadoc
/**
 * The Class Util.
 */
public class Util {
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static IConnectionPool getConnection() {
		return MyPool.getInstance("Oracle");
	}

	/**
	 * Write string to file.
	 *
	 * @param fn the fn
	 * @param dest the dest
	 */
	public static void WriteStringToFile(String fn, String dest) {

		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(fn);
			fo.write(dest.getBytes());
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Write to file.
	 *
	 * @param source the source
	 * @param dest the dest
	 * @return the string
	 */
	public static String writeToFile(File source, String dest) {
		String r = "true";
		FileOutputStream fo = null;
		FileInputStream fi = null;
		try {
			fo = new FileOutputStream(dest);
			fi = new FileInputStream(source);

			byte[] buffer = new byte[1024 * 8];

			int l = 0;
			l = fi.read(buffer);

			while (l > -1) {
				fo.write(buffer, 0, l);
				l = fi.read(buffer);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			r = e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			r = e.getMessage();
		} finally {
			try {
				fo.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return r;
	}

	/**
	 * Read from file.
	 *
	 * @param source the source
	 * @return the string builder
	 */
	public static StringBuilder readFromFile(File source) {

		StringBuilder sb = new StringBuilder();

		FileInputStream fi = null;
		try {
			fi = new FileInputStream(source);

			byte[] buffer = new byte[1024 * 8];

			int l = 0;
			l = fi.read(buffer);

			while (l > -1) {
				String line = new String(buffer, 0, l);
				sb.append(line);
				l = fi.read(buffer);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb;
	}

	/**
	 * Gets the local address.
	 *
	 * @return the local address
	 */
	public static String getLocalAddress() {
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "NO";
		}
		InetAddress ip = null;
		String ips = "";
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();

			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					ips += ip.getHostAddress() + " ";
				}
			}
		}
		return ips;
	}

	/**
	 * Gets the head icon path by id.
	 *
	 * @param id the id
	 * @return the head icon path by id
	 */
	public static String getHeadIconPathById(int id) {
		String si = String.valueOf(id);
		StringBuilder sb = new StringBuilder(32);

		for (int i = 0; i < si.length(); i++) {
			sb.append(si.charAt(i));
			sb.append("/");
		}
		return sb.toString();
	}

	/**
	 * Gets the diff year.
	 *
	 * @param start the start
	 * @param end the end
	 * @return the diff year
	 */
	public static int getDiffYear(java.sql.Timestamp start,
			java.sql.Timestamp end) {
		return end.getYear() - start.getYear();
	}

	/**
	 * Adds the time.
	 *
	 * @param start the start
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return the java.sql. timestamp
	 */
	public static java.sql.Timestamp addTime(java.sql.Timestamp start,
			int year, int month, int day) {
		return new java.sql.Timestamp(start.getTime() + year * 365 * 24 * 60
				* 60 * 1000 + month * 30 * 24 * 60 * 60 * 1000 + day * 24 * 60
				* 60 * 1000);
	}

	/**
	 * Gets the current SQL timestamp.
	 *
	 * @return the current SQL timestamp
	 */
	public static final java.sql.Timestamp getCurrentSQLTimestamp() {
		Calendar c = Calendar.getInstance();
		java.sql.Timestamp t = new java.sql.Timestamp(c.getTimeInMillis());
		return t;
	}

	/**
	 * To SQL timestamp.
	 *
	 * @param time the time
	 * @return the java.sql. timestamp
	 */
	public static final java.sql.Timestamp toSQLTimestamp(String time) {
		return java.sql.Timestamp.valueOf(time);
	}

	/**
	 * Date to SQL timestamp.
	 *
	 * @param date the date
	 * @return the timestamp
	 */
	public static Timestamp dateToSQLTimestamp(Date date) {
		if (date == null) {
			return java.sql.Timestamp.valueOf("2070-06-06 12:00:00");
		}
		String s = df.format(date);
		return java.sql.Timestamp.valueOf(s + " 12:00:00");
	}

	/**
	 * ȱʡ��DateFormat���󣬿��Խ�һ��java.util.Date��ʽ����yyyy-mm-dd���
	 */
	private static DateFormat df = DateFormat.getDateInstance(
			DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);

	/**
	 * Gets the now time.
	 *
	 * @return the now time
	 */
	public static String getNowTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM,
				Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * Gets the now date.
	 *
	 * @return the now date
	 */
	public static String getNowDate() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,
				Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * Gets the now date time.
	 *
	 * @return the now date time
	 */
	public static String getNowDateTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,
				DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * Gets the this year.
	 *
	 * @return the this year
	 */
	public static int getThisYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.YEAR);
	}

	/**
	 * Gets the this month.
	 *
	 * @return the this month
	 */
	public static int getThisMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MONTH) + 1;
	}

	/**
	 * Gets the to day of month.
	 *
	 * @return the to day of month
	 */
	public static int getToDayOfMonth() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.DAY_OF_MONTH);
	}

	/**
	 * Gets the hour.
	 *
	 * @return the hour
	 */
	public static int getHour() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.HOUR);
	}

	/**
	 * Gets the minute.
	 *
	 * @return the minute
	 */
	public static int getMinute() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.MINUTE);
	}

	/**
	 * Gets the second.
	 *
	 * @return the second
	 */
	public static int getSecond() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.SECOND);
	}

	/**
	 * Gets the to week of year.
	 *
	 * @return the to week of year
	 */
	public static int getToWeekOfYear() {
		GregorianCalendar gcNow = new GregorianCalendar();
		return gcNow.get(GregorianCalendar.WEEK_OF_YEAR);
	}

	/**
	 * Format date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String formatDate(java.util.Date date) {
		if (date == null)
			return "";
		else
			return df.format(date);
	}

	/**
	 * Format S date.
	 *
	 * @param date the date
	 * @return the string
	 */
	public static String formatSDate(java.util.Date date) {
		if (date == null)
			return "";
		else {
			SimpleDateFormat bartDateFormat = new SimpleDateFormat(
					"yyyy-M-d HH:mm:ss");
			return bartDateFormat.format(date);
		}
	}

	/**
	 * Date add.
	 *
	 * @param interval the interval
	 * @param number the number
	 * @param date the date
	 * @return the string
	 */
	public static String dateAdd(String interval, int number,
			java.util.Date date) {
		String strTmp = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		// ��������
		if (interval.equals("y")) {
			int currYear = gc.get(Calendar.YEAR);
			gc.set(Calendar.YEAR, currYear + number);
		}
		// ��������
		else if (interval.equals("m")) {
			int currMonth = gc.get(Calendar.MONTH);
			gc.set(Calendar.MONTH, currMonth + number);
		}
		// ��������
		else if (interval.equals("d")) {
			int currDay = gc.get(Calendar.DATE);
			gc.set(Calendar.DATE, currDay + number);
		}
		// ����Сʱ
		else if (interval.equals("h")) {
			int currDay = gc.get(Calendar.HOUR);
			gc.set(Calendar.HOUR, currDay + number);
		}
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-M-d HH:mm:ss");
		strTmp = bartDateFormat.format(gc.getTime());
		return strTmp;
	}

	/**
	 * Date diff.
	 *
	 * @param a the a
	 * @param b the b
	 * @return the int
	 */
	public static int dateDiff(java.util.Date a, java.util.Date b) {
		int tempDifference = 0;
		int difference = 0;
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();

		if (a.compareTo(b) < 0) {
			earlier.setTime(a);
			later.setTime(b);
		} else {
			earlier.setTime(b);
			later.setTime(a);
		}

		while (earlier.get(Calendar.YEAR) != later.get(Calendar.YEAR)) {
			tempDifference = 365 * (later.get(Calendar.YEAR) - earlier
					.get(Calendar.YEAR));
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		if (earlier.get(Calendar.DAY_OF_YEAR) != later
				.get(Calendar.DAY_OF_YEAR)) {
			tempDifference = later.get(Calendar.DAY_OF_YEAR)
					- earlier.get(Calendar.DAY_OF_YEAR);
			difference += tempDifference;

			earlier.add(Calendar.DAY_OF_YEAR, tempDifference);
		}

		return difference;
	}

	/**
	 * Gets the date.
	 *
	 * @param curYear the cur year
	 * @param curMonth the cur month
	 * @param curDate the cur date
	 * @return the date
	 */
	public static int getDate(int curYear, int curMonth, int curDate) {
		int day1 = 0;
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(curYear, curMonth - 1, curDate);
		int dayOfWeek = cal.get(cal.DAY_OF_WEEK);
		System.out.println("curDate=" + curDate + " dayOfWeek " + dayOfWeek);
		switch (dayOfWeek) {
		case 1: // ������
			day1 = 0;
			break;
		case 2: // ����һ
			day1 = 1;
			break;
		case 3: // ���ڶ�
			day1 = 2;
			break;
		case 4: // ������
			day1 = 3;
			break;
		case 5: // ������
			day1 = 4;
			break;
		case 6: // ������
			day1 = 5;
			break;
		case 7: // ������
			day1 = 6;
			break;
		}
		return day1;
	}

	/**
	 * Check time.
	 *
	 * @param id the id
	 * @return the string
	 */
	public static String checkTime(int id) {
		String bol = "";
		Calendar tt = Calendar.getInstance();
		String currDate = getNowDate();
		System.out.println("currDate=" + currDate);
		int result = tt.get(Calendar.DAY_OF_WEEK);

		int shour = tt.get(Calendar.HOUR_OF_DAY);

		if (id == 3) {
			switch (result) {
			case 1:
				break;
			case 7:
				if ((shour >= 8) && (shour < 12)) {
					bol = "disabled";
					break;
				}
			default:
				if ((shour >= 8) && (shour < 12)) {
					bol = "disabled";
					break;
				} else if ((shour >= 14) && (shour < 17)) {
					bol = "disabled";
					break;
				}
			}
		}
		return bol;
	}

	/**
	 * Gets the str date.
	 *
	 * @param strX the str X
	 * @return the str date
	 */
	public static Date getStrDate(String strX) {
		Date date1 = new Date();
		if (!strX.equals("")) {
			try {
				date1 = (DateFormat.getDateInstance()).parse(strX);
			} catch (Exception ex) {

				System.out.println(ex.toString());
			}
		} else {
			GregorianCalendar gcNow = new GregorianCalendar();
			date1 = gcNow.getTime();
		}

		return date1;
	}

	/**
	 *
	 * @param d1 the d 1
	 * @param d2 the d 2
	 * @return int
	 */
	public static int compareDate(String d1, String d2) {
		short vl = 1;
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(getStrDate(d1));
		int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);
		int day = gc.get(GregorianCalendar.DAY_OF_MONTH);
		gc.setTime(getStrDate(d2));
		int tempYear = gc.get(GregorianCalendar.YEAR);
		int tempMonth = gc.get(GregorianCalendar.MONTH);
		int tempDay = gc.get(GregorianCalendar.DAY_OF_MONTH);
		if (year != tempYear) {
			if (year > tempYear)
				vl = 2;
			else
				vl = 0;
		} else {
			if (month != tempMonth) {
				if (month > tempMonth)
					vl = 2;
				else
					vl = 0;
			} else {
				if (day != tempDay) {
					if (day > tempDay)
						vl = 2;
					else
						vl = 0;
				}
			}
		}
		return vl;
	}

	/**
	 * Gets the date string.
	 *
	 * @param l the l
	 * @param format the format
	 * @return the date string
	 */
	public static String getDateString(long l, String format) {
		String v = new SimpleDateFormat(format).format(new Date(l));
		return v;
	}

	/**
	 * Pseudo-random number generator object for use with randomString(). The
	 * Random class is not considered to be cryptographically secure, so only
	 * use these random Strings for low to medium security applications.
	 */
	private static Random randGen = new Random();

	/**
	 * Array of numbers and letters of mixed case. Numbers appear in the list
	 * twice so that there is a more equal chance that a number will be picked.
	 * We can use the array to get a random number or letter by picking a random
	 * array index.
	 */
	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	/**
	 * Returns a random String of numbers and letters (lower and upper case) of
	 * the specified length. The method uses the Random class that is built-in
	 * to Java which is suitable for low to medium grade security uses. This
	 * means that the output is only pseudo random, i.e., each number is
	 * mathematically generated so is not truly random.
	 * <p>
	 * 
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * Random digital.
	 *
	 * @param length the length
	 * @return the string
	 */
	public static final String randomDigital(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	/**
	 * Random number.
	 *
	 * @param max the max
	 * @return the int
	 */
	public static final int randomNumber(int max) {
		return randGen.nextInt(max);
	}

	/**
	 * Gets the string.
	 *
	 * @param o the o
	 * @return the string
	 */
	public static String getString(Object o) {
		if (o == null) {
			return "NULL";
		} else {
			return o.toString();
		}
	}

	/**
	 * Format integer.
	 *
	 * @param i the i
	 * @param j the j
	 * @return the string
	 */
	public static String formatInteger(int i, int j) {
		String frm = "%0" + j + "d";
		String s;
		s = String.format(frm, i);
		return s;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		GregorianCalendar gcNow = new GregorianCalendar();
		java.util.Date dNow = gcNow.getTime();

		String t = Util.getLocalAddress();
		System.out.println(t);
	}
}
