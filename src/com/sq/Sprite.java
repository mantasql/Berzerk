package com.sq;

import javafx.scene.image.Image;

public class Sprite {
    private String url;
    private Image image;
    private int width;
    private int height;

    public Sprite(String url,int width,int height) {
        this.url = url;
        this.image = new Image(url);
        this.width = width;
        this.height = height;
    }

    public void setImage(String url) {
        this.image = new Image(url);
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
