package fishgame;

import fishgame.scene.OverScene;
import fishgame.scene.WinScene;
import javafx.application.Platform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWin extends JFrame {

    /** 游戏状态 0未开始,1游戏中,2通关失败,3通关成功,4暂停,5重新开始*/

    //定义游戏默认状态
    public static int state = 0;

    Image offScreenImage;

    //宽高
    public static int width = 1080;
    public static int height = 958;

    public static double random;
    //计数器
    public static int time = 0;

    //背景
    Bg bg = new Bg();

    //敌方鱼类
    Enamy enamy;
    //是否生成boss
    public static boolean isboss = false;

    //boss类
    public static Enamy boss;
    public static Boolean over = true;


    //我方鱼类
    MyFish myFish = new MyFish();


    public void launch(){
        this.setVisible(true);
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("大鸟吃小鸟");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                over = false;
                GameUtils.EnamyList.clear();
                time = 0;
                myFish.level = 1;
                GameUtils.count = 0;
                myFish.x = 700;
                myFish.y = 500;
                myFish.width = 70;
                myFish.height = 60;
                boss =null;
                isboss = false;
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton()==1&&state==0){
                    state=1;
                    repaint();
                }
                if (e.getButton()==1&&(state==2||state==3)){
                    reGame();
                    state = 1;
                }
            }
        });

        //键盘移动
        this.addKeyListener(new KeyAdapter() {
            @Override//按压
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                //WASD
                if (e.getKeyCode()==87){
                    GameUtils.UP = true;
                }
                if (e.getKeyCode()==83){
                    GameUtils.DOWN = true;
                }
                if (e.getKeyCode()==65){
                    GameUtils.LEFT = true;
                }
                if (e.getKeyCode()==68){
                    GameUtils.RIGHT = true;
                }
                if (e.getKeyCode()==32){
                    switch (state){
                        case 1:
                            state = 4;
                            GameUtils.drawWord(getGraphics(),"游戏暂停!!!",Color.red,50,600,400);
                            break;
                        case 4:
                            state =1;
                            break;
                    }
                }
            }

            @Override//抬起
            public void keyReleased(KeyEvent e){
                super.keyReleased(e);
                if (e.getKeyCode()==87){
                    GameUtils.UP = false;
                }
                if (e.getKeyCode()==83){
                    GameUtils.DOWN = false;
                }
                if (e.getKeyCode()==65){
                    GameUtils.LEFT = false;
                }
                if (e.getKeyCode()==68){
                    GameUtils.RIGHT = false;
                }
            }
        });


        while (over){
            repaint();
            time++;
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public void closeWindow() {
        this.dispose();  // 关闭窗口
    }

    @Override
    public void paint(Graphics g) {
        //懒加载模式初始化对象
        offScreenImage = createImage(width,height);
        Graphics gImage = offScreenImage.getGraphics();
        bg.paintSelf(gImage , myFish.level);

        switch (state){
            case 0:

                break;
            case 1:
                logic();
                myFish.paintSelf(gImage);
                for (Enamy enamy:GameUtils.EnamyList) {
                    enamy.paintSelf(gImage);
                }
                if (isboss){
                    boss.x = boss.x + boss.dir * boss.speed;
                    boss.paintSelf(gImage);
                    if (boss.x<0){
                        gImage.setColor(Color.red);
                        gImage.fillRect(boss.x,boss.y,2400,boss.height/30);
                    }
                }

                break;
            case 2:
                GameUtils.UP =false;
                GameUtils.DOWN =false;
                GameUtils.LEFT =false;
                GameUtils.RIGHT =false;
                Platform.runLater(OverScene::load);
                over = false;
                this.dispose();

                for (Enamy enamy:GameUtils.EnamyList) {
                    enamy.paintSelf(gImage);
                }
                if (isboss){
                    boss.paintSelf(gImage);
                }
                break;
            case 3:
                GameUtils.UP =false;
                GameUtils.DOWN =false;
                GameUtils.LEFT =false;
                GameUtils.RIGHT =false;
                Platform.runLater(WinScene::load);
                over = false;
                this.dispose();
                myFish.paintSelf(gImage);
                break;
            case 4:
                return;

            default:
        }

        g.drawImage(offScreenImage,0,0,null);

    }



    void logic(){
        //关卡难度
        if (GameUtils.count<5){
            GameUtils.level = 0;
            myFish.level = 1;
        }else if (GameUtils.count<=15){
            GameUtils.level = 1;
        }else if (GameUtils.count<=50){
            GameUtils.level = 2;
            myFish.level = 2;
        }else if (GameUtils.count<=150){
            enamy = new Enamy_3_L();
            enamy.width = 150;
            enamy.height = 75;
            enamy.speed = 50;
            enamy.count = 1;
            enamy.type = 1;

            GameUtils.EnamyList.add(enamy);
            GameUtils.level = 3;
            myFish.level = 3;
        }else if (GameUtils.count<=200){
            GameUtils.level = 4;
            myFish.level = 3;
        }else if (GameUtils.count>200){
            state = 3;
        }


        random =Math.random();
        switch (GameUtils.level) {
            case 4:
                if (time%60==0){
                    if (random>0){
                        boss = new Enamy_Boss();
                        isboss =true;
                    }
                }
            case 3:
            case 2:
                if (time%30==0){
                    if (random>0.5){
                        enamy = new Enamy_3_L();
                    }else {
                        enamy = new Enamy_3_R();
                    }
                    GameUtils.EnamyList.add(enamy);
                }
            case 1:
                if (time%20==0){
                    if (random>0.5){
                        enamy = new Enamy_2_L();
                    }else {
                        enamy = new Enamy_2_R();
                    }
                    GameUtils.EnamyList.add(enamy);
                }
            case 0:
            if (time % 10 == 0) {
                if (random > 0.5) {
                    enamy = new Enamy_1_L();
                } else {
                    enamy = new Enamy_1_R();
                }
                GameUtils.EnamyList.add(enamy);
            }
            break;
            default:
        }
        for (Enamy enamy:GameUtils.EnamyList) {
            enamy.x = enamy.x + enamy.dir * enamy.speed;
            if (isboss){
                if (boss.getRec().intersects(enamy.getRec())){
                    enamy.x = -200;
                    enamy.y = -200;
                }
                if (boss.getRec().intersects(myFish.getRec())){
                    state = 2;
                }
            }

                //我方鱼与敌方鱼碰撞检测
                if (myFish.getRec().intersects(enamy.getRec())) {
                    if (myFish.level >= enamy.type) {

                        enamy.x = -200;
                        enamy.y = -200;
                        GameUtils.count = GameUtils.count + enamy.count;
                    } else {
                        state = 2;

                    }
                }

        }
    }
    //重新开始
    public static void reGame(){
        GameUtils.EnamyList.clear();
        time = 0;
        MyFish.level = 1;
        GameUtils.count = 0;
        MyFish.x = 700;
        MyFish.y = 500;
        MyFish.width = 100;
        MyFish.height = 120;
        boss =null;
        isboss = false;
    }

    public static void load() {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
