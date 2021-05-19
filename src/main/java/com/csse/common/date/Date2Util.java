package com.csse.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间转换工具
 */
public class Date2Util {
    public final static SimpleDateFormat g_SimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat g_SimpleDateFormat_I = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat g_SimpleDateFormat_II = new SimpleDateFormat("yyyyMM");
    public final static SimpleDateFormat g_SimpleDateFormat_III = new SimpleDateFormat("yyyy.MM.dd");
    public final static SimpleDateFormat sdfDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_I = new SimpleDateFormat("yyyyMMddHHmmss");
    public final static SimpleDateFormat sdfDateTimeFormat_IIII = new SimpleDateFormat("HH:mm:ss");
    public final static SimpleDateFormat sdfDateTimeFormat_YYYY = new SimpleDateFormat("yyyy");

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 返回日期格式(yyyy-MM-dd HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String toDateTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDateTimeFormat.format(date);
    }

    /**
     * 返回日期格式(yyyyMMddHHmmss)
     *
     * @param date
     * @return
     */
    public static String toDateTimeStr2(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDateTimeFormat_I.format(date);
    }

    /**
     * 返回日期格式(yyyy-MM-dd)
     *
     * @param date
     * @return
     */
    public static String toDateStr(Date date) {
        if (date == null) {
            return "";
        }
        return g_SimpleDateFormat_I.format(date);
    }

    /**
     * 返回时间格式(HH:mm:ss)
     *
     * @param date
     * @return
     */
    public static String toTimeStr(Date date) {
        if (date == null) {
            return "";
        }
        return sdfDateTimeFormat_IIII.format(date);
    }

    /**
     * 返回日期格式(yyyyMMdd)
     *
     * @param date
     * @return
     */
    public static String toDateStr2(Date date) {
        if (date == null) {
            return "";
        }
        return g_SimpleDateFormat.format(date);
    }

    /**
     * 返回日期格式(yyyyMM)
     *
     * @param date
     * @return
     */
    public static String toDateStr3(Date date) {
        if (date == null) {
            return "";
        }
        return g_SimpleDateFormat_II.format(date);
    }

    /**
     * <p>
     * 得到xxxx年xx月xx日 日期格式。
     * </p>
     *
     * @param date
     * @return
     */
    public static String toChinaDateStr(Date date) {
        if (date == null) {
            return "";
        }
        // 得到yyyy-mm-dd格式日期格式
        String dateStr = toDateStr(date);
        StringBuffer sb = new StringBuffer();
        if (dateStr != null && dateStr.length() > 0) {
            String[] newStr = dateStr.split("-");
            // 得到月
            int month = Integer.valueOf(newStr[1]);
            // 得到日
            int day = Integer.valueOf(newStr[2]);
            sb.append(newStr[0]).append("年");
            sb.append(month).append("月").append(day).append("日");
        }
        return sb.toString();
    }

