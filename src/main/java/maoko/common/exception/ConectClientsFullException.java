package maoko.common.exception;

/**
 * 连接数达到最大异常
 * 
 * @author fanpei
 * @date 2018-09-24 18:41
 *
 */
public class ConectClientsFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConectClientsFullException() {
		super("server conect clients is full");
	}

}
