package com.bklx;

/**
 * Created by bakoa on 12/29/2016.
 */
public class Alien extends Sprite {
    public Alien(int x, int y) {
        super(x, y);
        initAlien();
    }

    private void initAlien(){
        loadImage("src/images/alien.png");
        getImageDimensions();
    }

    public void move(){
        if (y < -600) {
            y = 0;
        }

        y += 1;
    }
}
