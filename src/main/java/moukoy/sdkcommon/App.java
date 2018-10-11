package moukoy.sdkcommon;

import soft.common.log.LogWriter;
import soft.common.log.LoginitException;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * 初始化
	 * 
	 * @param runpath
	 * @throws LoginitException
	 */
	public static void init(String runpath) throws LoginitException {
		LogWriter.init(runpath);
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
