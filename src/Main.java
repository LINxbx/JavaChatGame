import chat.scene.LoginScene;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginScene.load();
    }
    public static void main(String[] args){
        Application.launch(args);
    }
}