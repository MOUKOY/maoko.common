package maoko.common.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import maoko.common.StaticClass;
import maoko.common.StringUtil;
import maoko.common.model.AKeyValue;

/**
 * JSON助手
 *
 * @author fanpei
 * @date 2017年8月3日下午2:04:47
 */
public class JSONUtil extends StaticClass {
    protected static Gson gson;
    protected static JsonParser prParser;

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

    /**
     * 通过字符串产生List<T>对象
     * @param jsonsStr
     * @param classType
     * @param <T>
     * @return
     */
    public static <T> List<T> parseString2List(String jsonsStr, Class<T> classType) {
        Type type = new GsonParameterizedTypeImpl(classType);
        return gson.fromJson(jsonsStr, type);
    }

    @Deprecated
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

    /**
     * 二维数组转换Json
     *
     * @param objs
     * @return
     */
    public static String genArrayJsonStr(List<AKeyValue[]> objs) {
        if (objs != null) {
            JsonArray[] jas = new JsonArray[objs.size()];
            Iterator<AKeyValue[]> it = objs.iterator();
            int index = 0;
            while (it.hasNext()) {
                JsonArray ja = new JsonArray();
                jas[index] = ja;
                AKeyValue[] e = it.next();
                for (AKeyValue aKeyValue : e) {
                    ja.add(JSONUtil.genJsonStr(aKeyValue));
                }
                index++;
            }
            return gson.toJson(jas);
        } else
            return null;
    }

    public static <T> List<List<T>> genArrayObjects(String jsonsStr, Class<T> classType) {
        List<List<T>> ts = null;
        if (jsonsStr != null) {
            ts = new LinkedList<>();
            JsonArray[] arrays = gson.fromJson(jsonsStr, JsonArray[].class);
            if (arrays != null) {
                for (JsonArray jsa : arrays) {
                    int len = jsa.size();
                    List<T> values = new ArrayList<>(len);
                    for (int i = 0; i < len; i++) {
                        // String jsonStr=jsa.get(i).getAsString();
                        String jsonStr = gson.toJson(jsa.get(i));
                        T t = gson.fromJson(jsonStr, classType);
                        values.add(t);
                    }
                    ts.add(values);
                }
            }
        }
        return ts;
    }

}
