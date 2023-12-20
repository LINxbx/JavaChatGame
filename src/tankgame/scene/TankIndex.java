package tankgame.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


/*
在load()方法中，通过FXMLLoader的静态方法load()从资源文件加载FXML文件，然后将其设置为舞台（Stage）
的根节点（root node），以便显示在屏幕上。在这个过程中，如果加载FXML文件失败，将会打印出异常信息。

这个类的作用是将JavaFX应用程序的当前场景（Scene）切换为游戏的起始界面。在Director类的toIndex()方法中，
调用了Index类的load()方法，将主舞台（Stage）的场景设置为游戏的起始界面。这样，当游戏启动时，首先显示的就是游戏的起始界面。
 */
public class TankIndex {
   /*
   该类在Director类的toIndex()方法中被调用，将主舞台（Stage）的场景设置为游戏的起始界面。
   这样，当游戏启动时，首先显示的就是游戏的起始界面。
    */
    public static void load(Stage stage) {
        try {
            //使用FXMLLoader静态方法load()从资源文件加载FXML文件
            Parent root = FXMLLoader.load(Objects.requireNonNull(TankIndex.class.getResource("/tankgame/tankimage/Index.fxml")));
            //将加载的FXML文件设置为主舞台（Stage）的根节点，以便在屏幕上显示
            stage.getScene().setRoot(root);
        } catch (IOException e) {       //如果加载FXML文件失败，将会打印出异常信息
            e.printStackTrace();
        }
    }
}
