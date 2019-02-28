package maoko.common.log;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

public class FileResource {

	private String pathOrName;

	/**
	 * @param pathOrName / 开头
	 */
	public FileResource(String pathOrName) {
		this.pathOrName = pathOrName;
	}

	/**
	 * 返回指定资源的输入流
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getResourceStream() throws IOException {
		return this.getClass().getClassLoader().getResourceAsStream(pathOrName);
	}

	/**
	 * 返回指定资源的路径
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getResourcePath() throws UnsupportedEncodingException {
		URL url = this.getClass().getClassLoader().getResource(pathOrName);
		return URLDecoder.decode(url.getFile(), "utf-8");
	}
}
