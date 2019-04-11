package W6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class W6_1 {
    public static void main(String[] args) {
        Win6 w = new Win6();
    }
}

class Win6 extends JFrame {
    private JButton open, quit;
    private JLabel word, ch, wn, cn;
    private FileDialog dig;

    public Win6() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        dig = new FileDialog(this, "打开文件", FileDialog.LOAD);

        JLabel word = new JLabel("word number:");
        word.setForeground(Color.red);
        JLabel wn = new JLabel("000");
        JLabel ch = new JLabel("char number:");
        ch.setForeground(Color.blue);
        JLabel cn = new JLabel("000");

        open = new JButton("open");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dig.setVisible(true);
                String dir = dig.getDirectory();
                String file = dig.getFile();

                if (dir == null || file == null) {
                    return;
                }
                File f = new File(dir, file);
                String str;
                int w_n=0,c_n=0;
                try {

                    Scanner sc = new Scanner(new FileReader(f));
                    while(sc.hasNext())
                    {
                        str=sc.next();
                        w_n++;
                        c_n+=str.length();
                    }

                }
                catch (FileNotFoundException ev)
                {
                    ev.printStackTrace();
                    return;
                }

                wn.setText( str=String.format("%03d",w_n));
                cn.setText(String.format("%03d",c_n));
            }
        });

        quit = new JButton("quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(word);
        add(wn);
        add(ch);
        add(cn);
        add(open);
        add(quit);

        setResizable(false);
        setVisible(true);
    }
}
