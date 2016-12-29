package com.bklx;

/**
 * Created by bakoa on 12/29/2016.
 */
public class Missile extends Sprite {
    private final int BOARD_WIDTH = 800;
    private final int MISSILE_SPEED = 5;

    public Missile(int x, int y){
        super(x, y);
        initMissile();
    }

    private void initMissile(){
        loadImage("src/images/missile.png");
        getImageDimensions();
    }

    public void move(){
        y -= MISSILE_SPEED;
        if (y < 0) visible = false;
    }

}