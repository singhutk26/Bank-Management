import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class BankManagement {

    public static void main(String[] args){

        Connection con = null;
        Statement st = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1821");
            st = con.createStatement();
        }
        catch (Exception e) {
            System.out.println(e);
        }


        JFrame fr = new JFrame("ABC Bank");
        PageLogin ma = new PageLogin(fr, st);
        ma.showLayout();
        
    }
}