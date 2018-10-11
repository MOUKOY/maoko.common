package soft.common.exception;

import soft.common.StringUtil;

/**
 * 没有当前本地端口绑定的连接
 * 
 * @author fanpei
 * @date 2018-09-09 22:36
 *
 */
public class NoCurrentPortConnectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoCurrentPortConnectException(int port) {
		super(StringUtil.getMsgStr("connect store not contain this port:{}", Integer.toString(port)));
	}

}
