package tankgame.scene;

import tankgame.controller.OverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class TankGameOver {
    public static void load(Stage stage, boolean success) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TankIndex.class.getResource("/tankgame/tankimage/GameOver.fxml"));
            Parent root = fxmlLoader.load();
            OverController overController = fxmlLoader.getController();
            if(success) overController.flagSuccess();
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
