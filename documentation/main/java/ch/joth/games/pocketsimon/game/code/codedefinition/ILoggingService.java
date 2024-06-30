package ch.joth.games.pocketsimon.game.code.codedefinition;

import org.apache.logging.log4j.Level;

/**
 * This interface represents the logging service for the application.
 * It provides methods to log messages with different levels and classes.
 */
public interface ILoggingService {

    /**
     * This method logs a message with a specific level, class, and object.
     * @param message The message to be logged.
     * @param logLevel The level of the log.
     * @param logClass The class where the log is being made.
     * @param obj The object to be logged.
     */
    void log(String message, Level logLevel, Class<?> logClass, Object obj);

    /**
     * This method logs a message with a specific level and class.
     * @param message The message to be logged.
     * @param logLevel The level of the log.
     * @param logClass The class where the log is being made.
     */
    void log(String message, Level logLevel, Class<?> logClass);
}