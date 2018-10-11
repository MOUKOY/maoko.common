package soft.common.agorithm;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * AES加密助手，密码自定义固定
 * 
 * @author fanpei
 *
 */
public class AesCipher {
	// static Logger log = LogAssist.getLoger(AesCipher.class);

	private static byte[] keyValue = new byte[] { // 用户密钥
			116, -110, -67, 19, -16, 30, -68, 14, -66, -9, -92, 57, 91, -79, -62, 56, -33, -59, 95, 6, -93, 108, -53,
			30, -100, 118, 127, -49, -44, -84, 115, 96, 29, 22, 41, 71, 0, -87, -31, 10, -17, 4, 23, 65, -111, -90, -39,
			-73, -126, 75, -95, -17, -85, -58, 81, 30, -18, 62, 51, 43, -124, -46, 120, 7, -99, 17, -72, 71, -124, 34,
			-16 };

	private static byte[] iv = new byte[] { // 算法参数
			-12, 32, -5, 56, 45, -89, 95, -22, -15, 45, 50, 67, -32, 5 - 4, -81, 58 };

	private static SecretKey key; // 加密密钥
	private static AlgorithmParameterSpec paramSpec; // 算法参数
	private static Cipher ecipher; // 加密算法
	private static SecureRandom sercureRandom; // 随机秘钥，否则Windows和Linux上不一致

	static {
		KeyGenerator kgen;
		try {
			// 为算法定义一个随机秘钥，否则Windows和Linux上不一致
			sercureRandom = SecureRandom.getInstance("SHA1PRNG");
			sercureRandom.setSeed(keyValue);
			// 为指定算法生成一个密钥生成器对象。
			kgen = KeyGenerator.getInstance("AES");
			// 使用用户提供的随机源初始化此密钥生成器，使其具有确定的密钥长度。
			kgen.init(128, sercureRandom);
			// 使用KeyGenerator生成（对称）密钥。
			key = kgen.generateKey();
			// 使用iv中的字节作为IV来构造一个 算法参数。
			paramSpec = new IvParameterSpec(iv);
			// 生成一个实现指定转换的 Cipher 对象
			ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		} catch (NoSuchPaddingException e) {
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		}
	}

	/**
	 * 加密，使用指定数据源生成密钥，使用用户数据作为算法参数进行AES加密
	 * 
	 * @param msg
	 *            加密的数据
	 * @return
	 */
	public static String Encrypt(String msg) {
		String str = "";
		try {
			// 用密钥和一组算法参数初始化此 cipher
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
			// 加密并转换成16进制字符串
			str = AsHexString(ecipher.doFinal(msg.getBytes()));
		} catch (BadPaddingException | IllegalBlockSizeException | InvalidKeyException
				| InvalidAlgorithmParameterException e) {
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		}
		return str;
	}

	/**
	 * 解密，对生成的16进制的字符串进行解密
	 * 
	 * @param value
	 *            解密的数据
	 * @return
	 */
	public static String Decrypt(String value) {
		try {
			ecipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
			return new String(ecipher.doFinal(AsBytes(value)));
		} catch (BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException
				| IllegalBlockSizeException e) {
			// LogAssist.printStackTrace(log, e, LogLevel.WARN);
		}
		return "";
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param buf
	 * @return
	 */
	private static String AsHexString(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;
		for (i = 0; i < buf.length; i++) {
			if ((buf[i] & 0xff) < 0x10)// 小于十前面补零
				strbuf.append("0");
			strbuf.append(Long.toString(buf[i] & 0xff, 16));
		}
		return strbuf.toString();
	}

	/**
	 * 将16进制字符串转换成字节数组
	 * 
	 * @param src
	 * @return
	 */
	private static byte[] AsBytes(String src) {
		if (src.length() < 1)
			return null;
		byte[] encrypted = new byte[src.length() / 2];
		for (int i = 0; i < src.length() / 2; i++) {
			int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);// 取高位字节
			int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);// 取低位字节
			encrypted[i] = (byte) (high * 16 + low);
		}
		return encrypted;
	}
}
