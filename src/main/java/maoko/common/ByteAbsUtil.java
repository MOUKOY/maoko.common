package maoko.common;

/**
 * 由于java byte类型取值范围为[-128,127],协议byte取值范围[0,255]，故byte数据处理时，需调用此方法对数据进行short化。
 * short化：去除byte左侧高位符号位对数据正负的影响
 * 
 * @author fanpei
 *
 */
public class ByteAbsUtil {
	/**
	 * 获取byte无符号值
	 * 
	 * @param value
	 * @return
	 */
	public static short toUnsigned(byte value) {
		return (short) (value & 0x00ff);
	}

	/**
	 * 获取short无符号值
	 * @param value
	 * @return
	 */
	public static int toUnsigned(short value) {
		return value & 0x00ffff;
	}
	
	/**
	 * 获取int无符号值
	 * @param value
	 * @return
	 */
	public static long toUnsigned(int value) {
		return value & 0x00ffffffff;
	}

	public static void main(String[] args) {
		byte v = (byte) 0x81;
		System.out.println(toUnsigned(v));
	}
}
