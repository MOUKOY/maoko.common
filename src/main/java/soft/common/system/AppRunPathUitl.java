package soft.common.system;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

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
	public static String getRunPath(Class<?> locationClass) throws OstypeMissWatchException, DataIsNullException {
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
		return runPath;

	}

	/**
	 * 获取程序运行目录
	 * 
	 * @return
	 */
	public static String getAppRunPath() {
		String runPath = null;
		boolean isget = false;
		try {
			String tmpRunPath = System.getProperty(SDKCommon.RUNPATH);
			if (!StringUtil.isStrNullOrWhiteSpace(tmpRunPath)) {
				runPath = tmpRunPath;
				isget = true;
			} else {
				runPath = getClassRunPath();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (!isget) {
				System.setProperty(SDKCommon.RUNPATH, runPath);
				System.out.println("system run path init:" + runPath);
			}
		}
		return runPath;
	}

	public static String getClassRunPath() throws UnsupportedEncodingException {
		String basePath = AppRunPathUitl.class.getProtectionDomain().getCodeSource().getLocation().getPath();
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

}
