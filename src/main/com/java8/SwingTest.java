package com.java8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/****
 * Lambda表达式的基本结构
 *  (param1,param2,param3) -> {}
 */
public class SwingTest {


    public static void main(String[] args) {

        JFrame jFrame = new JFrame("My JFrame");
        JButton jButton = new JButton("My JButton");

        jButton.addActionListener(e->{
            System.out.println("Button Pressds");
            System.out.println("quyixiao");
        });
        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }



}
