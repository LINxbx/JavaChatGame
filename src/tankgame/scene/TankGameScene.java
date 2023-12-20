package tankgame.scene;

import javafx.application.Platform;
import tankgame.director.Director;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import tankgame.sound.SoundEffect;
import tankgame.sprite.*;
import tankgame.util.Direction;
import tankgame.util.Group;

import java.util.ArrayList;
import java.util.List;


/*
该类实现了游戏场景的绘制和初始化，并提供了游戏场景中的各种元素，如玩家坦克、敌方坦克、子弹、爆炸效果等的绘制和管理。
同时，它还包括了键盘事件的处理和游戏的暂停/继续功能的实现。该类的核心是Refresh内部类，
它通过继承JavaFX的AnimationTimer类，每次循环调用handle()方法，实现游戏场景的刷新。
 */
public class TankGameScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();//创建2D图形
    private KeyProcess keyProcess = new KeyProcess();
    private Refresh refresh = new Refresh();//刷新实现动画效果
    private boolean running = false;

    private Background background = new Background();

    private Tank self = null;
    public List<Bullet> bullets = new ArrayList<>();
    public List<Tank> tanks = new ArrayList<>();
    public List<Explode> explodes = new ArrayList<>();
    public List<Crate> crates = new ArrayList<>();
    public List<Rock> rocks = new ArrayList<>();
    public List<Tree> trees = new ArrayList<>();

    //绘制


    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
        self.impact(tanks);
        self.impact(crates);
        self.impact(rocks);


        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);
            b.paint(graphicsContext);
            b.impactCrates(crates);
            b.impactTank(tanks);
            b.impactRocks(rocks);
            b.impactTank(self);
        }

        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            tank.paint(graphicsContext);
            tank.impact(crates);
            tank.impact(self);
            tank.impact(rocks);
            tank.impact(tanks);
        }
        for (int i = 0; i < explodes.size(); i++) {
            Explode e = explodes.get(i);
            e.paint(graphicsContext);
        }

        for (int i = 0; i < crates.size(); i++) {
            Crate crate = crates.get(i);
            crate.paint(graphicsContext);
        }

        for (int i = 0; i < rocks.size(); i++) {
            Rock rock = rocks.get(i);
            rock.paint(graphicsContext);
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree tree = trees.get(i);
            tree.paint((graphicsContext));
        }
        graphicsContext.setFont(new Font(20));
        graphicsContext.fillText("敌军的数量：" + tanks.size(), 800, 60);
        graphicsContext.fillText("子弹数量：" + bullets.size(), 800, 90);

        if (!self.isAlive()) {
            SoundEffect.play("file:sound/游戏失败.mp3");
            Director.getInstance().gameOver(false);
        } else if (tanks.isEmpty()) {
            SoundEffect.play("file:sound/游戏胜利.mp3");
            Director.getInstance().gameOver(true);
        }
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyReleased(keyProcess);
        stage.getScene().setOnKeyPressed(keyProcess);
        running = true;
        self = new Tank(400, 500, Group.green, Direction.stop, Direction.up, this);
        initSprite();
        refresh.start();
    }


    private void initSprite() {
        for (int i = 0; i < 6; i++) {
            Tank tank = new Tank(200 + i * 80, 100, Group.red, Direction.stop, Direction.down, this);
            tanks.add(tank);
        }
        for (int i = 0; i < 20; i++) {
            Crate crate1 = new Crate(100 + i * 25, 200);
            Crate crate2 = new Crate(100 + i * 25, 224);
            crates.add(crate1);
            crates.add(crate2);
        }

        for (int i = 0; i < 5; i++) {
            Rock rock = new Rock(500 + i * 62, 300);
            rocks.add(rock);
        }
        for (int i = 0; i < 3; i++) {
            Tree tree = new Tree(600 + i * 68, 400);
            trees.add(tree);
        }
    }

    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        refresh.stop();
        self = null;
        tanks.clear();
        bullets.clear();
        crates.clear();
        explodes.clear();
        rocks.clear();
        trees.clear();

    }

    private class Refresh extends AnimationTimer {      //实现场景刷新
        public void handle(long now) {
            if (running) {
                paint();
            }
        }
    }

    private class KeyProcess implements EventHandler<KeyEvent> {        //调用接口处理键盘事件

        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.SPACE)) {    //按下空格后调用··
                    pauseOrContinue();
                }
                if (self != null) {
                    self.released(keyCode);
                }//移动
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                if (self != null) {
                    self.pressed(keyCode);
                }
            }
        }
    }

    public void pauseOrContinue() {
        if (running) {
            running = false;
        } else {
            running = true;
        }
    }

}
