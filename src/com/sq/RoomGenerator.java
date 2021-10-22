package com.sq;

import GameObjects.Pillar;
import GameObjects.Player;
import GameObjects.Robot;
import GameObjects.Wall;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static com.sq.DisplayManager.scaling;

public class RoomGenerator {
    private ArrayList<Wall> mazeMiddlePoints;
    private ArrayList<Wall> boarders;
    private ArrayList<Wall> walls;
    private Cell[][] cells;
    private int wallThickness = 4*scaling;
    private GameObjectManager gameObjectManager;

    public RoomGenerator(GameObjectManager gameObjectManager) {
        cells = new Cell[5][3];
        boarders = new ArrayList<Wall>();
        mazeMiddlePoints = new ArrayList<Wall>();
        walls = new ArrayList<Wall>();
        this.gameObjectManager = gameObjectManager;
        generateBoarders();
        placePlayerObject();
    }

    private void generateBoarders() {
        Wall wallUpperLeft0 = new Wall(new Pillar(4*scaling,0),new Pillar(104*scaling,0),wallThickness,gameObjectManager);
        Wall wallUpperLeft1 = new Wall(new Pillar(4*scaling,0),new Pillar(4*scaling,72*scaling),wallThickness,gameObjectManager);
        Wall wallUpperRight0 = new Wall(new Pillar(151*scaling,0),new Pillar(248*scaling,0),wallThickness,gameObjectManager);
        Wall wallUpperRight1 = new Wall(new Pillar(248*scaling,0),new Pillar(248*scaling,72*scaling),wallThickness,gameObjectManager);
        Wall wallBottomLeft0 = new Wall(new Pillar(4*scaling,132*scaling),new Pillar(4*scaling,204*scaling),wallThickness,gameObjectManager);
        Wall wallBottomLeft1 = new Wall(new Pillar(4*scaling,204*scaling),new Pillar(104*scaling,204*scaling),wallThickness,gameObjectManager);
        Wall wallBottomRight0 = new Wall(new Pillar(151*scaling,204*scaling),new Pillar(252*scaling,204*scaling),wallThickness,gameObjectManager);
        Wall wallBottomRight1 = new Wall(new Pillar(248*scaling,135*scaling),new Pillar(248*scaling,208*scaling),wallThickness,gameObjectManager);

        boarders.add(wallUpperLeft0);
        boarders.add(wallUpperLeft1);
        boarders.add(wallUpperRight0);
        boarders.add(wallUpperRight1);
        boarders.add(wallBottomLeft0);
        boarders.add(wallBottomLeft1);
        boarders.add(wallBottomRight0);
        boarders.add(wallBottomRight1);
    }

    private void placePlayerObject(){
        new Player(gameObjectManager);

        new Robot(12*scaling,12*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        new Robot(64*scaling,12*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        new Robot(160*scaling,12*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        new Robot(206*scaling,12*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(64*scaling,80*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(112*scaling,80*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(158*scaling,80*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(12*scaling,150*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(64*scaling,150*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(160*scaling,150*scaling, gameObjectManager.getPlayer(), gameObjectManager);
        //new Robot(206*scaling,150*scaling, gameObjectManager.getPlayer(), gameObjectManager);
    }

    private void generateMazeMiddlePoints() {
    }

    public void drawBoarders(){
        DisplayManager.graphicsContext.setFill(Color.rgb(0,0,108));
        for(Wall wall : boarders) {
            DisplayManager.graphicsContext.fillRect(wall.getXCoordinate(),wall.getYCoordinate(),wall.getObjectWidth(),wall.getObjectHeight());
        }

        //DisplayManager.graphicsContext.fillRect(248*scaling,204*scaling,10,10);
        //DisplayManager.graphicsContext.fillRect(248*scaling,135*scaling,10,10);
    }

    /*private void generateBoarders() {
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
        Pillar wallPoint0 = new Pillar(4*scaling,56*scaling,68*scaling);
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
    }*/

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

    /*public void drawMazePoints() {
        DisplayManager.graphicsContext.setFill(Color.rgb(0,0,108));
        for(int i=0;i<8;i++) {
            DisplayManager.graphicsContext.fillRect(getMazePoints().get(i).getX(),getMazePoints().get(i).getY(),12,12);
        }
    }

    */
    public void fillBackground(Color color) {
        DisplayManager.graphicsContext.setFill(color);
        DisplayManager.graphicsContext.fillRect(0,0,DisplayManager.WIDTH*scaling,DisplayManager.HEIGHT*scaling);
    }
    /*

    public void drawBoarders() {
        DisplayManager.graphicsContext.setStroke(Color.rgb(0,0,108));
        DisplayManager.graphicsContext.setLineWidth(getBoarders().get(0).getThickness());

        for(int i=0;i<12;i+=3) {
            DisplayManager.graphicsContext.strokeLine(getBoarders().get(i).getX(),getBoarders().get(i).getY(),
                    getBoarders().get(i+1).getX(),getBoarders().get(i+1).getY());

            DisplayManager.graphicsContext.strokeLine(getBoarders().get(i).getX(),getBoarders().get(i).getY(),
                    getBoarders().get(i+2).getX(),getBoarders().get(i+2).getY());
        }

    }*/
}
