import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

class PageSignUp implements ActionListener {
    JFrame fr;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
    JTextField t1,t2,t3,t4,t5,t6,t7,t8;
    JButton b1, b2;
    Statement st;

    PageSignUp(JFrame fr, Statement st){
        this.fr = fr;
        this.st = st;

        l1 = new JLabel("ABC Bank");
        l2 = new JLabel("Prayagraj");

        l3=new JLabel("Name: ");
        t1=new JTextField();

        l4=new JLabel("Password: ");
        t2=new JTextField();

        l5=new JLabel("DOB: ");
        t3=new JTextField();

        l6=new JLabel("Gender: ");
        t4=new JTextField();

        l7=new JLabel("Address: ");
        t5=new JTextField();

        l8=new JLabel("Religion: ");
        t6=new JTextField("");

        l9=new JLabel("Phone No: ");
        t7=new JTextField();

        l10=new JLabel("Email ID: ");
        t8=new JTextField();

        l11=new JLabel("");

        b1=new JButton("Go Back");
        b2=new JButton("Create Account");
    }

    void showLayout(){
        fr.setLayout(null);
        fr.setTitle("Create Account");
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
        l11.setBounds(350,320,240,50);
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
            PageLogin ln = new PageLogin(fr, st);
            ln.showLayout();
        }
        else if (ae.getSource() == b2) {
            String name = t1.getText();
            String pass = t2.getText();
            String dob = t3.getText();
            String gender = t4.getText();
            String address = t5.getText();
            String religion = t6.getText();
            String phoneno = t7.getText();
            String email = t8.getText().toLowerCase();

            String successMssg = "Account Created Successfully";
            String errorMssg = "Given Input Length too High";

            if (name.length() > 30 || pass.length() > 4 || dob.length() > 15 || gender.length() > 10 || address.length() > 50 || religion.length() > 20 || phoneno.length() > 10 || email.length() > 40) {
                showMessage(errorMssg, Color.RED);
            }
            else {
                
                try {
                    ResultSet rs1 = st.executeQuery("select * from bank where email = '" + email + "'");
                    ResultSet rs2 = st.executeQuery("select * from bank where phoneno = '" + phoneno + "'");
                    if (rs1.next() || rs2.next()) {
                        showMessage("User Already Exists.", Color.RED);
                    }
                    else {
                        st.executeUpdate("insert into bank values('" +name+ "','" +pass+ "','0','" +dob+ "','" +gender+ "','" +address+ "','" +religion+ "','" +phoneno+ "','" +email+ "')");
                        showMessage(successMssg, Color.GREEN);
                    }
                }
                catch (Exception e) {
                    System.out.println(e);
                }
               
            }

        }
    }
}