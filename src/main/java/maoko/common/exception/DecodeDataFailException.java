package maoko.common.exception;

/**
 * 解析stream流异常
 * 
 * @author fanpei
 *
 */
public class DecodeDataFailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DecodeDataFailException(String dsrc) {
		super(dsrc);
	}
}
