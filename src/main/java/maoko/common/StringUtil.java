package maoko.common;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maoko.common.gson.JSONUtil;
import org.apache.commons.text.StringEscapeUtils;

import maoko.common.agorithm.HashUtil;
import maoko.common.log.log4j2StringStrcat;

/**
 * 字符串助手
 * 
 * @author fanpei
 *
 */
public class StringUtil extends StaticClass {
	private static final String UTF_8 = "UTF-8";

	private static Pattern p = Pattern.compile("\\s*|\t|\r|\n");

	/**
	 * java 运算符
	 */
	private static String[] fbsArr = { "\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|" };
	/**
	 * 16进制字符
	 */
	private static final char[] HEX_CHAR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	/**
	 * 替换文件空白部分
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String replaceBlank(String srcStr) {
		String dest = "";
		if (!isStringNull(srcStr)) {
			Matcher m = p.matcher(srcStr);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * 检验字符串是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isStringNull(String input) {

		if (null == input) {
			return true;
		}
		return false;
	}

	/**
	 * 检验字符串是否为空，并且是否等于空格
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isStrNullOrWhiteSpace(String input) {

		if (isStringNull(input) || input.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 整形字符串转换成进制字符串前面补0
	 * 
	 * @param srcStr       待补0字段
	 * @param resultLength 补0后字符串整数部分需要达到多长，例如0x1001，的整数部分长为4
	 * @return 返回字符串格式为:0x0....的格式
	 */
	public static String addZeroStart(String srcStr, int resultLength) {

		if (srcStr.length() < resultLength) {
			int times = resultLength - srcStr.length();
			while (times > 0) {
				srcStr = String.format("%c%s", '0', srcStr);
				times--;
			}
		}

		return "0x" + srcStr;
	}

	/**
	 * 获取字符串utf-8编码的字节数组
	 * 
	 * @param srcStr 待编码字符串
	 * @return
	 */
	public static byte[] getUtf8Bytes(String srcStr) {
		byte[] xmlStrBytes = null;
		String value = srcStr;
		try {
			if (srcStr == null)
				value = "";
			xmlStrBytes = value.getBytes(UTF_8);
		} catch (UnsupportedEncodingException e) {
		}
		return xmlStrBytes;
	}

	/**
	 * 从字符串utf-8字节数组中创建字符串
	 * 
	 * @param bytes 待创建字符串字节数组
	 * @return
	 */
	public static String getUtf8Str(byte[] bytes) {

		String result = null;
		try {
			result = new String(bytes, UTF_8);
		} catch (UnsupportedEncodingException e) {
		}
		return result;
	}

	/**
	 * 将字符串进行utf-8编码
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String encodeUtf8Str(String srcStr) {
		String result = null;
		try {
			result = new String(srcStr.getBytes(), UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串按照指定的分割符进行分割
	 * 
	 * @param str 源字符串
	 * @param c   分隔符
	 * @return
	 */
	public static String[] spilt(String str, String c) {
		return str.split(c);
	}

	/**
	 * 将""字符串转化为NUll
	 */
	public static String parseNull(String element) {
		String value = null;
		if ("".equals(element)) {
			return value;
		} else
			return element;
	}

	/**
	 * 将字符串转变成""
	 * 
	 * @param element
	 * @return
	 */
	public static String parseEmpty(String element) {
		if (null == element) {
			return "";
		} else
			return element;
	}

	/**
	 * MD5加码 生成32位md5码
	 * 
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {
		return HashUtil.string32MD5(str);
	}

	/**
	 * MD5加码 生成16位md5码
	 * 
	 * @param str
	 * @return
	 */
	public static String get16MD5Str(String str) {
		return HashUtil.string16MD5(str);
	}

	/**
	 * 转义xml字符串value值
	 * 
	 * @param xmlValue
	 * @return
	 */
	public static String escapeXml(String xmlValue) {
		if (xmlValue == null || "".equals(xmlValue))
			return "";

		return StringEscapeUtils.escapeXml10(xmlValue);// 1.0版本的转义
	}

	/**
	 * 反转义字符串
	 * 
	 * @param xmlValue
	 * @return
	 */
	public static String unescapeXml(String xmlValue) {
		return StringEscapeUtils.escapeXml10(xmlValue);// 1.0版本的转义
	}

	/**
	 * 转义字符串java筛选字符 $ () *+.[ ]?\^{},|
	 * 
	 * @param javaValue
	 * @return
	 */
	public static String escapeJAVA(String javaValue) {
		if (javaValue == null || "".equals(javaValue))
			return "";
		for (String s : fbsArr) {
			if (javaValue.contains(s))
				javaValue = javaValue.replace(s, "\\" + s);
		}
		return javaValue;
	}

	/**
	 * 获取字节左侧高位char表示形式
	 * 
	 * @param b
	 * @return
	 */
	public static char getByteLeft(byte b) {
		return HEX_CHAR[b >>> 4 & 0xf];

	}

	/**
	 * 获取字节右侧侧低位char表示形式
	 * 
	 * @param b
	 * @return
	 */
	public static char getByteRight(byte b) {
		return HEX_CHAR[b & 0xf];
	}

