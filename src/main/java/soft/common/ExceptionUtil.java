package soft.common;

/**
 * 异常获取消息助手
 * 
 * @author fanpei
 *
 */
public class ExceptionUtil extends StaticClass {
	private static String newLine = System.getProperty("line.separator");

	public static String getSvrPrintMessage(Throwable e) {

		Throwable cause = e.getCause();
		Throwable result = e;
		while (null != (cause = result.getCause()) && (result != cause)) {
			result = cause;
		}
		String msg = result.getMessage();
		String errType = result.toString();
		StackTraceElement ste = result.getStackTrace()[0];

		// String location = String.format("%s|%s|-%s", ste.getClassName(),
		// ste.getMethodName(), ste.getLineNumber());
		return String.format("%sType:%s%sLocation:%s%s%sReason:%s", newLine, errType, newLine, newLine,
				String.valueOf(ste), newLine, msg == null ? "no Description" : msg);
	}

	/**
	 * 获取异常信息
	 * 
	 * @param e
	 *            异常对象
	 * @return
	 */
	public static String getCauseMessage(Throwable e) {

		Throwable cause = e.getCause();
		Throwable result = e;
		while (null != (cause = result.getCause()) && (result != cause)) {
			result = cause;
		}
		String msg = result.getMessage();

		// String location = String.format("%s|%s|-%s", ste.getClassName(),
		// ste.getMethodName(), ste.getLineNumber());
		return msg == null | "".equals(msg) ? "" : msg;
	}

	/**
	 * 获取异常信息
	 * 
	 * @param optDesc
	 *            操作描述
	 * @param e
	 *            异常对象
	 * @return
	 */
	public static String getCauseMessage(String optDesc, Throwable e) {
		return String.format("%s:%s", optDesc, getCauseMessage(e));
	}

}
