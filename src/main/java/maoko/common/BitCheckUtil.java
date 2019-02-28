package maoko.common;

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
	 * @param bytes 待计算数组
	 * @param begin 开始位置
	 * @param len   长度
	 * @return
	 */
	public static byte xorCheck(byte[] bytes, int begin, int len) {
		if (len > bytes.length)
			throw new ArrayIndexOutOfBoundsException("end is more than bytes.len");

		byte xorResult = 0;
		for (int i = begin; i < len; i++) {
			xorResult ^= bytes[i];
		}
		return xorResult;
	}

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
