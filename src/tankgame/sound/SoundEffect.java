package tankgame.sound;


import javafx.scene.media.AudioClip;

//在软件开发中，常常会将一些常用的功能抽象出来，放在一个独立的 util （工具）包中，方便复用和维护。
public class SoundEffect {

    public static void play(String src){
        AudioClip audioClip = new AudioClip(src);
            audioClip.play();
        }
}
