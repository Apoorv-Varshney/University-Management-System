import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Marks extends JFrame
{
    JTextArea t1;
    JPanel p1;

    public Marks(){}

    public Marks(String text)
    {
        setSize(800,500);
        setLayout(new BorderLayout());

        p1=new JPanel();
        t1=new JTextArea(15,15);

        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif",Font.ITALIC,18));

        add(p1,"North");

        add(jsp,"Center");

        setLocation(450,200);
        mark(text);
    }

    public void mark(String str)
    {
        try
        {
            conn c=new conn();
            t1.setText("\t\tResult of Examination\n\nSubject\n");
            ResultSet rs1 = c.s.executeQuery("select * from subject where rollno="+str);

                if(rs1.next())
                {
                    t1.append("\n\t"+rs1.getString("subject1"));
                    t1.append("\t"+rs1.getString("subject2"));
                    t1.append("\t"+rs1.getString("subject3"));
                    t1.append("\t"+rs1.getString("subject4"));
                    t1.append("\t"+rs1.getString("subject5"));
                    t1.append("\n------------------------------------------------------------------------");
                    t1.append("\n");
                }

                ResultSet rs2 = c.s.executeQuery("select * from marks where rollno="+str);

                if(rs2.next())
                {
                    int m1 = Integer.parseInt(rs2.getString("marks1"));
                    int m2 = Integer.parseInt(rs2.getString("marks2"));
                    int m3 = Integer.parseInt(rs2.getString("marks3"));
                    int m4 = Integer.parseInt(rs2.getString("marks4"));
                    int m5 = Integer.parseInt(rs2.getString("marks5"));
                    int total=m1+m2+m3+m4+m5;
                    float percentage = total/5F;

                    t1.append("\nMarks\n\n\t"+rs2.getString("marks1"));
                    t1.append("\t"+rs2.getString("marks2"));
                    t1.append("\t"+rs2.getString("marks3"));
                    t1.append("\t"+rs2.getString("marks4"));
                    t1.append("\t"+rs2.getString("marks5"));
                    t1.append("\n------------------------------------------------------------------------");
                    t1.append("\n");
                    t1.append("\nTotal Marks:\t");
                    t1.append(total+"/500\t");
                    t1.append("Percentage:\t");
                    t1.append(String.valueOf(percentage));
                    t1.append("\n-------------------------------------------------------------------------");
                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        new Marks().setVisible(true);
    }
}
