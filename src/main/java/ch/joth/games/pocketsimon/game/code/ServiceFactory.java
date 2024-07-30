package ch.joth.games.pocketsimon.game.code;

import ch.joth.games.pocketsimon.game.code.codeimplementation.*;

/**
 * This class is a factory for creating instances of various services.
 */
public class ServiceFactory {

    /**
     * Default constructor for the ServiceFactory class.
     * It is used to create an instance of the ServiceFactory class.
     */
    public ServiceFactory() {
// Comment to prevent Warnings from JavaDoc generation
    }

    private AudioService _audioService;

    /**
     * This method returns an instance of the AudioService.
     * If an instance does not already exist, it creates one.
     *
     * @return An instance of the AudioService.
     */
    public AudioService AudioService() {
        if (_audioService == null) {
            _audioService = new AudioService();
        }
        return _audioService;
    }

    private GameBehaviourService _gameBehaviour;

    /**
     * This method returns an instance of the GameBehaviour.
     * If an instance does not already exist, it creates one.
     *
     * @return An instance of the GameBehaviour.
     */
    public GameBehaviourService GameBehaviour() {
        if (_gameBehaviour == null) {
            _gameBehaviour = new GameBehaviourService();
        }
        return _gameBehaviour;
    }

    private FormRendererService _formRenderer;

    /**
     * This method returns an instance of the FormRenderer.
     * If an instance does not already exist, it creates one.
     *
     * @return An instance of the FormRenderer.
     */
    public FormRendererService FormRenderer() {
        if (_formRenderer == null) {
            _formRenderer = new FormRendererService();
        }
        return _formRenderer;
    }

    private LoggingService _loggingService;

    /**
     * This method returns an instance of the LoggingService.
     * If an instance does not already exist, it creates one.
     *
     * @return An instance of the LoggingService.
     */
    public LoggingService LoggingService() {
        if (_loggingService == null) {
            _loggingService = new LoggingService();
        }
        return _loggingService;
    }

    // Holds the instance of ConfigService
    private ConfigService _configService;

    /**
     * This method returns an instance of the ConfigService.
     * If an instance does not already exist, it creates one.
     *
     * @return An instance of the ConfigService.
     */
    public ConfigService ConfigService() {
        if (_configService == null) {
            _configService = new ConfigService();
        }
        return _configService;
    }

    /**
     * This method resets the instances of all services.
     */
    public void reset() {
        _audioService = null;
        _gameBehaviour = null;
        _formRenderer = null;
        _loggingService = null;
        _configService = null;
    }
}