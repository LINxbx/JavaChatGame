package chat.scene;

import chat.judge.JudgeExit;
import chat.sound.SoundEffect;
import chat.udp.UDPSender;
import fishgame.scene.StartScene;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import puzzlegame.GameJFrame;
import snakegame.scene.SnakeGameStart;

import sweepmine.Main;
import tankgame.director.Director;
import tetrisgame.Tetris;


public class ChatScene {
    public static TextField inputField = new TextField();
    public static String chatputField;
    public static TextArea chatArea = new TextArea();
    public static TextField oppsiteField = new TextField();
    static String name = LoginScene.account;
    static String password = LoginScene.password;

    public static void load() throws Exception {
        //舞台
        Stage stage = new Stage();
        //控件
        AnchorPane root = new AnchorPane();
        //设置场景，大小为960*640
        Scene scene = new Scene(root, 960, 640);
        //添加在场景中
        stage.setScene(scene);
        //添加窗口名
        stage.setTitle("ChatRoom");
        //添加图标
        stage.getIcons().add(new Image("chat/chatimage/ChatIcon.png"));
        //添加窗口大小不可变
        stage.setResizable(false);
        //显示窗口
        stage.show();
        //添加背景
        root.setStyle("-fx-background-image: url('chat/chatimage/ChatBackground.jpg')");

        //添加本地端口
        Label local = new Label("本地端口：" + JudgeExit.getUserNum(LoginScene.account, LoginScene.password));
        //添加颜色和大小
        local.setStyle("-fx-font-size: 20px;-fx-text-fill: BLACK");
        local.setLayoutX(100);
        local.setLayoutY(50);
        root.getChildren().add(local);

        //添加对方端口
        Label oppsite = new Label("对方端口");
        //添加颜色和大小
        oppsite.setStyle("-fx-font-size: 20;-fx-text-fill: BLACK");
        oppsite.setLayoutX(100);
        oppsite.setLayoutY(100);
        root.getChildren().add(oppsite);
        //添加确定按钮
        Button button = new Button("确定");
        button.setLayoutX(400);
        button.setLayoutY(100);
        root.getChildren().add(button);
        //添加对方端口输入框
        oppsiteField = new TextField();//2576
        //添加内容为空
        oppsiteField.setText("");
        oppsiteField.setLayoutX(200);
        oppsiteField.setLayoutY(100);
        oppsiteField.setPromptText("请先输入对方端口");
        root.getChildren().add(oppsiteField);

        //确定按钮添加事件
        button.setOnAction(event -> {
            //如果按钮的文字为”更换“，则将输入框变为可输入，并将按钮的文字设置为”确定“,将UDPSender的open设置为true
            if (button.getText().equals("更换")) {
                oppsiteField.setEditable(true);  //设置输入框可输入
                button.setText("确定"); //设置按钮文字
            } else {
                try {
                    //跳出一个对话框，提示输入
                    //如果为空输入或者输入本身端口，则提示输入错误
                    if (oppsiteField.getText().equals("") || oppsiteField.getText().equals(JudgeExit.getUserNum(name, password))) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("提示");
                        alert.setHeaderText("输入错误");
                        alert.setContentText("请输入正确的端口号");
                        alert.showAndWait();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("提示");
                        alert.setHeaderText("设置成功");
                        alert.setContentText("您设置的对方端口号为：" + oppsiteField.getText());
                        alert.showAndWait();
                        //将输入框设置为不可输入
                        oppsiteField.setEditable(false);
                        //将按钮文字设置为”更换“
                        button.setText("更换");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        //添加聊天标签
        Label chat = new Label("聊天框");
        //添加颜色和大小
        chat.setStyle("-fx-font-size: 30;-fx-text-fill: BLACK");
        chat.setLayoutX(100);
        chat.setLayoutY(150);
        root.getChildren().add(chat);
        //添加聊天框
        chatArea = new TextArea();
        chatArea.setFont(Font.font("宋体", FontWeight.LIGHT, FontPosture.ITALIC, 20));
        chatArea.setLayoutX(100);
        chatArea.setLayoutY(200);
        root.getChildren().add(chatArea);
        //设置聊天框大小
        chatArea.setPrefSize(400, 200);
        //设置聊天框不可编辑
        chatArea.setEditable(false);

        //添加输入框
        inputField = new TextField();
        inputField.setLayoutX(100);
        inputField.setLayoutY(420);
        //设置输入框大小
        inputField.setPrefSize(400, 50);

        inputField.setPromptText("输入我想玩+游戏名可打开游戏");      //设置提示内容
        // 创建 BoxBlur 效果
        root.getChildren().add(inputField);

        //添加发送按钮
        Button sent = new Button("发送");
        //设置按钮大小和颜色
        sent.setStyle("-fx-font-size: 20px;-fx-text-fill: BLACK;-fx-background-color: WHITE");
        //设置位置
        sent.setLayoutX(520);
        sent.setLayoutY(420);
        //添加按钮
        root.getChildren().add(sent);
        //发送按钮添加事件
        sent.setOnAction(event -> {
            try {
                SoundEffect.play("file:sound/ChatSend.mp3");
                chatputField = inputField.getText();
                new Thread(new  UDPSender("127.0.0.1", Integer.parseInt(oppsiteField.getText()))).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            inputField.setText("");
        });
        // 注册输入框按键事件处理器
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                // 模拟按钮点击事件
                sent.fire();
            }
        });
        //添加返回按钮
        Button reButton = new Button("返回");
        reButton.setLayoutX(480);
        reButton.setLayoutY(500);
        reButton.setStyle("-fx-background-color: WHITE; -fx-text-fill: BLACK; -fx-font-size: 20px");
        root.getChildren().add(reButton);
        //按钮添加事件
        reButton.setOnAction(event -> {
            //关闭当前窗口
            stage.close();
            //打开新窗口
            LoginScene.load();
        });
        //

        //添加贪吃蛇图标
        Image SnakeImage = new Image("chat/chatimage/SnakeIcon.jpg");
        ImageView SnakeImageView = new ImageView(SnakeImage);
        SnakeImageView.setFitWidth(60);
        SnakeImageView.setFitHeight(60);
        SnakeImageView.setLayoutX(838);
        SnakeImageView.setLayoutY(40);
        root.getChildren().add(SnakeImageView);
        SnakeImageView.setOnMouseClicked(event -> {
            SnakeGameStart.load();
        });
        //添加贪吃蛇标签
        Label SnakeLabel = new Label("贪吃蛇");
        SnakeLabel.setLayoutX(838);
        SnakeLabel.setLayoutY(105);
        SnakeLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: WHITE");
        root.getChildren().add(SnakeLabel);


        //添加坦克大战图标
        Image TankImage = new Image("chat/chatimage/TankIcon.png");
        ImageView TankImageView = new ImageView(TankImage);
        TankImageView.setFitWidth(60);
        TankImageView.setFitHeight(60);
        TankImageView.setLayoutX(838);
        TankImageView.setLayoutY(135);
        root.getChildren().add(TankImageView);
        TankImageView.setOnMouseClicked(event -> {
            Director.getInstance().init();
        });

        //添加坦克大战标签
        Label TankLabel = new Label("坦克大战");
        TankLabel.setLayoutX(830);
        TankLabel.setLayoutY(200);
        TankLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: White");
        root.getChildren().add(TankLabel);

        //添加大鱼吃小鱼图标
        Image FishImage = new Image("chat/chatimage/FishIcon.jpg");
        ImageView FishImageView = new ImageView(FishImage);
        FishImageView.setFitWidth(60);
        FishImageView.setFitHeight(60);
        FishImageView.setLayoutX(838);
        FishImageView.setLayoutY(230);
        root.getChildren().add(FishImageView);
        FishImageView.setOnMouseClicked(event -> {
            StartScene.load();
        });

        //添加大鱼吃小鱼标签
        Label FishLabel = new Label("大鸟吃小鸟");
        FishLabel.setLayoutX(820);
        FishLabel.setLayoutY(295);
        FishLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: White");
        root.getChildren().add(FishLabel);

        //添加俄罗斯方块图标
        Image TetrisImage = new Image("chat/chatimage/TetrisIcon.jpg");
        ImageView TetrisImageView = new ImageView(TetrisImage);
        TetrisImageView.setFitWidth(60);
        TetrisImageView.setFitHeight(60);
        TetrisImageView.setLayoutX(838);
        TetrisImageView.setLayoutY(325);
        root.getChildren().add(TetrisImageView);
        TetrisImageView.setOnMouseClicked(event -> {
            Tetris.gameRunning = true;
            Tetris.load();
        });

        //添加俄罗斯方块标签
        Label TetrisLabel = new Label("俄罗斯方块");
        TetrisLabel.setLayoutX(820);
        TetrisLabel.setLayoutY(390);
        TetrisLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: White");
        root.getChildren().add(TetrisLabel);


        //添加拼图图标
        Image PuzzleImage = new Image("chat/chatimage/PuzzleIcon.png");
        ImageView PuzzleImageView = new ImageView(PuzzleImage);
        PuzzleImageView.setFitWidth(60);
        PuzzleImageView.setFitHeight(60);
        PuzzleImageView.setLayoutX(838);
        PuzzleImageView.setLayoutY(420);
        root.getChildren().add(PuzzleImageView);
        PuzzleImageView.setOnMouseClicked(event -> {
            new GameJFrame();
        });
        //添加拼图标签
        Label PuzzleLabel = new Label("拼图");
        PuzzleLabel.setLayoutX(847);
        PuzzleLabel.setLayoutY(485);
        PuzzleLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: White");
        root.getChildren().add(PuzzleLabel);
        

        //添加扫雷图标
        Image SweepImage = new Image("chat/chatimage/SweepIcon.png");
        ImageView SweepImageView = new ImageView(SweepImage);
        SweepImageView.setFitWidth(60);
        SweepImageView.setFitHeight(60);
        SweepImageView.setLayoutX(838);
        SweepImageView.setLayoutY(515);
        root.getChildren().add(SweepImageView);
        SweepImageView.setOnMouseClicked(event -> {
            Main.load();
        });

        //添加扫雷标签
        Label SweepLabel = new Label("扫雷");
        SweepLabel.setLayoutX(847);
        SweepLabel.setLayoutY(580);
        SweepLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: White");
        root.getChildren().add(SweepLabel);

    }
}
