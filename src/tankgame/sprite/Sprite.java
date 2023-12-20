package tankgame.sprite;

import tankgame.scene.TankGameScene;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


/*
这个类是一个抽象类，表示游戏中的精灵，包括游戏中的角色、物体或其他可视元素，
例如坦克、子弹、墙壁等。它定义了精灵的基本属性和方法，如图像、位置、大小、绘制、碰撞检测和销毁等。
其他具体的精灵类都可以继承这个抽象类，以实现自己的特定功能和行为。
 */
public abstract class Sprite {

    Image image;
    double x, y, width, height;
    TankGameScene gameScene;

    public Sprite(Image image, double x, double y, double width, double height, TankGameScene gameScene) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gameScene = gameScene;
    }

    public Sprite(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(image, x, y, width, height);
    }

    public Rectangle2D getContour() {
        return new Rectangle2D(x, y, width, height);
    }

    public void destroy() {};
}
