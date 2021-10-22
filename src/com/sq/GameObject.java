package com.sq;

public abstract class GameObject {
    private float xCoordinate;
    private float yCoordinate;
    private int objectWidth;
    private int objectHeight;
    private Sprite[] sprites;
    private boolean isActive;
    private GameObjectManager gameObjectManager;
    private String tag;

    public GameObject(float xCoordinate,float yCoordinate, int objectWidth, int objectHeight,String tag, GameObjectManager gameObjectManager) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
        this.tag = tag;
        isActive = true;
        this.gameObjectManager = gameObjectManager;
    };

    public GameObject(){}

    public float getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(float xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public float getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(float yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }

    public void setPosition(float xCoordinate, float yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public String getTag() {
        return tag;
    }
}
