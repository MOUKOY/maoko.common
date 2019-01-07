package soft.common.system;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

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
	 * 获取运行路径
	 * 
	 * @param locationClass
	 * @return
	 * @throws OstypeMissWatchException
	 * @throws DataIsNullException
	 */
	public static String getRunPath(Class<?> locationClass) throws OstypeMissWatchException, DataIsNullException {
		if (null == locationClass)
			throw new DataIsNullException("locationClass is null");

		String runPath = null;
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
				System.setProperty("softRun.path", runPath);
			}
		} catch (UnsupportedEncodingException e) {
		}
		return runPath;

	}

	/**
	 * 获取是jar包启动 还是class file启动
	 * 
	 * @return
	 */
	public static String getRunProtocol(Class<?> clazz) {
		URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
		return url.getProtocol();
	}

}
