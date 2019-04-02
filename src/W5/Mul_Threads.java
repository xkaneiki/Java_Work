package W5;
import java.lang.InterruptedException.*;

public class Mul_Threads {
    public static void main(String[] args) {
        MyThread t1=new MyThread("hello");
        MyThread t2=new MyThread("world");
        t1.start();
        t2.start();
//        Consumer_Producer s=new Consumer_Producer();
//        s.start();
    }
}

class MyThread extends Thread{
    private String str;
    public MyThread(String s)
    {
        super();
        str=s;
    }
    @Override
    public void run()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println(str);
//            this.sleep(100);
            try {
                sleep((int)Math.random()*10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Consumer_Producer extends Thread{
    private int product=0;
    private int max_product=10;
    public synchronized void produce(){
        if(product>=max_product)
        {
            try {
                wait();
                System.out.println("货已满");
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return;
        }
        product++;
        System.out.println("p"+product);
        notifyAll();
    }

    public synchronized void consumer(){
        if(product<=0)
        {
            try {
                wait();
                System.out.println("货已空");
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            return;
        }
        product--;
        System.out.println("c"+product);
        notifyAll();
    }

    @Override
    public void run() {
        while(true) {
            produce();
            consumer();
        }    }
}