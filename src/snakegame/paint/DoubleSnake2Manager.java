package snakegame.paint;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.*;
import snakegame.sound.SoundEffect;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DoubleSnake2Manager {
    public static int[] x2 = new int[100];
    public static int[] y2 = new int[100];
    public static int m2 = 0;

    public static ImageView[] nodeViews2 = new ImageView[100];
    public static Timer timer2 = new Timer();
    public static int TimeGo2 = 100;
    public static String Direction2 = "right";
    public static int b = 0;

    public DoubleSnake2Manager() throws SocketException {
    }

    public static void SnakeBuild() {
        SoundEffect.Background();
        //蛇的创建
        x2[0] = 400;
        y2[0] = 500;

        //创建蛇头
        DoubleSnakeList.SnakePaint2(x2[0], y2[0]);
        //蛇的移动
        // 注册KeyEvent的监听器
        DoubleSnakeGameScene.Gameroot2.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    if (Objects.equals(Direction2, "down") && m2 > 0) {
                        break;
                    } else {
                        moveSnake(0, -25);
                        EatFood(0, 25);
                        Direction2 = "up";
                        nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadUp2.png"));
                        break;
                    }
                case S:
                    if (Objects.equals(Direction2, "up") && m2 > 0) {
                        break;
                    } else {
                        moveSnake(0, 25);
                        EatFood(0, -25);
                        Direction2 = "down";
                        nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadDown2.png"));
                        break;
                    }
                case A:
                    if (Objects.equals(Direction2, "right") && m2 > 0) {
                        break;
                    } else {
                        moveSnake(-25, 0);
                        EatFood(25, 0);
                        Direction2 = "left";
                        nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadLeft2.png"));
                        break;
                    }
                case D:
                    if (Objects.equals(Direction2, "left") && m2 > 0) {
                        break;
                    } else {
                        moveSnake(25, 0);
                        EatFood(-25, 0);
                        Direction2 = "right";
                        nodeViews2[0].setImage(new Image("snakegame/snakeimage/HeadRight2.png"));
                        break;
                    }

            }

        });

        for (int i = 0; i < DoubleSnakeList.Snake2.size(); i++) {
            nodeViews2[i] = new ImageView(DoubleSnakeList.Snake2.get(i).getImage());
            Point2D nodePoint = DoubleSnakeList.Snake2.get(i).getPoint();
            nodeViews2[i].setX(nodePoint.getX());
            nodeViews2[i].setY(nodePoint.getY());
            DoubleSnakeGameScene.Gameroot2.getChildren().add(nodeViews2[i]);
        }
        TimerAhead();

    }


    // 计时器
    public static void TimerAhead() {
        timer2 = new Timer();//创建
        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                // 蛇自主移动
                switch (Direction2) {    //通过方向判断向哪个方向移动
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


                //发送
                try {
                    UDPSender1("127.0.0.1", 6668);
                } catch (SocketException e) {
                    throw new RuntimeException(e);
                }
                SnakeDie();
                //接收
                try {
                    Receive();
                    System.out.println("蛇2接收成功");
                } catch (IOException e) {
                    System.out.println("蛇2接收失败:" + e.getMessage());
                }
                System.out.println("蛇2还在运行");

                FoodExit = "yes";


            }
        }, 0, TimeGo2); // 0代表延时0ms后开始执行，100代表每隔100ms执行一次
    }


    public static void moveSnake(int dx, int dy) {
        int[] a = new int[100];
        int[] b = new int[100];
        for (int i = 0; i < DoubleSnakeList.Snake2.size(); i++) {  //用来替换xy数组保证xy数组的不变
            a[i] = x2[i];
            b[i] = y2[i];
        }
        // 移动蛇
        for (int i = 1; i < DoubleSnakeList.Snake2.size(); i++) {      //一节一节向前移
            x2[i] = a[i - 1];
            y2[i] = b[i - 1];
        }
        //改变位置
        x2[0] += dx;
        y2[0] += dy;
        // 更新蛇的位置
        for (int i = 0; i < DoubleSnakeList.Snake2.size(); i++) {
            nodeViews2[i].setX(x2[i]);
            nodeViews2[i].setY(y2[i]);
        }
    }


    //判断蛇是否吃到食物
    public static void EatFood(int Ex, int Ey) {
        Platform.runLater(() -> {
            System.out.println();
            if (x2[0] == Foodx && y2[0] == Foody) {
                FoodExit = "no";
                System.out.println("蛇2吃到食物");
                SoundEffect.play("file:sound/SnakeEat.mp3");
                // 让食物消失
                DoubleSnakeGameScene.Gameroot2.getChildren().remove(DoubleSnakeFood.foodImageView);
                //蛇尾巴加一
                m2++;
                x2[m2] = x2[m2 - 1] + Ex;
                y2[m2] = y2[m2 - 1] + Ey;
                DoubleSnakeList.addTailNode2(x2[m2], y2[m2]);
            }
        });
    }


    //判断死亡
    public static void SnakeDie() {
        Platform.runLater(() -> {
            String Win = "";
            for (int i = 1; i < DoubleSnakeList.Snake2.size(); i++) {
                if (x2[0] == DoubleSnake1Manager.x1[i] && y2[0] == DoubleSnake1Manager.y1[i]) {
                    Win = "输了";
                }
            }
            if (x2[0] == -25 || x2[0] == 1000 || y2[0] == -25 || y2[0] == 600) {    //碰到边界
                SoundEffect.background.stop();      //关掉背景音乐
                SoundEffect.play("file:sound/SnakeDie.mp3");
                timer2.cancel();     //关掉计时器
                DoubleSnake1Manager.timer1.cancel();
                //添加失败原因1
                b = 1;
                DoubleSnakeGameOver.load();
            } else if (Win == "输了") {   //碰到蛇1
                SoundEffect.background.stop();      //关掉背景音乐
                SoundEffect.play("file:sound/SnakeDie.mp3");
                timer2.cancel();     //关掉计时器
                DoubleSnake1Manager.timer1.cancel();
                //添加失败原因1
                b = 3;
                DoubleSnakeGameOver.load();
            } else {
                for (int i = 1; i < DoubleSnakeList.Snake2.size(); i++) {      //碰到自己
                    if (x2[0] == x2[i] && y2[0] == y2[i]) {
                        SoundEffect.background.stop();      //关掉背景音乐
                        SoundEffect.play("file:sound/SnakeDie.mp3");
                        timer2.cancel();     //关掉计时器
                        DoubleSnake1Manager.timer1.cancel();
                        //添加失败原因2
                        b = 2;
                        DoubleSnakeGameOver.load();
                    }
                }
            }
        });
    }

    //UdpReceive
    //创建DatagramSocket,端口为6667
    static DatagramSocket socket2;


    //创建数据包,用于接收数据
    static byte[] buf = new byte[1024]; // 初始化buf,长度为1024
    static DatagramPacket packet = new DatagramPacket(buf, buf.length);

    public static int Foodx;
    public static int Foody;

    public static void Receive() throws IOException {
        socket2 = new DatagramSocket(6667);
        System.out.println("开始解析");
        //调用receive从DatagramSocket接收数据
        socket2.receive(packet);
        //获取消息sender的信息和消息内容
        String msg = new String(packet.getData(), 0, packet.getLength());
        buf = msg.getBytes();
        System.out.println("正在解析");
        String[] s = msg.split(":");  //解析消息内容
        DoubleSnakeList.Snake1.clear();   //开始前清除蛇链表
        // 清除场景中之前的蛇1图像
        Platform.runLater(() -> {
            DoubleSnakeGameScene.Gameroot2.getChildren().remove(DoubleSnakeFood.foodImageView);
            DoubleSnakeGameScene.Gameroot2.getChildren().removeAll(DoubleSnake1Manager.nodeViews1);

        });
        //获取长度
        int snakeLength = 0;
        if (s.length > 4) {
            try {
                snakeLength = Integer.parseInt(s[4]) + 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        //获取食物坐标
        Foodx = Integer.parseInt(s[1]);
        Foody = Integer.parseInt(s[2]);
        //获取坐标
        DoubleSnake1Manager.Direction1 = s[3];//方向
        if (s.length > 5) {
            for (int i = 0; i < snakeLength; i++) {
                try {
                    DoubleSnake1Manager.x1[i] = Integer.parseInt(s[i * 2 + 5]);
                    DoubleSnake1Manager.y1[i] = Integer.parseInt(s[i * 2 + 6]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                System.out.print(DoubleSnake1Manager.x1[i] + " " + DoubleSnake1Manager.y1[i] + " ,");
            }
        }
        //添加蛇头
        DoubleSnakeList.SnakePaint1(DoubleSnake1Manager.x1[3], DoubleSnake1Manager.y1[3]);
        //添加身体
        for (int i = 1; i < snakeLength; i++) {
            DoubleSnakeList.SnakeNode tailNode = DoubleSnakeList.Snake1.getLast();
            //获取蛇尾节点的位置
            Point2D tailPoint = tailNode.getPoint();
            //创建新节点的位置，新节点在蛇尾的上一个位置，即和蛇尾位置相同·
            Point2D newPoint = new Point2D.Double(DoubleSnake1Manager.x1[i], DoubleSnake1Manager.y1[i]);
            //创建新节点对应的图片
            Image bodyImage = new Image("snakegame/snakeimage/SnakeBody.png");
            //创建新节点
            DoubleSnakeList.SnakeNode newNode = new DoubleSnakeList.SnakeNode(newPoint, bodyImage);
            //将新节点添加到蛇尾的后面
            DoubleSnakeList.Snake1.addLast(newNode);
            ImageView newView1 = new ImageView(bodyImage);
            newView1.setX(DoubleSnake1Manager.x1[i]);
            newView1.setY(DoubleSnake1Manager.y1[i]);
            DoubleSnake1Manager.nodeViews1[DoubleSnake1Manager.m1] = newView1;
        }
        //循环将蛇显示出来
        int finalSnakeLength = snakeLength;
        Platform.runLater(() -> {
            for (int i = 0; i < finalSnakeLength; i++) {
                try {
                    DoubleSnake1Manager.nodeViews1[i] = new ImageView(DoubleSnakeList.Snake1.get(i).getImage());
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                Point2D nodePoint = DoubleSnakeList.Snake1.get(i).getPoint();
                DoubleSnake1Manager.nodeViews1[i].setX(DoubleSnake1Manager.x1[i]);
                DoubleSnake1Manager.nodeViews1[i].setY(DoubleSnake1Manager.y1[i]);
                DoubleSnakeGameScene.Gameroot2.getChildren().add(DoubleSnake1Manager.nodeViews1[i]);
            }
        });
        //方向改变
        Platform.runLater(() -> {
            switch (s[3]) {    //通过方向判断向哪个方向移动
                case "up":
                    if (DoubleSnake1Manager.nodeViews1[0] != null) {
                        DoubleSnake1Manager.nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadUp.png"));
                    }
                    break;
                case "down":
                    if (DoubleSnake1Manager.nodeViews1[0] != null) {
                        DoubleSnake1Manager.nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadDown.png"));
                    }
                    break;
                case "left":
                    if (DoubleSnake1Manager.nodeViews1[0] != null) {
                        DoubleSnake1Manager.nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadLeft.png"));
                    }
                    break;
                case "right":
                    if (DoubleSnake1Manager.nodeViews1[0] != null) {
                        DoubleSnake1Manager.nodeViews1[0].setImage(new Image("snakegame/snakeimage/HeadRight.png"));
                    }
                    break;
            }
        });
        //食物呈现
        Platform.runLater(() -> {
            DoubleSnakeFood.foodImageView.setLayoutY(Foody);
            DoubleSnakeFood.foodImageView.setLayoutX(Foodx);
            DoubleSnakeGameScene.Gameroot2.getChildren().add(DoubleSnakeFood.foodImageView);
        });

        //判断游戏胜利
        DoubleSnake1Manager.a = Integer.parseInt(s[0]);
        Platform.runLater(() -> {
            System.out.println("a:" + DoubleSnake1Manager.a);

            DoubleSnakeGameWin.load();

        });
        socket2.close();
    }

    //UdpSender
    //创建一个应用程序编程接口socket
    static DatagramSocket socket1;


    //创建一个流 用于录入键盘的数据
    static BufferedReader bfr2;
    static DatagramPacket packet2 = null;
    static byte[] buf2 = new byte[1024]; // 初始化buf,长度为1024
    static String Snake2Information = "";
    static String FoodExit = "yes";

    //UDPSender
    public static void UDPSender1(String toIP, int toPort) throws SocketException {
        socket1 = new DatagramSocket(6663);
        bfr2 = new BufferedReader(new InputStreamReader(System.in));//从键盘录入数据到流中

        try {
            System.out.println("在蛇2中B:" + b);
            System.out.println(getSnakeInfo());
            String msg = getSnakeInfo();    //发送的消息内容
            buf2 = msg.getBytes();
            packet2 = new DatagramPacket(buf2, buf2.length, InetAddress.getByName(toIP), toPort);
        } catch (UnknownHostException e) {
            System.out.println("无法解析接收端IP地址,请检查接收端IP设置是否正确!");
        }


        // 发送数据包
        try {
            socket1.send(packet2);
            System.out.println("发送成功");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Snake2Information = "";
        if (socket1 != null) socket1.close();
    }


    //记录蛇1的食物坐标，蛇头方向，m1，各坐标
    private static String getSnakeInfo() {
        for (int i = 0; i <= m2; i++) {
            Snake2Information = Snake2Information + x2[i] + ":" + y2[i] + ":";
        }

        return b + ":" + FoodExit + ":" + Direction2 + ":" + m2 + ":" + Snake2Information;
    }

}
