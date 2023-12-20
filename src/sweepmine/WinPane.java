package sweepmine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WinPane extends JDialog implements ActionListener {

    SweepMine frame;
    Font font;
    JButton bon1, bon2;
    JLabel label;
    JPanel panel;
    public WinPane(String s, SweepMine frame) {
        this.frame = frame;
        setTitle(s);
        addPart();
        setBounds(500, 200, 400,200);
        close();
        setModal(true);
        setVisible(true);

    }

    //设置默认关闭方式
    public void close() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.newGame();
                frame.dispose();
            }
        });
    }
    public void addPart() {
        panel = new JPanel();
        //panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
        panel.setLayout(null);

        font = new Font("楷体", 1, 18);

        label = new JLabel("恭喜！你赢了！");
        label.setFont(font);
        label.setBounds(130, 10, 200, 50);
        panel.add(label);

        bon1 = new JButton("开始新游戏");
        bon1.setFont(font);
        bon1.addActionListener(this);
        bon1.setMargin(new Insets(0, 0,0 ,0));
        panel.add(bon1);
        bon1.setBounds(30, 100, 150, 40);

        bon2 = new JButton("退出");
        bon2.setFont(font);
        bon2.addActionListener(this);
        bon2.setMargin(new Insets(0, 0,0 ,0));
        panel.add(bon2);
        bon2.setBounds(200, 100, 150, 40);

        add(panel, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bon1) {
            frame.newGame();
            dispose();
        }
        if (e.getSource() == bon2) {
            frame.record.writeLevel(frame.level);
            if (frame.level == 3)
                frame.record.writeLevel4(frame.row, frame.column, frame.mine);
            frame.dispose();
        }
    }
}
