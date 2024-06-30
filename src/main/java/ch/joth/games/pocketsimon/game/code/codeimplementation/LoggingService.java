package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.codedefinition.ILoggingService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class implements the ILoggingService interface and provides methods to log messages with different levels and classes.
 */
public class LoggingService implements ILoggingService {

    /**
     * Default constructor for the LoggingService class.
     * It is used to create an instance of the LoggingService class.
     */
    public LoggingService() {
    }

    /**
     * This method logs a message with a specific level, class, and object.
     * @param message The message to be logged.
     * @param logLevel The level of the log.
     * @param logClass The class where the log is being made.
     * @param obj The object to be logged.
     */
    public void log(String message, Level logLevel, Class<?> logClass, Object obj) {
        Logger logger = LogManager.getLogger(logClass);
        logger.log(logLevel, message, obj);
    }

    /**
     * This method logs a message with a specific level and class.
     * @param message The message to be logged.
     * @param logLevel The level of the log.
     * @param logClass The class where the log is being made.
     */
    public void log(String message, Level logLevel, Class<?> logClass) {
        Logger logger = LogManager.getLogger(logClass);
        logger.log(logLevel, message);
    }

}