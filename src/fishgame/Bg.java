package fishgame;

import fishgame.scene.OverScene;
import javafx.application.Platform;

import java.awt.*;

public class Bg {

    void paintSelf(Graphics g , int fishLevel){

        g.drawImage(GameUtils.bgimg,0,0,null);
        switch (GameWin.state){
            case 0:
                GameUtils.drawWord(g,"开始",Color.red,80,500,500);
                break;
            case 1:
                GameUtils.drawWord(g,"得分"+GameUtils.count,Color.ORANGE,50,100,120);
                GameUtils.drawWord(g,"关卡"+GameUtils.level,Color.ORANGE,50,400,120);
                GameUtils.drawWord(g,"等级"+fishLevel,Color.ORANGE,50,700,120);
                break;
            case 2:
//                Platform.runLater(OverScene::load);
//                GameWin close = new GameWin();
//                close.closeWindow();
//                System.out.println("失败");
//                GameWin.state = 6;

                break;
            case 3:
//                GameUtils.drawWord(g,"得分"+GameUtils.count,Color.ORANGE,50,100,120);
//                GameUtils.drawWord(g,"关卡"+GameUtils.level,Color.ORANGE,50,400,120);
//                GameUtils.drawWord(g,"等级"+fishLevel,Color.ORANGE,50,700,120);
//                GameUtils.drawWord(g ,"胜利",Color.red,80,500,500);
                break;
            case 4:
                GameUtils.drawWord(g,"得分"+GameUtils.count,Color.ORANGE,50,100,120);
                GameUtils.drawWord(g,"关卡"+GameUtils.level,Color.ORANGE,50,400,120);
                GameUtils.drawWord(g,"等级"+fishLevel,Color.ORANGE,50,700,120);
            default:

        }
    }
}
