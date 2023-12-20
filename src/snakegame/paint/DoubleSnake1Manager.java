package snakegame.paint;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.DoubleSnakeGameScene;
import snakegame.scene.DoubleSnakeGameWin2;
import snakegame.scene.DoubleSnakeGameOver;
import snakegame.sound.SoundEffect;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DoubleSnake1Manager {
    public static int[] x1 = new int[100];
    public static int[] y1 = new int[100];
    public static int m1 = 0;

    public static ImageView[] nodeViews1 = new ImageView[100];
    public static Timer timer1 = new Timer();
    public static int TimeGo1 = 100;
    public static String Direction1 = "right";
    public static int a = 0;

    public static void SnakeBuild() {
        SoundEffect.Background();
        //蛇的创建
        x1[0] = 400;
        y1[0] = 400;


        //创建蛇头
        DoubleSnakeList.SnakePaint1(x1[0], y1[0]);
        //蛇的移动
        // 注册KeyEvent的监听器
        DoubleSnakeGameScene.Gameroot1.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (Objects.equals(Direction1, "down") && m1 > 0) {
                        break;
                    } else {
                        moveSnake(0, -25);
                        EatFood(0, 25);
                        Direction1 = "up";
                        nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadUp.png"));
                        break;
                    }
                case DOWN:
                    if (Objects.equals(Direction1, "up") && m1 > 0) {
                        break;
                    } else {
                        moveSnake(0, 25);
                        EatFood(0, -25);
                        Direction1 = "down";
                        nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadDown.png"));
                        break;
                    }
                case LEFT:
                    if (Objects.equals(Direction1, "right") && m1 > 0) {
                        break;
                    } else {
                        moveSnake(-25, 0);
                        EatFood(25, 0);
                        Direction1 = "left";
                        nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadLeft.png"));
                        break;
                    }
                case RIGHT:
                    if (Objects.equals(Direction1, "left") && m1 > 0) {
                        break;
                    } else {
                        moveSnake(25, 0);
                        EatFood(-25, 0);
                        Direction1 = "right";
                        nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadRight.png"));
                        break;
                    }
            }


        });


        for (int i = 0; i < DoubleSnakeList.Snake1.size(); i++) {
            nodeViews1[i] = new ImageView(DoubleSnakeList.Snake1.get(i).getImage());
            Point2D nodePoint = DoubleSnakeList.Snake1.get(i).getPoint();
            nodeViews1[i].setX(nodePoint.getX());
            nodeViews1[i].setY(nodePoint.getY());
            DoubleSnakeGameScene.Gameroot1.getChildren().add(nodeViews1[i]);
        }

        TimerAhead();
        //食物
        DoubleSnakeFood.snakeFood1();

    }


    // 计时器
    public static void TimerAhead() {
        timer1 = new Timer();//创建
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                // 蛇自主移动
                switch (Direction1) {    //通过方向判断向哪个方向移动
                    case "up":
                        moveSnake(0, -25);
                        EatFood(0, 25);
                        Direction1 = "up";
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

                //接收
                try {
                    Receive();
                    System.out.println("蛇1接收成功");
                } catch (IOException e) {
                    System.out.println("蛇1接收失败:" + e.getMessage());
                }
                System.out.println("蛇1还在运行");
                SnakeDie();
                //发送
                try {
                    UDPSender1("127.0.0.1", 6667);
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }


            }
        }, 0, TimeGo1); // 0代表延时0ms后开始执行，100代表每隔100ms执行一次
    }


    public static void moveSnake(int dx, int dy) {
        int[] a = new int[100];
        int[] b = new int[100];
        int snakeLength = DoubleSnakeList.Snake1.size();
        for (int i = 0; i < snakeLength; i++) {  //用来替换xy数组保证xy数组的不变
            a[i] = x1[i];
            b[i] = y1[i];
        }
        // 移动蛇
        for (int i = 1; i < snakeLength; i++) {      //一节一节向前移
            x1[i] = a[i - 1];
            y1[i] = b[i - 1];
        }
        //改变位置
        x1[0] += dx;
        y1[0] += dy;
        // 更新蛇的位置
        if (x1 != null && x1.length > 0 &&
                y1 != null && y1.length > 0 &&
                nodeViews1 != null) {
            for (int i = 0; i < snakeLength; i++) {
                nodeViews1[i].setX(x1[i]);
                nodeViews1[i].setY(y1[i]);
            }
        } else {
            System.out.println("x1, y1 or nodeViews1 is null!");
        }
    }


    //判断蛇是否吃到食物
    public static void EatFood(int Ex, int Ey) {

        Platform.runLater(() -> {
            if (x1[0] == DoubleSnakeFood.Fx && y1[0] == DoubleSnakeFood.Fy) {

                SoundEffect.play("file:sound/SnakeEat.mp3");
                // 让食物消失
                DoubleSnakeGameScene.Gameroot1.getChildren().remove(DoubleSnakeFood.foodImageView);
                //蛇尾巴加一
                m1++;
                x1[m1] = x1[m1 - 1] + Ex;
                y1[m1] = y1[m1 - 1] + Ey;
                DoubleSnakeList.addTailNode1(x1[m1], y1[m1]);
                //再生成
                DoubleSnakeFood.snakeFood1();
            }
        });
    }


    //判断死亡
    public static void SnakeDie() {
        Platform.runLater(() -> {
            String Win = "";
            for (int i = 1; i < DoubleSnakeList.Snake1.size(); i++) {
                if (x1[0] == DoubleSnake2Manager.x2[i] && y1[0] == DoubleSnake2Manager.y2[i]) {
                    Win = "输了";
                }
            }
            if (x1[0] == -25 || x1[0] == 1000 || y1[0] == -25 || y1[0] == 600) {    //碰到边界
                SoundEffect.background.stop();      //关掉背景音乐
                SoundEffect.play("file:sound/SnakeDie.mp3");
                timer1.cancel();     //关掉计时器
                DoubleSnake2Manager.timer2.cancel();
                //添加失败原因1
                a = 1;
                DoubleSnakeGameOver.load();
            } else if (Win == "输了") {       //碰到蛇2
                SoundEffect.background.stop();      //关掉背景音乐
                SoundEffect.play("file:sound/SnakeDie.mp3");
                timer1.cancel();     //关掉计时器
                DoubleSnake2Manager.timer2.cancel();
                //添加失败原因1
                a = 3;
                DoubleSnakeGameOver.load();
            } else {
                for (int i = 1; i < DoubleSnakeList.Snake1.size(); i++) {      //碰到自己
                    if (x1[0] == x1[i] && y1[0] == y1[i]) {
                        SoundEffect.background.stop();      //关掉背景音乐
                        SoundEffect.play("file:sound/SnakeDie.mp3");
                        timer1.cancel();     //关掉计时器
                        DoubleSnake2Manager.timer2.cancel();
                        //添加失败原因2
                        a = 2;
                        DoubleSnakeGameOver.load();
                    }
                }
            }
        });
    }

    //UdpReceive
    static DatagramSocket socket2;


    //创建数据包,用于接收数据
    static byte[] buf2 = new byte[1024]; // 初始化buf,长度为1024
    static DatagramPacket packet2 = new DatagramPacket(buf2, buf2.length);

    public static void Receive() throws IOException {
        socket2 = new DatagramSocket(6668);
        System.out.println("开始解析");
        //调用receive从DatagramSocket接收数据
        socket2.receive(packet2);
        //获取消息sender的信息和消息内容
        String msg = new String(packet2.getData(), 0, packet2.getLength());
        buf2 = msg.getBytes();
        System.out.println("正在解析");
        String[] s = msg.split(":");  //解析消息内容
        DoubleSnakeList.Snake2.clear();   //开始前清除蛇链表
        // 清除场景中之前的蛇1图像
        Platform.runLater(() -> {
            DoubleSnakeGameScene.Gameroot1.getChildren().removeAll(DoubleSnake2Manager.nodeViews2);
        });
        //获取长度
        int snakeLength = 0;
        try {
            snakeLength = Integer.parseInt(s[3]) + 1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        System.out.println(s[1]);
        //获取坐标
        DoubleSnake2Manager.Direction2 = s[2];//方向
        if (s.length > 4 && s.length >= (snakeLength - 1) * 2 + 4) {
            for (int i = 0; i < snakeLength; i++) {
                try {
                    DoubleSnake2Manager.x2[i] = Integer.parseInt(s[i * 2 + 4]);
                    DoubleSnake2Manager.y2[i] = Integer.parseInt(s[i * 2 + 5]);
                    System.out.print(DoubleSnake2Manager.x2[i] + " " + DoubleSnake2Manager.y2[i] + ",");
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        //添加蛇头
        DoubleSnakeList.SnakePaint2(DoubleSnake2Manager.x2[2], DoubleSnake2Manager.y2[2]);
        //添加身体
        for (int i = 1; i < snakeLength; i++) {
            DoubleSnakeList.SnakeNode tailNode = DoubleSnakeList.Snake2.getLast();
            //获取蛇尾节点的位置
            Point2D tailPoint = tailNode.getPoint();
            //创建新节点的位置，新节点在蛇尾的上一个位置，即和蛇尾位置相同·
            Point2D newPoint = new Point2D.Double(DoubleSnake2Manager.x2[i], DoubleSnake2Manager.y2[i]);
            //创建新节点对应的图片
            Image bodyImage = new Image("snakegame/snakeimage/SnakeBody2.png");
            //创建新节点
            DoubleSnakeList.SnakeNode newNode = new DoubleSnakeList.SnakeNode(newPoint, bodyImage);
            //将新节点添加到蛇尾的后面
            DoubleSnakeList.Snake2.addLast(newNode);
            ImageView newView2 = new ImageView(bodyImage);
            newView2.setX(DoubleSnake2Manager.x2[i]);
            newView2.setY(DoubleSnake2Manager.y2[i]);
            DoubleSnake2Manager.nodeViews2[DoubleSnake2Manager.m2] = newView2;
        }
        //循环将蛇显示出来
        int finalSnakeLength = snakeLength;
        Platform.runLater(() -> {
            for (int i = 0; i < finalSnakeLength; i++) {
                try {
                    DoubleSnake2Manager.nodeViews2[i] = new ImageView(DoubleSnakeList.Snake2.get(i).getImage());
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                Point2D nodePoint = DoubleSnakeList.Snake2.get(i).getPoint();
                DoubleSnake2Manager.nodeViews2[i].setX(DoubleSnake2Manager.x2[i]);
                DoubleSnake2Manager.nodeViews2[i].setY(DoubleSnake2Manager.y2[i]);
                DoubleSnakeGameScene.Gameroot1.getChildren().add(DoubleSnake2Manager.nodeViews2[i]);
            }
        });
        //方向改变
        Platform.runLater(() -> {
            switch (s[2]) {    //通过方向判断向哪个方向移动
                case "up":
                    if (DoubleSnake2Manager.nodeViews2[0] != null) {
                        DoubleSnake2Manager.nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadUp2.png"));
                    }
                    break;
                case "down":
                    if (DoubleSnake2Manager.nodeViews2[0] != null) {
                        DoubleSnake2Manager.nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadDown2.png"));
                    }
                    break;
                case "left":
                    if (DoubleSnake2Manager.nodeViews2[0] != null) {
                        DoubleSnake2Manager.nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadLeft2.png"));
                    }
                    break;
                case "right":
                    if (DoubleSnake2Manager.nodeViews2[0] != null) {
                        DoubleSnake2Manager.nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadRight2.png"));
                    }
                    break;
            }
        });
        //判断游戏胜利
        Platform.runLater(() -> {
            System.out.println("b:" + Integer.parseInt(s[0]));
            DoubleSnakeGameWin2.load();
        });

        if (s[1].equals("no")) {
            Platform.runLater(() -> {
                System.out.println("蛇2吃到食物");
                // 让食物消失
                DoubleSnakeGameScene.Gameroot1.getChildren().remove(DoubleSnakeFood.foodImageView);
                //再生成
                DoubleSnakeFood.snakeFood1();
            });
        }

        socket2.close();
    }


    //创建一个应用程序编程接口socket
    static DatagramSocket socket1;


    //创建一个流 用于录入键盘的数据
    static BufferedReader bfr;
    //发送数据目的地的IP
    private String toIP;
    //发送数据目的地的端口
    private int toPort;
    static DatagramPacket packet = null;
    static byte[] buf = new byte[1024]; // 初始化buf,长度为1024
    static String Snake1Information = "";

    //UDPSender
    public static void UDPSender1(String toIP, int toPort) throws SocketException {
        socket1 = new DatagramSocket(6664);
        bfr = new BufferedReader(new InputStreamReader(System.in));//从键盘录入数据到流中

        try {
            System.out.println(getSnakeInfo());
            String msg = getSnakeInfo();    //发送的消息内容
            buf = msg.getBytes();
            packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(toIP), toPort);
        } catch (UnknownHostException e) {
            System.out.println("无法解析接收端IP地址,请检查接收端IP设置是否正确!");
        }


        // 发送数据包
        try {
            socket1.send(packet);
            System.out.println("发送成功");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Snake1Information = "";
        if (socket1 != null) socket1.close();
    }


    //记录蛇1的食物坐标，蛇头方向，m1，各坐标
    private static String getSnakeInfo() {
        for (int i = 0; i < DoubleSnakeList.Snake1.size(); i++) {
            Snake1Information = Snake1Information + x1[i] + ":" + y1[i] + ":";
        }

        return a + ":" + DoubleSnakeFood.Fx + ":" + DoubleSnakeFood.Fy + ":" + Direction1 + ":" + m1 + ":" + Snake1Information;
    }


}
