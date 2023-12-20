package snakegame.paint;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import snakegame.scene.SingleSnakeGameScene;

import java.util.Random;

public class SingleSnakeFood {
    public static int Fx;
    public static int Fy;
    private static Image foodImage = new Image("snakegame/snakeimage/Food.png");
    public static ImageView foodImageView = new ImageView(foodImage);
    public static void snakeFood(){
        Random xF = new Random();
        Random yF = new Random();
        Fx = xF.nextInt(975);
        Fy = yF.nextInt(575);
        Fx = (Fx / 25) * 25;
        Fy = (Fy / 25) * 25;
        //判断不出现在蛇的身体位置
        for(int i = 0; i< SingleSnakeManager.m; i++){
            if(Fx == SingleSnakeManager.x[i] && Fy == SingleSnakeManager.y[i]){
                xF = new Random();
                yF = new Random();
                Fx = xF.nextInt(975);
                Fy = yF.nextInt(575);
                Fx = (Fx / 25) * 25;
                Fy = (Fy / 25) * 25;
            }
        }
        foodImageView = new ImageView(foodImage);
        foodImageView.setLayoutY(Fy);
        foodImageView.setLayoutX(Fx);
        SingleSnakeGameScene.Gameroot.getChildren().add(foodImageView);
    }
}
