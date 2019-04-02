package W5;

import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class W5_2 {
    public static void main(String[] args) {
        Win5 w = new Win5();
    }
}

class Win5 extends JFrame {
    private JButton st, ed;
    private MyPanel j;
    private JPanel buttom;

    public Win5() {
        setSize(300, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j = new MyPanel(this);
        Thread t = new Thread(j);

        add(j, BorderLayout.CENTER);
        st = new JButton("开始");
        st.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.presume();
                j.add();
            }
        });
        ed = new JButton("结束");
        ed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                j.pstop();

            }
        });
        buttom = new JPanel(new FlowLayout());
        buttom.add(st);
        buttom.add(ed);
        add(buttom, BorderLayout.SOUTH);
        setVisible(true);
        setResizable(false);
        t.start();
    }
}

class MyPanel extends JPanel implements Runnable {
    Vector<Ball> bs = new Vector<>();
    private int w, h;
    private boolean suspend = false;

    synchronized void pstop() {
        suspend = true;
        bs.clear();
    }

    synchronized void presume() {

        if (suspend) {
            suspend = false;
            notify();
        }
    }

    public MyPanel(JFrame j) {
        super();
        h = j.getHeight();
        w = j.getWidth();
    }

    public void add() {
        Ball b = new Ball(w / 2 - 10, h - 70, 20);
        bs.add(b);
    }

    @Override
    public void paint(Graphics g) {

        for (int i = 0; i < bs.size(); i++) {
            bs.get(i).paint(g);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                synchronized (this) {
                    while (suspend) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < bs.size(); i++) {
                Ball b = bs.get(i);
                if (b.getx() <= 0 || b.getx() + b.getr() >= w) b.setspx(-b.getspx());
                if (b.gety() <= 0 || b.gety() + b.getr() >= h - 50) b.setspy(-b.getspy());
                b.setx(b.getx() + b.getspx());
                b.sety(b.gety() + b.getspy());
            }
            repaint();
        }
    }
}

class Ball {
    private int x, y, r;
    private int spx = 10, spy = 10;

    public Ball(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getspx() {
        return spx;
    }

    public int getspy() {
        return spy;
    }

    public void setspx(int spx) {
        this.spx = spx;
    }

    public void setspy(int spy) {
        this.spy = spy;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    public int getr() {
        return r;
    }

    public void setr(int r) {
        this.r = r;
    }

    public Ball() {
        x = 0;
        y = 0;
        r = 20;
    }

    public void set(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void paint(Graphics g) {
        g.fillOval(x, y, r, r);
    }
}

