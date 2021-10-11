package com.sq;

import javafx.geometry.Rectangle2D;

public class GameObject {
    protected int xCoordinate;
    protected int yCoordinate;
    protected int objectWidth;
    protected int objectHeight;
    protected boolean isSolid;
    protected boolean isDynamic;
    protected Sprite[] sprites;
    protected Rectangle2D collider;

    public GameObject(int xCoordinate,int yCoordinate, int objectWidth, int objectHeight, boolean isSolid, boolean isDynamic) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
        this.isSolid = isSolid;
        this.isDynamic = isDynamic;
    }

    public int getX() {
        return xCoordinate;
    }

    public int getY() {
        return yCoordinate;
    }

    protected void setupObjectSprites(){}

    protected void onCollision(){

    }

    public Sprite[] getSprites() {
        return sprites;
    }
}
