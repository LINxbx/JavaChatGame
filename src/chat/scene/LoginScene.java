package chat.scene;

import chat.judge.JudgeExit;
import chat.udp.UDPReceiver;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class LoginScene {
    public static String account ;
    public static String password ;

    public static void load() {
        //舞台
        Stage stage = new Stage();
        //控件
        AnchorPane root = new AnchorPane();
        //场景
        Scene scene = new Scene(root, 1280, 720);
        stage.setTitle("ChatRoom");
        stage.setResizable(false);
        stage.getIcons().add(new Image("chat/chatimage/ChatIcon.png"));
        // 将新创建的场景设置为舞台的场景
        stage.setScene(scene);
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.show();
        //添加背景
        root.setStyle("-fx-background-image: url('chat/chatimage/LoginBackground.jpg')");
        //添加登录
        Label SignTitle = new Label("登录");
        //添加标签颜色和大小
        SignTitle.setStyle("-fx-font-size: 40px; -fx-text-fill: RED");
        //标签在布局中的位置
        SignTitle.setLayoutX(600);
        SignTitle.setLayoutY(100);
        root.getChildren().add(SignTitle);

        //添加账号
        Label Account = new Label("账号");
        //添加标签颜色和大小
        Account.setStyle("-fx-font-size: 20px; -fx-text-fill: RED");
        Account.setLayoutX(500);
        Account.setLayoutY(200);
        root.getChildren().add(Account);
        //添加输入框
        TextField AccountField = new TextField();
        AccountField.setLayoutX(550);
        AccountField.setLayoutY(200);
        root.getChildren().add(AccountField);

        //添加密码
        Label Password = new Label("密码");
        //添加标签颜色和大小
        Password.setStyle("-fx-font-size: 20px; -fx-text-fill: RED");
        Password.setLayoutX(500);
        Password.setLayoutY(300);
        root.getChildren().add(Password);
        //添加输入框
        TextField PasswordField = new TextField();
        PasswordField.setLayoutX(550);
        PasswordField.setLayoutY(300);
        root.getChildren().add(PasswordField);

        //无法登录文本提醒
        Label SignUnableLabel = new Label("无法登录->");
        SignUnableLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 15));
        root.getChildren().add(SignUnableLabel);
        root.setTopAnchor(SignUnableLabel, 600.0);
        root.setLeftAnchor(SignUnableLabel, 600.0);


        //账号密码错误反馈
        Label SignForgetLabel = new Label("账号或者密码错误！");
        SignForgetLabel.setTextFill(Color.RED);
        SignForgetLabel.setFont(Font.font("T", FontPosture.ITALIC, 20));
        SignForgetLabel.setVisible(false);
        root.getChildren().add(SignForgetLabel);
        root.setTopAnchor(SignForgetLabel, 600.0);
        root.setLeftAnchor(SignForgetLabel, 700.0);

        //添加登录按钮
        Button Loginbutton = new Button();
        Loginbutton.setText("登录");
        Loginbutton.setLayoutX(600);
        Loginbutton.setLayoutY(400);
        Loginbutton.setStyle("-fx-background-color: WHITE; -fx-text-fill: BLACK; -fx-font-size: 20px;");
        root.getChildren().add(Loginbutton);

        System.out.println(ChatScene.oppsiteField);

        //添加登录按钮点击事件
        Loginbutton.setOnAction(event -> {
            try {
                if (JudgeExit.isPassword(AccountField.getText(), PasswordField.getText())) {
                    stage.close();
                    //设置账号和密码字段
                    password = PasswordField.getText();
                    account = AccountField.getText();
                    ChatScene.load();
                    new Thread(new UDPReceiver(Integer.parseInt(JudgeExit.getUserNum(account, password)), JudgeExit.getUserNum(account,password))).start();
                } else {
                    SignForgetLabel.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //添加注册按钮
        Button Registerbutton = new Button();
        Registerbutton.setText("注册");
        Registerbutton.setLayoutX(700);
        Registerbutton.setLayoutY(400);
        Registerbutton.setStyle("-fx-background-color: WHITE; -fx-text-fill: BLACK; -fx-font-size: 20px;");
        root.getChildren().add(Registerbutton);
        //添加注册按钮点击事件
        Registerbutton.setOnAction(event -> {
            //关闭当前窗口
            stage.close();
            //打开新窗口
            try {
                RegisterScene.register();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
