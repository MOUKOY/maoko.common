package soft.common;

/**
 * 按位校验助手
 * 
 * @author fanpei
 *
 */
public class BitCheckUtil extends StaticClass {

	/**
	 * 异或运算
	 * 
	 * @param srcResult
	 * @param bytes
	 * @return
	 */
	public static byte xorCheck(byte srcResult, byte[] bytes) {
		byte xorResult = srcResult;
		for (byte b : bytes) {
			xorResult ^= b;
		}
		return xorResult;
	}

	/**
	 * 异或
	 * 
	 * @param srcResult
	 * @param bit
	 * @return
	 */
	public static byte xorCheck(byte srcResult, byte bit) {
		byte xorResult = srcResult;
		xorResult ^= bit;
		return xorResult;
	}
}
