package tankgame.sprite;

import javafx.scene.image.Image;

//Sprite（精灵）通常指游戏中的角色、物体或其他可视元素。它们通常是游戏世界中的可移动实体，可以在游戏中移动、旋转、缩放、碰撞等
public class Background extends Sprite {
    public Background() {
        super(new Image("tankgame/tankimage/背景.png"), 0, 0, 960, 640);
    }
}
