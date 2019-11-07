package maoko.sdk;

import java.io.IOException;

import maoko.common.exception.DataIsNullException;
import maoko.common.exception.OstypeMissWatchException;
import maoko.common.log.Log4j2Writer;
import maoko.common.log.LoginitException;
import maoko.common.system.AppRunPathUitl;

/**
 * Hello world!
 */
public class SDKCommon {
    private static boolean iniited = false;// 是否初始化

    /**
     * 自定义运行目录字符串
     */
    public static final String RUNPATH = "maokoRun.path";

    /**
     * 初始化-初始化日志
     *
     * @throws LoginitException
     * @throws DataIsNullException
     * @throws OstypeMissWatchException
     */
    public synchronized static void init() throws LoginitException, OstypeMissWatchException, DataIsNullException {
        if (!iniited) {
            iniited = true;
            System.out.println("sdkcommon is initing....");
            Log4j2Writer.init();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            init();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
