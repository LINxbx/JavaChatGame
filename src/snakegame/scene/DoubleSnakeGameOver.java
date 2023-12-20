package snakegame.scene;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snakegame.paint.DoubleSnake1Manager;
import snakegame.paint.DoubleSnake2Manager;

public class DoubleSnakeGameOver {
    public static void load() {
        Stage stage = new Stage();
        AnchorPane rootOver = new AnchorPane();
        Scene scene = new Scene(rootOver);
        stage.setTitle("贪吃蛇大作战");
        stage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.setResizable(false);
//        stage.show();
        //设置背景
        rootOver.setStyle("-fx-background-color: BLACK");
        //设置标签
        Label GameOver = new Label("游戏失败");
        GameOver.setLayoutX(350);
        GameOver.setLayoutY(100);
        GameOver.setStyle("-fx-font-size:60px; -fx-text-fill:RED");
        rootOver.getChildren().add(GameOver);
        //添加失败原因1
        if (DoubleSnake1Manager.a == 1) {
            Label StuckSnake = new Label("你碰到了墙壁！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage1.close();
            stage.show();
        }
        if (DoubleSnake2Manager.b == 1) {
            Label StuckSnake = new Label("你碰到了墙壁！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage2.close();
            stage.show();
        }
        //添加失败原因2
        if (DoubleSnake1Manager.a == 2) {
            Label StuckSnake = new Label("你碰到了自己！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage1.close();
            stage.show();
        }
        if (DoubleSnake2Manager.b == 2) {
            Label StuckSnake = new Label("你碰到了自己！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage2.close();
            stage.show();
        }
        //添加失败原因3
        if (DoubleSnake1Manager.a == 3) {
            Label StuckSnake = new Label("你碰到了另一个玩家！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage1.close();
            stage.show();
        }
        if (DoubleSnake2Manager.b == 3) {
            Label StuckSnake = new Label("你碰到了另一个玩家！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            rootOver.getChildren().add(StuckSnake);
            DoubleSnakeGameScene.GameStage2.close();
            stage.show();
        }
    }
}
