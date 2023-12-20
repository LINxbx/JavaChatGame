package fishgame;

import java.awt.*;

public class MyFish {
    //图片
    Image img = GameUtils.MyFishimg_L;
    //坐标
    public static int x = 700;
    public static int y = 500;
    public static int width = 70;
    public static int height = 60;
    //移动速度
    public static int speed = 20;
    //等级
    public static int level = 1;




    void logic(){
        if (GameUtils.UP){
            y = y-speed;
        }
        if (GameUtils.DOWN){
            y = y+speed;
        }
        if (GameUtils.LEFT){
            x =x-speed;
            img = GameUtils.MyFishimg_L;
        }
        if (GameUtils.RIGHT){
            x = x+speed;
            img = GameUtils.MyFishimg_R;
        }
    }

    //绘制自身的方法
    public void paintSelf(Graphics g){
        logic();
        g.drawImage(img,x,y,width+GameUtils.count,height+GameUtils.count,null);
    }
    //获取自身矩形的方法,用于碰撞检测'
    public Rectangle getRec(){
        return new Rectangle(x,y,width+GameUtils.count,height+GameUtils.count);
    }
}
