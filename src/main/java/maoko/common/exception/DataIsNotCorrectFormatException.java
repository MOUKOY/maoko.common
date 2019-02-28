package maoko.common.exception;

/**
 * 数据格式化不正确-不是正确的数据格式
 * 
 * @author fanpei
 * @date 2018-09-26 21:19
 *
 */
public class DataIsNotCorrectFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataIsNotCorrectFormatException(String dscr) {
		super(dscr);
	}

}
