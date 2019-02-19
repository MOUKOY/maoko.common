package soft.common.system;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

import moukoy.sdkcommon.SDKCommon;
import soft.common.StaticClass;
import soft.common.StringUtil;
import soft.common.exception.DataIsNullException;
import soft.common.exception.OstypeMissWatchException;
import soft.common.file.PathUtil;
import soft.common.model.enm.EOsType;

/**
 * 程序运行目录获取助手
 * 
 * @author fanpei
 *
 */
public class AppRunPathUitl extends StaticClass {

	/**
	 * 获取类所在的运行路径
	 * 
	 * @param locationClass
	 * @return
	 * @throws OstypeMissWatchException
	 * @throws DataIsNullException
	 */
	public static void initRunPath(Class<?> locationClass) throws OstypeMissWatchException, DataIsNullException {
		String runPath = null;
		if (null == locationClass)
			throw new DataIsNullException("locationClass is null");

		try {
			URL url = locationClass.getProtectionDomain().getCodeSource().getLocation();
			runPath = new File(URLDecoder.decode(url.getPath(), "utf-8")).getAbsolutePath();
			runPath = PathUtil.getParentDir(runPath);
			EOsType osType = OSPlatformUtil.getOSType();
			if (runPath == null) {
				System.out.println("path get fail,init runPath...");
				if (EOsType.Linux == osType) {// linux
					runPath = "/tmp/softNet";
					System.out.println("init linux runPath sucessful");
				} else if (EOsType.Windows == osType) {
					runPath = "c:/tmp/softNet";
				} else
					throw new OstypeMissWatchException("此框架仅仅运行在windows和linux上，版本不匹配");
			}
			if (!StringUtil.isStringNull(runPath)) {
				File runFile = new File(runPath);
				if (!runFile.exists()) {
					runFile.mkdirs();
				}

			}
		} catch (UnsupportedEncodingException e) {
		}

		System.out.println("system run path init:" + runPath);
		System.setProperty(SDKCommon.RUNPATH, runPath);

	}

	/**
	 * 获取程序运行目录
	 * 
	 * @return
	 */
	public static String getAppRunPath() {
		String runPath = null;
		boolean initedRunpath = false;
		try {
			String tmpRunPath = System.getProperty(SDKCommon.RUNPATH);
			if (!StringUtil.isStrNullOrWhiteSpace(tmpRunPath)) {
				runPath = tmpRunPath;
			} else {
				runPath = getClassRunPath2();
				initedRunpath = true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (initedRunpath) {
				System.out.println("system run path init:" + runPath);
				System.setProperty(SDKCommon.RUNPATH, runPath);
			}
		}
		return runPath;
	}

	public static String getClassRunPath(Class<?> clazz) throws UnsupportedEncodingException {
		String basePath = clazz.getProtectionDomain().getCodeSource().getLocation().getPath();
		basePath = URLDecoder.decode(basePath, "utf-8");
		if (basePath.endsWith(".jar")) {
			basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
		} else {
			URL url = Thread.currentThread().getContextClassLoader().getResource("");
			basePath = URLDecoder.decode(url.getFile(), "utf-8");
		}
		File f = new File(basePath);
		basePath = f.getAbsolutePath();
		return basePath;
	}

	public static String getClassRunPath2() throws IOException {
		String basePath = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Enumeration<URL> urls = loader.getResources("");
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			if (null != url) {
				basePath = URLDecoder.decode(url.getFile(), "utf-8");
				if (basePath.endsWith(".jar")) {// 可执行jar包运行的结果里包含".jar"
					// 获取jar包所在目录
					basePath = basePath.substring(0, basePath.lastIndexOf("/") + 1);
				}

				File f = new File(basePath);
				basePath = f.getAbsolutePath();
				break;
			}
		}

		return basePath;
	}
}
