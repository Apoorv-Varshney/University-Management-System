import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ExaminationDetails extends JFrame implements ActionListener
{
    JPanel contentPane;
    JTable table;
    JTextField search;
    JButton b1,b2,b3;

    public void Book()
    {
        try
        {
            conn con = new conn();
            String sql = "select * from student";
            PreparedStatement st = con.c.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            table.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
            st.close();
            con.c.close();
        }
        catch (Exception e) {}

    }

    ExaminationDetails()
    {
        setSize(980,475);
        setLocation(300,150);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(40, 133, 900, 282);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int row = table.getSelectedRow();
                search.setText(table.getModel().getValueAt(row, 10).toString());
            }
        });

        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.DARK_GRAY);
        table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
        scrollPane.setViewportView(table);

        Button b4=new Button("Back");
        b4.setForeground(Color.GRAY);
        b4.setFont(new Font("Trebuchet MS",Font.BOLD,18));
        b4.setBounds(97,89,72,33);
        contentPane.add(b4);

        b4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });

        b1 = new JButton("Result");
        b1.addActionListener(this);
        b1.setBorder(new LineBorder(new Color(255, 20, 147), 2, true));
        b1.setForeground(new Color(199, 21, 133));
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
        b1.setBounds(564, 89, 138, 33);
        contentPane.add(b1);


        JLabel l1 = new JLabel("Check Result");
        l1.setForeground(new Color(107, 142, 35));
        l1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 30));
        l1.setBounds(300, 15, 400, 47);
        contentPane.add(l1);


        search = new JTextField();
        search.setBackground(new Color(255, 240, 245));
        search.setBorder(new LineBorder(new Color(255, 105, 180), 2, true));
        search.setForeground(new Color(47, 79, 79));
        search.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
        search.setBounds(189, 89, 357, 33);
        contentPane.add(search);
        search.setColumns(10);

        Book();

    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        try{
            conn con = new conn();
            if(ae.getSource() == b1){
                new Marks(search.getText()).setVisible(true);
                this.setVisible(false);
            };
        }
        catch(Exception e){}
    }
    public static void main(String[] args)
    {
        new ExaminationDetails().setVisible(true);
    }
}
