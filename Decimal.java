import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;

public class Decimal implements ActionListener{

    private int count = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0;
    JTextField tf_mantissa = new JTextField(20);
    JTextField tf_exponent = new JTextField(20);
    
    JButton button1 = new JButton("");
    JButton button2 = new JButton("");
    JButton button3 = new JButton("");

    JLabel out_binary = new JLabel("");
    JLabel out_hex = new JLabel("");

    String str_binary="";
    String str_hex="";

    String str_mantissa="";
    String str_exponent="";

    Decimal(){
        check_inputs();
    }

    public void check_inputs(){
        //JPanel panel = new JPanel();
        JFrame frame = new JFrame();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setTitle("Decimal Mantissa and Base-10");
        //frame.add(panel);
        frame.setSize(800, 450);
        frame.setLayout(null);

        //panel.setLayout(null);

        JLabel label = new JLabel("INPUT");
        label.setBounds(370,20,80,25);
        frame.add(label);

        JLabel label2 = new JLabel("Decimal Mantissa and Base-10");
        label2.setBounds(300,45,200,25);
        frame.add(label2);
        
        
        JLabel label3 = new JLabel("Mantissa");
        label3.setBounds(320,100,80,25);
        frame.add(label3);

        JLabel label4 = new JLabel("Exponent");
        label4.setBounds(457,100,80,25);
        frame.add(label4);

        JLabel label5 = new JLabel("OUTPUT");
        label5.setBounds(365,210,80,25);
        frame.add(label5);

        JLabel label6 = new JLabel("Binary");
        label6.setBounds(368,235,80,25);
        frame.add(label6);

        out_binary = new JLabel("");
        out_binary.setBounds(180,255,1000,25);
        frame.add(out_binary);
        
        JLabel label7 = new JLabel("Hexadecimal");
        label7.setBounds(362,280,80,25);
        frame.add(label7);

        out_hex = new JLabel("");
        out_hex.setBounds(340,300,500,25);
        frame.add(out_hex);

        JLabel error_mantissa = new JLabel("");
        error_mantissa.setBounds(95,120,400,25);
        error_mantissa.setForeground(Color.red);
        frame.add(error_mantissa);

        JLabel error_exp = new JLabel("");
        error_exp.setBounds(105,120,400,25);
        frame.add(error_exp);

        tf_mantissa.setBounds(260,80, 170, 25);
        frame.add(tf_mantissa);

        tf_exponent.setBounds(445,80, 80, 25);
        frame.add(tf_exponent);

        button1 = new JButton("Convert");
        button1.setBounds(340,150,100,40);
        button2.setFocusCycleRoot(false);
        button1.addActionListener(this);
        frame.add(button1);

        button2 = new JButton("Write result to Notepad");
        button2.setBounds(350,340,190,40);
        button2.setFocusCycleRoot(false);
        button2.setEnabled(false);
        button2.addActionListener(this);
        frame.add(button2);

        button3 = new JButton("Copy");
        button3.setBounds(250,340,80,40);
        button3.setFocusCycleRoot(false);
        button3.setEnabled(false);
        button3.addActionListener(this);
        frame.add(button3);

        tf_mantissa.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
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
                    error_mantissa.setText("Enter only numeric digits(0-9), ' - ', ' . ' ");
                    error_exp.setText("");
                }
            }
        });

        tf_exponent.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
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
        
        str_mantissa = tf_mantissa.getText();

        str_exponent = tf_exponent.getText();

        count = 0;
        count2 = 0;
        count3 = 0;
        count4 = 0;
        count5 = 0;
        count6 = 0;

        for (int i = 0; i < str_mantissa.length(); i++) {
            if (str_mantissa.charAt(i) == '.') {
                count++;
            }
        }

        for (int i = 0; i < str_mantissa.length(); i++) {
            if (str_mantissa.charAt(i) >= '0' && str_mantissa.charAt(i) <= '9') {
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

        for (int i = 0; i < str_mantissa.length(); i++) {
            if (str_mantissa.charAt(i) != '0') {
                count6++;
            }
            if (str_mantissa.charAt(i) == '.'||str_mantissa.charAt(i) == '-'){
                count6 = count6 - 1;
            }
        }

        /*
        for (int i = 0; i < str_mantissa.length(); i++) {
            if (str_mantissa.charAt(i) >= '0' && str_mantissa.charAt(i) <= '9') {
                count6++;
            }
        }
        */



        // check for invalid mantissa inputs

        if (count > 1){
            tf_mantissa.setText(null);
            tf_exponent.setText(null);
            System.out.println("ERROR1");
            count = 0;

            frame_invalid();
        }

        else if (count2 == 0) {
            tf_mantissa.setText(null);
            tf_exponent.setText(null);
            System.out.println("ERROR2");
            count2 = 0;

            frame_invalid();
        }

        else if (count3 > 1){
            tf_mantissa.setText(null);
            tf_exponent.setText(null);
            System.out.println("ERROR3");
            count3 = 0;

            frame_invalid();
        }

        else if(count3==1 && str_mantissa.charAt(0)!='-'){
            tf_mantissa.setText(null);
            tf_exponent.setText(null);
            System.out.println("ERROR4");
            count3 = 0;

            frame_invalid();
        }

        // check for invalid exponent inputs

        else if(count4 == 0){
            tf_exponent.setText(null);
            tf_mantissa.setText(null);
            System.out.println("ERROR5");
            count4=0;

            frame_invalid();
        }

        else if (count5 > 1){
            tf_exponent.setText(null);
            tf_mantissa.setText(null);
            System.out.println("ERROR6");
            count5 = 0;

            frame_invalid();
        }

        else if(count5==1 && str_exponent.charAt(0)!='-'){
            tf_exponent.setText(null);
            tf_mantissa.setText(null);
            System.out.println("ERROR7");
            count5 = 0;

            frame_invalid();
        }


        // valid mantissa, handling 0s

        else if(count6==0){

            str_mantissa = "0";
            temp();
        }

        else if(count==1 && str_mantissa.charAt(0)=='.'){
            //count=0;

            System.out.println("after occ call "+remove_leadingzeroes(str_mantissa));
            str_mantissa = remove_leadingzeroes(str_mantissa);

            StringBuilder str_builder = new StringBuilder(str_mantissa);
            str_builder.insert(0, '0');

            str_mantissa = str_builder.toString();

            temp();

        }

        else if(count==1 && str_mantissa.charAt(str_mantissa.length()-1)=='.'){

            System.out.println("after occ call "+remove_leadingzeroes(str_mantissa));
            str_mantissa = remove_leadingzeroes(str_mantissa);

  
            StringBuilder str_builder2 = new StringBuilder();
            //StringBuilder str_builder2 = new StringBuilder(str_mantissa);
            //str_builder2.deleteCharAt(str_mantissa.length()-1);
            str_builder2.append(str_mantissa).append('0');
            str_mantissa = str_builder2.toString();

            
            temp();
        }

        else {

            System.out.println("after occ call "+ remove_leadingzeroes(str_mantissa));
            str_mantissa = remove_leadingzeroes(str_mantissa);

            temp();
            
        }

        System.out.println(str_mantissa);
        System.out.println(str_exponent);

        if(e.getSource()== button2){
            if(str_mantissa.length()!= 0 && str_exponent.length() != 0 ){
                WriteToFile write = new WriteToFile(str_binary, str_hex);
                System.out.println("FILE");
            }
            
        }

        if(e.getSource() == button3){

            if(str_mantissa.length()!= 0 && str_exponent.length() != 0 ){
                StringSelection selection = new StringSelection(str_binary + "\n" + str_hex);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);

                System.out.println("COPIED");
            }
            
        }
        
    }

    public void temp(){
            //int expInput = -5; // to be deleted
            //Boolean signMantissa = true; // to be deleted
            //int[] mantissa = {1, 0, 1, 1, 1, 0, 1, 0}; // to be deleted

            Initialization init = new Initialization(str_mantissa, str_exponent, 10);
            Converter cm = new Converter(init.getAns(), init.getExp(), init.getSign());
            
            System.out.println(cm.getBinaryOutput()); // to be deleted
            System.out.println(cm.getHexOutput()); // to be deleted

            out_binary.setText(cm.getBinaryOutput());
            out_hex.setText(cm.getHexOutput());

            str_binary = cm.getBinaryOutput();
            str_hex = cm.getHexOutput();

            button2.setEnabled(true);
            button3.setEnabled(true);
    }

    public String remove_leadingzeroes(String str){
            int i = 0; 
            int j = 0; 
            int k = 0;
            int n = 1;
            int m = 1;

            while (str.charAt(i) == '0') {
                i++; 
            }

            if(count3 == 1){

                while (str.charAt(m) == '0') {
                    m++; 
                }
    
                while (str.charAt(n) == '0') {
                    n++; 
                }
            }
                
            while (str.charAt(j) == '0' && str.charAt(j) != '.') {
                
                if(j < str.length()-1 ){
                   if(str.charAt(j+1) != '0'){
                        k++;
                    }     
                }

                j++;
            }

            // remove trailing 0s

            StringBuffer sb = new StringBuffer(str); 

            if (i > 0 && count == 0){
                sb.replace(0, i, ""); 
                System.out.println("VALID1");
            }

            else if(count == 1 && i > 0 && k == 0){
                sb.replace(1, i, ""); 
                System.out.println("VALID2");
            }

            else if (i > 0 && count == 1){
                sb.replace(0, i, ""); 
                System.out.println("VALID3");
            }

            else if (n > 1){
                sb.replace(1, m, ""); 
                System.out.println("VALID4");
            }


            return str = sb.toString();
    }

    public void frame_invalid(){
        JFrame frame_invalid = new JFrame();

        frame_invalid.pack();
        frame_invalid.setLocationRelativeTo(null);

        JLabel label_invalid = new JLabel("INVALID INPUT");
        label_invalid.setBounds(20,20,100,25);
        frame_invalid.add(label_invalid);

        frame_invalid.setSize(100, 100);
        frame_invalid.setVisible(true);

        button2.setEnabled(false);
        button3.setEnabled(false);

        out_binary.setText("");
        out_hex.setText("");
    }
}
