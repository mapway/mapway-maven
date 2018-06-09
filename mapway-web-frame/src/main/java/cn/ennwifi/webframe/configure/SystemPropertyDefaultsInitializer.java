package cn.ennwifi.webframe.configure;

import cn.ennwifi.webframe.tools.Times;

import java.sql.Timestamp;
import java.util.TimeZone;
import java.util.logging.Logger;

/**
 * The type System property defaults initializer.
 */
public class SystemPropertyDefaultsInitializer {

    private static final Logger logger = Logger.getLogger(SystemPropertyDefaultsInitializer.class
            .getName());


    /**
     * Initialize.
     */
    public void initialize() {
        logger.info("SystemPropertyWebApplicationInitializer onStartup called");

        printTimes();
        // can be set runtime before Spring instantiates any beans
        // TimeZone.setDefault(TimeZone.getTimeZone("GMT+00:00"));
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));

        printTimes();

    }

    private void printTimes() {
        logger.info("=============时间信息打印=============");
        long now = System.currentTimeMillis();
        logger.info("系统时间戳:" + now);
        logger.info("格式化:" + Times.formatTime(now));
        Timestamp tnow = Times.now();
        logger.info("Timestamp:" + Times.formatTime(tnow));

    }

}
