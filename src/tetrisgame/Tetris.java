package tetrisgame;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//编写俄罗斯方块主类
public class Tetris extends JPanel {
    public static Boolean gameRunning = true;
    //声明正在下落的方块
    private Tetromino currentOne = Tetromino.randomOne();
    //声明将要下落的方块
    private Tetromino nextOne = Tetromino.randomOne();
    //声明游戏主区域
    private Cell[][] wall = new Cell[18][9];
    //声明单元格的值为48像素
    private static final int CELL_SIZE = 48;

    //声明游戏分数池
    int[] scores_pool = {0,1,2,5,10};
    //声明当前获得游戏的分数
    private int totalScore = 0;
    //当前已消除的行数
    private int totalLine = 0;

    //声明游戏的三种状态，分别是：游戏中、暂停、游戏结束
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    //声明变量存放当前游戏状态的值
    private int game_state;
    //声明一个数组，用来显示游戏状态
    String[] show_state = {"P[pause]","C[continue]","S[replay]"};

    //载入方块图片
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage O;
    public static BufferedImage S;
    public static BufferedImage T;
    public static BufferedImage Z;
    public static BufferedImage backImage;

    static {
        try {
            I = ImageIO.read(new File("src/tetrisgame/images/I.png"));
            J = ImageIO.read(new File("src/tetrisgame/images/J.png"));
            L = ImageIO.read(new File("src/tetrisgame/images/L.png"));
            O = ImageIO.read(new File("src/tetrisgame/images/O.png"));
            S = ImageIO.read(new File("src/tetrisgame/images/S.png"));
            T = ImageIO.read(new File("src/tetrisgame/images/T.png"));
            Z = ImageIO.read(new File("src/tetrisgame/images/Z.png"));
            backImage = ImageIO.read(new File("src/tetrisgame/images/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(backImage, 0, 0, null);
        //平移坐标轴
        g.translate(22, 15);
        //绘制游戏主区域
        paintWall(g);
        //绘制正在下落的四方格
        paintCurrentOne(g);
        //绘制下一个将要下落的四方格
        paintNextOne(g);
        //绘制游戏得分
        paintScore(g);
        //绘制游戏当前状态
        paintState(g);
    }

    public void start(){
        game_state = PLAYING;
        KeyListener l = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                switch (code){
                    case KeyEvent.VK_DOWN:
                        sortDropAction(); //下落一格
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeftAction(); //左移
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightAction(); //右移
                        break;
                    case KeyEvent.VK_UP:
                        rotateRightAction(); //顺时针旋转
                        break;
                    case KeyEvent.VK_SPACE:
                        handDropAction(); //瞬间下落
                        break;
                    case KeyEvent.VK_P:
                        //判断当前游戏的状态
                        if(game_state == PLAYING){
                            game_state = PAUSE;
                        }
                        break;
                    case KeyEvent.VK_C:
                        //判断游戏状态
                        if(game_state == PAUSE){
                            game_state = PLAYING;
                        }
                        break;
                    case KeyEvent.VK_S:
                        //表示游戏重新开始
                        game_state = PLAYING;
                        wall = new Cell[18][9];
                        currentOne = Tetromino.randomOne();
                        nextOne = Tetromino.randomOne();
                        totalScore = 0;
                        totalLine = 0;
                        break;
                }
            }
        };

        //将俄罗斯方块窗口设置为焦点
        this.addKeyListener(l);
        this.requestFocus();

        while(gameRunning){

            //判断，当前游戏状态在游戏中时，每隔0.5秒下落
            if(game_state == PLAYING){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //判断能否下落
                if(canDrop()){
                    currentOne.softDrop();
                }else{
                    //嵌入到墙中
                    landToWall();
                    //判断能否消行
                    destroyLine();
                    //判断游戏是否结束
                    if(isGameOver()){
                        game_state = GAMEOVER;
                    }else{
                        currentOne = nextOne;
                        nextOne = Tetromino.randomOne();
                    }
                }
            }
            repaint();
        }
    }

    //创建顺时针旋转
    public void rotateRightAction(){
        currentOne.rotateRight();
        //判断是否越界或者是否重合
        if(outOfBounds() || coincide()){
            currentOne.rotateLeft();
        }
    }

    //瞬间下落
    public void handDropAction(){
        while(true){
            //判断四方格能否下落
            if(canDrop()){
                currentOne.softDrop();
            }else{
                break;
            }
        }
        //嵌入到墙中
        landToWall();
        //判断能否消行
        destroyLine();
        //判断游戏是否结束
        if(isGameOver()){
            game_state = GAMEOVER;
        }else{
            //游戏没有结束，继续生成新的四方格
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
        }
    }

    //按键一次四方格下落一格
    public void sortDropAction(){
        //判断能否下落
        if(canDrop()){
            //当前四方格下落一格
            currentOne.softDrop();
        }else{
            //将四方格嵌入到墙中
            landToWall();
            //判断能否消行
            destroyLine();
            //判断游戏是否结束
            if(isGameOver()){
                game_state = GAMEOVER;
            }else{
                //当游戏没有结束时，则继续生成新的四方格
                currentOne = nextOne;
                nextOne = Tetromino.randomOne();
            }
        }
    }

    //四方格嵌入到墙中
    private void landToWall() {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            wall[row][col] = cell;
        }
    }

