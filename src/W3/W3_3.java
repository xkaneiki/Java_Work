import java.util.*;
// import java.io.*;
import javax.swing.*;
import java.awt.*;

public class W3_3 {
    public static void main(String[] args) {
        // System.out.println("this is W3_3.");
        Win w = new Win(500, 500);
    }
}

class Win extends JFrame {
    public Win(int w, int h) {
        setSize(w, h);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyPanel p = new MyPanel();
        add(p);

        setVisible(true);
    }
}

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        MyLine l = new MyLine();
        MyRectangle r = new MyRectangle();
        MyOval o=new MyOval();
        Random rd = new Random();
        for (int i = 0; i < 20; i++) {
            switch (rd.nextInt(3)) {
                case 0:
                    r.set(rd.nextInt(500), rd.nextInt(500));
                    r.setwh(rd.nextInt(500), rd.nextInt(500));
                    r.draw(g);
                    break;
                case 1:
                    l.set(rd.nextInt(500), rd.nextInt(500));
                    l.setg(rd.nextInt(500), rd.nextInt(500));
                    l.draw(g);
                    break;
                default:
                    o.set(rd.nextInt(500), rd.nextInt(500));
                    o.setwh(rd.nextInt(500), rd.nextInt(500));
                    o.draw(g);
                    break;
            }

        }
    }
}

abstract class MyShape {
    private int x, y;

    public MyShape() {
        x = y = 0;
    }

    public MyShape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return 0;
    }

    public abstract void draw(Graphics g);
}

class MyLine extends MyShape {
    private int x1, y1;

    public void setg(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void draw(Graphics g) {
        g.drawLine(super.getx(), super.gety(), x1, y1);
    }
}

class MyRectangle extends MyShape {
    private int w, h;

    public void setwh(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.drawRect(super.getx(), super.gety(), w, h);
    }
}

class MyOval extends MyShape {
    private int w, h;

    public void setwh(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.drawOval(super.getx(), super.gety(), w, h);
    }
}