package actions;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import gui.StartScreen;

public class audioManager {
public static FloatControl volumeControl;
public static Clip clip;
public static void playSound(String soundFile) {

    try {
        // Use the class loader to find the resource
        File file = new File(audioManager.class.getResource(soundFile).getFile());
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        volumeControl = (FloatControl)
        clip.getControl(FloatControl.Type.MASTER_GAIN);
      
        setVolume(1F);
    }	 
    catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
    {
        e.printStackTrace();
    }
}
public static void stopSound(String soundFile) {
	if(clip != null && clip.isRunning()) {
		clip.stop();
		clip.close();
	}
	
}

public static void setVolume(float volume) {
    if (volumeControl != null) {
        volumeControl.setValue(volume);
    }
  }
}

 