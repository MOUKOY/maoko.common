package maoko.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import maoko.common.exception.DataIsNullException;

/**
 * 对象实例化创建助手
 * 
 * @author fanpei
 *
 */
public class InstanceUitl extends StaticClass {

	/**
	 * 根据类全名创建对象
	 * 
	 * @param className
	 * @return
	 * @throws DataIsNullException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Object createObject(String className)
			throws DataIsNullException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<?> clazz = Class.forName(className);
		return createObject(clazz);

	}

	/**
	 * 创建T类型对象实例
	 * 
	 * @param els
	 * @return
	 * @throws DataIsNullException    Class<T> els is null
	 * @throws IllegalAccessException 不合法的访问异常
	 * @throws InstantiationException 不能实例化
	 * @throws Exception
	 */
	public static <T> T createObject(Class<T> els)
			throws DataIsNullException, InstantiationException, IllegalAccessException {

		return createObject("T", els);

	}

	/**
	 * @param dscr 描述信息
	 * @param els
	 * @return
	 * @throws DataIsNullException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <T> T createObject(String dscr, Class<T> els)
			throws DataIsNullException, InstantiationException, IllegalAccessException {
		if (els == null)
			throw new DataIsNullException(
					StringUtil.getMsgStr("dscr:{}, Class<T> type  is null,creat object is null", dscr));

		return els.newInstance();

	}

	/**
	 * @param dscr
	 * @param els
	 * @param initargs 构造函数参数
	 * @return
	 * @throws DataIsNullException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T createObject(Class<T> els, Object initargs)
			throws DataIsNullException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {

		Constructor<T> ct = els.getConstructor(initargs.getClass());
		return ct.newInstance(initargs);

	}
}
