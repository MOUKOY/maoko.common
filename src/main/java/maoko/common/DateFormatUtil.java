package maoko.common;

import maoko.common.StaticClass;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class DateFormatUtil extends StaticClass {

    public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断时间之差是否满足span
     *
     * @param start
     * @param end
     * @param span  两时间差值 单位毫秒
     * @return
     */
    public static boolean timeSpan(Date start, Date end, long span) {
        long spantmp = end.getTime() - start.getTime();
        return span > spantmp;
    }

    public static boolean compare(Date start, Date end) {
        return end.getTime() - start.getTime() > 0;
    }

    /**
     * 以默认的格式序列化date
     * 默认格式为：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String dateformat(Date date) {
        return DateFormatUtils.format(date, DATEFORMAT);
    }

    public static String dateformat(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 以默认的格式反序列化
     * 默认格式为：yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date formatDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, DATEFORMAT);
    }

    public static Date formatDate(String dateStr, String pattern) throws ParseException {
        return DateUtils.parseDate(dateStr, pattern);
    }
}
