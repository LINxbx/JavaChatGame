package tankgame.sprite;


import tankgame.scene.TankGameScene;
import javafx.scene.image.Image;
import tankgame.util.Direction;
import tankgame.util.Group;

import java.util.HashMap;
import java.util.Map;


//Sprite（精灵）通常指游戏中的角色、物体或其他可视元素。它们通常是游戏世界中的可移动实体，可以在游戏中移动、旋转、缩放、碰撞等
public abstract class Role extends Sprite {




    boolean alive = true;
    Group group;
    Direction dir;
    double speed;
    Map<String,Image> imageMap = new HashMap<>();

    public Role(double x, double y, double width, double height, tankgame.util.Group group, tankgame.util.Direction dir, TankGameScene TankgameScene) {
        super(null, x, y, width, height, TankgameScene);
        this.group = group;
        this.dir = dir;
    }

    public abstract void move();
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
