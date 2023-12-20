package tankgame.controller;

import tankgame.director.Director;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import tankgame.sound.SoundEffect;


/*
当鼠标点击startGame时，调用了Director类的gameStart()方法开始游戏。
当鼠标移动到startGame上时，将其不透明度降低，当鼠标离开时，将其不透明度恢复。
 */
public class IndexController {

    @FXML
    private ImageView startGame;

    @FXML
    void mouseClickedStartGame(MouseEvent event) {
        SoundEffect.play("file:sound/开始游戏.mp3");
        Director.getInstance().gameStart();     //开始按钮
    }

    @FXML
    void mouseEnteredStartGame(MouseEvent event) {
            startGame.setOpacity(0.5);
    }

    @FXML
    void mouseExitedStartGame(MouseEvent event) {
            startGame.setOpacity(1);
        }

}