    /**
     * <p>
     * 获取当前系统时间的小时数
     * </p>
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * <p>
     * 获取当前系统时间的分钟数
     * </p>
     *
     * @return
     */
    public static int getCurrentMinutes() {
        Calendar calendar = new GregorianCalendar();
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * <p>
     * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
     * </p>
     *
     * @return
     */
    public static String getMonthFirstDay() {
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = 0;
        if (day == 1) {
            calendar.add(Calendar.MONTH, -1);
        }
        month = calendar.get(Calendar.MONTH);
        if (month < 10) {
            return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
        } else {
            return "" + calendar.get(Calendar.YEAR) + month + "01";
        }
    }

    public static Date getFristDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    public static Date getLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    /**
     * <p>
     * 获取当前时间前几天或后几天的日期
     * </p>
     *
     * @return
     */
    public static Date getAddDays(int days) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * <p>
     * 获取某个月后的日期格式（yyyyMMdd）
     * </p>
     *
     * @return
     */
    public static String getAfterMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.MONTH, monthNum);
        return g_SimpleDateFormat.format(calendar.getTime());
    }

    /**
     * <p>
     * 返回日期（格式yyyyMMdd）
     * </p>
     *
     * @param timeMillis
     * @return
     */
    public static String getFormatDate(long timeMillis) {
        return sdfDateTimeFormat_I.format(new Date(timeMillis));
    }

    /**
     * 获取当前系统时间距离传入时间的毫秒数
     *
     * @param strTime 格式[ DD:00:00]
     * @return
     * @throws ParseException
     */
    public static long getSleepTime(String strTime) throws ParseException {
        long p = 1;
        long l_date = System.currentTimeMillis();
        Date date_now = new Date(l_date);
        String strDate = g_SimpleDateFormat_I.format(date_now) + strTime;
        if (date_now.before(sdfDateTimeFormat.parse(strDate)))
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        else {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date_now);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            Date date = calendar.getTime();
            strDate = g_SimpleDateFormat_I.format(date) + strTime;
            p = (sdfDateTimeFormat.parse(strDate)).getTime() - l_date;
        }
        return p;
    }

    public static String getPredate() {
        Date nowDate = new Date();
        String nowdates = g_SimpleDateFormat_I.format(nowDate);
        String[] dates = nowdates.split("-");
        int year = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int day = Integer.parseInt(dates[2]) - 1;
        if (day == 0) {
            switch (month - 1) {
                case 1:
                    day = 31;
                    break;
                case 3:
                    day = 31;
                    break;
                case 5:
                    day = 31;
                    break;
                case 7:
                    day = 31;
                    break;
                case 8:
                    day = 31;
                    break;
                case 10:
                    day = 31;
                    break;
                case 0:
                    month = 13;
                    year = year - 1;
                    day = 31;
                    break;
                case 4:
                    day = 30;
                    break;
                case 6:
                    day = 30;
                    break;
                case 9:
                    day = 30;
                    break;
                case 11:
                    day = 30;
                    break;
                case 2:
                    if (year % 4 == 0) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                    break;
                default:
                    break;
            }
            month = month - 1;
        }
        String predate = Integer.toString(year) + "-"
                + (month < 10 ? "0" + month : month) + "-"
                + (day < 10 ? "0" + day : day);
        return predate;
    }

    public static long getCurrentYear() {
        return Long.parseLong(sdfDateTimeFormat_YYYY.format(new Date()));
    }

    /**
     * 判断一个日期字符串是否合法
     *
     * @param date
     * @param format
     * @return
     * @author liufengyu
     */
    public static boolean isDateStringCorrect(String date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);

        try {
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * <P>将字符串类型的日期格式 转换为 符合要求的日期格式</P>
     *
     * @param date
     * @param format
     * @return
     */
    public static String getStrDate4String(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            Date d = df.parse(date);
            return df.format(d);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * <p/>
     * 将Date类型的日期格式 转换为 符合要求的 String日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static String toDateStr(Date date, String format) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * <p/>
     * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
     * </P>
     *
     * @param date
     * @param format
     * @return
     */
    public static Date strToDate(String date, String format) {
        if (date == null || date.trim().equals("")) {
            return null;
        } else {
            SimpleDateFormat df = new SimpleDateFormat(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
    }

    /**
     * 计算指定日期时间之间的时间差
     *
     * @param beginStr 开始日期字符串
     * @param endStr   结束日期字符串
     * @param f        时间差的形式0-秒,1-分种,2-小时,3--天 日期时间字符串格式:yyyyMMddHHmmss
     */
    public static int getInterval(String beginStr, String endStr, int f) {
        int hours = 0;
        try {
            Date beginDate = sdfDateTimeFormat.parse(beginStr);
            Date endDate = sdfDateTimeFormat.parse(endStr);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            /**
             * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
             * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
             * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
             * 天
             * */
            switch (f) {
                case 0: // second
                    return (int) (millisecond / 1000);
                case 1: // minute
                    return (int) (millisecond / (1000 * 60));
                case 2: // hour
                    return (int) (millisecond / (1000 * 60 * 60));
                case 3: // day
                    return (int) (millisecond / (1000 * 60 * 60 * 24));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hours;
    }

    /**
     * <p/>
     * 得到起始日期和结束日期之间的天数
     * </P>
     *
     * @param sdate  起始日期
     * @param edate  结束日期
     * @param format 根据 日期参数的格式，传对应的SimpleDateFormat格式
     * @return 天数
     */
    public static int daysBetween(String sdate, String edate, SimpleDateFormat format) {
        try {
            Date beginDate = format.parse(sdate);
            Date endDate = format.parse(edate);
            long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减得到日期差X(单位:毫秒)
            return (int) (millisecond / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的天数(yyyy-MM-dd)
     *
     * @param sdate
     * @param edate
     * @return 相差天数
     */
    public static int daysBetween(Date sdate, Date edate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdate = sdf.parse(sdf.format(sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            edate = sdf.parse(sdf.format(edate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(sdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(edate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60 * 60 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数 (yyyy-MM-dd)
     *
     * @param sdate
     * @param edate
     * @return 相差天数
     */
    public static int daysBetween(String sdate, String edate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = cal.getTimeInMillis();
        try {
            cal.setTime(sdf.parse(sdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 60 * 60 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2012-09-08 10:10:10");
        Date d2 = sdf.parse("2030-09-15 00:00:00");
        System.out.println(daysBetween(d1, d2));

        Date now = new Date();
        System.out.println(daysBetween(sdf.format(now), "2015-12-12 00:00:00"));
    }
}
