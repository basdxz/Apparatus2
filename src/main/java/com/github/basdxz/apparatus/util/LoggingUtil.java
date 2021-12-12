package com.github.basdxz.apparatus.util;


import lombok.val;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static com.github.basdxz.apparatus.ApparatusMod.NAME;
import static org.apache.logging.log4j.Level.*;

/*
    Pretty much https://stackoverflow.com/a/37711767 but adapted to work an earlier log4j
 */
public class LoggingUtil {

    protected static final Logger LOGGER = LogManager.getLogger(NAME);
    protected static final Map<String, TimeAndCount> lastLoggedTime = new HashMap<>();

    public static void debug(String message) {
        debug(message, null);
    }

    public static void debug(String message, Throwable throwable) {
        log(LOGGER, DEBUG, message, throwable);
    }

    public static void info(String message) {
        info(message, null);
    }

    public static void info(String message, Throwable throwable) {
        log(LOGGER, INFO, message, throwable);
    }

    public static void warn(String message) {
        warn(message, null);
    }

    public static void warn(String message, Throwable throwable) {
        log(LOGGER, WARN, 5000, message, throwable);
    }

    public static void error(String message) {
        error(message, null);
    }

    public static void error(String message, Throwable throwable) {
        log(LOGGER, ERROR, message, throwable);
    }

    public static void log(Logger logger, Level level, long timeBetweenLogs, String message, Throwable throwable) {
        if (!logger.isEnabled(level))
            return;

        val uniqueIdentifier = getFileAndLine();
        val lastTimeAndCount = lastLoggedTime.get(uniqueIdentifier);
        if (lastTimeAndCount == null) {
            log(logger, level, message, throwable);
        } else {
            synchronized (lastTimeAndCount) {
                val now = System.currentTimeMillis();
                if (now - lastTimeAndCount.time < timeBetweenLogs) {
                    lastTimeAndCount.count++;
                    return;
                } else {
                    log(logger, level, "|x" + lastTimeAndCount.count + "| " + message, throwable);
                }
            }
        }
        lastLoggedTime.put(uniqueIdentifier, new TimeAndCount());
    }

    protected static String getFileAndLine() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean enteredLogConsolidated = false;
        for (StackTraceElement ste : stackTrace) {
            if (ste.getClassName().equals(LoggingUtil.class.getName())) {
                enteredLogConsolidated = true;
            } else if (enteredLogConsolidated) {
                // We have now file/line before entering LogConsolidated.
                return ste.getFileName() + ":" + ste.getLineNumber();
            }
        }
        return "?";
    }

    protected static void log(Logger logger, Level level, String message, Throwable t) {
        if (t == null) {
            logger.log(level, message);
        } else {
            logger.log(level, message, t);
        }
    }

    protected static class TimeAndCount {
        private final long time;
        private int count;

        TimeAndCount() {
            this.time = System.currentTimeMillis();
        }
    }

}
