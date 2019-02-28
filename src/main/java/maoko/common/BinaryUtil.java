package maoko.common;
/**
 * 
 * 二进制工具类
 *
 * @author  qj
 * @since    2019年1月30日
 */
public class BinaryUtil extends StaticClass {

	/**
	 * 将整数{@code value}转换为{@code length}长度的二进制字符串
	 * 如果长度大于需要的长度则取最右边的length位
	 * 如果长度小于需要的长度则在左边补0
	 * @param value 需要转换的整数
	 * @param length	结果长度
	 * @return 结果
	 */
	public static String toBinaryString(int value,int length) {
		StringBuffer binary=new StringBuffer(Integer.toBinaryString(value));
		if(binary.length()>length) {
			return binary.substring(binary.length()-length);
		}
		if(binary.length()<length) {
			int originLength=binary.length();
			for(int i=0;i<length-originLength;i++) {
				binary.insert(0, "0");
			}
		}
		return binary.toString();
	}
	
	public static String toBinaryString(byte[] values,int length) {
		StringBuffer binary=new StringBuffer();
		for(byte i:values) {
			binary.append(toBinaryString(i,length));
		}
		return binary.toString();
	}
	
}
