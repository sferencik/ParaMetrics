package sferencik.teamcity.parametrics;

import jetbrains.buildServer.log.Loggers;

public class Logger {
    static void info(String message) {
        Loggers.SERVER.info("[ParaMetrics] " + message);
    }
    static void error(String message) {
        Loggers.SERVER.error("[ParaMetrics] " + message);
    }
}
