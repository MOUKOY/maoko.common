package soft.common;

import soft.common.exception.DataIsNullException;

/**
 * 对象实例化创建助手
 * 
 * @author fanpei
 *
 */
public class InstanceUitl extends StaticClass {

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
}
