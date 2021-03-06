package cn.mapway.ui.client.tools;

import java.sql.Timestamp;
import java.util.Date;

import com.google.gwt.core.client.Callback;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;


/**
 * 客户工具集.
 *
 * @author zhangjianshe
 */
public class Clients {


    /**
     * 获取缺省的日期时间格式.
     *
     * @return the date time format
     */
    public static final DateTimeFormat getDateTimeFormat() {
        return getDateTimeFormat("yyyy年MM月dd日 HH:mm:ss");
    }

    /**
     * 获取日期时间格式.
     *
     * @param format the format
     * @return the date time format
     */
    public static final DateTimeFormat getDateTimeFormat(String format) {

        return DateTimeFormat.getFormat(format);
    }

    /**
     * 获取日期时间格式.
     *
     * @return the date format
     */
    public static final DateTimeFormat getDateFormat() {
        return getDateTimeFormat("yyyy年MM月dd日");
    }

    /**
     * Format timestamp.
     *
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(long time, String format) {
        return formatTimestamp(new Timestamp(time), format);
    }

    /**
     * Format timestamp.
     *
     * @param t      the t
     * @param format the format
     * @return the string
     */
    public static final String formatTimestamp(Timestamp t, String format) {
        if (t == null) {
            return "";
        }
        DateTimeFormat df;
        if (format == null || format.length() == 0) {
            df = getDateTimeFormat();
        } else {
            df = getDateTimeFormat(format);
        }
        return df.format(new Date(t.getTime()));
    }

    /**
     * Format timestamp.
     *
     * @param t the t
     * @return the string
     */
    public static final String formatTimestamp(Timestamp t) {
        return formatTimestamp(t, "");
    }

    /**
     * Format date.
     *
     * @param d the d
     * @return the string
     */
    public static final String formatDate(Date d) {
        if (d == null) {
            return "";
        }
        return formatDate(d, null);
    }


    /**
     * 随机字符串.
     *
     * @param len the len
     * @return the string
     */
    public static final native String randomString(int len) /*-{
		var len = len || 32;
		var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
		var maxPos = $chars.length;
		var pwd = '';
		for (i = 0; i < len; i++) {
			pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
		}
		return pwd;
  }-*/;

    /**
     * Parses the date.
     *
     * @param value  the value
     * @param format the format
     * @return the date
     */
    public static Date parseDate(String value, String format) {
        if (value == null || value.length() == 0) {
            return new Date();
        }
        DateTimeFormat df;
        if (format == null || format.length() == 0) {
            df = getDateTimeFormat();
        } else {
            df = getDateTimeFormat(format);
        }
        return df.parse(value);
    }

    /**
     * Parses the date.
     *
     * @param value the value
     * @return the date
     */
    public static Date parseDate(String value) {
        return parseDate(value, "");
    }

    /**
     * Now.
     *
     * @return the long
     */
    public static long now() {
        Date d = new Date();
        return d.getTime();
    }

    /**
     * The Constant DAY_MILLION.
     */
    public static final long DAY_MILLION = 24 * 60 * 60 * 1000;

    /**
     * Adds the day.
     *
     * @param time the time
     * @param day  the day
     * @return the long
     */
    public static long addDay(long time, int day) {
        return time + day * 24 * DAY_MILLION;
    }


    /**
     * 提供对话框.
     *
     * @param text     the text
     * @param callback the callback
     */
    public static void confirm(String text, Callback<Boolean, Boolean> callback) {
        boolean b = Window.confirm(text);
        if (b == true) {
            callback.onSuccess(true);
        } else {
            callback.onSuccess(false);
        }
    }


    /**
     * Format date string.
     *
     * @param value  the value
     * @param format the format
     * @return string
     */
    public static String formatDate(Date value, String format) {
        if (value == null) {
            return "";
        }
        if (format == null || format.length() == 0)
            return getDateFormat().format(value);
        else
            return getDateTimeFormat(format).format(value);
    }
}
