package com.bklx;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by bakoa on 12/29/2016.
 */
public class Craft extends Sprite {
    private int dx;
    private int dy;
    private ArrayList missiles;

    public Craft(int x, int y){
        super(x, y);
        initCraft();
    }

    private void initCraft(){
        missiles = new ArrayList();
        loadImage("src/images/spacecraft.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y -= dy;
    }

    public ArrayList getMissiles(){
        return missiles;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) fire();
        if (key == KeyEvent.VK_UP) dy++;
        if (key == KeyEvent.VK_DOWN) dy--;
        if (key == KeyEvent.VK_LEFT)dx--;
        if (key == KeyEvent.VK_RIGHT) dx++;
    }

    public void fire(){
        missiles.add(new Missile(x + (this.width / 2) - 2, y));
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) dy = 0;
        if (key == KeyEvent.VK_DOWN) dy = 0;
        if (key == KeyEvent.VK_LEFT)dx = 0;
        if (key == KeyEvent.VK_RIGHT) dx = 0;
    }
}

