package maoko.common;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties读取助手
 * 
 * @author fanpei
 * @date 2018-09-09 14:30
 *
 */
public class PropertiesUtil extends StaticClass {

	/**
	 * 根据Key读取Value
	 * 
	 * @param path properties文件路径
	 * @param key
	 * @return
	 */
	public static String getValueByKey(String path, String key) {
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(path));
			pps.load(in);
			String value = pps.getProperty(key);
			System.out.println(key + " = " + value);
			return value;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 读取Properties的全部信息
	 * 
	 * @param path
	 * @throws IOException
	 */
	public static Map<String, String> getAllProperties(String path) throws IOException {
		Map<String, String> values = new HashMap<>();
		Properties pps = new Properties();
		File proFile = new File(path);
		InputStream in = new FileInputStream(proFile);
		pps.load(in);
		Enumeration<?> en = pps.propertyNames(); // 得到配置文件的名字

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			values.put(strKey, strValue);
		}
		return values;

	}

	// 写入Properties信息
	public static void writeProperties(String filePath, String pKey, String pValue) throws IOException {
		Properties pps = new Properties();

		InputStream in = new FileInputStream(filePath);
		// 从输入流中读取属性列表（键和元素对）
		pps.load(in);
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(filePath);
		pps.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		pps.store(out, "Update " + pKey + " name");
	}

	public static void main(String[] args) throws IOException {

		// Map<String, String> obj =
		// getAllProperties("F:/project/ElectronicLock/src/config/sys.properties");
		// try {
		// ConfReader.init("F:/project/ElectronicLock/src/");
		// System.err.println();
		// } catch (ConfException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}
}
