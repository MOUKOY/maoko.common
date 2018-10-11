package soft.common;

/**
 * 整数 & byte[]转换助手--地位在前，高位在后
 * 
 * @author fanpei
 *
 */
public class BConvrtUtil extends StaticClass {

	/**
	 * 将字节高低位调换
	 * 
	 * @return
	 */
	public static byte ChangeHighLowLoc(byte data) {
		byte high = (byte) (data & 0xf0);
		byte low = (byte) (data & 0x0f);
		return (byte) (low | high);
	}

	// long类型转成byte数组
	public static byte[] longToByte(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (temp & 0xff);// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	// byte数组转成long
	public static long byteToLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 最低位
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;

		// s0不变
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 32;
		s5 <<= 40;
		s6 <<= 48;
		s7 <<= 56;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	/**
	 * byte数组转成long-高位在左
	 * 
	 * @param b
	 * @return
	 */
	public static long byteHighToLong(byte[] b) {
		long s = 0;
		long[] sr = new long[8];
		for (int i = 0; i < b.length; i++) {
			sr[i] = b[i] & 0xff; // 最高位
		}

		// s7不变
		sr[6] <<= 8;// 左移自动补0
		sr[5] <<= 16;
		sr[4] <<= 24;
		sr[3] <<= 32;
		sr[2] <<= 40;
		sr[1] <<= 48;
		sr[0] <<= 56;
		s = sr[0] | sr[1] | sr[2] | sr[3] | sr[4] | sr[5] | sr[6] | sr[7];// 或运算相加
		return s;
	}

	public static byte[] intToByte(int number) {
		int temp = number;
		byte[] b = new byte[4];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (temp & 0xff);// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	public static int byteToInt(byte[] b) {
		int s = 0;
		int s0 = 0;
		int s1 = 0;
		int s2 = 0;
		int s3 = 0;

		s0 = b[0] & 0xff;// 最低位
		if (b.length > 1)
			s1 = b[1] & 0xff;
		if (b.length > 2)
			s2 = b[2] & 0xff;
		if (b.length > 3)
			s3 = b[3] & 0xff;
		s3 <<= 24;
		s2 <<= 16;
		s1 <<= 8;
		s = s0 | s1 | s2 | s3;
		return s;
	}

	public static byte[] shortToByte(short number) {
		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (temp & 0xff);// 将最低位保存在最低位
			temp = temp >> 8; // 向右移8位
		}
		return b;
	}

	public static short byteToShort(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最低位
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	/**
	 * 合并组装新的数组
	 * 
	 * @param datas1
	 * @param datas2
	 * @return
	 */
	public static byte[] combineArry(byte[] datas1, byte[] datas2) {
		if (datas1 == null) {
			return datas2;
		}
		if (datas2 == null) {
			return datas1;
		}

		byte[] newData = new byte[datas1.length + datas2.length];
		System.arraycopy(datas1, 0, newData, 0, datas1.length);
		System.arraycopy(datas2, 0, newData, datas1.length, datas2.length);
		return newData;
	}

}
