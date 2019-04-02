import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class W4_2 {
    public static int v;

    public static void main(String[] args) {
        Random r = new Random();
        v = r.nextInt(1000);
        Win4 w = new Win4();
    }
}

class Win4 extends JFrame {
    private JLabel cn, l, ans;
    private JTextField t;
    private JButton confirm, restart, exit;
    private int cnt;

    public Win4() {
        setSize(350, 120);
        setTitle("猜数游戏");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cn = new JLabel("您已经猜了0次");
        l = new JLabel("输入您猜的数:");
        ans = new JLabel("");
        t = new JTextField(15);
        t.setBackground(null);
        // t.setSize(50, 20);
        confirm = new JButton("确认");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = t.getText();
                int num = 0;
                for (int i = 0; i < s.length(); i++) {
                    num = num * 10 + (int) (s.charAt(i) - '0');
                    if (num > W4_2.v) {
                        ans.setText("猜大了。");
                        t.setBackground(Color.RED);
                    } else if (num == W4_2.v) {
                        ans.setText("猜中了！");
                        t.setBackground(null);
                        t.setEnabled(false);
                    } else {
                        ans.setText("猜小了。");
                        t.setBackground(Color.BLUE);
                    }
                }
                cnt++;
                cn.setText(String.format("您已经猜了%d次", cnt));
            }
        });
        restart = new JButton("重新开始");
        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                W4_2.v = r.nextInt(1000);
                ans.setText("");
                t.setText("");
                t.setBackground(null);
                t.setEnabled(true);
                cn.setText("您已经猜了0次");
                cnt = 0;
            }
        });
        exit = new JButton("退出");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JPanel jc = new JPanel();
        jc.setLayout(new FlowLayout());
        add(cn, BorderLayout.NORTH);

        jc.add(l);
        jc.add(t);
        jc.add(ans);
        add(jc, BorderLayout.CENTER);

        JPanel js = new JPanel();
        js.setLayout(new FlowLayout());
        js.add(confirm);
        js.add(restart);
        js.add(exit);
        add(js, BorderLayout.SOUTH);
        // add(p);
        setVisible(true);
    }
}
