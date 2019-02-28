package maoko.common.exception;

/**
 * 不包含异常
 * 
 * @author fanpei
 * @date 2018-10-31 17:18
 *
 */
public class NotContainException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotContainException(String dsrc) {
		super(dsrc);
	}

}
