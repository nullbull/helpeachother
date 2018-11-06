package com.heo.common.utils;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.LoggerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * 
 * @author justinniu
 */
public class DateUtils
{

    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 判断日期是否在当天
     */
    public static boolean isToday(Date date)
    {
        Date now = new Date(System.currentTimeMillis());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(now);
        String subDate = format.substring(0,10);
        String begin = subDate+" 00:00:00";
        String end = subDate+" 23:59:59";
        Date beginTime = null;
        Date endTime = null;
        try
        {
            beginTime = simpleDateFormat.parse(begin);
            endTime = simpleDateFormat.parse(end);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        if(date.after(beginTime) && date.before(endTime))
        {
            return true;
        }
        return false;
    }

  /*  public static void main(String[] args) throws ParseException {
        String date = "2018-10-06 12:03:30";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date cur = null;
        cur = simpleDateFormat.parse(date);
        System.out.println(isToday(cur));
    }*/

}
