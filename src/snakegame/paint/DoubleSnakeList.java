package snakegame.paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.DoubleSnakeGameScene;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class DoubleSnakeList {
    public static LinkedList<SnakeNode> Snake1 = new LinkedList<SnakeNode>(); //创建蛇链表
    public static LinkedList<SnakeNode> Snake2 = new LinkedList<SnakeNode>();
    public static Image headImage;

    public static class SnakeNode {
        private Point2D point;     //节点坐标
        private Image image;        //节点对应的图片

        public Point2D getPoint() {
            return point;
        }

        public void setPoint(Point2D point) {
            this.point = point;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public SnakeNode(Point2D point, Image image) {  //构造方法
            this.point = point;
            this.image = image;

        }

    }


    public static void SnakePaint1(int x, int y) {
        Point2D head = new Point2D.Double(x, y);  // 创建蛇头节点，这里先假设蛇头的初始位置
        headImage = new Image("snakegame/snakeimage/HeadRight.png");
        SnakeNode headNode = new SnakeNode(head, headImage);        //调用构造方法
        Snake1.addFirst(headNode);// 将蛇头节点添加到链表的头部

    }

    public static void SnakePaint2(int x, int y) {
        Point2D head = new Point2D.Double(x, y);  // 创建蛇头节点，这里先假设蛇头的初始位置
        headImage = new Image("snakegame/snakeimage/HeadRight2.png");
        SnakeNode headNode = new SnakeNode(head, headImage);        //调用构造方法
        // 将蛇头节点添加到链表的头部
        Snake2.addFirst(headNode);
    }

    //增加身体
    public static void addTailNode1(int x, int y) {
        //获取蛇尾节点
        SnakeNode tailNode = Snake1.getLast();
        //获取蛇尾节点的位置
        Point2D tailPoint = tailNode.getPoint();
        //创建新节点的位置，新节点在蛇尾的上一个位置，即和蛇尾位置相同·
        Point2D newPoint = new Point2D.Double(x, y);
        //创建新节点对应的图片
        Image bodyImage = new Image("snakegame/snakeimage/SnakeBody.png");
        //创建新节点
        SnakeNode newNode = new SnakeNode(newPoint, bodyImage);
        //将新节点添加到蛇尾的后面
        Snake1.addLast(newNode);
        //添加新节点视图到 nodeViews
        ImageView newView1 = new ImageView(bodyImage);
        newView1.setX(x);
        newView1.setY(y);
        DoubleSnake1Manager.nodeViews1[DoubleSnake1Manager.m1] = newView1;
        DoubleSnakeGameScene.Gameroot1.getChildren().add(newView1);
    }

    public static void addTailNode2(int x, int y) {
        //获取蛇尾节点
        SnakeNode tailNode = Snake2.getLast();
        //获取蛇尾节点的位置
        Point2D tailPoint = tailNode.getPoint();
        //创建新节点的位置，新节点在蛇尾的上一个位置，即和蛇尾位置相同·
        Point2D newPoint = new Point2D.Double(x, y);
        //创建新节点对应的图片
        Image bodyImage = new Image("snakegame/snakeimage/SnakeBody2.png");
        //创建新节点
        SnakeNode newNode = new SnakeNode(newPoint, bodyImage);
        //将新节点添加到蛇尾的后面
        Snake2.addLast(newNode);
        //添加新节点视图到 nodeViews
        ImageView newView2 = new ImageView(bodyImage);
        newView2.setX(x);
        newView2.setY(y);
        DoubleSnake2Manager.nodeViews2[DoubleSnake2Manager.m2] = newView2;
        DoubleSnakeGameScene.Gameroot2.getChildren().add(newView2);
    }


}
