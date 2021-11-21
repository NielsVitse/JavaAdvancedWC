package be.ehb.ti.nielsvitse.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private JFrame frame;
    private JPanel panel1, panel2;
    private JLabel label1, label2;
    private JButton button1, button2;
    private Teller r1, r2;

    public MainFrame() {
        getFrame().setVisible(true);
        r1 = new Teller("thread1",label1);
        Thread t1 = new Thread(r1);
        t1.start();
        r2 = new Teller("thread2",label2);
        Thread t2 = new Thread(r2);
        t2.start();
    }

    public JFrame getFrame() {
        if(frame==null){
            frame = new JFrame("WC1.2");
            Container c = frame.getContentPane();
            c.setLayout(new GridLayout(2,1));
            c.add(getPanel1());
            c.add(getPanel2());
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        return frame;
    }

    public JPanel getPanel1() {
        if(panel1==null){
            panel1 = new JPanel();
            panel1.setLayout(new GridLayout(2,1));
            panel1.add(getLabel1());
            panel1.add(getButton1());
        }
        return panel1;
    }

    public JPanel getPanel2() {
        if(panel2==null){
            panel2 = new JPanel();
            panel2.setLayout(new GridLayout(2,1));
            panel2.add(getLabel2());
            panel2.add(getButton2());
        }
        return panel2;
    }

    public JLabel getLabel1() {
        if(label1==null){
            label1 = new JLabel("0");
        }
        return label1;
    }

    public JLabel getLabel2() {
        if(label2==null){
            label2 = new JLabel("0");
        }
        return label2;
    }

    public JButton getButton1() {
        if(button1==null){
            button1 = new JButton("Press me");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(r1.isStop()){
                        r1.setStop(false);
                    } else{
                        r1.setStop(true);
                    }
                }
            });
        }
        return button1;
    }

    public JButton getButton2() {
        if(button2==null){
            button2 = new JButton("Press me");
            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    r2.setStop(!r2.stop);
                }
            });
        }
        return button2;
    }



    public class Teller implements Runnable{
        private String naam;
        private int teller = 0;
        private JLabel label;
        private boolean stop = true;

        public Teller(String naam, JLabel label) {
            this.naam = naam;
            this.label = label;
        }

        public boolean isStop() {
            return stop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {

            while(true){
                if(!stop){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    teller++;
                    label.setText(String.valueOf(teller));
                }
                System.out.println(naam+" loopt");
            }
        }
    }
}
