package W7;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.CollationKey;
import java.util.*;
import java.io.*;


public class W7 {
    public static void main(String[] ags) {
        Win7 w = new Win7();
    }
}

class Win7 extends JFrame {
    MyPanel[] p = new MyPanel[3];
    JPanel[] op=new JPanel[3];
    JButton[] st = new JButton[3];
    JButton[] sp = new JButton[3];
    JButton[] re = new JButton[3];
    JButton sta, spa;
    Thread[] th=new Thread[3];

    public Win7() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(930, 400);
        for (int i = 0; i < 3; i++) {


            op[i]=new JPanel();
            op[i].setLayout(new FlowLayout());
            op[i].setPreferredSize(new Dimension(300,300));
//            op[i].setBackground(Color.blue);

            p[i] = new MyPanel();
//            p[i].setSize(100,100);
//            p[i].setBackground(Color.red);

            st[i] = new JButton("start");
            sp[i] = new JButton("stop");
            re[i] = new JButton("resume");

            op[i].add(st[i]);
            op[i].add(sp[i]);
            op[i].add(re[i]);
            op[i].add(p[i]);

            th[i]=new Thread(p[i]);
        }

        for(int i=0;i<3;i++)
        {
            st[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<3;i++)
                    {
                        if(st[i]==e.getSource())
                        {
                            p[i].presume();
                            break;
                        }
                    }
                }
            });
            re[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<3;i++)
                    {
                        if(re[i]==e.getSource())
                        {
                            p[i].presume();
                            break;
                        }
                    }
                }
            });
            sp[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int i=0;i<3;i++)
                    {
                        if(sp[i]==e.getSource())
                        {
                            p[i].pstop();
                            break;
                        }
                    }
                }
            });
        }

        JPanel cen = new JPanel();
        cen.setLayout(new FlowLayout());
        for (int i = 0; i < 0; i++) {
            cen.add(op[i]);
        }

        JPanel bm = new JPanel();
        bm.setLayout(new FlowLayout());
        sta = new JButton("start all");
        sta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<3;i++){
                    p[i].presume();
                }
            }
        });
        spa = new JButton("stop all");
        spa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<3;i++)
                {
                    p[i].pstop();
                }
            }
        });
        bm.add(sta);
        bm.add(spa);

        setLayout(new FlowLayout());
        for(int i=0;i<3;i++)
        {
            add(op[i]);
            th[i].start();
        }
        add(bm);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

class MyPanel extends JPanel implements Runnable {
    private int bias;
    private boolean suspend=true;
    private JSlider s;
    public MyPanel() {
        super();
        setPreferredSize(new Dimension(200,225));
        setLayout(new BorderLayout());
        s=new JSlider(0,36,0);
        s.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                synchronized (this)
                {
                    bias=s.getValue()*10;
                    repaint();
                }
            }
        });
        add(s,BorderLayout.SOUTH);
        bias=0;
    }
    public void setBias(int bias)
    {
        this.bias=bias;
    }
    public synchronized void pstop()
    {
        suspend=true;
    }
    public synchronized void presume()
    {
        if(suspend)
        {
            suspend=false;
            notify();
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(0, 0, 200, 200);
        g.setColor(Color.red);
        for(int ag=0;ag<360;ag+=90) {
            g.fillArc(10, 10, 180, 180, ag+bias,30);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                synchronized (this)
                {
                    while(suspend)
                    {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            bias += 10;
            repaint();
            s.setValue((bias/10)%36);
        }

    }
}


