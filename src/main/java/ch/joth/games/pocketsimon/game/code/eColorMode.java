package ch.joth.games.pocketsimon.game.code;

/**
 * Enum representing the color modes available in the game.
 * The color modes are either COLOR_ON or COLOR_OFF.
 */
public enum eColorMode {

    /**
     * Represents the color mode being on. In this mode, colors are displayed.
     */
    COLOR_ON,

    /**
     * Represents the color mode being off. In this mode, only one Button Color is displayed.
     */
    COLOR_OFF,
    /**
     * Represents the color mode being off. In this mode, only one Button Color is displayed when the buttons needs to be pressed.
     */
    COLOR_AUDIO_ONLY
}