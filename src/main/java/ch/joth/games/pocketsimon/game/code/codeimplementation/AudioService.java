package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.codedefinition.IAudioService;
import ch.joth.games.pocketsimon.game.code.eSoundFile;
import org.apache.logging.log4j.Level;

import javax.sound.sampled.*;
import java.util.Objects;

/**
 * AudioService class implements IAudioService and LineListener.
 * It is responsible for handling the audio events and playing the sound files.
 */
public class AudioService implements IAudioService {
    /**
     * Default constructor for the AudioService class.
     * It is used to create an instance of the AudioService class.
     */
    public AudioService() {
    }

    /**
     * Plays the sound file with the provided file name.
     * Logs an error if there is an exception during playback.
     *
     * @param fileName a string representing the name of the sound file
     */
    private void playSound(String fileName) {

        try {

            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResourceAsStream("/music/" + fileName)));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            if (fileName.contains("fail")) {
                clip.setFramePosition(clip.getFrameLength() / 2);
            }
            clip.start();

        } catch (Exception e) {

            new ServiceFactory().LoggingService().log("Error during playback of sound file: " + fileName, Level.ERROR, e.getClass());

        }

    }

    /**
     * Plays the sound file based on the provided eSoundFile.
     * Calls the playSound method with the corresponding file name.
     * Logs an error if the sound file is not found.
     *
     * @param soundFile an instance of eSoundFile
     */
    public void playSound(eSoundFile soundFile) {
        switch (soundFile) {
            case Red:
                playSound("red.wav");
                break;
            case Blue:
                playSound("blue.wav");
                break;
            case Green:
                playSound("green.wav");
                break;
            case Yellow:
                playSound("yellow.wav");
                break;
            case Fail:
                playSound("fail.wav");
                break;

            default:
                new ServiceFactory().LoggingService().log("Sound file not found: " + soundFile, Level.ERROR, getClass());
                break;
        }
    }

}