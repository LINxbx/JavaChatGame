package puzzlegame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    //JFrame界面 窗口
    //子类也表示界面窗口
    //规定:GameJFrame这个界面表示的就是游戏的主界面
    //以后和游戏相关的所有逻辑都写在这个类中
    //创建二维数组
    int date[][]=new int[4][4];
    int x=0;
    int y=0;
    int[][] win={
            {1,5,9,13},
            {2,6,10,14},
            {3,7,11,15},
            {4,8,12,0}
    };
    //创建选项下面的条目对象
    JMenuItem replayItem=new JMenuItem("重新游戏");
    JMenuItem reloginItem=new JMenuItem("重新登录");

    JMenuItem accountItem=new JMenuItem("公众号");
    int step=0;
    String path="src/puzzlegame/image/animal1";
    public GameJFrame(){
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据(打乱)
        initDate();

        //初始化照片
        initImage();
        //让界面显示出来
        this.setVisible(true);

    }

    //打乱数组
    private void initDate() {
        int temp[]={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r=new Random();
        for (int i = 0; i < temp.length; i++) {
            int index=r.nextInt(temp.length);
            int t=temp[i];
            temp[i]=temp[index];
            temp[index]=t;

        }
        //创建二维数组并添加数据
        for (int i = 0; i < temp.length; i++) {
            if(temp[i]==0){
                x=i/4;
                y=i%4;
                date[x][y]=0;

            }else{
                date[i/4][i%4]=temp[i];
            }

        }



    }


    //初始化照片
    //添加照片的时候,就需要按照二维数组中管理的数据添加图片
    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        if(victory()){
            JLabel winJLable=new JLabel(new ImageIcon("src/puzzlegame/image/win.png"));
            winJLable.setBounds(203,283,197,73);
            this.getContentPane().add(winJLable);

        }
        JLabel stepCount=new JLabel("步数"+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);




        //外循环----把内循环重复执行4次
        for (int j= 0; j < 4; j++) {
            //内循环----表示一行添加4个图片
            for (int i = 0; i <=3; i++) {
                int num=date[i][j];

                //创建一个图片imageicom对象
                //创建一个jlable的对象(管理容器)
//                JLabel jLabel=new JLabel(new ImageIcon(path+num+".jpg"));
                JLabel jLabel=new JLabel(new ImageIcon(path+"/" + num+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*i+70,105*j+150,105,105);
                //给图片添加边框
                //1:让图片凹下去
                //0:让图片凸起来
                jLabel.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel);

            }

        }
        //添加背景图片
        //细节:先加载的图片在上方,后加载的图片在下方
        ImageIcon bg=new ImageIcon(("src/puzzlegame/image/background.png"));
        JLabel background=new JLabel(bg);
        background.setBounds(23,50,508,560);
        this.getContentPane().add(background);



        //刷新一下界面
        this.getContentPane().repaint();



    }

    private void initJMenuBar() {
        //初始化菜单
        //创建整个菜单对象
        JMenuBar jMenuBar=new JMenuBar();
        //创建菜单上面的两个选项的对象(功能 关于我们)
        JMenu fuctionJMemu=new JMenu("功能");
        JMenu aboutJMemu=new JMenu("关于我们");




        //将每一个选项下面的条目添加到选项当中
        fuctionJMemu.add(replayItem);
        fuctionJMemu.add(reloginItem);
        aboutJMemu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        accountItem.addActionListener(this);



        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(fuctionJMemu);
        jMenuBar.add(aboutJMemu);


        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);

    }

    public void initJFrame() {
        //设置界面的宽高
        this.setSize(580,750);
        //设置界面的标题
        this.setTitle("拼图游戏单机版");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(2);
        //取消默认的居中放置,只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);
        //给整个键盘添加监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
//按下不松时会调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        if(code==65){
            //把界面里的所有图片删除
            this.getContentPane().removeAll();
            //加载第一张完整的图片
            JLabel all=new JLabel(new ImageIcon(path+"/all.jpg"));
            all.setBounds(70,105,420,420);
            this.getContentPane().add(all);
            //添加背景图片
            //细节:先加载的图片在上方,后加载的图片在下方
            ImageIcon bg=new ImageIcon("src/puzzlegame/image/background.png");
            JLabel background=new JLabel(bg);
            background.setBounds(23,50,508,560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //对上,下,左,右进行判断
        //左:37,上:38,右:39,下:40
        int code=e.getKeyCode();
        if(code==37){
            if(x==3){
                return;
            }
            date[x][y]=date[x+1][y];
            date[x+1][y]=0;
            x++;
            step++;
            initImage();

        }else if(code==38){
            if(y==3){
                return;
            }
            //向上移动
            //把空白方块下面的数字向上移动
            //x,y表示空白方块
            //x+1,y表示空白方块下面的方块
            //把空白方块下面的数字赋值给空白方块
            date[x][y]=date[x][y+1];
            date[x][y+1]=0;
            y++;
            step++;
            initImage();


        }else if(code==39){
            if(x==0){
                return;
            }
            date[x][y]=date[x-1][y];
            date[x-1][y]=0;
            x--;
            step++;
            initImage();

        }else if(code==40){
            if(y==0){
                return;
            }
            date[x][y]=date[x][y-1];
            date[x][y-1]=0;
            y--;
            step++;
            initImage();

        }else if(code==65){
            initImage();
        }else if(code==87){
            date=new int[][]{
                    {1,5,9,13},
                    {2,6,10,14},
                    {3,7,11,15},
                    {4,8,12,0}
            };
            initImage();
        }

    }
    //判断date数组中的数据是否和win数组中相同
    //如果全部相同,返回true,否则返回false
    public boolean victory(){
        for(int i=0;i<date.length;i++){
            for (int j = 0; j < date[i].length; j++) {
                if(date[i][j]!=win[i][j]){
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==replayItem){
            //计步器清零
            step=0;
            //再次打乱
            initDate();
            //重新加载
            initImage();

        }else if(obj==accountItem){
        }


    }
}
