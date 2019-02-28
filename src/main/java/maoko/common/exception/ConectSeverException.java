package maoko.common.exception;

/**
 * 连接错误，连接服务器失败
 * 
 * @author fanpei
 *
 */
public class ConectSeverException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConectSeverException(String error, Throwable e) {
		super(error, e);
	}

}
