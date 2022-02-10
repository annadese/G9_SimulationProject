import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.io.*;

public class Binary implements ActionListener{

    private int count = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0 ;
    JTextField tf_mantissa = new JTextField(20);
    JTextField tf_exponent = new JTextField(20);

    JButton button1 = new JButton("");
    JButton button2 = new JButton("");
    JButton button3 = new JButton("");

    Binary(){
        check_inputs();
    }

    public void check_inputs(){

        //JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Binary Mantissa and Base-2");
        //frame.add(panel);
        frame.setSize(420, 420);
        frame.setLayout(null);

        //panel.setLayout(null);

        JLabel label = new JLabel("INPUT");
        label.setBounds(180,20,80,25);
        frame.add(label);

        JLabel label2 = new JLabel("Binary Mantissa and Base-2");
        label2.setBounds(120,45,200,25);
        frame.add(label2);
        
        
        JLabel label3 = new JLabel("Mantissa");
        label3.setBounds(125,100,80,25);
        frame.add(label3);

        JLabel label4 = new JLabel("Exponent");
        label4.setBounds(262,100,80,25);
        frame.add(label4);

        JLabel label5 = new JLabel("OUTPUT");
        label5.setBounds(175,210,80,25);
        frame.add(label5);

        JLabel label6 = new JLabel("Binary");
        label6.setBounds(65,240,80,25);
        frame.add(label6);

        JLabel out_binary = new JLabel("0 010011000111 110");
        out_binary.setBounds(170,240,1000,25);
        frame.add(out_binary);
        
        JLabel label7 = new JLabel("Hexadecimal");
        label7.setBounds(65,260,80,25);
        frame.add(label7);

        JLabel out_hex = new JLabel("0000 0000 0000 0000");
        out_hex.setBounds(170,260,500,25);
        frame.add(out_hex);

        JLabel error_mantissa = new JLabel("");
        error_mantissa.setBounds(95,120,400,25);
        error_mantissa.setForeground(Color.red);
        frame.add(error_mantissa);

        JLabel error_exp = new JLabel("");
        error_exp.setBounds(105,120,400,25);
        frame.add(error_exp);

        tf_mantissa.setBounds(65,80, 170, 25);
        frame.add(tf_mantissa);

        tf_exponent.setBounds(250,80, 80, 25);
        frame.add(tf_exponent);

        button1 = new JButton("Convert");
        button1.setBounds(150,150,100,40);
        button2.setFocusCycleRoot(false);
        button1.addActionListener(this);
        frame.add(button1);

        button2 = new JButton("Write result to Notepad");
        button2.setBounds(160,305,190,40);
        button2.setFocusCycleRoot(false);
        button2.setEnabled(false);
        button2.addActionListener(this);
        frame.add(button2);

        button3 = new JButton("Copy");
        button3.setBounds(60,305,80,40);
        button3.setFocusCycleRoot(false);
        button3.setEnabled(false);
        button3.addActionListener(this);
        frame.add(button3);

        tf_mantissa.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '1') {
                    tf_mantissa.setEditable(true);
                    error_mantissa.setText("");
                    error_exp.setText("");
                }
                else if(ke.getKeyCode()== KeyEvent.VK_BACK_SPACE){
                    tf_mantissa.setEditable(true);
                    error_mantissa.setText("");
                    error_exp.setText("");
                }
                else if (ke.getKeyChar() == '.') {
                    tf_mantissa.setEditable(true);
                    error_mantissa.setText("");
                    error_exp.setText("");
                }
                
                else if (ke.getKeyChar() == '-') {
                    tf_mantissa.setEditable(true);
                    error_mantissa.setText("");
                    error_exp.setText("");
                }

