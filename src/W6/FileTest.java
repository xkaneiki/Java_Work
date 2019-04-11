package W6;

//import java.io.FileInputStream;
import java.io.*;
import java.util.Scanner;

public class FileTest {
    public static void main(String[] args)
    {
        File f=new File("/Users/kaneiki/Desktop/Java/JavaExperiments/src/W6/hello.txt");
        try {
            FileInputStream fin = new FileInputStream(f);
            try {
                byte b = (byte) fin.read();
                byte c = (byte) fin.read();
                System.out.println(b);
                System.out.println(c);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try {
            FileReader fr = new FileReader(f);
            try {

                int t = fr.read();
                System.out.println(t);
                Scanner s=new Scanner(fr);
                String str=s.next();
                System.out.println(str);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try{
            FileWriter fw=new FileWriter(f);
            PrintWriter p=new PrintWriter(fw);
            p.println("hello java!");
            p.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}


