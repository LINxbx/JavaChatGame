package tankgame.sprite;

import javafx.scene.image.Image;

public class Rock extends Sprite {
    public Rock(double x, double y) {
        super(new Image("tankgame/tankimage/石头.png/"), x, y, 62, 62);
    }
}
