import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

class PageCheckProfile implements ActionListener {
    JFrame fr;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JTextField t1, t2, t3, t4, t5, t6, t7, t8;
    JComboBox cmb;
    JButton b1, b2;
    Statement st;
    String email;

    PageCheckProfile(JFrame fr, Statement st, String email){
        this.fr = fr;
        this.st = st;
        this.email = email;

        try {
            ResultSet rs = st.executeQuery("select * from bank where email = '" + email + "'");
            rs.next();

            t1=new JTextField(rs.getString("username"));
            t2=new JTextField(rs.getString("password"));
            t3=new JTextField(rs.getString("dob"));
            t4=new JTextField(rs.getString("gender"));
            t5=new JTextField(rs.getString("address"));
            t6=new JTextField(rs.getString("religion"));
            t7=new JTextField(rs.getString("phoneno"));
            t8=new JTextField(rs.getString("email"));
        }
        catch (Exception e) {
            t1=new JTextField("");
            t2=new JTextField("");
            t3=new JTextField("");
            t4=new JTextField("");
            t5=new JTextField("");
            t6=new JTextField("");
            t7=new JTextField("");
            t8=new JTextField("");
            System.out.println(e);
        }

        l1 = new JLabel("ABC Bank");
        l2 = new JLabel("Prayagraj");
        l3=new JLabel("Name: ");
        l4=new JLabel("Password: ");
        l5=new JLabel("DOB: ");
        l6=new JLabel("Gender: ");
        l7=new JLabel("Address: ");
        l8=new JLabel("Religion: ");
        l9=new JLabel("Phone No: ");
        l10=new JLabel("Email ID: ");
        l11=new JLabel("");
        b1=new JButton("Go Back");
        b2=new JButton("Update Records");
    }

    void showLayout(){
        fr.setLayout(null);
        fr.setTitle("Check Profile");
        fr.setSize(900,600);

        l1.setBounds(400, 40, 100, 30);
        l2.setBounds(400, 60, 100, 30);

        l3.setBounds(150,130,100,30);
        t1.setBounds(250,130,140,30);

        l4.setBounds(480,130,100,30);
        t2.setBounds(580,130,140,30);

        l5.setBounds(150,180,100,30);
        t3.setBounds(250,180,140,30);

        l6.setBounds(480,180,100,30);
        t4.setBounds(580,180,140,30);

        l7.setBounds(150,230,100,30);
        t5.setBounds(250,230,140,30);

        l8.setBounds(480,230,100,30);
        t6.setBounds(580,230,140,30);

        l9.setBounds(150,280,100,30);
        t7.setBounds(250,280,140,30);

        l10.setBounds(480,280,100,30);
        t8.setBounds(580,280,140,30);

        b1.setBounds(150,360,240,50);
        b2.setBounds(480,360,240,50);

        b1.addActionListener(this);
        b2.addActionListener(this);

        fr.add(l1);
        fr.add(l2);
        fr.add(l3);
        fr.add(l4);
        fr.add(l5);
        fr.add(l6);
        fr.add(l7);
        fr.add(l8);
        fr.add(l9);
        fr.add(l10);
        fr.add(t1);
        fr.add(t2);
        fr.add(t3);
        fr.add(t4);
        fr.add(t5);
        fr.add(t6);
        fr.add(t7);
        fr.add(t8);
        fr.add(b1);
        fr.add(b2);
        
        fr.setVisible(true);
    }

    void showMessage(String mssg, Color color) {
        l11.setText(mssg);
        l11.setForeground(color);
        l11.setBounds(345,320,240,50);
        b1.setBounds(150,380,240,50);
        b2.setBounds(480,380,240,50);
        fr.add(l11);
        fr.setSize(900,640);
    }

    void hideLayout() {
        fr.remove(l1);
        fr.remove(l2);
        fr.remove(l3);
        fr.remove(l4);
        fr.remove(l5);
        fr.remove(l6);
        fr.remove(l7);
        fr.remove(l8);
        fr.remove(l9);
        fr.remove(l10);
        fr.remove(l11);
        fr.remove(t1);
        fr.remove(t2);
        fr.remove(t3);
        fr.remove(t4);
        fr.remove(t5);
        fr.remove(t6);
        fr.remove(t7);
        fr.remove(t8);
        fr.remove(b1);
        fr.remove(b2);
    }

    public void actionPerformed (ActionEvent ae) {
        if (ae.getSource() == b1) {
            hideLayout();
            PageManageAccount ma = new PageManageAccount(fr, st, email);
            ma.showLayout();
        }
        else if (ae.getSource() == b2) {
            String name = t1.getText();
            String pass = t2.getText();
            String dob = t3.getText();
            String gender = t4.getText();
            String address = t5.getText();
            String religion = t6.getText();
            String phoneno = t7.getText();
            String newMail = t8.getText().toLowerCase();

            String successMssg = "Account Updated Successfully";
            String errorMssg = "Given Input Length is too High";

            if (name.length() > 30 || pass.length() > 4 || dob.length() > 15 || gender.length() > 10 || address.length() > 50 || religion.length() > 20 || phoneno.length() > 10 || newMail.length() > 40) {
                showMessage(errorMssg, Color.RED);
            }
            else {
                showMessage(successMssg, Color.GREEN);

                try {
                    ResultSet rs = st.executeQuery("select * from bank where email = '" + email + "'");
                    rs.next();
                    st.executeUpdate("update bank set password = '"+pass+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set dob = '"+dob+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set gender = '"+gender+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set address = '"+address+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set religion = '"+religion+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set phoneno = '"+phoneno+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set email = '"+newMail+"' where email = '"+email+"'");
                    st.executeUpdate("update bank set username = '"+name+"' where email = '"+email+"'");

                    email = newMail;
                }
                catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
    }

}