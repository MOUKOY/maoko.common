package moukoy.sdkcommon;

import java.io.UnsupportedEncodingException;

import soft.common.exception.DataIsNullException;
import soft.common.exception.OstypeMissWatchException;
import soft.common.log.Log4j2Writer;
import soft.common.log.LoginitException;
import soft.common.system.AppRunPathUitl;

/**
 * Hello world!
 *
 */
public class SDKCommon {
	private static boolean iniited = false;// 是否初始化

	/**
	 * 自定义运行目录字符串
	 */
	public static final String RUNPATH = "softRun.path";

	/**
	 * 初始化
	 * 
	 * @throws LoginitException
	 * @throws DataIsNullException
	 * @throws OstypeMissWatchException
	 */
	public synchronized static void init() throws LoginitException, OstypeMissWatchException, DataIsNullException {
		if (!iniited) {
			iniited = true;
			System.out.println("sdkcommon is initing....");
			AppRunPathUitl.getAppRunPath();// 初始化系统运行目录
			Log4j2Writer.init();
		}
	}

	public static void main(String[] args) {
		try {
			AppRunPathUitl.getClassRunPath();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
