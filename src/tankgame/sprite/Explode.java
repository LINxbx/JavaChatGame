package tankgame.sprite;

import tankgame.scene.TankGameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


//Sprite（精灵）通常指游戏中的角色、物体或其他可视元素。它们通常是游戏世界中的可移动实体，可以在游戏中移动、旋转、缩放、碰撞等
public class Explode extends Sprite {
    private int count = 0;
    private Image[] images = new Image[]{
            new Image("tankgame/tankimage/爆炸1.png/"),
            new Image("tankgame/tankimage/爆炸2.png/"),
            new Image("tankgame/tankimage/爆炸3.png/"),
            new Image("tankgame/tankimage/爆炸4.png/"),
            new Image("tankgame/tankimage/爆炸5.png/"),
            new Image("tankgame/tankimage/爆炸6.png/"),
            new Image("tankgame/tankimage/爆炸7.png/"),
            new Image("tankgame/tankimage/爆炸8.png/"),
            new Image("tankgame/tankimage/爆炸9.png/")

    };

    public Explode(double x, double y, TankGameScene gameScene) {
        super(null, x, y, 0 ,0,gameScene);
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if(count >= images.length){
            gameScene.explodes.remove(this);
            return;
        }
        image = images[count];
        double ex = x - image.getWidth()/2;
        double ey = y - image.getHeight()/2;
        graphicsContext.drawImage(image,ex,ey);
        count ++;
    }
}
