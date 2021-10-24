package com.sq;

import java.util.ArrayList;
import java.util.Random;

public class Cell {
    private boolean wallBottom;
    private boolean wallRight;
    private ArrayList<PointObject> neighboringPillars;

    public Cell (ArrayList<PointObject> neighboringPillars, boolean wallBottom, boolean wallRight) {
        this.wallBottom = wallBottom;
        this.wallRight = wallRight;
        this.neighboringPillars = neighboringPillars;
        generateCell();
    }

    private void generateCell() {
        if(!wallBottom && !wallRight) {
            Random random = new Random();
            boolean randomBoolean = random.nextBoolean();
            if(randomBoolean) {
                wallRight = true;
            }
            else wallBottom = true;
        }
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

    public ArrayList<PointObject> getNeighboringPillars() {
        return neighboringPillars;
    }
}
