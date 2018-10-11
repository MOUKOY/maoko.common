package soft.common;

/**
 * 字符串组装器
 * 
 * @author fanpei
 *
 */
public class StrConUtil {

	/**
	 * 有序拼接字符串
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String conectStr(String str1, String str2) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length());
		return returnSb.append(str1).append(str2).toString();
	}

	public static String conectStr(String str1, String str2, char c) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length() + 1);
		return returnSb.append(str1).append(c).append(str2).toString();
	}

	/**
	 * 有序拼接字符串
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @return
	 */
	public static String conectStr(String str1, String str2, String str3) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length() + str3.length());
		return returnSb.append(str1).append(str2).append(str3).toString();

	}

	public static String conectStr(String str1, String str2, String str3, char c) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length() + str3.length() + 1);
		return returnSb.append(str1).append(c).append(str2).append(c).append(str3).toString();

	}

	/**
	 * 有序拼接字符串
	 * 
	 * @param str1
	 * @param str2
	 * @param str3
	 * @param str4
	 * @return
	 */
	public static String conectStr(String str1, String str2, String str3, String str4) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length() + str3.length() + str4.length());
		return returnSb.append(str1).append(str2).append(str3).append(str4).toString();

	}

	public static String conectStr(String str1, String str2, String str3, String str4, char c) {
		StringBuilder returnSb = new StringBuilder(str1.length() + str2.length() + str3.length() + str4.length() + 1);
		return returnSb.append(str1).append(c).append(str2).append(c).append(str3).append(c).append(str4).toString();

	}

}
