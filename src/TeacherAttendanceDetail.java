import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class TeacherAttendanceDetail extends JFrame implements ActionListener
{
    JTable j1;
    JButton b1;
    String h[]={"Employee ID","Date Time","First Half","Second Half"};
    String d[][]=new String[15][4];
    int i=0,j=0;

    TeacherAttendanceDetail()
    {
        super("View Teacher Attendence");
        setSize(600, 300);
        setLocation(450, 150);
        setLayout(new BorderLayout());

        try{
            String q="select * from attendance_teacher";
            conn c1=new conn();
            ResultSet rs=c1.s.executeQuery(q);

            while(rs.next()){
                d[i][j++]=rs.getString("emp_id");
                d[i][j++]=rs.getString("Date");
                d[i][j++]=rs.getString("first");
                d[i][j++]=rs.getString("second");
                i++;
                j=0;
            }

            j1=new JTable(d,h);

        }catch(Exception e){}

        b1=new JButton("Print");
        add(b1,"South");
        JScrollPane s1=new JScrollPane(j1);
        add(s1);

        b1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        try{
            j1.print();
        }catch(Exception ee){}
    }
    public static void main(String[] args)
    {
        new TeacherAttendanceDetail().setVisible(true);
    }
}
