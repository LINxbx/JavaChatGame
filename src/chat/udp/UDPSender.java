package chat.udp;
import chat.scene.ChatScene;

import fishgame.scene.StartScene;
import javafx.application.Platform;
import puzzlegame.GameJFrame;
import snakegame.scene.SnakeGameStart;

import sweepmine.Main;
import tetrisgame.Tetris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSender implements Runnable{
    //创建一个应用程序编程接口socket
    DatagramSocket socket=null;
    //创建一个流 用于录入键盘的数据
    BufferedReader bfr;
    //发送数据目的地的IP
    private String toIP;
    //发送数据目的地的端口
    private int toPort;
    String inputField = ChatScene.chatputField;

    public UDPSender(String toIP, int toPort) {
        this.toIP = toIP;
        this.toPort = toPort;
        try {
            socket=new DatagramSocket();//创建一个socket
        } catch (SocketException e) {
            e.printStackTrace();
        }
        bfr=new BufferedReader(new InputStreamReader(System.in));//从键盘录入数据到流中
    }

    @Override
    public void run() {
        while (true) {//循环发送数据
            try {
                String msg = inputField;//从文本框中获取发送内容
                ChatScene.chatArea.setText(ChatScene.chatArea.getText() + "local: " + msg + "\n");//将发送内容显示到聊天框
                if(msg.equals("我想玩贪吃蛇")){
                    SnakeGameStart.load();
                }
                if(msg.equals("我想玩坦克大战")) {
                    tankgame.director.Director.getInstance().init();
                }
                if(msg.equals("我想玩拼图")) {
                    new GameJFrame();
                }
                if(msg.equals("我想玩扫雷")) {
                    Main.load();
                }
                if(msg.equals("我想玩俄罗斯方块")){
                    Tetris.load();
                }
                if(msg.equals("我想玩大鸟吃小鸟")){
                    Platform.runLater(StartScene::load);
                }
                byte[] buffer = msg.getBytes();//转换为字节数组类型
                InetAddress inet = InetAddress.getByName(toIP);
                //将消息内容封装到一个DatagramPacket中
                DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length, inet, toPort);
                socket.send(packet);
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //释放资源
        if(socket!=null){
            socket.close();
        }
        if (bfr!=null){
            try {
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
