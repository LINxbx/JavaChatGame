package snakegame.scene;

import snakegame.paint.SingleSnakeManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SingleSnakeGameScene {
    public static AnchorPane Gameroot;
    public static Stage GameStage;

    public static void game() {
        GameStage = new Stage();
        Gameroot = new AnchorPane();
        Scene scene = new Scene(Gameroot, 1000, 600);
        GameStage.setTitle("贪吃蛇大作战");
        GameStage.setResizable(false);
        GameStage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        GameStage.setScene(scene);
        GameStage.show();
        //添加背景
        Gameroot.setStyle("-fx-background-color:BLACK");
        //图形创建
        SingleSnakeManager.SnakeBuild();

    }
}
