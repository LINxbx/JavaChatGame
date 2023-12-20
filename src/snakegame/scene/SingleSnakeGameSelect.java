package snakegame.scene;

import snakegame.paint.SingleSnakeManager;
import snakegame.paint.SingleSnakeList;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SingleSnakeGameSelect {
    public static void load() {
        Platform.runLater(() -> {
            Stage stage = new Stage();
            AnchorPane root = new AnchorPane();
            Scene scene = new Scene(root);
            stage.setTitle("贪吃蛇大作战");
            stage.getIcons().add(new Image("snakegame/snakeimage/SnakeIcon.png"));
            stage.setWidth(1000);
            stage.setHeight(561);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            root.setStyle("-fx-background-color: BLACK");
            root.setStyle("-fx-background-image: url('snakegame/snakeimage/SnakeSelectBackground.png')");
            //添加选择关卡
            Label Select = new Label("请选择关卡");
            Select.setStyle("-fx-text-fill: RED;-fx-font-size: 50px");
            Select.setLayoutX(330);
            Select.setLayoutY(60);
            root.getChildren().add(Select);

            //添加第一关按钮
            Button FirstButton = new Button("第一关");
            FirstButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            FirstButton.setLayoutX(200);
            FirstButton.setLayoutY(200);
            root.getChildren().add(FirstButton);
            FirstButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 150;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
            //添加第二关按钮
            Button ScendButton = new Button("第二关");
            ScendButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            ScendButton.setLayoutX(430);
            ScendButton.setLayoutY(200);
            root.getChildren().add(ScendButton);
            ScendButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 110;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
            //添加第三关按钮
            Button ThirdButton = new Button("第三关");
            ThirdButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            ThirdButton.setLayoutX(630);
            ThirdButton.setLayoutY(200);
            root.getChildren().add(ThirdButton);
            ThirdButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 70;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
            //添加第四关按钮
            Button FourthButton = new Button("第四关");
            FourthButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            FourthButton.setLayoutX(200);
            FourthButton.setLayoutY(350);
            root.getChildren().add(FourthButton);
            FourthButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 50;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
            //添加第五关按钮
            Button FifthButton = new Button("第五关");
            FifthButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            FifthButton.setLayoutX(430);
            FifthButton.setLayoutY(350);
            root.getChildren().add(FifthButton);
            FifthButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 30;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
            //添加第六关按钮
            Button SixthButton = new Button("第六关");
            SixthButton.setStyle("-fx-background-color: transparent;-fx-font-size: 30px;-fx-text-fill: RED");
            SixthButton.setLayoutX(630);
            SixthButton.setLayoutY(350);
            root.getChildren().add(SixthButton);
            SixthButton.setOnAction(event -> {
                stage.close();
                SingleSnakeManager.TimeGo = 15;
                SingleSnakeManager.Direction = "right";
                SingleSnakeManager.m = 0;
                SingleSnakeList.Snake.clear();
                SingleSnakeGameScene.game();
            });
        });
    }
}
