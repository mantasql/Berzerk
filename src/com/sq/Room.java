package com.sq;

import GameObjects.Wall;

public class Room extends GameObject {

    private Wall[] edgeWalls = new Wall[8];
    private Wall[][] mazeWalls = new Wall[4][2];
    private Wall doorWall = new Wall(4*DisplayManager.scaling,0,0);
    private Cell[][] cells = new Cell[5][3];


    public enum Cell
    {
        Left(1),
        Right(2),
        Top(4),
        Bottom(8);

        private final int flag;
        Cell(int flag){
            this.flag = flag;
        }
    }


    public Room(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate,10,10,false,false);
        for(int column = 0;column<4;column++) {
            for(int row = 0;row<2;row++) {
                mazeWalls[column][row] = new Wall(4*DisplayManager.scaling,0,0);
            }
        }
    }
}
