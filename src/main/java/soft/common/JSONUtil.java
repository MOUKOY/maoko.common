package soft.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * JSON助手
 * 
 * @author fanpei
 *
 * @date 2017年8月3日下午2:04:47
 */
public class JSONUtil extends StaticClass {
	static Gson gson;
	static JsonParser prParser;
	static {
		gson = new Gson();
		prParser = new JsonParser();
	}

	/**
	 * 通过json byte[]获取对象
	 * 
	 * @param jsons
	 * @param classType
	 * @return
	 */
	public static <T> T genObject(byte[] jsons, Class<T> classType) {
		T t = null;
		if (jsons != null) {
			String jsonsStr = StringUtil.getUtf8Str(jsons);
			t = gson.fromJson(jsonsStr, classType);
		}
		return t;
	}

	public static <T> T genObject(String jsonsStr, Class<T> classType) {
		T t = null;
		if (jsonsStr != null) {
			t = gson.fromJson(jsonsStr, classType);
		}
		return t;
	}

	public static <T> List<T> genObjects(String jsonsStr, Class<T> classType) {
		List<T> ts = null;
		if (jsonsStr != null) {
			ts = new LinkedList<>();
			JsonArray arrays = prParser.parse(jsonsStr).getAsJsonArray();
			if (arrays != null) {
				Iterator<JsonElement> it = arrays.iterator();
				while (it.hasNext()) {
					JsonElement je = it.next();
					T t = gson.fromJson(je, classType);
					ts.add(t);
				}
			}
		}
		return ts;
	}

	/**
	 * 通过对象产生json对象byte[]
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] genJsonBytes(Object obj) {
		return StringUtil.getUtf8Bytes(genJsonStr(obj));
	}

	/**
	 * 通过对象产生json 字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String genJsonStr(Object obj) {
		return gson.toJson(obj);
	}

}
