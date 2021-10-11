package com.sq;
import GameObjects.Player;
import GameObjects.Wall;

import java.util.ArrayList;

import static com.sq.DisplayManager.scaling;

public class RoomGenerator {
    private ArrayList<Wall> mazeMiddlePoints;
    private ArrayList<Wall> boarders;
    private ArrayList<Wall> walls;
    private Cell[][] cells;
    private Player player;

    public RoomGenerator() {
        //playerController = new PlayerController();
        cells = new Cell[5][3];
        boarders = new ArrayList<Wall>();
        mazeMiddlePoints = new ArrayList<Wall>();
        walls = new ArrayList<Wall>();
        generateBoarders();
        generateMazeMiddlePoints();
        player = new Player();
    }

    private void generateBoarders() {
        Wall wall0 = new Wall(4*scaling,104*scaling,0);
        Wall wall1 = new Wall(4*scaling,151*scaling,0);
        Wall wall2 = new Wall(4*scaling,4*scaling,72*scaling);
        Wall wall3 = new Wall(4*scaling,4*scaling,132*scaling);
        Wall wall4 = new Wall(4*scaling,104*scaling,204*scaling);
        Wall wall5 = new Wall(4*scaling,151*scaling,204*scaling);
        Wall wall6 = new Wall(4*scaling,248*scaling,72*scaling);
        Wall wall7 = new Wall(4*scaling,248*scaling,135*scaling);

        //Map corner points
        Wall upperLeftCornerPoint = new Wall(4*scaling,4*scaling,0);
        Wall bottomLeftCornerPoint = new Wall(4*scaling,4*scaling,204*scaling);
        Wall upperRightCornerPoint = new Wall(4*scaling,248*scaling,0);
        Wall bottomRightCornerPoint = new Wall(4*scaling,248*scaling,204*scaling);

        boarders.add(upperLeftCornerPoint);
        boarders.add(wall0);
        boarders.add(wall2);

        boarders.add(upperRightCornerPoint);
        boarders.add(wall1);
        boarders.add(wall6);

        boarders.add(bottomLeftCornerPoint);
        boarders.add(wall3);
        boarders.add(wall4);

        boarders.add(bottomRightCornerPoint);
        boarders.add(wall5);
        boarders.add(wall7);
    }

    private void generateMazeMiddlePoints() {
        Wall wallPoint0 = new Wall(4*scaling,56*scaling,68*scaling);
        Wall wallPoint1 = new Wall(4*scaling,104*scaling,68*scaling);
        Wall wallPoint2 = new Wall(4*scaling,152*scaling,68*scaling);
        Wall wallPoint3 = new Wall(4*scaling,200*scaling,68*scaling);
        Wall wallPoint4 = new Wall(4*scaling,56*scaling,136*scaling);
        Wall wallPoint5 = new Wall(4*scaling,104*scaling,136*scaling);
        Wall wallPoint6 = new Wall(4*scaling,152*scaling,136*scaling);
        Wall wallPoint7 = new Wall(4*scaling,200*scaling,136*scaling);

        mazeMiddlePoints.add(wallPoint0);
        mazeMiddlePoints.add(wallPoint1);
        mazeMiddlePoints.add(wallPoint2);
        mazeMiddlePoints.add(wallPoint3);
        mazeMiddlePoints.add(wallPoint4);
        mazeMiddlePoints.add(wallPoint5);
        mazeMiddlePoints.add(wallPoint6);
        mazeMiddlePoints.add(wallPoint7);
    }

    private void setupCells() {
        cells[4][0].setWallRight(true);
        cells[4][1].setWallRight(true);
        cells[4][2].setWallRight(true);
        cells[0][2].setWallBottom(true);
        cells[1][2].setWallBottom(true);
        cells[2][2].setWallBottom(true);
        cells[3][2].setWallBottom(true);
        cells[4][2].setWallBottom(true);

        for(int i=0;i<5;i++){
            for(int j=0;j<3;j++){
                //cell[i][j].setNeighboringPillars(new ArrayList<Wall>().add(boarders.get(0)));
            }
        }
    }

    public void generateMaze() {

    }

    public ArrayList<Wall> getBoarders() { return boarders; }
    public ArrayList<Wall> getMazePoints() { return mazeMiddlePoints; }
}
