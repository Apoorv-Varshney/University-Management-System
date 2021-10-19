import java.awt.*;
import javax.swing.*;

public class Splash
{
    public static void main(String[] args)
    {
        Frame f=new Frame();
        f.setVisible(true);

        int i,x=1;

        for(i=2;i<=600;i+=4,x+=1)
        {
            f.setLocation(600-((i+x)/2),400-(i/2));
            f.setSize(i+3*x,i+x/2);

            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}

class Frame extends JFrame implements Runnable
{
    Thread t1;
    Frame()
    {
        super("University Management System");
        setLayout(new FlowLayout());

        ImageIcon c1=new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i1=c1.getImage().getScaledInstance(1200,800,Image.SCALE_DEFAULT);
        ImageIcon i2=new ImageIcon(i1);

        JLabel j1=new JLabel(i2);
        add(j1);

        t1=new Thread(this);
        t1.start();
    }
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(7000);
            this.setVisible(false);
            new Login();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
