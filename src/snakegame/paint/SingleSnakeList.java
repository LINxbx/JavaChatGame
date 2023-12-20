package snakegame.paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.SingleSnakeGameScene;

import java.awt.geom.Point2D;
import java.util.LinkedList;

//难点/突破点
public class SingleSnakeList {
    public static LinkedList<SnakeNode> Snake = new LinkedList<SnakeNode>(); //创建蛇链表
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

    public static void SnakePaint(int x, int y) {
        Point2D head = new Point2D.Double(x, y);  // 创建蛇头节点，这里先假设蛇头的初始位置
        headImage = new Image("snakegame/snakeimage/HeadRight.png");
        SnakeNode headNode = new SnakeNode(head, headImage);        //调用构造方法
        Snake.addFirst(headNode);// 将蛇头节点添加到链表的头部
    }

    //增加身体
    public static void addTailNode(int x, int y) {
        //获取蛇尾节点
        SnakeNode tailNode = Snake.getLast();
        //获取蛇尾节点的位置
        Point2D tailPoint = tailNode.getPoint();
        //创建新节点的位置，新节点在蛇尾的上一个位置，即和蛇尾位置相同·
        Point2D newPoint = new Point2D.Double(x, y);
        //创建新节点对应的图片
        Image bodyImage = new Image("snakegame/snakeimage/SnakeBody.png");
        //创建新节点
        SnakeNode newNode = new SnakeNode(newPoint, bodyImage);
        //将新节点添加到蛇尾的后面
        Snake.addLast(newNode);
        //添加新节点视图到 nodeViews
        ImageView newView = new ImageView(bodyImage);
        newView.setX(x);
        newView.setY(y);
        SingleSnakeManager.nodeViews[SingleSnakeManager.m] = newView;
        SingleSnakeGameScene.Gameroot.getChildren().add(newView);
    }


}
