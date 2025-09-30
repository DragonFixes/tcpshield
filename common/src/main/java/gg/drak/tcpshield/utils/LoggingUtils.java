package gg.drak.tcpshield.utils;

import gg.drak.tcpshield.configs.LoggingConfig;
import gg.drak.tcpshield.eventables.DragonManager;
import net.minecraft.text.Text;

public class LoggingUtils {
    public static void logSystem(String message) {
        System.out.println(message);
    }

    public static void logSingle(String message) {
        try {
            Text component = ColorUtils.colorizeToComponent(message);
            DragonManager.getServer().sendMessage(component);
        } catch (NullPointerException npe) {
            logSystem(message);
        } catch (Exception e) {
            logSystem(message);
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        String[] lines = message.split("\n");
        for (String line : lines) {
            logSingle(line);
        }
    }

    public static void logInfo(String message) {
        if (LoggingConfig.isInfoEnabled()) log(LoggingConfig.getInfoPrefix() + message);
    }

    public static void logWarn(String message) {
        if (LoggingConfig.isWarnEnabled()) log(LoggingConfig.getWarnPrefix() + message);
    }

    public static void logError(String message) {
        if (LoggingConfig.isErrorEnabled()) log(LoggingConfig.getErrorPrefix() + message);
    }

    public static void logDebug(String message) {
        if (LoggingConfig.isDebugEnabled()) log(LoggingConfig.getDebugPrefix() + message);
    }

    public static void logInfo(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            logInfo(element.toString());
        }
    }

    public static void logWarn(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            logWarn(element.toString());
        }
    }

    public static void logError(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            logError(element.toString());
        }
    }

    public static void logDebug(StackTraceElement[] stackTraceElements) {
        for (StackTraceElement element : stackTraceElements) {
            logDebug(element.toString());
        }
    }

    public static void logInfo(Throwable throwable) {
        logInfo(throwable.getMessage());
        logInfo(throwable.getStackTrace());
    }

    public static void logWarn(Throwable throwable) {
        logWarn(throwable.getMessage());
        logWarn(throwable.getStackTrace());
    }

    public static void logError(Throwable throwable) {
        logError(throwable.getMessage());
        logError(throwable.getStackTrace());
    }

    public static void logDebug(Throwable throwable) {
        logDebug(throwable.getMessage());
        logDebug(throwable.getStackTrace());
    }

    public static void logInfo(String message, Throwable throwable) {
        logInfo(message);
        logInfo(throwable);
    }

    public static void logWarn(String message, Throwable throwable) {
        logWarn(message);
        logWarn(throwable);
    }

    public static void logError(String message, Throwable throwable) {
        logError(message);
        logError(throwable);
    }

    public static void logDebug(String message, Throwable throwable) {
        logDebug(message);
        logDebug(throwable);
    }
}
