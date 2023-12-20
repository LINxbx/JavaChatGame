package fishgame.scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snakegame.scene.SingleSnakeGameSelect;
import snakegame.sound.SoundEffect;

public class StartScene {
    public static void load(){
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        stage.setTitle("大鸟吃小鸟");
        stage.getIcons().add(new Image("fishgame/images/img.png"));
        stage.setWidth(960);
        stage.setHeight(600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        //添加背景
        root.setStyle("-fx-background-image: url('fishgame/images/background.jpg')");
        //老鹰
        Image image = new Image("fishgame/images/enemyFish/boss.png");
        ImageView bird = new ImageView(image);
        bird.setLayoutX(0);
        bird.setLayoutY(0);
        bird.setFitWidth(200);
        bird.setFitHeight(200);
        root.getChildren().add(bird);
        //仙鹤
        Image image2 = new Image("fishgame/images/enemyFish/fish3_l.png");
        ImageView bird2 = new ImageView(image2);
        bird2.setLayoutX(760);
        bird2.setLayoutY(0);
        bird2.setFitWidth(200);
        bird2.setFitHeight(200);
        root.getChildren().add(bird2);
        //大鸟吃小鸟
        Label birdLabel = new Label("大鸟吃小鸟");
        birdLabel.setLayoutX(320);
        birdLabel.setLayoutY(100);
        birdLabel.setStyle("-fx-font-size:60px; -fx-text-fill:WHITE");
        root.getChildren().add(birdLabel);
        //添加按钮
        Button StartButton = new Button("开始游戏");
        StartButton.setStyle("-fx-font-size: 30px;-fx-text-fill: Red");
        StartButton.setLayoutX(400);
        StartButton.setLayoutY(350);
        root.getChildren().add(StartButton);
        //设置按钮点击事件
        StartButton.setOnAction(event -> {
            stage.close();
            fishgame.GameWin.reGame();
            fishgame.GameWin.state = 1;
            fishgame.GameWin.over = true;
            fishgame.GameWin.load();
        });

    }
}
