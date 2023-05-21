package pl.put.poznan.json.logic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileLogger {
    private static final Logger logger = LoggerFactory.getLogger(FileLogger.class);

    public static void doSomething() {
        logger.info("Doing something...");
        logger.debug("Debugging information");
        logger.error("An error occurred");
    }
}
