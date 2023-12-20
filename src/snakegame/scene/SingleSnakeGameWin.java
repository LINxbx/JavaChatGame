package snakegame.scene;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SingleSnakeGameWin {
    public static void load(){
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
        root.setStyle("-fx-background-color: BLACK");
        //添加横条
        Image Snakebb = new Image("snakegame/snakeimage/Snakebb.png");
        ImageView SnakebbView = new ImageView(Snakebb);
        SnakebbView.setX(150);
        SnakebbView.setY(0);
        root.getChildren().add(SnakebbView);
        //设置标签
        Label GameOver = new Label("恭喜你完成所有关卡");
        GameOver.setLayoutX(250);
        GameOver.setLayoutY(100);
        GameOver.setStyle("-fx-font-size:50px; -fx-text-fill:RED");
        root.getChildren().add(GameOver);
        //添加返回游戏
        Button OverGame = new Button("返回游戏");
        OverGame.setStyle("-fx-font-size: 30px;-fx-text-fill: RED;");
        OverGame.setLayoutX(390);
        OverGame.setLayoutY(300);
        root.getChildren().add(OverGame);
        OverGame.setOnAction(event -> {
            stage.close();
            SnakeGameStart.load();
        });
    }
}
