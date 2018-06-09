package cn.ennwifi.webframe.tools;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间辅助类.
 *
 * @author zhangjianshe
 */
public class Times {


    /**
     * 解析时间数据.
     *
     * @param data   the data
     * @param format the format
     * @return the timestamp
     */
    public static final Timestamp parseTime(String data, String format) {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return new Timestamp(df.parse(data).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return now();
        }
    }

    /**
     * Parses the time.
     *
     * @param data the data
     * @return the timestamp
     */
    public static final Timestamp parseTime(String data) {
        return parseTime(data, null);
    }

    /**
     * Parses the time.
     *
     * @param appStartTime the app start time
     * @return the timestamp
     */
    public static Timestamp parseTime(Long appStartTime) {
        return new Timestamp(appStartTime);
    }

    /**
     * Format time.
     *
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(long time, String format) {

        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        // df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return df.format(new Date(time));
    }

    /**
     * Format time.
     *
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(Date time, String format) {

        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (time == null) {
            time = new Date();
        }
        return df.format(time);
    }

    /**
     * Format time.
     *
     * @param time the time
     * @return the string
     */
    public static final String formatTime(long time) {
        return formatTime(time, "");
    }

    /**
     * Format time.
     *
     * @param time the time
     * @return the string
     */
    public static final String formatTime(Timestamp time) {
        if (time == null) {
            return "";
        }
        return formatTime(time.getTime(), "");
    }

    /**
     * Format time.
     *
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(Timestamp time, String format) {
        if (time == null) {
            return "";
        }
        return formatTime(time.getTime(), format);
    }

    /**
     * 当前的时间.
     *
     * @return the timestamp
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * Format date.
     *
     * @param date the date
     * @return the string
     */
    public static String formatDate(java.sql.Date date) {
        if (date == null) {
            return "";
        } else {
            return formatTime(date.getTime());
        }
    }

    /**
     * 解析时间格式为 Calendar 对象.
     *
     * @param datetime 时间字符串
     * @return Calendar calendar
     */
    public static Calendar parseTimeCalendar(String datetime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseTime(datetime).getTime());
        return calendar;
    }

    /**
     * 解析时间格式为 Calendar 对象.
     *
     * @param datetime 时间字符串
     * @param format   时间格式字符串
     * @return Calendar calendar
     */
    public static Calendar parseTimeCalendar(String datetime, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(parseTime(datetime, format).getTime());
        return calendar;
    }
    /**
     * 将日期转换为8760小时中的小时忽略闰年
     */
    /**
     * 获取某日期的天数.
     *
     * @param time the time
     * @return day by date
     */
    public static Integer getDayByDate(String time) {
        Integer nData = 0;
        try {
            DateFormat fm = new SimpleDateFormat("MM-dd");
            Date date = fm.parse(time);
            String str = String.format("%tj", date);
            nData = Integer.parseInt(str);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nData;
    }

    /**
     * 获取2个日期间隔天数。
     *
     * @param dateStart the date start
     * @param dateEnd   the date end
     * @return space day by date
     * @throws ParseException the parse exception
     */
    public static Integer getSpaceDayByDate(String dateStart, String dateEnd) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        Date startDate = sdf.parse(dateStart);
        Date endDate = sdf.parse(dateEnd);
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        long timeStart = cal.getTimeInMillis();
        cal.setTime(endDate);
        long timeEnd = cal.getTimeInMillis();
        Integer between_days = (int) ((timeEnd - timeStart) / (1000 * 3600 * 24));

        return between_days;
    }

    /**
     * Add datehours string.
     *
     * @param day the day
     * @param x   the x
     * @return the string
     */
    public static String addDatehours(String day, int x)//返回的是字符串型的时间，输入的
    //是String day, int x
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");// 24小时制
        //引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
        //量day格式一致
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, x);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);

    }
}
