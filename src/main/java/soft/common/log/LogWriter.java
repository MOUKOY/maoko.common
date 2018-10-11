package soft.common.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import soft.common.file.PathUtil;
import soft.common.model.system.LogLevel;

public class LogWriter implements IWriteLog {

	private Logger log;
	public final static String CONFIGFILE = "/config/log4j2.xml";

	/**
	 * 初始化log4j
	 * 
	 * @param RunPath
	 * @throws IOException
	 */
	public static void init(String RunPath) throws LoginitException {
		try {
			String filepath = PathUtil.combinePath(RunPath, CONFIGFILE);
			File file = new File(filepath);
			if (!file.exists())
				throw new FileNotFoundException(filepath + " is not exist");

			// InputStream in =
			// ConfigUtil.class.getClassLoader().getResourceAsStream("log4j2.xml");
			// FileCopy.copy(in, filepath, false);
			System.setProperty("log4j.configurationFile", file.toURI().toURL().toString());
			// 异步模式
			// System.setProperty("Log4jContextSelector",
			// "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");

			// ConfigurationSource source = new ConfigurationSource(new
			// FileInputStream(file));
			// LoggerContext context = Configurator.initialize(null, source);
			// XmlConfiguration xmconfig = new XmlConfiguration(context, source);
			// context.start(xmconfig);

		} catch (Exception e) {
			throw new LoginitException("日志初始化失败", e);
		}

	}

	/**
	 * 关闭日志
	 */
	public static void stop() {
		LogManager.shutdown();
	}

	public LogWriter(Class<?> clas) {
		log = LogManager.getLogger(clas);
	}

	public LogWriter(String dscp) {
		log = LogManager.getLogger(dscp);
	}

	private void print(LogLevel lev, String dscrp, Object... e) {
		if (log != null && log.isDebugEnabled()) {
			switch (lev) {
			case DEBUG:
				log.debug(dscrp, e);
				break;
			case WARN:
				log.warn(dscrp, e);
				break;
			case ERROR:
				log.error(dscrp, e);
				break;
			default:
				log.info(dscrp, e);
				break;
			}
		}
	}

	@Override
	public void error(String dscrp, Object... e) {
		print(LogLevel.ERROR, dscrp, e);
	}

	@Override
	public void error(Throwable e) {
		print(LogLevel.ERROR, "", e);
	}

	@Override
	public void debug(String dscrp, Object... e) {
		print(LogLevel.DEBUG, dscrp, e);
	}

	@Override
	public void warn(String dscrp, Object... e) {
		print(LogLevel.WARN, dscrp, e);
	}

	@Override
	public void info(String dscrp, Object... e) {
		print(LogLevel.INFO, dscrp, e);
	}

}
