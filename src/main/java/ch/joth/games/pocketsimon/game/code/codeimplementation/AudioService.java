package ch.joth.games.pocketsimon.game.code.codeimplementation;

import ch.joth.games.pocketsimon.game.code.ServiceFactory;
import ch.joth.games.pocketsimon.game.code.codedefinition.IAudioService;
import ch.joth.games.pocketsimon.game.code.eSoundFile;
import org.apache.logging.log4j.Level;

import javax.sound.sampled.*;
import java.util.Objects;

/**
 * This class represents the audio service for the game.
 * It implements the IAudioService interface and LineListener for handling audio events.
 */
public class AudioService implements IAudioService {

    /**
     * This method plays a sound file.
     *
     * @param fileName The name of the sound file to play.
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