                else {
                    tf_mantissa.setEditable(false);
                    error_mantissa.setText("Enter only numeric digits(0-1), ' - ', ' . ' ");
                    error_exp.setText("");
                }
            }
        });

        tf_exponent.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
            String value = tf_exponent.getText();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    tf_exponent.setEditable(true);
                    error_exp.setText("");
                    error_mantissa.setText("");
                }
                else if(ke.getKeyCode()== KeyEvent.VK_BACK_SPACE){
                    tf_exponent.setEditable(true);
                    error_exp.setText("");
                    error_mantissa.setText("");
                }
                else if (ke.getKeyChar() == '-') {
                    tf_exponent.setEditable(true);
                    error_exp.setText("");
                    error_mantissa.setText("");
                }
                else {
                    tf_exponent.setEditable(false);
                    error_exp.setText("Enter only numeric digits(0-9), ' - '");
                    error_mantissa.setText("");
                }
            }
        });
    
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        
    
        //if(e.getSource() == button1){
            
            String str_mantissa = tf_mantissa.getText();
            //System.out.println(str_mantissa);

            String str_exponent = tf_exponent.getText();
            //System.out.println(str_exponent);

            count = 0;
            count2 = 0;
            count3 = 0;
            count4 = 0;
            count5 = 0;

            for (int i = 0; i < str_mantissa.length(); i++) {
                if (str_mantissa.charAt(i) == '.') {
                    count++;
                }
            }

            for (int i = 0; i < str_mantissa.length(); i++) {
                if (str_mantissa.charAt(i) == '0'){ 
                    count2++;
                }
                else if(str_mantissa.charAt(i) == '1') {
                    count2++;
                }
            }

            for (int i = 0; i < str_mantissa.length(); i++) {
                if (str_mantissa.charAt(i) == '-') {
                    count3++;
                }
            }

            for (int i = 0; i < str_exponent.length(); i++) {
                if (str_exponent.charAt(i) >= '0' && str_exponent.charAt(i) <= '9') {
                    count4++;
                }
            }

            for (int i = 0; i < str_exponent.length(); i++) {
                if (str_exponent.charAt(i) == '-') {
                    count5++;
                }
            }


            // check for invalid mantissa inputs

            if (count > 1){
                tf_mantissa.setText(null);
                System.out.println("ERROR1");
                count = 0;

                frame_invalid();
            }

            else if (count2 == 0) {
                tf_mantissa.setText(null);
                System.out.println("ERROR2");

                frame_invalid();
            }

            else if (count3 > 1){
                tf_mantissa.setText(null);
                System.out.println("ERROR3");
                count3 = 0;

                frame_invalid();
            }

            else if(count3==1 && str_mantissa.charAt(0)!='-'){
                tf_mantissa.setText(null);
                System.out.println("ERROR4");
                count3 = 0;

                frame_invalid();
            }

            


            // check for invalid exponent inputs

            else if(count4 == 0){
                tf_exponent.setText(null);
                System.out.println("ERROR5");
                count4=0;

                frame_invalid();
            }

            else if (count5 > 1){
                tf_exponent.setText(null);
                System.out.println("ERROR6");
                count5 = 0;

                frame_invalid();
            }

            else if(count5==1 && str_exponent.charAt(0)!='-'){
                tf_exponent.setText(null);
                System.out.println("ERROR7");
                count5 = 0;

                frame_invalid();
            }

            // valid mantissa, add zero

            else if(count==1 && str_mantissa.charAt(0)=='.'){
                count=0;

                StringBuilder str_builder = new StringBuilder(str_mantissa);

                str_builder.insert(0, '0');

                str_mantissa = str_builder.toString();

            }

            else if(count==1 && str_mantissa.charAt(str_mantissa.length()-1)=='.'){
                count=0;
            
                StringBuilder str_builder2 = new StringBuilder();

                str_builder2.append(str_mantissa).append('0');

                str_mantissa = str_builder2.toString();
            }

            else {
                button2.setEnabled(true);
            }

            System.out.println(str_mantissa);
            System.out.println(str_exponent);

            if(e.getSource()== button2){
                WriteToFile write = new WriteToFile(str_mantissa, str_exponent);
                
                if(str_mantissa.length()!= 0 && str_exponent.length() != 0 ){
                    System.out.println("FILE");
                }
            }
        //}

        
        

        

        
        
        
    }

    public void check_occurrences(){

    }

    public void frame_invalid(){
        JFrame frame_invalid = new JFrame();

        frame_invalid.pack();
        frame_invalid.setLocationRelativeTo(null);

        JLabel label_invalid = new JLabel("INVALID INPUT");
        label_invalid.setBounds(20,20,80,25);
        frame_invalid.add(label_invalid);

        frame_invalid.setSize(100, 100);
        frame_invalid.setVisible(true);

        button2.setEnabled(false);
    }

}
