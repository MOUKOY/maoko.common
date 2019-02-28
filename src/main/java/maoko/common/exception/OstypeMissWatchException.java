package maoko.common.exception;

/**
 * 操作系统版本不匹配异常
 * 
 * @author fanpei
 * @date 2019年1月7日下午10:13:39
 */
public class OstypeMissWatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OstypeMissWatchException(String dsrc) {
		super(dsrc);
	}

}
