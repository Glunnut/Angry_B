package Livrable2.ab;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlaySound {
	final Clip clip;

	public PlaySound(String urlSon) throws LineUnavailableException,
			IOException, UnsupportedAudioFileException {
		URL url = PlaySound.class.getResource(urlSon);
		clip = AudioSystem.getClip();
		try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
	}

}
