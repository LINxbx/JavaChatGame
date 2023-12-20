package tankgame.director;

import javafx.scene.control.Alert;
import tankgame.scene.TankGameScene;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/*
这个Director类是一个单例模式的类，用于在JavaFX应用程序中管理舞台（stage）和场景（scene）的切换，
控制游戏的启动、结束以及场景的切换。在游戏框架中，通常会将Director类作为中心控制器，
负责协调游戏的各个部分，并提供统一的接口，简化开发者的开发过程。
实例：定义一个Person类，然后通过这个类创建出多个实例，每个实例都代表一个具体的人，具有不同的属性和行为。
 */
public class Director {
    public static final double WIDTH = 960,HEIGHT = 640;
    private Director(){}//私有构造 防止类外部通过new关键字来创建实例，只能在类内部通过静态方法来获取唯一的实例
    private static  Director instance = new Director();//单例实例：只有一个具体对象，之后调用都只返回这个实例
    private TankGameScene gameScene = new TankGameScene();//游戏场景实例
    private Stage stage;

    public static Director getInstance(){       //获取Director的单例实例
        return instance;
    }

    public void init(){//游戏窗口
        Platform.runLater(() -> {
            stage = new Stage();
            AnchorPane root = new AnchorPane();
            Scene scene = new Scene(root, WIDTH, HEIGHT);
            stage.setTitle("坦克大战");
            stage.getIcons().add(new Image("tankgame/tankimage/坦克.png"));
            stage.setResizable(false);//画面大小不变
            stage.setScene(scene);
            stage.setWidth(WIDTH);
            stage.setHeight(HEIGHT);
            toIndex();
            stage.show();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("提示");
            alert.setHeaderText("按下F键开火");
            alert.setContentText("上下左右控制坦克移动");
            alert.showAndWait();
        });
    }


    public void toIndex(){      //开始界面
        tankgame.scene.TankIndex.load(stage);
    }
    public void gameOver(boolean success){
        gameScene.clear(stage);
        tankgame.scene.TankGameOver.load(stage, success);
    }
    public void gameStart(){        //这是那个按钮，按下按钮跳转界面
        gameScene.init(stage);
    }
}
