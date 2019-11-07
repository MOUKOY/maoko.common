package maoko.common.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import maoko.common.system.AppRunPathUitl;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import maoko.common.file.PathUtil;
import maoko.common.model.system.LogLevel;
import maoko.sdk.SDKCommon;

public class Log4j2Writer implements IWriteLog {

    private Logger log;
    public final static String CONFIGFILE = "config/log4j2.xml";
    public final static String CONFIGFILENAME = "log4j2.xml";

    /**
     * 初始化log4j
     *
     * @throws IOException
     */
    public static void init() throws LoginitException {
        FileInputStream inConfStream = null;
        LoggerContext context = null;
        try {
            //String runPath = System.getProperty(SDKCommon.RUNPATH);
            String runPath = AppRunPathUitl.getAppRunPathNew();
            String filepath = PathUtil.combinePath(runPath, CONFIGFILE);
            File file = new File(filepath);
            String configPath = file.getAbsolutePath();
            System.out.println("setting log4j2.xml path " + configPath);
            if (!file.exists())// 不存在使用默认值
            {
                InputStream in = new FileResource(CONFIGFILE).getResourceStream();
                if (in != null) {
                    FileUtils.copyInputStreamToFile(in, file);
                    System.err.println(CONFIGFILE + " is not found,sys will use default config");
                }
            }
            /*
             * System.setProperty("log4j.configurationFile", url.toString()); LoggerContext
             * context = (LoggerContext) LogManager.getContext(false);
             * context.reconfigure(); 这种方法也行，但是代码中修改系统属性，不太好维护
             */
            // context = (LoggerContext) LogManager.getContext(false);
            // context.reconfigure();
            inConfStream = new FileInputStream(file);
            ConfigurationSource source = new ConfigurationSource(inConfStream, file.toURI().toURL());
            context = Configurator.initialize(null, source);

            // 未初始化成功重新加载-与springmvc集成时，日志先启动问题修复
            //未初始化成功重新加载-与springboot集成时，日志配置文件加载非自定义文件修复修复
            Configuration configuration = context.getConfiguration();
            if (configuration instanceof DefaultConfiguration || !configPath.equals(configuration.getName())) {
                inConfStream = new FileInputStream(file);
                source = new ConfigurationSource(inConfStream, file.toURI().toURL());
                final Configuration config = ConfigurationFactory.getInstance().getConfiguration(context, source);
                context.start(config);
            }
            Log4j2Writer log = new Log4j2Writer(Log4j2Writer.class);
            log.info("log4j has been inited sucessful");

        } catch (Exception e) {
            throw new LoginitException("日志初始化失败", e);
        } finally {

            try {
                if (inConfStream != null) {
                    inConfStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭日志
     */
    public static void stop() {
        LogManager.shutdown();
    }

    public Log4j2Writer(Class<?> clas) {
        log = LogManager.getLogger(clas);
    }

    public Log4j2Writer(String dscp) {
        log = LogManager.getLogger(dscp);
    }

    private void print(LogLevel lev, String dscrp, Object... e) {
        if (log != null) {
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
