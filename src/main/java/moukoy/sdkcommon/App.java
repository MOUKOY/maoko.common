package moukoy.sdkcommon;

import soft.common.log.Log4j2Writer;
import soft.common.log.LoginitException;

/**
 * Hello world!
 *
 */
public class App {

	/**
	 * 初始化
	 * 
	 * @throws LoginitException
	 */
	public static void init() throws LoginitException {
		Log4j2Writer.init();
	}

	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
