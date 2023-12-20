/**
 * Sample Skeleton for 'GameOver.fxml' Controller Class
 */

package tankgame.controller;

import tankgame.director.Director;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class OverController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="flag"
    private ImageView flag; // Value injected by FXMLLoader

    @FXML // fx:id="toIndex"
    private ImageView toIndex; // Value injected by FXMLLoader

    @FXML
    void mouseClickedToIndex(MouseEvent event) {

        Director.getInstance().toIndex();
    }

    @FXML
    void mouseEnteredToIndex(MouseEvent event) {

        toIndex.setOpacity(0.5);
    }

    @FXML
    void mouseExitedToIndex(MouseEvent event) {
       toIndex.setOpacity(1);
    }


    public void flagSuccess(){
        flag.setImage(new Image("tankgame/tankimage/胜利.png"));
    }


}
