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
    private ArrayList<Alien> aliens;
    private boolean ingame;
    private final int ICRAFT_X = Main.WIDTH / 2 - 25;
    private final int ICRAFT_Y = Main.HEIGHT / 2 + 170;
    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 600;
    private final int DELAY = 10;
    private Timer timer;
    private Craft craft;

    private final int[][] pos = {
            {2380, 29}, {2500, 59}, {1380, 89},
            {780, 109}, {580, 139}, {680, 239},
            {790, 259}, {760, 50}, {790, 150},
            {980, 209}, {560, 45}, {510, 70},
            {930, 159}, {590, 80}, {530, 60},
            {940, 59}, {990, 30}, {920, 200},
            {900, 259}, {660, 50}, {540, 90},
            {810, 220}, {860, 20}, {740, 180},
            {820, 128}, {490, 170}, {700, 30}
    };

    public Board() {
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);
        initAliens();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initAliens(){
        aliens = new ArrayList<>();
        for (int[] p : pos){
            aliens.add(new Alien(p[0], p[1]));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //doDrawing(g);
        if (ingame) drawObjects(g);
        else drawGameOver(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /*private void doDrawing(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        ArrayList ms = craft.getMissiles();

        for (Object m1 : ms){
            Missile m = (Missile)m1;
            g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }
    }*/

    private void drawObjects(Graphics g){
        if (craft.isVisible()) g.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

        ArrayList<Missile> ms = craft.getMissiles();
        for (Missile m : ms) {
            if (m.isVisible()) g.drawImage(m.getImage(), m.getX(), m.getY(), this);
        }

        for (Alien a : aliens) {
            if (a.isVisible()) g.drawImage(a.getImage(), a.getX(), a.getY(), this);
        }

        g.setColor(Color.WHITE);
        g.drawString("Aliens left: " + aliens.size(), 5, 15);
    }

    private void drawGameOver(Graphics g){
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.WHITE);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateMissiles();
        updateCraft();
        updateAliens();
        checkCollisions();

        repaint();
    }

    private void inGame() {
        if (!ingame) timer.stop();
    }

    private void updateAliens(){
        if (aliens.isEmpty()) { ingame = false; return; }
        for (int i=0;i<aliens.size();i++) {
            Alien a = aliens.get(i);
            if (a.isVisible()) a.move();
            else aliens.remove(i);
        }
    }

    public void checkCollisions(){
        Rectangle r3 = craft.getBounds();
        for (Alien alien : aliens){
            Rectangle r2 = alien.getBounds();
            if (r3.intersects(r2)){
                craft.setVisible(false);
                alien.setVisible(false);
                ingame = false;
            }
        }

        ArrayList<Missile> ms = craft.getMissiles();
        for (Missile m : ms) {
            Rectangle r1 = m.getBounds();
            for (Alien alien : aliens) {
                Rectangle r2 = alien.getBounds();
                if (r1.intersects(r2));
                m.setVisible(false);
                alien.setVisible(false);
            }
        }
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