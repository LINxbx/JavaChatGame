package tankgame.sprite;

import tankgame.director.Director;
import tankgame.scene.TankGameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import tankgame.sound.SoundEffect;
import tankgame.util.Direction;
import tankgame.util.Group;

import java.util.List;

//Sprite（精灵）通常指游戏中的角色、物体或其他可视元素。它们通常是游戏世界中的可移动实体，可以在游戏中移动、旋转、缩放、碰撞等
public class Bullet extends Role {
    public Bullet(double x, double y, Group group, Direction dir, TankGameScene gameScene) {
        super(x, y, 0, 0, group, dir, gameScene);
        speed = 8;
        if (dir.equals(Direction.up) || dir.equals(Direction.down)) {
            width = 25;
            height = 24;
        } else if (dir.equals(Direction.left) || dir.equals(Direction.right)) {
            width = 25;
            height = 24;
        }
        if (group.equals(Group.green)) {
            switch (dir) {
                case up:
                    image = new Image("tankgame/tankimage/子弹1up.png");
                    break;
                case down:
                    image = new Image("tankgame/tankimage/子弹1down.png");
                    break;
                case left:
                    image = new Image("tankgame/tankimage/子弹1left.png");
                    break;
                case right:
                    image = new Image("tankgame/tankimage/子弹1right.png");
                    break;
            }
        } else {
            switch (dir) {
                case up:
                    image = new Image("tankgame/tankimage/子弹2up.png");
                    break;
                case down:
                    image = new Image("tankgame/tankimage/子弹2down.png");
                    break;
                case left:
                    image = new Image("tankgame/tankimage/子弹2left.png");
                    break;
                case right:
                    image = new Image("tankgame/tankimage/子弹2right.png");
                    break;
            }
        }
    }

    @Override
    public void move() {
        switch (dir) {
            case up:
                y -= speed;
                break;
            case down:
                y += speed;
                break;
            case left:
                x -= speed;
                break;
            case right:
                x += speed;
                break;
        }
        if (x < 0 || y < 0 || x > Director.WIDTH || y > Director.HEIGHT) {
            gameScene.bullets.remove(this);
        }
    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        if (!alive) {
            gameScene.bullets.remove(this);
            gameScene.explodes.add(new Explode(x, y, gameScene));
            SoundEffect.play("file:sound/爆炸.mp3/");
            return;
        }
        super.paint(graphicsContext);
        move();
    }

    //碰撞
    public boolean impactTank(Tank tank) {
        if (tank != null && !tank.group.equals(this.group) && getContour().intersects(tank.getContour())) {
            tank.setAlive(false);
            alive = false;
            return true;
        }
        return false;
    }

    public void impactTank(List<Tank> tanks) {
        for (Tank t : tanks) {
            impactTank(t);
        }
    }

    public boolean impactCrate(Crate crate) {
        if (crate != null && getContour().intersects(crate.getContour())) {
            alive = false;
            gameScene.crates.remove(crate);
            return true;
        }
        return false;
    }

    public void impactCrates(List<Crate> crates) {
        for (int i = 0; i < crates.size(); i++) {
            Crate crate = crates.get(i);
            impactCrate(crate);
        }
    }

    public boolean impactRock(Rock rock) {
        if (rock != null && getContour().intersects(rock.getContour())) {
            alive = false;
            return true;
        }
        return false;
    }

    public void impactRocks(List<Rock> rocks) {
        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            impactRock(rock);
        }
    }
}
