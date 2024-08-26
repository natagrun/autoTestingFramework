package org.utils;

import org.apache.logging.log4j.Logger;

import java.io.File;

public class FileUtils {
    protected static Logger logger = LoggerSingleton.getInstance().getLogger();

    public static void clearDirectory(String directoryPath){
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {

            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.delete()) {
                    logger.info("File {} successfully deleted", file.getName());
                } else {
                    logger.error("Cant delete file {}.", file.getName());
                }
            }
        } else {
            logger.error("The specified directory does not exist or is not a directory.");
        }

    }
}
