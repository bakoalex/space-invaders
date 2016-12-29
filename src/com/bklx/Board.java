package com.bklx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by bakoa on 12/29/2016.
 */
public class Board extends JPanel implements ActionListener {
    private final int ICRAFT_X = Main.WIDTH / 2 - 25;
    private final int ICRAFT_Y = Main.HEIGHT / 2 + 170;
    private final int DELAY = 10;
    private Timer timer;
    private Craft craft;

    public Board() {
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        craft = new Craft(ICRAFT_X, ICRAFT_Y);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        ArrayList ms = craft.getMissiles();

        for (Object m1 : ms){
            Missile m = (Missile)m1;
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateMissiles();
        updateCraft();
        repaint();
    }

    private void updateMissiles(){
        ArrayList ms = craft.getMissiles();

        for (int i=0;i<ms.size(); i++){
            Missile m = (Missile) ms.get(i);
            if (m.isVisible()) m.move();
            else ms.remove(i);
        }
    }

    private void updateCraft(){
        craft.move();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}