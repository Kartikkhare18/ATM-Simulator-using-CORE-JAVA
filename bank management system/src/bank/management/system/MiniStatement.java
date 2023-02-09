package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener{
    
    MiniStatement(String pinnumber)
    {
        //title
        setTitle("Mini Statement");
        setLayout(null);
        
        JLabel mini = new JLabel();
        mini.setBounds(20,140,400,200);
        add(mini);
        
        JLabel bank = new JLabel("INDIAN BANK");
        bank.setBounds(150,20,100,20);
        add(bank);
        
        JLabel card = new JLabel();
        card.setBounds(20,80,300,20);
        add(card);
        
        JLabel balance = new JLabel();
        balance.setBounds(20,400,300,20);
        add(balance);
        
        //login table query 
        try{
           Conn conn = new Conn();
           ResultSet rs= conn.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
           while(rs.next())
           {
               card.setText("Card Number: "+ rs.getString("cardnumber").substring(0,4) + "xxxxxxxx"+ rs.getString("cardnumber").substring(12));
           }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        //bank table query 
        try{
           Conn conn = new Conn();
           int bal = 0;
           ResultSet rs= conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
           while(rs.next())
           {
               mini.setText(mini.getText() +"<html>" + rs.getString("date")+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><hrml>");
           }
                    if (rs.getString("type").equals("deposit"))
                    {
                        bal += Integer.parseInt(rs.getString("amount"));
                }
                    else
                    {
                        bal -= Integer.parseInt(rs.getString("amount"));
                    }
            balance.setText("Your current account balance is Rs. "+ bal);
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        setSize(400, 600);
        setLocation(20,20);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new MiniStatement("");
    }
    
}
