package soft.common;

/**
 * 时间消耗计算助手
 * 
 * @author fanpei
 *
 */
public class TimeSpendUtil extends StaticClass {

	public static void doSomething(ITimeDo ido) {
		long time1 = System.currentTimeMillis();
		ido.doSomething();
		long time2 = System.currentTimeMillis();
		System.err.println(ido.getDscr() + "spend time:" + Long.toString(time2 - time1));
	}

	/**
	 * 时间操作
	 * 
	 * @author fanpei
	 *
	 */
	public interface ITimeDo {
		void doSomething();

		String getDscr();// 事情描述
	}
}
