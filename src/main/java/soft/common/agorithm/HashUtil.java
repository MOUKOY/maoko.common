package soft.common.agorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 散列算法助手
 * 
 * @author fanpei
 *
 */
public class HashUtil {
	// static Logger log = LogAssist.getLoger(HashUtil.class);

	static {
		try {
			getMigest();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static MessageDigest getMigest() throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		return md5;
	}

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string32MD5(String inStr) {

		if (inStr == null)
			return inStr;
		StringBuilder hexValue = new StringBuilder();
		try {
			MessageDigest md5 = getMigest();
			byte[] md5Bytes = md5.digest(inStr.getBytes("utf-8"));
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = (md5Bytes[i]) & 0xff;
				if (val < 16)
					hexValue.append("0");
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		}

		return hexValue.toString();
	}

	/***
	 * MD5加码 生成16位md5码
	 */
	public static String string16MD5(String inStr) {
		String res = "";
		try {
			String reslut = string32MD5(inStr);
			if (reslut != null)
				res = reslut.substring(8, 24);
		} catch (Exception e) {
			// TODO: handle exception
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		}
		return res;
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	public static void main(String[] args) {
		System.out.println(string16MD5("fanpei"));
		System.out.println(string16MD5("lyy"));
		System.out.println(string16MD5("1234"));
		System.out.println(string16MD5("测试test999"));
	}
}
