package maoko.common;

/**
 * 16进制字符串转换为二进制
 * 
 * @author fanpei
 *
 */
public class HexConverter extends StaticClass {

	static char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	static String STR = "0123456789ABCDEF";

	/**
	 * 字符串转换成为16进制(无需Unicode编码)  
	 * 
	 * @param str
	 *             
	 * @return  
	 */
	public static String str2HexStr(String str) {
		byte[] bs = str.getBytes();
		StringBuilder sb = new StringBuilder("");
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(CHARS[bit]);
			bit = bs[i] & 0x0f;
			sb.append(CHARS[bit]);
		}
		return sb.toString().trim();
	}

	/**
	 * 16进制直接转换成为字符串(无需Unicode解码)  
	 * 
	 * @param hexStr
	 *             
	 * @return  
	 */
	public static String hexStr2Str(String hexStr) {
		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = STR.indexOf(hexs[2 * i]) * 16;
			n += STR.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
	
	public static void main(String[] args) {
		String hexStr="FE C2 A9 17 01 07 01 00 2E 21 57 54 23 88 34 38 30 29 65 40 10 00 17 0C 00 00 00 00 00 00 80 00 64 50 06 37 42 31 31 30 31 37 37 30 31 38 32 30 30 36 98 FF";
		System.out.println(HexConverter.hexStr2Str(hexStr.replace(" ", "")));
	}
}
