package fishgame;

import java.awt.*;

public class Enamy {
    //定义图片
    Image img;
    //定义物体坐标
    int x;
    int y;
    int width;
    int height;
    //移动速度
    int speed;
    //方向
    int dir = 1;
    //类型
    int type;
    //分值
    int count;
    //绘制自身方法
    public void paintSelf(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }
    //获取自身矩形用于碰撞检测
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}

//敌方鱼左类
class Enamy_1_L extends Enamy{
    Enamy_1_L(){
        this.x = -45;
        this.y = (int)(Math.random()*700+100);
        this.width = 60;
        this.height = 80;
        this.speed = 10;
        this.count = 1;
        this.type = 1;
        this.img = GameUtils.enamy1_img;
    }
}
class Enamy_1_R extends Enamy_1_L{
    Enamy_1_R(){
        this.x = 1400;
        dir = -1;
        this.img =GameUtils.enamyr_img;
    }
}
class Enamy_2_L extends Enamy{
    Enamy_2_L(){
        this.x = -100;
        this.y = (int)(Math.random()*700+100);
        this.width = 110;
        this.height = 110;
        this.speed = 5;
        this.count = 2;
        this.type = 2;
        this.img =GameUtils.enamyl_2img;
    }
}
class Enamy_2_R extends Enamy_2_L{
    Enamy_2_R(){
        this.x = 1400;
        dir = -1;
        this.img = GameUtils.enamyr_2img;
    }
}
class Enamy_3_L extends Enamy{
    Enamy_3_L(){
        this.x =-300;
        this.y = (int)(Math.random()*700+100);
        this.width = 300;
        this.height = 150;
        this.speed = 20;
        this.count = 3;
        this.type = 3;
        this.img =GameUtils.enamyl_3img;
    }
    @Override
    public Rectangle getRec(){
        return  new Rectangle(x+40,y+30,width-80,height-60);
    }
}
class Enamy_3_R extends Enamy_3_L{
    Enamy_3_R(){
        this.x = 1400;
        dir = -1;
        this.img =GameUtils.enamyr_3img;
    }
}
class Enamy_Boss extends Enamy{
    Enamy_Boss(){
        this.x = -1000;
        this.y = (int)(Math.random()*700+100);
        this.width = 200;
        this.height = 200;
        this.speed = 80;
        this.count = 0;
        this.type = 10;
        this.img = GameUtils.bossimg;
    }
}
