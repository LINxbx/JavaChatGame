package fishgame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    //方向
    static boolean UP =false;
    static boolean DOWN =false;
    static boolean LEFT =false;
    static boolean RIGHT =false;

    //分数
    public static int count = 60;

    //关卡等级
    public static int level = 0;

    //敌方鱼类集合
    public static List<Enamy> EnamyList = new ArrayList<>();


    //背景图
    public static Image bgimg = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/sea.jpg");

    //敌方鱼类
    public static Image enamy1_img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish1_r.gif");
    public static Image enamyr_img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish1_l.gif");
    public static Image enamyl_2img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish2_r.png");
    public static Image enamyr_2img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish2_l.png");
    public static Image enamyl_3img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish3_r.png");
    public static Image enamyr_3img = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/fish3_l.png");
    public static Image bossimg = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/enemyFish/boss.png");
    //我方鱼类
    public static Image MyFishimg_L = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/myFish/myfish_left.png");
    public static Image MyFishimg_R = Toolkit.getDefaultToolkit().createImage("src/fishgame/images/myFish/myfish_right.png");




    //绘制文字的工具类
    public static void drawWord(Graphics g,String str ,Color color,int size,int x, int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str, x, y);
    }

}
