package soft.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.management.modelmbean.XMLParseException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml助手
 * 
 * @author fanpei
 *
 */
public class XmlReadUtil {
	SAXReader saxReadr;
	Document document;
	InputStream file;
	Element rootEle;// 根节点

	public XmlReadUtil(InputStream in) throws FileNotFoundException, DocumentException {
		saxReadr = new SAXReader();
		document = saxReadr.read(file = in);
		rootEle = document.getRootElement();
	}

	/**
	 * 返回根节点
	 * 
	 * @return
	 */
	public Element getRootEle() {
		return rootEle;
	}

	/**
	 * 获取指定节点值
	 * 
	 * @param parentElement 父节点
	 * @param eleName       指定子节点名称
	 * @return
	 * @throws XMLParseException
	 */
	public String getElementValue(Element parentElement, String eleName) throws XMLParseException {
		return XmlParseUtil.getElementValue(parentElement, eleName, true);

	}

	public Element getElement(Element parentElement, String eleName) throws XMLParseException {
		return XmlParseUtil.getElemnt(parentElement, eleName, false);
	}

	public void close() {
		try {
			if (file != null)
				file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
