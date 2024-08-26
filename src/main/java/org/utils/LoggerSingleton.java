package org.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoggerSingleton {
    private static final Logger logger = LogManager.getLogger();
    private static LoggerSingleton instance = null;

    private LoggerSingleton() {}

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            synchronized (LoggerSingleton.class) {
                if (instance == null) {
                    instance = new LoggerSingleton();
                }
            }
        }
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }
}
