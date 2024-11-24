import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class PageDepositMoney implements ActionListener  {
    JLabel l1, l2, l3, l4;
    JFrame fr;
    JTextField t1;
    JButton b1, b2;
    Statement st;
    String email;

    PageDepositMoney(JFrame fr, Statement st, String email) {
        this.fr = fr;
        this.st = st;
        this.email = email;

        l1 = new JLabel("ABC Bank");
        l2 = new JLabel("Prayagraj");
        l3 = new JLabel("Enter Amount: ");
        l4 = new JLabel("");
        t1 = new JTextField("");
        b1 = new JButton("Deposit Money");
        b2 = new JButton("Go Back");
    }

    void showLayout() {
        fr.setTitle("Deposit Money");
        fr.setLayout(null);
        fr.setSize(500, 400);
        l1.setBounds(225, 30, 200, 50);
        l2.setBounds(225, 50, 200, 50);

        l3.setBounds(80, 140, 150, 40);
        t1.setBounds(260, 140, 150, 40);

        b1.setBounds(80, 210, 150, 40);
        b2.setBounds(260, 210, 150, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);

        fr.add(l1);
        fr.add(l2);
        fr.add(l3);
        fr.add(t1);
        fr.add(b1);
        fr.add(b2);

        fr.setVisible(true);
    }

    void showMessage(String mssg, Color color) {
        String amount = t1.getText();
        l4.setText(mssg);
        l4.setForeground(color);
        fr.add(l4);

        l4.setBounds(80, 190, 400, 30);
        b1.setBounds(80, 250, 150, 40);
        b2.setBounds(260, 250, 150, 40);

        fr.setSize(500, 470);
    }

    void hideLayout() {
        fr.remove(l1);
        fr.remove(l2);
        fr.remove(l3);
        fr.remove(l4);
        fr.remove(t1);
        fr.remove(b1);
        fr.remove(b2);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {

            try {
                ResultSet rs = st.executeQuery("select * from bank where email = '" + email + "'");
                rs.next();
                int currBalance = Integer.parseInt(rs.getString("balance"));
                int depositAmt = Integer.parseInt(t1.getText());

                if (rs.getString("balance").length() > 20) {
                    showMessage("Sorry, but our bank can't hold that much amount.", Color.RED);
                }
                else {
                    showMessage("₹" + depositAmt + " is Succesfully Deposited to your Account.", Color.GREEN);
                    st.executeUpdate("update bank set balance = '" + (currBalance + depositAmt) + "' where email = '" + email + "'");
                    
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
            
        }
        else if (ae.getSource() == b2) {
            hideLayout();
            PageManageAccount ma = new PageManageAccount(fr, st, email);
            ma.showLayout();
        }
    }
}