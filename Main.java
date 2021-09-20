//Nolan Bruner
//Exercise 2
//09/8/2021

package com.company;
import javax.swing.*;
import javax.swing.border.Border;
//import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//
public class Main {
    // Border extension from overstack via the user Lalchand and Perer Mortensen
    // from https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
    //start
    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    // end extension of Border called RoundedBorder


    // begin main
    public static void main(String[] args) {
        JFrame f = new JFrame("Button Example");
        JButton buttons[][];
        JButton searchBar[];
        //how to add an image to a button
        // Icon backpace = new ImageIcon("backspace.jpg");

        //textbox for text message
        final JTextField tf = new JTextField();
        tf.setText("Type a text message...");
        tf.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        // array of qwerty ordered alphabet
        String alphabet[][] = {{"q","w","e","r","t","y","u","i","o","p"},{"a","s","d","f","g","h","j","k","l"},{"^","z","x","c","v","b","n","m","<"},{"123","😀", "🎤","Space","Return" }};
        // array of JButtons
        buttons = new JButton[4][10];
        searchBar = new JButton[3];

        // search bar buttons and a textbox
        searchBar[0] = new JButton("+");
        searchBar[0].setBounds(0,0,50,25);
        searchBar[0].setForeground(Color.blue);
        searchBar[0].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        searchBar[0].setContentAreaFilled(false);
        f.add(searchBar[0]);
        tf.setBounds(50,0,375,25);
        f.add(tf);
        searchBar[2] = new JButton("Send");
        searchBar[2].setBounds(425,0,100,25);
        searchBar[2].setBorder(new RoundedBorder(20));
        searchBar[2].setBackground(Color.BLUE);
        searchBar[2].setForeground(Color.white);

        f.add(searchBar[2]);
        f.setSize(800,600);
  
       //Action handler for send
        searchBar[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tf.getText());
            }
        });

        // Populate keyboard
        for(int i = 0; i< alphabet.length;i++) {
            //   System.out.println(alphabet.length);
            //   System.out.println(alphabet[i].length);
            // add keys and spacing
            for (int j = 0; j < alphabet[i].length;j++) {
                if(i == 1){
                    buttons[i][j] = new JButton(alphabet[i][j]);
                    buttons[i][j].setBounds(25+j*50,25+i*25,50,20);
                    buttons[i][j].setForeground(Color.blue);
                    //setBackground(Color.);
                    //setFocusPainted(false);
                    buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].setBorder(new RoundedBorder(20));
                    //setBorderPainted(false);
                    //   .setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

                }
                else if(i ==2){
                    buttons[i][j] = new JButton(alphabet[i][j]);
                    buttons[i][j].setBounds(j*56,25+i*25,55,20);
                    buttons[i][j].setForeground(Color.blue);
                    buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].setBorder(new RoundedBorder(20));
                }

                else {
                    buttons[i][j] = new JButton(alphabet[i][j]);
                    buttons[i][j].setBounds(j * 50, 27 + i * 25, 50, 20);
                    buttons[i][j].setForeground(Color.blue);
                    buttons[i][j].setContentAreaFilled(false);
                    buttons[i][j].setBorder(new RoundedBorder(15));
                }
                f.add(buttons[i][j]);
            }
        }
        // custom spacing for bottom row
        buttons[3][0].setBounds(0,100,55,20);
        buttons[3][1].setBounds(55,100,55,20);
        buttons[3][2].setBounds(110,100,55,20);
        buttons[3][3].setBounds(165,100,250,20);
        buttons[3][4].setBounds(415,100,100,20);
        // action listener

        // Add event handlers to every button
        for(int i = 0; i< alphabet.length; i++){
            for(int j = 0; j<alphabet[i].length;j++){

                //if backspace
                if(i == 2 && j ==8){
                    int finalI = i;
                    int finalJ = j;
                    buttons[finalI][finalJ].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String oldstr =  (tf.getText().substring(0,tf.getText().length()-1));
                            tf.setText(oldstr);
                            // (tf.getText().substring(0,tf.getText().length()-1));

                        }
                    });
                }
                //if space
                else if(i == 3 && j ==3){
                    int finalI = i;
                    int finalJ = j;
                    buttons[finalI][finalJ].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(tf.getText().contains("Type a text message...")) {
                                tf.setText("");
                            }
                            tf.setText(tf.getText()+ " ");


                        }
                    });
                }
                // if emoji button
                else if(i == 3 && j ==1){
                    int finalI = i;
                    int finalJ = j;
                    buttons[finalI][finalJ].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(tf.getText().contains("Type a text message..."))
                                tf.setText("");
                            tf.setText(tf.getText()+ ":-)");


                        }
                    });
                }
                // else a letter key was pressed
                else {
                    int finalI = i;
                    int finalJ = j;
                    buttons[finalI][finalJ].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(tf.getText().contains("Type a text message..."))
                                tf.setText("");
                            //textbox = prvious + new character
                            tf.setText(tf.getText() + alphabet[finalI][finalJ]);
                        }
                    });
                }
            }
        }

        f.setLayout(null);
        f.setVisible(true);
    }
}
