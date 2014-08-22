package mainPackage;
import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.ResourceLoader;

public class LoadSound {
	private Audio wavEffect;
	boolean isAlive = false;
	
	public LoadSound(String SoundName) {
		start.sounds.add(this);
        try {
		    // you can play wavs by loading the complete thing into 
		    // a sound
		    wavEffect = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sounds"+alternativeCommons.adionalPath+"/"+SoundName));
	        } catch (IOException e) {
		    e.printStackTrace();
        }
	} 
	void start() {
		wavEffect.playAsSoundEffect(1.0f, 1.0f, false);
		isAlive = true;
	}
	
	void update(){
		if (wavEffect.isPlaying()) {
			SoundStore.get().poll(0);
		}
		else {
			isAlive = false;
		}
	}
	
	void closeStream() {
		wavEffect.stop();
	}
}
