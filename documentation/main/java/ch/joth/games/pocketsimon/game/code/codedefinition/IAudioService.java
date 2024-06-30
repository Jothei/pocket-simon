package ch.joth.games.pocketsimon.game.code.codedefinition;

import ch.joth.games.pocketsimon.game.code.eSoundFile;

/**
 * This interface represents the audio service for the game.
 * It provides a method to play a sound file.
 */
public interface IAudioService {
    /**
     * This method plays a sound file.
     * @param fileName The name of the sound file to play.
     */
      void playSound(eSoundFile fileName);

    }
