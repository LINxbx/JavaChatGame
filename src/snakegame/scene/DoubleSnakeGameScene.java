package snakegame.scene;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snakegame.paint.DoubleSnake1Manager;
import snakegame.paint.DoubleSnake2Manager;

public class DoubleSnakeGameScene {
    public static AnchorPane Gameroot1;
    public static AnchorPane Gameroot2;
    public static Stage GameStage1;
    public static Stage GameStage2;

    public static void game1() {
        GameStage1 = new Stage();
        Gameroot1 = new AnchorPane();
        Scene scene = new Scene(Gameroot1, 1000, 600);
        GameStage1.setTitle("玩家一");
        GameStage1.setResizable(false);
        GameStage1.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        GameStage1.setScene(scene);
        GameStage1.show();
        //添加背景
        Gameroot1.setStyle("-fx-background-color:BLACK");
        //图形创建
        DoubleSnake1Manager.SnakeBuild();

    }

    public static void game2() {
        GameStage2 = new Stage();
        Gameroot2 = new AnchorPane();
        Scene scene = new Scene(Gameroot2, 1000, 600);
        GameStage2.setTitle("玩家二");
        GameStage2.setResizable(false);
        GameStage2.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
        GameStage2.setScene(scene);
        GameStage2.show();
        //添加背景
        Gameroot2.setStyle("-fx-background-color:BLACK");
        //图形创建
        DoubleSnake2Manager.SnakeBuild();

    }
}
