package snakegame.paint;

import snakegame.sound.SoundEffect;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.SingleSnakeGameOver;
import snakegame.scene.SingleSnakeGameScene;

import java.awt.geom.Point2D;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class SingleSnakeManager {
    public static int[] x = new int[100];
    public static int[] y = new int[100];
    public static int m = 0;
    public static ImageView[] nodeViews = new ImageView[100];
    public static Timer timer = new Timer();
    public static int TimeGo;
    public static String Direction = "right";

    public static void SnakeBuild() {
        SoundEffect.Background();
        //蛇的创建
        x[0] = 400;
        y[0] = 400;

        //创建蛇头
        SingleSnakeList.SnakePaint(x[0], y[0]);
        //蛇的移动
        // 注册KeyEvent的监听器
        SingleSnakeGameScene.Gameroot.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (Objects.equals(Direction, "down") && m > 0) {
                        break;
                    } else {
                        Direction = "up";
                        nodeViews[0].setImage(new Image("snakegame/snakeimage/HeadUp.png"));
                        break;
                    }
                case DOWN:
                    if (Objects.equals(Direction, "up") && m > 0) {
                        break;
                    } else {
                        Direction = "down";
                        nodeViews[0].setImage(new Image("snakegame/snakeimage/HeadDown.png"));
                        break;
                    }
                case LEFT:
                    if (Objects.equals(Direction, "right") && m > 0) {
                        break;
                    } else {
                        Direction = "left";
                        nodeViews[0].setImage(new Image("snakegame/snakeimage/HeadLeft.png"));
                        break;
                    }
                case RIGHT:
                    if (Objects.equals(Direction, "left") && m > 0) {
                        break;
                    } else {
                        Direction = "right";
                        nodeViews[0].setImage(new Image("snakegame/snakeimage/HeadRight.png"));
                        break;
                    }
            }
            SnakeDie();
        });

        for (int i = 0; i < SingleSnakeList.Snake.size(); i++) {
            nodeViews[i] = new ImageView(SingleSnakeList.Snake.get(i).getImage());
            Point2D nodePoint = SingleSnakeList.Snake.get(i).getPoint();
            nodeViews[i].setX(nodePoint.getX());
            nodeViews[i].setY(nodePoint.getY());
            SingleSnakeGameScene.Gameroot.getChildren().add(nodeViews[i]);
        }
        TimerAhead();
        //食物
        SingleSnakeFood.snakeFood();

    }


    // 计时器
    public static void TimerAhead() {
        timer = new Timer();//创建
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 蛇自主移动
                switch (Direction) {    //通过方向判断向哪个方向移动
                    case "up":
                        moveSnake(0, -25);
                        EatFood(0, 25);
                        break;
                    case "down":
                        moveSnake(0, 25);
                        EatFood(0, -25);
                        break;
                    case "left":
                        moveSnake(-25, 0);
                        EatFood(25, 0);
                        break;
                    case "right":
                        moveSnake(25, 0);
                        EatFood(-25, 0);
                        break;
                }
                SnakeDie();
            }
        }, 0, TimeGo); // 0代表延时0ms后开始执行，100代表每隔100ms执行一次
    }


    public static void moveSnake(int dx, int dy) {
        int[] a = new int[100];
        int[] b = new int[100];
        for (int i = 0; i < SingleSnakeList.Snake.size(); i++) {  //用来替换xy数组保证xy数组的不变
            a[i] = x[i];
            b[i] = y[i];
        }
        // 移动蛇
        for (int i = 1; i < SingleSnakeList.Snake.size(); i++) {      //一节一节向前移
            x[i] = a[i - 1];
            y[i] = b[i - 1];
        }
        //改变位置
        x[0] += dx;
        y[0] += dy;
        // 更新蛇的位置
        for (int i = 0; i < SingleSnakeList.Snake.size(); i++) {
            nodeViews[i].setX(x[i]);
            nodeViews[i].setY(y[i]);
        }
    }


    //判断蛇是否吃到食物
    public static void EatFood(int Ex, int Ey) {
        Platform.runLater(() -> {
            if (x[0] == SingleSnakeFood.Fx && y[0] == SingleSnakeFood.Fy) {
                SoundEffect.play("file:sound/SnakeEat.mp3");
                // 让食物消失
                SingleSnakeGameScene.Gameroot.getChildren().remove(SingleSnakeFood.foodImageView);
                //蛇尾巴加一
                m++;
                x[m] = x[m - 1] + Ex;
                y[m] = y[m - 1] + Ey;
                SingleSnakeList.addTailNode(x[m], y[m]);
                //再生成
                SingleSnakeFood.snakeFood();
            }
        });
    }


    //判断死亡
    public static void SnakeDie() {
        Platform.runLater(() -> {
            if (x[0] == -25 || x[0] == 1000 || y[0] == -25 || y[0] == 600) {    //碰到边界
                SoundEffect.background.stop();      //关掉背景音乐
                SoundEffect.play("file:sound/SnakeDie.mp3");
                timer.cancel();     //关掉计时器
                SingleSnakeGameScene.GameStage.close();
                //添加失败原因1
                SingleSnakeGameOver.load(1);
            } else {
                for (int i = 1; i < SingleSnakeList.Snake.size(); i++) {      //碰到自己
                    if (x[0] == x[i] && y[0] == y[i]) {
                        SoundEffect.background.stop();      //关掉背景音乐
                        SoundEffect.play("file:sound/SnakeDie.mp3");
                        timer.cancel();     //关掉计时器
                        SingleSnakeGameScene.GameStage.close();
                        //添加失败原因2
                        SingleSnakeGameOver.load(2);
                    }
                }
            }
        });
    }
}
