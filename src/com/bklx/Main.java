package com.bklx;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Main(){
        initUI();
    }

    private void initUI(){
        add(new Board());
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Space Invaders ALPHA");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // write your code here
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main shootingMissiles = new Main();
                shootingMissiles.setVisible(true);
            }
        });
    }
}
