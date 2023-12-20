package snakegame.scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoubleSnakeGameSelect {
    public static void load() {
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        stage.setTitle("贪吃蛇大作战");
        stage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //添加背景
        root.setStyle("-fx-background-image: url('snakegame/snakeimage/Background.png')");
        //添加横条
        Image Snakebb = new Image("snakegame/snakeimage/Snakebb.png");
        ImageView SnakebbView = new ImageView(Snakebb);
        SnakebbView.setX(150);
        SnakebbView.setY(0);
        root.getChildren().add(SnakebbView);
        //添加按钮
        Button StartButton = new Button("玩家一");
        StartButton.setStyle("-fx-font-size: 30px;-fx-text-fill: Red");
        StartButton.setLayoutX(270);
        StartButton.setLayoutY(350);
        root.getChildren().add(StartButton);
        //设置按钮点击事件
        StartButton.setOnAction(event -> {
            DoubleSnakeGameScene.game1();
            stage.close();
        });
        //添加按钮
        Button StartButton2 = new Button("玩家二");
        StartButton2.setStyle("-fx-font-size: 30px;-fx-text-fill: Red");
        StartButton2.setLayoutX(500);
        StartButton2.setLayoutY(350);
        root.getChildren().add(StartButton2);
        //设置按钮点击事件
        StartButton2.setOnAction(event -> {
            DoubleSnakeGameScene.game2();
            stage.close();
        });
    }
}
