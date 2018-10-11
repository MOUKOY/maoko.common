package soft.common;

import java.util.UUID;

/**
 * guid助手
 * 
 * @author fanpei
 *
 */
public class UUIDUtil extends StaticClass {

	/**
	 * 获取guid字符串形式
	 * 
	 * @return
	 */
	public static String getUUIDStr() {
		UUID cmdid = UUID.randomUUID();
		return cmdid.toString().replace("-", "");
	}

	/**
	 * 获取guidbyte流形式
	 * 
	 * @return
	 */
	public static byte[] getUUIDBytes() {
		return StringUtil.getUtf8Bytes(getUUIDStr());
	}

	public static byte[] getUUIDBytes(String value) {
		return StringUtil.getUtf8Bytes(value);
	}
}
