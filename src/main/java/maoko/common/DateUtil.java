package maoko.common;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 日期助手
 * 
 * @author fanpei
 *
 */
@Deprecated
public class DateUtil {
	public static final String FOMATSTR = "yy/MM/dd,HH:mm:ssZ";
	public static final FastDateFormat CHINA_FORMAT = FastDateFormat.getInstance(FOMATSTR, Locale.CHINA);
	// public static final FastDateFormat ENGILISH_FORMAT =
	// FastDateFormat.getInstance(FOMATSTR, Locale.ENGLISH);

	public String format(long timestamp) {
		return CHINA_FORMAT.format(timestamp);
	}

	/**
	 * 转换日期格式
	 * 
	 * @return String
	 */
	public static String format(Date date) {
		return CHINA_FORMAT.format(date);
	}

	public static void main(String[] args) {
		String servertime = DateUtil.format(new Date());
		System.out.println(servertime.substring(0, servertime.length() - 2));
	}
}