	/**
	 * 打印byte
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2HexStr(byte b) {
		StringBuilder chars = new StringBuilder(4);
		chars.append('0');
		chars.append('x');
		chars.append(getByteLeft(b));
		chars.append(getByteRight(b));
		return chars.toString();
	}

	/**
	 * 获取字节的十六进制形式，无0x前缀
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2HexStrNo0X(byte b) {
		StringBuilder chars = new StringBuilder(2);
		chars.append(getByteLeft(b));
		chars.append(getByteRight(b));
		return chars.toString();
	}

	public static char[] byte2HexChars(byte b) {
		char[] chars = new char[2];
		short s = ByteAbsUtil.toUnsigned(b);
		if (s < 16) {
			chars[0] = HEX_CHAR[0 & 0xf];
			chars[1] = HEX_CHAR[s & 0xf];
		} else {
			chars[0] = HEX_CHAR[s >>> 4 & 0xf];
			chars[1] = HEX_CHAR[s & 0xf];
		}
		return chars;
	}

	/**
	 * 将二进制转变成16进制
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytes2HexStr(byte[] bytes) {

		if (bytes == null) {
			return null;
		}
		if (bytes.length > 0) {
			StringBuilder strb = new StringBuilder();
			int index = 0;
			for (byte b : bytes) {
				++index;
				strb.append(byte2HexChars(b));
				if (index != bytes.length)
					strb.append(" ");
			}
			return strb.toString();
		}
		return "";

	}

	/**
	 * 获取对象json bytes 压缩 流【先JSon后压缩】
	 * 
	 * @return
	 */
	public static byte[] gZipObjToJsonBytes(Object obj) {
		String bsStr = JSONUtil.genJsonStr(obj);
		byte[] utf8Strs = getUtf8Bytes(bsStr);
		return ZipUtils.gzip(utf8Strs);
	}

	/**
	 * 解压字符串【先解压bytes再转变成字符串】
	 * 
	 * @param bytes
	 * @return
	 */
	public static String unGZipBytesToStr(byte[] bytes) {
		return getUtf8Str(ZipUtils.gunzip(bytes));
	}

	/**
	 * 压缩字符串【压缩String】
	 * 
	 * @param value
	 * @return
	 */
	public static String gzipUTF8StrToStr(String value) {
		String tmpValue = encodeUtf8Str(value);
		return ZipUtils.gzip(tmpValue);
	}

	/**
	 * 压缩字符串【压缩String的bytes】
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] gzipUTF8StrToBytes(String value) {
		byte[] bytes = getUtf8Bytes(value);
		return ZipUtils.gzip(bytes);
	}

	/**
	 * 组装信息
	 * 
	 * @param message 包含{}的字符串描述
	 * @param params  参数
	 * @return
	 */
	public static String getMsgStr(String message, Object... params) {
		return log4j2StringStrcat.getMsgStr(message, params);
	}

	/**
	 * 十六进制字符串转byte数组
	 * 
	 * @param hexValues 十六进制表示 如48 65 6C 6C 6F 20 57 6F 72 6C 64 21 以空格分隔
	 * @return
	 */
	public static byte[] hexStr2Bytes(String hexValues) {
		byte[] datas = null;
		if (!StringUtil.isStringNull(hexValues)) {
			hexValues = hexValues.replaceAll(" ", "");
			int Len = hexValues.length() / 2;
			datas = new byte[Len];
			for (int x = 0; x < Len; x++) {
				String hex = hexValues.substring(x * 2, x * 2 + 2);
				datas[x] = (byte) Integer.parseInt(hex, 16);
			}
		}

		return datas;
	}

	/**
	 * 字符串反转
	 * 
	 * @param dStr    待反转字符串
	 * @param num     一次性翻转的字符串个数，如 dStr="0205",num=2,则反转后的值为：0502
	 * @param joinStr 加入反转后的分隔符，如 dStr="0205",num=2,jonStr="-",则反转后的值为05-02
	 * @return
	 */
	public static String reverseStr(String dStr, int num, String joinStr) {
		StringBuilder reverse = new StringBuilder();
		int length = dStr.length();
		for (int i = 0; i < length; i++) {
			if ((i + 1 - num) % 2 == 0) {
				if (!StringUtil.isStringNull(joinStr))
					reverse.insert(0, joinStr);
				reverse.insert(0, dStr.substring(i + 1 - num, i + 1));
			} else {
				if (i == length - 1)//
				{
					if (!StringUtil.isStringNull(joinStr))
						reverse.insert(0, joinStr);
					reverse.insert(0, dStr.charAt(i));
				}
			}
		}
		return reverse.toString();
	}

	/**
	 * 字符串反转
	 * 
	 * @param dStr 待反转字符串
	 * @param num  一次性翻转的字符串个数，如 dStr="0205",num=2,则反转后的值为：0502
	 * @return
	 */
	public static String reverseStr(String dStr, int num) {
		return reverseStr(dStr, num, null);
	}
}
