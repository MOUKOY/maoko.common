package soft.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期助手
 * @author liulu
 *
 */
public class DateUtil {

	//定义存入数据库的日期格式
	private static final String dateFormat="yyyy-MM-dd HH:mm:ss";
	

	private static SimpleDateFormat format=new SimpleDateFormat(dateFormat);

	
	/**
	 * 得到当前日期
	 * @return Date
	 * @throws ParseException 
	 */
	public static Date getNowDate() throws ParseException {
		return format.parse(format.format(new Date()));
		

	}
	
	/**
	 * 转换日期格式
	 * @return String
	 */
	public static String getStringDate(Date date) {
		return format.format(date);

	}
	
	
	/**
	 * 获取上周周一和周五的时间
	 */
	public static String[] getLastWeekTime() {
		String[] times=new String[2];

		Calendar MonCa=Calendar.getInstance();  
		Calendar FriCa=Calendar.getInstance(); 
		
		int dayOfWeek = MonCa.get(Calendar.DAY_OF_WEEK)-1;
		int MonOffset = 1 - dayOfWeek;
		int FriOffset = 6- dayOfWeek;
		//时分秒清零
		MonCa.set(Calendar.HOUR_OF_DAY,0);
		FriCa.set(Calendar.HOUR_OF_DAY,0);
		MonCa.set(Calendar.MINUTE,0);
		FriCa.set(Calendar.MINUTE,0);
		MonCa.set(Calendar.SECOND,0);
		FriCa.set(Calendar.SECOND,0);
		
		MonCa.add(Calendar.DATE,MonOffset-7);
		FriCa.add(Calendar.DATE,FriOffset-7);
		
		String startTime=getStringDate(MonCa.getTime());
		String endTime=getStringDate(FriCa.getTime());
		
		times[0]=startTime;
		times[1]=endTime;
		
		return times;
		
	}
	
	/**
	 * 获取本周周一到现在的时间
	 */
	public static String[] getThisWeekTime() {
		String[] times=new String[2];
		
		Calendar MonCa=Calendar.getInstance();  
		
		int dayOfWeek = MonCa.get(Calendar.DAY_OF_WEEK)-1;
		int MonOffset = 1 - dayOfWeek;
		//时分秒清零
		MonCa.set(Calendar.HOUR_OF_DAY,0);
		MonCa.set(Calendar.MINUTE,0);
		MonCa.set(Calendar.SECOND,0);
		MonCa.add(Calendar.DATE,MonOffset-7);

		String startTime=getStringDate(MonCa.getTime());
		String endTime=getStringDate(new Date());
		
		times[0]=startTime;
		times[1]=endTime;
		
		return times;
		
	}
}
