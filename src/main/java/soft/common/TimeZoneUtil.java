package soft.common;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时区转换助手
 * 
 * @author fanpei
 * @date 2018-09-24 14:47
 *
 */
public class TimeZoneUtil extends StaticClass {

	public static Date getTime(TimeZone zone) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"), Locale.CHINESE);
		cal.setTimeZone(zone);
		return cal.getTime();
	}
}
