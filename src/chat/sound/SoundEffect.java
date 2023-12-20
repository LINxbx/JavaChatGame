package chat.sound;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static void play(String src){
        AudioClip audioClip = new AudioClip(src);
        audioClip.play();
    }
}
