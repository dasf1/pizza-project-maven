package pizza_project;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Bill extends JPanel implements ActionListener {

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(10, 10, 460, 50);
        g.setColor(Color.blue);
        g.drawRect(10, 70, 460, 240);
        g.setColor(Color.red);
        g.drawRect(10, 320, 460, 60);
        

    }

    Label l1, l2, l3, l4, l5, l6, l7, l8;
    Button b2;
    TextField t1, t2, t3, t4, t5;
    Checkbox c1, c2, c3, c4, c5;
    String str;
    int q, r, cost, Amount, a, b, c;
    CheckboxGroup cbg;

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rst = null;

    public Bill() {

        setLayout(null);

        l1 = new Label("Cafe Coffee Day");
        l2 = new Label("Customer Name");
        l3 = new Label("Quantity");
        l4 = new Label("Price one piece");
        l5 = new Label("Cost of Toppings");
        l6 = new Label("Amount");
        l7 = new Label("Pizza Type");
        l8 = new Label("Toppings Type");
        b2 = new Button("Calculate Amount");
        t1 = new TextField(null);
        t2 = new TextField();
        t3 = new TextField();
        t4 = new TextField();
        t5 = new TextField();
        c1 = new Checkbox("Extra cheese  cost =20");
        c2 = new Checkbox("Mushrooms cost=15");
        c3 = new Checkbox("Onions cost=10");
        cbg = new CheckboxGroup();
        c4 = new Checkbox("Plain", true, cbg);
        c5 = new Checkbox("VIP", false, cbg);
        add(c1);
        add(c2);
        add(c3);
        add(c4);
        add(c5);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(b2);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(l8);

        t3.setEditable(false);
        t4.setEditable(false);
        t5.setEditable(false);

        b2.addActionListener(this);

        l1.setBounds(200, 20, 200, 30);
        l2.setBounds(30, 80, 100, 20);
        l3.setBounds(30, 130, 100, 20);
        l4.setBounds(30, 180, 100, 20);
        l5.setBounds(30, 230, 100, 20);
        l6.setBounds(30, 280, 100, 20);
        l7.setBounds(300, 80, 100, 20);
        l8.setBounds(330, 150, 100, 20);
        b2.setBounds(200, 330, 130, 30);
        t1.setBounds(150, 80, 100, 20);
        t2.setBounds(150, 130, 100, 20);

        t3.setBounds(150, 180, 100, 20);
        t4.setBounds(150, 230, 100, 20);
        t5.setBounds(150, 280, 100, 20);
        c1.setBounds(330, 180, 140, 20);
        c2.setBounds(330, 210, 140, 20);
        c3.setBounds(330, 240, 140, 20);
        c4.setBounds(370, 80, 100, 20);
        c5.setBounds(370, 100, 100, 20);

        //set Quantity equals one by default
        t2.setText("1");

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String t11 = "";

        if (t1.getText().equals(t11)) {

            JOptionPane.showMessageDialog(null, "please set your name !!",
                    null, JOptionPane.ERROR_MESSAGE);

        } //q=Integer.parseInt(t2.getText());
        else {
            try {

                if (ae.getSource() == b2) {

                    a = 0;
                    b = 0;
                    c = 0;

                    q = Integer.parseInt(t2.getText());
                    String pizza_type = "";
                    if (c4.getState() == true) {
                        if (q > 0) {
                            t3.setText("60 Rs");
                            r = 60;
                        }

                        pizza_type = "Plain";

                    } else if (c5.getState() == true) {
                        t3.setText("100 Rs");
                        r = 100;
                        pizza_type = "VIP";
                    }
                    if (c1.getState() == true) {
                        a = 20;
                    }
                    if (c2.getState() == true) {
                        b = 15;
                    }
                    if (c3.getState() == true) {
                        c = 10;
                    }

                    if (q <= 0) {

                        JOptionPane.showMessageDialog(null, "Error Quantity less than or equals zero!!",
                                null, JOptionPane.ERROR_MESSAGE);

                    } else {

                        cost = q * a + q * b + q * c;
                        t4.setText("" + cost);
                        Amount = q * r + cost;
                        t5.setText("" + Amount);

                        try {

                            con = Connect.connect();
                            
  String sql1 = "insert into bill (customer_name,pizza_type,quantity,price_one_piece,amount) values"
   + " ('" + t1.getText() + "' , '" + pizza_type + "', " + Integer.parseInt(t2.getText()) + " , '" + t3.getText().trim() + "' ," + Amount + " )";
                          
                            pst = con.prepareStatement(sql1);
                            
                            pst.execute();
                            t1.setText("");
                            t2.setText("1");
                            t3.setText("");
                            t4.setText("");
                            t5.setText("");
                            JOptionPane.showMessageDialog(null, "done, Save");

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }

                    }

                }
            } 
            
            
            
            
            catch (Exception e) {
               e.printStackTrace();
                JOptionPane.showMessageDialog(null, " Error Quantity please try again",
                        null, JOptionPane.ERROR_MESSAGE);
            }
        }}

    public static void main(String args[]) {

        JFrame m = new JFrame();
      
        Bill mf = new Bill();

        m.setSize(500, 450);
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setVisible(true);
        m.setResizable(false);
        m.setLocation(300,70);
        m.add(mf);

    }

}
