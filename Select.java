import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;

public class Select implements ActionListener {

    public int selected;


    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JButton button1 = new JButton("Binary w/ Base-2");
    JButton button2 = new JButton("Decimal w/ Base-10");

    Select(){
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Binary-64 Floating Point Converter");

        label = new JLabel("INSTRUCTIONS: Choose any of");
        label.setBounds(110,40,200,50);

        label2 = new JLabel("the following buttons below.");
        label2.setBounds(120,55,200,50);

        button1.setBounds(112,120,170,40);
        button1.setFocusCycleRoot(false);
        button1.addActionListener(this);

        button2.setBounds(112, 190, 170, 40);
        button2.setFocusCycleRoot(false);
        button2.addActionListener(this);
        

        frame.add(label);
        frame.add(label2);
        frame.add(button1);
        frame.add(button2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 330);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==button1) {
            //frame.dispose();
            Binary binary = new Binary();
        }

        else if(e.getSource()==button2){
            //frame.dispose();
            Decimal decimal = new Decimal();
        }
    }
}
