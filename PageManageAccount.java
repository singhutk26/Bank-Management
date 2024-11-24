import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class PageManageAccount implements ActionListener {
    JFrame fr;
    JLabel l1, l2;
    JButton b1, b2, b3, b4, b5;
    String email;
    Statement st;

    PageManageAccount (JFrame fr, Statement st, String email) {
        this.fr = fr;
        this.st = st;
        this.email = email;
        l1 = new JLabel("ABC Bank");
        l2 = new JLabel("Prayagraj");
        b1 = new JButton("Check Profile");
        b2 = new JButton("Deposit Money");
        b3 = new JButton("Withdraw Money");
        b4 = new JButton("Check Balance");
        b5 = new JButton("Sign Out");
    }

    void showLayout() {
        fr.setTitle("Manage Account");
        fr.setLayout(null);
        fr.setSize(400, 600);
        l1.setBounds(170, 30, 200, 50);
        l2.setBounds(170, 50, 200, 50);

        b1.setBounds(100, 120, 200, 50);
        b2.setBounds(100, 190, 200, 50);
        b3.setBounds(100, 260, 200, 50);
        b4.setBounds(100, 330, 200, 50);
        b5.setBounds(100, 400, 200, 50);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        fr.add(l1);
        fr.add(l2);
        fr.add(b1);
        fr.add(b2);
        fr.add(b3);
        fr.add(b4);
        fr.add(b5);

        fr.setVisible(true);
    }

    void hideLayout() {
        fr.remove(l1);
        fr.remove(l2);
        fr.remove(b1);
        fr.remove(b2);
        fr.remove(b3);
        fr.remove(b4);
        fr.remove(b5);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            hideLayout();
            PageCheckProfile cp = new PageCheckProfile(fr, st, email);
            cp.showLayout();
        }
        else if (ae.getSource() == b2) {
            hideLayout();
            PageDepositMoney dm = new PageDepositMoney(fr, st, email);
            dm.showLayout();
        }
        else if (ae.getSource() == b3) {
            hideLayout();
            PageWithdrawMoney wm = new PageWithdrawMoney(fr, st, email);
            wm.showLayout();
        }
        else if (ae.getSource() == b4) {
            hideLayout();
            PageCheckBalance cb = new PageCheckBalance(fr, st, email);
            cb.showLayout();
        }
        else if (ae.getSource() == b5) {
            hideLayout();
            PageLogin lg = new PageLogin(fr, st) ;
            lg.showLayout();
        }
    }
}