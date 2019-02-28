package maoko.common;

import java.util.List;

import javax.management.modelmbean.XMLParseException;

import org.dom4j.Attribute;
import org.dom4j.Element;

import maoko.common.exception.DataIsNullException;

/**
 * XML解释器
 * 
 * @author fanpei
 *
 */
public class XmlParseUtil extends StaticClass {

	/**
	 * 根据父节点获取子节点
	 * 
	 * @param parentElement 指定父节点
	 * @param eleName       子节点名称
	 * @return
	 * @throws DataIsNullException
	 * @throws XMLParseException
	 */
	public static Element getElemnt(Element parentElement, String eleName)
			throws DataIsNullException, XMLParseException {

		if (parentElement == null)
			throw new DataIsNullException("[" + eleName + "]父节点为空或不存在");

		Element element = null;
		boolean isCorret = false;// 符号变量
		element = parentElement.element(eleName);
		if (element != null) {
			isCorret = true;
		}

		checkXmlParse(eleName, isCorret);

		return element;
	}

	/**
	 * 通过制定父节点获取子节点
	 * 
	 * @param parentElement   指定节点
	 * @param eleName         子节点名称
	 * @param allowElmentNull true:允许节点为空
	 * @return
	 * @throws XMLParseException
	 */
	public static Element getElemnt(Element parentElement, String eleName, boolean allowElmentNull)
			throws XMLParseException {

		Element element = null;
		boolean isCorret = false;// 符号变量
		element = parentElement.element(eleName);
		if (allowElmentNull || element != null) {
			isCorret = true;
		}

		checkXmlParse(eleName, isCorret, allowElmentNull);

		return element;
	}

	/**
	 * 获取指定节点下，某节点的所有子节点
	 * 
	 * @param parentElement 指定父节点
	 * @param eleName       节点名称
	 * @return
	 * @throws XMLParseException
	 */
	@SuppressWarnings("rawtypes")
	public static List getElemnts(Element parentElement, String eleName) throws XMLParseException {
		Element element = getElemnt(parentElement, eleName, false);
		return element.elements();
	}

	/**
	 * 获取某属性节点字符串值
	 * 
	 * @param element   拥有当前属性的Elemnt
	 * @param attriName 待检查属性名称
	 * @allowElmentNull 是否允許該節點為空或字符串為空
	 * @return 该属性字符串值
	 * @throws XMLParseException
	 */
	public static String getAttributeValue(Element element, String attriName, boolean allowElmentNull)
			throws XMLParseException {
		String value = null;
		boolean isCorret = false;// 符号变量
		Attribute attribute = element.attribute(attriName);
		if (attribute != null) {
			value = attribute.getValue();
			if (!StringUtil.isStrNullOrWhiteSpace(value)) {
				isCorret = true;
			}
		}
		checkXmlParse(attriName, isCorret, allowElmentNull);

		return value;
	}

	public static String getElementValue(Element element, boolean allowElementNull) throws XMLParseException {
		String value = null;
		boolean isCorret = false;// 符号变量
		value = element.getStringValue();
		if (!StringUtil.isStrNullOrWhiteSpace(value)) {
			isCorret = true;
		}
		checkXmlParse(element.getName(), isCorret, allowElementNull);

		return value;
	}

	/**
	 * 获取某节点子节点的值
	 * 
	 * @param parentElement    指定父节点
	 * @param eleName          指定子节点名称
	 * @param allowElementNull 是否允许子节点为空
	 * @return
	 * @throws XMLParseException
	 */
	public static String getSonElementValue(Element parentElement, String eleName, boolean allowElementNull)
			throws XMLParseException {
		Element element = getElemnt(parentElement, eleName, allowElementNull);
		return element.getStringValue();
	}

	/**
	 * @param eleName          节点值
	 * @param isCorret         是否正确
	 * @param allowElementNull 是否允许值为空
	 * @throws XMLParseException
	 */
	private static void checkXmlParse(String eleName, boolean isCorret, boolean allowElementNull)
			throws XMLParseException {
		if (!allowElementNull && !isCorret) {// 允许节点为空的情况
			throw new XMLParseException("[" + eleName + "]属性不存在或值为空");
		}
	}

	/**
	 * allowElementNull默认false的校验
	 * 
	 * @param eleName
	 * @param isCorret
	 * @throws XMLParseException
	 */
	private static void checkXmlParse(String eleName, boolean isCorret) throws XMLParseException {
		checkXmlParse(eleName, false, isCorret);
	}
}
