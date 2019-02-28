package maoko.common.exception;

/**
 * 超出范围异常
 * 
 * @author fanpei
 * @date 2018-10-31 17:17
 *
 */
public class OutOfRangeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutOfRangeException(String dsrc) {
		super(dsrc);
	}

}
