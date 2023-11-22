package busProject;

import javax.swing.*;
import java.awt.*;

public class BusGUI extends JFrame {
JPanel jpanel;
JTextField listArea; //대기중인 버스. area로 하니 안되다가 field로 바꾸니 정상
JTextField almostArea; //잠시후도착

    BusGUI() {init();}

    void init() {
    this.setTitle("버스 대기판");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 400, 400);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); //화면 중앙에 표시
        jpanel=new JPanel(new GridLayout(2,1));
        listArea=new JTextField("버스 현황");
        almostArea=new JTextField("잠시 후 도착");
        jpanel.add(listArea);
        jpanel.add(almostArea);
        this.add(jpanel);
//        getContentPane().add(jpanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        BusGUI busGUI=new BusGUI();
    }

}
