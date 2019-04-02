package W3;
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class W3_1 extends JFrame{
    public W3_1()
    {
        setSize(500,500);
        setTitle("line");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyPanel j=new MyPanel();
        add(j);
        setVisible(true);

    }
    public static void main(String[] args)
    {
//        System.out.println("hello");
        W3_1 w=new W3_1();
    }

}

class MyPanel extends JPanel
{
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawLine(0,250,500,250);
        g.drawLine(250,0,250,500);

        int n=500;
        int[] x=new int[1000];
        int[] y=new int[1000];

        for(int i=-250;i<250;i++)
        {
            x[i+250]=i+250;
            y[i+250]=-i*i+250;
        }

        g.drawPolygon(x,y,n);
    }
}