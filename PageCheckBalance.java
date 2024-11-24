import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class PageCheckBalance implements ActionListener {
    JLabel l1, l2, l3;
    JFrame fr;
    JButton b1;
    Statement st;
    String email;

    PageCheckBalance(JFrame fr, Statement st, String email) {
        this.fr = fr;
        this.st = st;
        this.email = email;

        String balance = "0";
        try {
            ResultSet rs = st.executeQuery("select * from bank where email = '" + email + "'");
            rs.next();
            balance = rs.getString("balance");
        }
        catch (Exception e) {
            System.out.println(e);
        }

        l1 = new JLabel("ABC Bank");
        l2 = new JLabel("Prayagraj");
        l3 = new JLabel("Your Balance is ₹" + balance);
        b1 = new JButton("Go Back");
    }

    void showLayout() {
        fr.setLayout(null);
        fr.setSize(400, 400);
        l1.setBounds(175, 30, 200, 50);
        l2.setBounds(175, 50, 200, 50);
        l3.setBounds(135, 140, 400, 40);
        b1.setBounds(125, 210, 150, 40);

        fr.add(l1);
        fr.add(l2);
        fr.add(l3);
        fr.add(b1);

        b1.addActionListener(this);

        fr.setVisible(true);
    }

    void hideLayout() {
        fr.remove(l1);
        fr.remove(l2);
        fr.remove(l3);
        fr.remove(b1);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            hideLayout();
            PageManageAccount ma = new PageManageAccount(fr, st, email);
            ma.showLayout();
        }
    }
}