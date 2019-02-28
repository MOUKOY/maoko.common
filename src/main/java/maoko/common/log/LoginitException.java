package maoko.common.log;

/**
 * 日志初始化异常
 * 
 * @author fanpei
 *
 */
public class LoginitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginitException(String messge, Throwable e) {
		super(messge, e);
	}

}
