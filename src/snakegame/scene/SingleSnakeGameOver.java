package snakegame.scene;

import snakegame.paint.SingleSnakeManager;
import snakegame.paint.SingleSnakeList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SingleSnakeGameOver {
    public static void load(int a) {
        Stage stage = new Stage();
        AnchorPane rootOver = new AnchorPane();
        Scene scene = new Scene(rootOver);
        stage.setTitle("贪吃蛇大作战");
        stage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //设置背景
        rootOver.setStyle("-fx-background-color: BLACK");
        //设置标签
        Label GameOver = new Label("游戏失败");
        GameOver.setLayoutX(350);
        GameOver.setLayoutY(100);
        GameOver.setStyle("-fx-font-size:60px; -fx-text-fill:RED");
        rootOver.getChildren().add(GameOver);
        //添加失败原因1
        if (a == 1) {
            Label StuckSnake = new Label("你碰到了墙壁！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
        }
        //添加失败原因2
        if (a == 2) {
            Label StuckSnake = new Label("你碰到了自己！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
        }
        //添加结束游戏按钮
        Button OverGame = new Button("结束游戏");
        OverGame.setStyle("-fx-font-size: 30px;-fx-text-fill: RED;");
        OverGame.setLayoutX(600);
        OverGame.setLayoutY(350);
        rootOver.getChildren().add(OverGame);
        OverGame.setOnAction(event -> {
            stage.close();
        });
        //添加重新开始按钮
        Button ReturnButton = new Button("返回重试");
        ReturnButton.setStyle("-fx-text-fill: RED; -fx-font-size: 30px");
        ReturnButton.setLayoutX(200);
        ReturnButton.setLayoutY(350);
        rootOver.getChildren().add(ReturnButton);
        //添加重新开始按钮事件
        ReturnButton.setOnAction(event -> {
            SingleSnakeManager.timer.cancel();
            SingleSnakeManager.Direction = "right";
            stage.close();
            SingleSnakeManager.m = 0;
            SingleSnakeList.Snake.clear();
            SingleSnakeGameScene.game();
        });
        //添加下一关按钮
        Button NextButton = new Button("下一关");
        NextButton.setStyle("-fx-font-size: 30px;-fx-text-fill: RED");
        NextButton.setLayoutX(415);
        NextButton.setLayoutY(350);
        rootOver.getChildren().add(NextButton);
        //添加下一个按钮事件
        NextButton.setOnAction(event -> {
            SingleSnakeManager.timer.cancel();
            if (SingleSnakeManager.TimeGo >= 100) {
                SingleSnakeManager.TimeGo -= 40;
            } else {
                SingleSnakeManager.TimeGo -= 20;
            }
            if (SingleSnakeManager.TimeGo > 0) {
                SingleSnakeManager.Direction = "right";
                stage.close();
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            } else {
                stage.close();
                SingleSnakeGameWin.load();
            }
        });
        //添加返回关卡选择按钮
        Button ReturnSlect = new Button("返回关卡选择");
        ReturnSlect.setStyle("-fx-font-size: 30px;-fx-text-fill: RED");
        ReturnSlect.setLayoutX(380);
        ReturnSlect.setLayoutY(450);
        rootOver.getChildren().add(ReturnSlect);
        ReturnSlect.setOnAction(event -> {
            stage.close();
            SingleSnakeGameSelect.load();
        });
    }
}