    //判断四方格能否下落
    public boolean canDrop(){
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            //判断是否到达底部
            if(row == wall.length - 1){
                return false;
            }else if(wall[row + 1][col] != null){ //判断是否有方块
                return false;
            }
        }
        return true;
    }

    //创建消行方法
    public void destroyLine(){
        //声明变量，统计当前消除的行数
        int line = 0;
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            //判断当前行是否已满
            if(isFullLine(row)){
                line++;
                for(int i = row;i > 0;i--){
                    System.arraycopy(wall[i - 1],0,wall[i],0,wall[0].length);
                }
                wall[0] = new Cell[9];
            }
        }

        //在分数池中获取分数，累加到总分数中
        totalScore += scores_pool[line];
        //统计消除总行数
        totalLine += line;
    }

    //判断当前行是否已满
    public boolean isFullLine(int row){
        Cell[] cells = wall[row];
        for (Cell cell : cells) {
            if(cell == null){
                return false;
            }
        }
        return true;
    }

    //判断游戏是否结束
    public boolean isGameOver(){
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if(wall[row][col] != null){
                return true;
            }
        }
        return false;
    }

    //绘制游戏当前状态
    private void paintState(Graphics g) {
        if(game_state == PLAYING){
            g.drawString(show_state[0],500,660);
        }else if(game_state == PAUSE){
            g.drawString(show_state[1],500,660);
        }else if(game_state == GAMEOVER){
            g.drawString(show_state[2],500,660);
            g.setColor(Color.red);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,60));
            g.drawString("GAMEOVER!", 30, 400);
        }
    }

    //绘制游戏得分
    private void paintScore(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
        g.drawString("SCORES: " + totalScore, 500, 248);
        g.drawString("LINES: " + totalLine, 500, 430);
    }

    //绘制下一个将要下落的四方格
    private void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 370;
            int y = cell.getRow() * CELL_SIZE + 25;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    //绘制正在下落的四方格
    private void paintCurrentOne(Graphics g) {
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, null);
        }
    }

    //绘制游戏主区域
    private void paintWall(Graphics g) {
        for(int i = 0;i < wall.length;i++){
            for(int j = 0;j < wall[i].length;j++){
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                Cell cell = wall[i][j];
                //判断当前单元格是否有小方块，如果没有则绘制矩形，否则将小方块嵌入到墙中
                if(cell == null){
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                }else{
                    g.drawImage(cell.getImage(), x, y, null);
                }
            }
        }
    }

    //判断游戏是否出界
    public boolean outOfBounds(){
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int col = cell.getCol();
            int row = cell.getRow();
            if(row < 0 || row > wall.length - 1 || col < 0 || col > wall[0].length - 1){
                return true;
            }
        }
        return false;
    }
    //判断方块是否重合
    public boolean coincide(){
        Cell[] cells = currentOne.cells;
        for (Cell cell : cells) {
            int row = cell.getRow();
            int col = cell.getCol();
            if(wall[row][col] != null){
                return true;
            }
        }
        return false;
    }
    //按键一次四方格左移一次
    public void moveLeftAction(){
        currentOne.moveLeft();
        //判断是否越界或者四方格是否重合
        if(outOfBounds() || coincide()){
            currentOne.moveRight();
        }
    }
    //按键一次四方格右移一次
    public void moveRightAction(){
        currentOne.moveRight();
        //判断是否越界
        if(outOfBounds() || coincide()){
            currentOne.moveLeft();
        }
    }
    public static void load(){


        //创建一个窗口对象
        JFrame frame = new JFrame("俄罗斯方块");
        //创建游戏界面，也就是面板
        Tetris panel = new Tetris();
        //将面板嵌入到窗口中
        frame.add(panel);
        //设置可见
        frame.setVisible(true);
        frame.setResizable(false);
        //设置窗口尺寸
        frame.setSize(810, 940);
        //设置窗口居中
        frame.setLocationRelativeTo(null);
        //设置窗口关闭时程序终止
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                gameRunning = false;  // 将循环变量改为false
            }
        });
        //游戏主要逻辑封装在方法中
        panel.start();
    }
}

