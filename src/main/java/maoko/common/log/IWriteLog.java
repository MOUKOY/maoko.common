package maoko.common.log;

public interface IWriteLog {

	/**
	 * 打印错误信息
	 * 
	 * @param dscrp
	 *            操作描述
	 * @param type
	 *            命令类型
	 * @param e
	 *            异常
	 */
	void error(Throwable e);

	void error(String dscrp, Object... e);

	/**
	 * 打印错误信息
	 * 
	 * @param dscrp
	 *            操作描述
	 * @param type
	 *            命令类型
	 * @param e
	 *            异常
	 */
	public void debug(String dscrp, Object... e);

	public void warn(String dscrp, Object... e);

	public void info(String dscrp, Object... e);

}
