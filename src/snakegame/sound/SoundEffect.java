package snakegame.sound;

import javafx.scene.media.AudioClip;

public class SoundEffect {
    public static AudioClip background = new AudioClip("file:sound/SnakeGameBackground.mp3");

    public static void play(String src){
        AudioClip audioClip = new AudioClip(src);
        audioClip.play();
    }
    public static void Background(){
        background.setCycleCount(3);
        background.play();
    }
}
