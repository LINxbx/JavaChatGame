package chat.udp;

import chat.scene.ChatScene;
import fishgame.scene.OverScene;
import fishgame.scene.StartScene;
import javafx.application.Platform;
import puzzlegame.GameJFrame;
import sweepmine.Main;
import tankgame.director.Director;
import snakegame.scene.SnakeGameStart;
import tetrisgame.Tetris;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPReceiver implements Runnable {
    //创建一个socket
    DatagramSocket socket = null;
    //接收方自己所在的端口
    private int fromPort;
    //数据发送者的姓名
    private String msgFrom;

    public UDPReceiver(int fromPort, String msgFrom) {
        this.fromPort = fromPort;
        this.msgFrom = msgFrom;
        try {
            socket = new DatagramSocket(fromPort);//创建一个socket
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (true) {//循环接收
            try {
                byte[] buffer = new byte[1024 * 8];//创建缓冲区，用于接收数据
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());//将接收到的字节类型转换为字符串
                //设置Area的文本
                msgFrom = ChatScene.oppsiteField.getText();
                ChatScene.chatArea.setText(ChatScene.chatArea.getText() + msgFrom + ": " + msg + "\n");
                if(msgFrom.equals("我想玩扫雷")) {
                    Main.load();
                }
                if(msgFrom.equals("我想玩贪吃蛇")) {
                    SnakeGameStart.load();
                }
                if(msgFrom.equals("我想玩坦克大战")) {
                    Director.getInstance().init();
                }
                if(msgFrom.equals("我想玩拼图")) {
                    new GameJFrame();
                }
                if(msgFrom.equals("我想玩俄罗斯方块")){
                    Tetris.load();
                }
                if(msgFrom.equals("我想玩大鸟吃小鸟")){
                    Platform.runLater(StartScene::load);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
