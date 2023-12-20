package tankgame.sprite;

import tankgame.director.Director;
import tankgame.scene.TankGameScene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import tankgame.util.Direction;
import tankgame.util.Group;

import java.util.List;
import java.util.Random;

////Sprite（精灵）通常指游戏中的角色、物体或其他可视元素。它们通常是游戏世界中的可移动实体，可以在游戏中移动、旋转、缩放、碰撞等

public class Tank extends Role {

    Direction pdir;

    boolean keyup,keydown,keyleft,keyright;
    double oldx,oldy;

    public static Random random = new Random();

    public Tank(double x, double y, Group group, Direction dir, Direction pdir, TankGameScene gameScene) {
        super(x, y, 70, 85, group, dir, gameScene);//宽高写死
        this.pdir = pdir;
        speed = 5;
        if(group.equals(Group.green)) {
            imageMap.put("up", new Image("tankgame/tankimage/up1.png/"));
            imageMap.put("down", new Image("tankgame/tankimage/down1.png/"));
            imageMap.put("left", new Image("tankgame/tankimage/left1.png/"));
            imageMap.put("right", new Image("tankgame/tankimage/right1.png/"));
        } else {
            imageMap.put("up", new Image("tankgame/tankimage/up2.png/"));
            imageMap.put("down", new Image("tankgame/tankimage/down2.png/"));
            imageMap.put("left", new Image("tankgame/tankimage/left2.png/"));
            imageMap.put("right", new Image("tankgame/tankimage/right2.png/"));
        }
    }
    public void pressed(KeyCode keyCode){
        switch (keyCode){
            case UP:
                keyup = true;
                break;
            case DOWN:
                keydown = true;
                break;
            case LEFT:
                keyleft = true;
                break;
            case RIGHT:
                keyright = true;
                break;
        }
        redirect();
    }
    public void released(KeyCode keyCode){
        switch (keyCode){
            case F:
                openFire();
            case UP:
                keyup = false;
            case DOWN:
                keydown = false;
            case LEFT:
                keyleft = false;
            case RIGHT:
                keyright = false;
        }
        redirect();
    }

    public void redirect(){
        if(keyup && !keydown && !keyleft && !keyright) dir = Direction.up;
        else if(!keyup && keydown && !keyleft && !keyright) dir = Direction.down;
        else if(!keyup && !keydown && keyleft && !keyright) dir = Direction.left;
        else if(!keyup && !keydown && !keyleft && keyright) dir = Direction.right;
        else if(!keyup && !keydown && !keyleft && !keyright) dir = Direction.stop;

    }


    @Override

    public void move() {
        oldx = x;
        oldy = y;
        switch (dir){
            case up:
                y -= speed;
                break;
            case down:
                y +=speed;
                break;
            case left:
                x -= speed;
                break;
            case right:
                x +=speed;
                break;
        }
        if(dir != Direction.stop){
            pdir = dir;
        }
        //防止坦克越界
        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > Director.WIDTH - width - 20) x = Director.WIDTH - width - 20;
        if(y > Director.HEIGHT - height - 45) y = Director.HEIGHT - height - 45;

        if(group.equals(Group.red)) {
            int i = random.nextInt(60);
            switch (i){
                case 15:
                    Direction d[] = Direction.values();
                    dir = d[random.nextInt(d.length)];
                    break;
                case 30:
                    openFire();
                    break;
            }
        }
    }


    public void paint(GraphicsContext graphicsContext){
        if(group.equals(Group.red)&& !alive){
            gameScene.tanks.remove(this);
            return;
        }
        switch (pdir){
            case up:
                image = imageMap.get("up");
                break;
            case down:
                image = imageMap.get("down");
                break;
            case left:
                image = imageMap.get("left");
                break;
            case right:
                image = imageMap.get("right");
                break;
        }
        super.paint(graphicsContext);
        move();
    }

    public  void openFire(){
        double bulletx = x;
        double bullety = y;
        switch (pdir){
            case up:
                bulletx = x + 25;
                bullety = y;
                break;
            case down:
                bulletx = x + 25;
                bullety = y + height;
                break;
            case left:
                bulletx = x;
                bullety = y + 25;
                break;
            case right:
                bulletx = x + width;
                bullety = y + 25;
                break;
        }
        gameScene.bullets.add(new Bullet(bulletx,bullety,group,pdir,gameScene));
    }

    public boolean impact(Sprite sprite) {
        if(sprite != null&& !sprite.equals(this) && getContour().intersects(sprite.getContour())){
            x = oldx;
            y = oldy;
            return true;
        }
        return false;
    }

    public void impact(List<? extends Sprite> sprites){
        for(Sprite s : sprites){
            impact(s);
        }
    }
}
