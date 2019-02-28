package maoko.common.exception;

/**
 * 数据为空异常
 * 
 * @author fanpei
 * @date 2018-09-16 21:43
 *
 */
public class DataIsNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIsNullException(String dsrc) {
		super(dsrc);
	}

}
