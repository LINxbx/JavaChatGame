package snakegame.scene;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snakegame.paint.DoubleSnake1Manager;

public class DoubleSnakeGameWin {
    public static void load() {
        Stage GameStage1 = DoubleSnakeGameScene.GameStage1;
        Stage GameStage2 = DoubleSnakeGameScene.GameStage2;
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        stage.setTitle("贪吃蛇大作战");
        stage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.setResizable(false);
//        stage.show();
        root.setStyle("-fx-background-color: BLACK");
        //设置标签
        Label GameWin = new Label("游戏胜利");
        GameWin.setLayoutX(400);
        GameWin.setLayoutY(100);
        GameWin.setStyle("-fx-font-size:50px; -fx-text-fill:RED");
        root.getChildren().add(GameWin);
        //添加胜利原因1
        if (DoubleSnake1Manager.a == 1) {
            Label StuckSnake = new Label("玩家一碰到了墙壁！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            root.getChildren().add(StuckSnake);
            GameStage2.close();
            stage.show();


        }

        //添加失败原因2
        if (DoubleSnake1Manager.a == 2) {
            Label StuckSnake = new Label("玩家一碰到了自己！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            root.getChildren().add(StuckSnake);

            GameStage2.close();
            stage.show();


        }

        //添加失败原因3
        if (DoubleSnake1Manager.a == 3) {
            Label StuckSnake = new Label("玩家一碰到了你！");
            StuckSnake.setLayoutX(380);
            StuckSnake.setLayoutY(200);
            StuckSnake.setStyle("-fx-font-size: 30px; -fx-text-fill: RED");
            root.getChildren().add(StuckSnake);
            GameStage2.close();
            stage.show();


        }


    }
}
