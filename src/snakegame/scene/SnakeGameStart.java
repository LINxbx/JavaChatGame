package snakegame.scene;

import snakegame.sound.SoundEffect;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SnakeGameStart {

    public static void load() {
        Platform.runLater(() -> {
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
            Button StartButton = new Button("单人游戏");
            StartButton.setStyle("-fx-font-size: 30px;-fx-text-fill: Red");
            StartButton.setLayoutX(270);
            StartButton.setLayoutY(350);
            root.getChildren().add(StartButton);
            //设置按钮点击事件
            StartButton.setOnAction(event -> {
                SoundEffect.play("file:sound/开始游戏.mp3");
                stage.close();
                SingleSnakeGameSelect.load();
            });

            //添加按钮
            Button DoubleButton = new Button("双人游戏");
            DoubleButton.setStyle("-fx-font-size: 30px;-fx-text-fill: Red");
            DoubleButton.setLayoutX(500);
            DoubleButton.setLayoutY(350);
            root.getChildren().add(DoubleButton);
            //设置按钮点击事件
            DoubleButton.setOnAction(event -> {
                SoundEffect.play("file:sound/开始游戏.mp3");
                stage.close();
                DoubleSnakeGameSelect.load();
            });

        });
    }
}
