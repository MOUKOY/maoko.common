package maoko.common.conf;

/**
 * 配置文件错误
 * 
 * @author fanpei
 * @date 2018-09-09 15:16
 *
 */
public class ConfException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConfException(String dsrc) {
		super(dsrc);
	}

}
