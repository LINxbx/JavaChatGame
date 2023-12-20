package chat.scene;

import chat.judge.JudgeExit;
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


public class RegisterScene {
    public static void register() throws FileNotFoundException {
        Stage stage = new Stage();
        AnchorPane root = new AnchorPane();
        root.setStyle("-fx-background-image: url('chat/chatimage/RegisterBackground.jpg')");
        stage.getIcons().add(new Image("chat/chatimage/ChatIcon.png"));
        //设置场景，大小为960*640
        Scene scene = new Scene(root, 1170, 720);
        //添加在场景中
        stage.setScene(scene);
        stage.show();
        //添加窗口不可变
        stage.setResizable(false);
        //添加注册标签
        Label RegisterTitle = new Label("注册");
        //添加标签颜色和大小
        RegisterTitle.setStyle("-fx-font-size: 40px; -fx-text-fill: RED");
        RegisterTitle.setLayoutX(550);
        RegisterTitle.setLayoutY(50);
        root.getChildren().add(RegisterTitle);

        //添加用户名
        Label UserName = new Label("用户名");
        //添加标签颜色和大小
        UserName.setStyle("-fx-font-size: 20px; -fx-text-fill: RED");
        UserName.setLayoutX(400);
        UserName.setLayoutY(350);
        root.getChildren().add(UserName);
        //添加输入框
        TextField UserNameField = new TextField();
        UserNameField.setLayoutX(500);
        UserNameField.setLayoutY(350);
        root.getChildren().add(UserNameField);

        //添加账号
        Label Account = new Label("账号");
        //添加标签颜色和大小
        Account.setStyle("-fx-font-size: 20px; -fx-text-fill: RED");
        Account.setLayoutX(400);
        Account.setLayoutY(150);
        root.getChildren().add(Account);
        //添加输入框
        TextField AccountField = new TextField();
        AccountField.setLayoutX(500);
        AccountField.setLayoutY(150);
        root.getChildren().add(AccountField);

        //添加密码
        Label Password = new Label("密码");
        //添加标签颜色和大小
        Password.setStyle("-fx-font-size: 20px; -fx-text-fill: RED");
        Password.setLayoutX(400);
        Password.setLayoutY(250);
        root.getChildren().add(Password);
        //添加输入框
        TextField PasswordField = new TextField();
        PasswordField.setLayoutX(500);
        PasswordField.setLayoutY(250);
        root.getChildren().add(PasswordField);

        //该账号已存在提醒
        Label RegisterRepeatLabel = new Label("该账号或用户名已存在!");
        RegisterRepeatLabel.setTextFill(Color.RED);
        RegisterRepeatLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 30));//设置属性
        RegisterRepeatLabel.setVisible(false);
        root.getChildren().add(RegisterRepeatLabel);
        root.setTopAnchor(RegisterRepeatLabel, 400.0);
        root.setLeftAnchor(RegisterRepeatLabel, 500.0);


        //注册成功提醒
        Label RegisterSuccessLabel = new Label("注册成功!");
        RegisterSuccessLabel.setTextFill(Color.RED);
        RegisterSuccessLabel.setFont(Font.font("T", FontWeight.LIGHT, FontPosture.ITALIC, 30));//设置属性
        RegisterSuccessLabel.setVisible(false);
        root.getChildren().add(RegisterSuccessLabel);
        root.setTopAnchor(RegisterSuccessLabel, 400.0);
        root.setLeftAnchor(RegisterSuccessLabel, 500.0);


        //添加返回按钮
        Button rebutton = new Button("返回");
        rebutton.setStyle("-fx-font-size: 20px; -fx-text-fill: BLACK");
        rebutton.setLayoutX(700);
        rebutton.setLayoutY(450);
        //添加返回按钮点击事件
        rebutton.setOnAction(event -> {
            //关闭当前页面
            stage.close();
            //打开登录页面
            LoginScene.load();
        });
        //添加注册按钮
        Button RegisterButton = new Button("注册");
        //添加标签颜色和大小
        RegisterButton.setStyle("-fx-font-size: 20px; -fx-text-fill: BLACK");
        RegisterButton.setLayoutX(570);
        RegisterButton.setLayoutY(450);
        //添加注册按钮点击事件
        RegisterButton.setOnAction(event -> {
            try {
                //判断账号是否已存在
                if (JudgeExit.isUser(UserNameField.getText())) {
                    JudgeExit.writeUser( AccountField.getText(), PasswordField.getText(),UserNameField.getText());
                    RegisterSuccessLabel.setVisible(true);
                    RegisterRepeatLabel.setVisible(false);
                } else {
                    RegisterSuccessLabel.setVisible(false);
                    RegisterRepeatLabel.setVisible(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        root.getChildren().addAll(RegisterButton, rebutton);
    }
}
