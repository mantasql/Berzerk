package com.sq;

import GameObjects.Wall;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private boolean wallBottom;
    private boolean wallRight;
    private Wall middlePoint;
    private ArrayList<Wall> neighboringPillars;

    public Cell () {
        wallBottom = false;
        wallRight = false;
    }

    private void generateCells() {
        if(wallBottom == false && wallRight == false) {
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            if(randomBoolean) {
                wallRight = true;
            }
            else wallBottom = true;
        }
    }

    private void draw() {

    }

    public boolean isWallBottom() {
        return wallBottom;
    }

    public void setWallBottom(boolean wallBottom) {
        this.wallBottom = wallBottom;
    }

    public boolean isWallRight() {
        return wallRight;
    }

    public void setWallRight(boolean wallRight) {
        this.wallRight = wallRight;
    }

    public ArrayList<Wall> getNeighboringPillars() {
        return neighboringPillars;
    }

    public void setNeighboringPillars(ArrayList<Wall> neighboringPillars) {
        this.neighboringPillars = neighboringPillars;
    }
}
