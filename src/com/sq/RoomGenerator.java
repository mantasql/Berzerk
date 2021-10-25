package com.sq;

import GameObjects.*;
import Managers.GameObjectManager;

import java.util.ArrayList;
import java.util.Random;

import static Managers.DisplayManager.scaling;

public class RoomGenerator {
    private ArrayList<Wall> boarders;
    private ArrayList<Pathway> pathways;
    private PointObject[] robotSpawnLocations;
    private int wallThickness = 4*scaling;
    private final GameObjectManager gameObjectManager;

    public RoomGenerator(GameObjectManager gameObjectManager) {
        boarders = new ArrayList<>();
        pathways = new ArrayList<>();
        robotSpawnLocations = new PointObject[11];
        this.gameObjectManager = gameObjectManager;
        firstTimeSetup();
    }

    private void firstTimeSetup(){
        setupRobotSpawnLocations();
        generateBoarders();
        generatePathways();
        placePlayerObject();
        spawnRobotsAtRandomPositions();
    }

    private void generateBoarders() {
        Wall wallUpperLeft0 = new Wall(new PointObject(4*scaling,0),new PointObject(104*scaling,0),wallThickness,gameObjectManager);
        Wall wallUpperLeft1 = new Wall(new PointObject(4*scaling,0),new PointObject(4*scaling,72*scaling),wallThickness,gameObjectManager);
        Wall wallUpperRight0 = new Wall(new PointObject(151*scaling,0),new PointObject(248*scaling,0),wallThickness,gameObjectManager);
        Wall wallUpperRight1 = new Wall(new PointObject(248*scaling,0),new PointObject(248*scaling,72*scaling),wallThickness,gameObjectManager);
        Wall wallBottomLeft0 = new Wall(new PointObject(4*scaling,135*scaling),new PointObject(4*scaling,207*scaling),wallThickness,gameObjectManager);
        Wall wallBottomLeft1 = new Wall(new PointObject(4*scaling,204*scaling),new PointObject(104*scaling,204*scaling),wallThickness,gameObjectManager);
        Wall wallBottomRight0 = new Wall(new PointObject(151*scaling,204*scaling),new PointObject(252*scaling,204*scaling),wallThickness,gameObjectManager);
        Wall wallBottomRight1 = new Wall(new PointObject(248*scaling,135*scaling),new PointObject(248*scaling,208*scaling),wallThickness,gameObjectManager);

        boarders.add(wallUpperLeft0);
        boarders.add(wallUpperLeft1);
        boarders.add(wallUpperRight0);
        boarders.add(wallUpperRight1);
        boarders.add(wallBottomLeft0);
        boarders.add(wallBottomLeft1);
        boarders.add(wallBottomRight0);
        boarders.add(wallBottomRight1);
    }

    private void generatePathways() {
        Pathway pathway0 = new Pathway(Direction.UP,new PointObject(104*scaling,0),new PointObject(151*scaling,3*scaling),wallThickness,gameObjectManager);
        Pathway pathway1 = new Pathway(Direction.RIGHT,new PointObject(248*scaling,72*scaling),new PointObject(251*scaling,135*scaling),wallThickness,gameObjectManager);
        Pathway pathway2 = new Pathway(Direction.DOWN,new PointObject(104*scaling,204*scaling),new PointObject(151*scaling,207*scaling),wallThickness,gameObjectManager);
        Pathway pathway3 = new Pathway(Direction.LEFT,new PointObject(4*scaling,72*scaling),new PointObject(7*scaling,135*scaling),wallThickness,gameObjectManager);

        pathways.add(pathway0);
        pathways.add(pathway1);
        pathways.add(pathway2);
        pathways.add(pathway3);
    }

    private void placePlayerObject(){
        new Player(gameObjectManager);
    }

    private void setupRobotSpawnLocations(){
        robotSpawnLocations[0] = new PointObject(12*scaling,12*scaling);
        robotSpawnLocations[1] = new PointObject(64*scaling,12*scaling);
        robotSpawnLocations[2] = new PointObject(160*scaling,12*scaling);
        robotSpawnLocations[3] = new PointObject(206*scaling,12*scaling);
        robotSpawnLocations[4] = new PointObject(64*scaling,80*scaling);
        robotSpawnLocations[5] = new PointObject(112*scaling,80*scaling);
        robotSpawnLocations[6] = new PointObject(158*scaling,80*scaling);
        robotSpawnLocations[7] = new PointObject(12*scaling,150*scaling);
        robotSpawnLocations[8] = new PointObject(64*scaling,150*scaling);
        robotSpawnLocations[9] = new PointObject(160*scaling,150*scaling);
        robotSpawnLocations[10] = new PointObject(206*scaling,150*scaling);
    }

    private void spawnRobotsAtRandomPositions(){
        Random random = new Random();
        int numberOfRobots = random.nextInt(11);
        System.out.println("Number of robots spawned: "+ numberOfRobots);
        ArrayList<Integer> spawnedPositions = new ArrayList<>();
        for(int i = 0; i < numberOfRobots; i++){
            Integer randomSpawnLocation = random.nextInt(11);
            if(spawnedPositions.stream().noneMatch(e -> e.equals(randomSpawnLocation)))
            {
                spawnedPositions.add(randomSpawnLocation);
                new Robot((int)robotSpawnLocations[randomSpawnLocation].getXCoordinate(),(int)robotSpawnLocations[randomSpawnLocation].getYCoordinate(), gameObjectManager.getPlayer(), gameObjectManager);
            } else {
                i--;
            }
        }
    }

    public void generateNextLevel(Direction previousRoomDirection){
        destroyAllEntities();
        spawnRobotsAtRandomPositions();
        placePlayerAtCorrectPosition(previousRoomDirection);
        closeDoorAtCorrectPosition(previousRoomDirection);
    }

    private void placePlayerAtCorrectPosition(Direction previousRoomDirection){
        switch (previousRoomDirection){
            case UP -> gameObjectManager.getPlayer().setPosition(120*scaling,180*scaling);
            case DOWN -> gameObjectManager.getPlayer().setPosition(120*scaling,20*scaling);
            case LEFT -> gameObjectManager.getPlayer().setPosition(230*scaling,95*scaling);
            case RIGHT -> gameObjectManager.getPlayer().setPosition(30*scaling,95*scaling);
        }
    }
    private void closeDoorAtCorrectPosition(Direction doorDirection){
        gameObjectManager.getPathways().stream().filter(Pathway::isHasDoor).findFirst().ifPresent(Pathway::openDoor);
        switch (doorDirection){
            case UP -> gameObjectManager.getPathways().stream().filter(c -> c.getDirection() == Direction.DOWN).findFirst().ifPresent(Pathway::closeDoor);
            case DOWN -> gameObjectManager.getPathways().stream().filter(c -> c.getDirection() == Direction.UP).findFirst().ifPresent(Pathway::closeDoor);
            case RIGHT -> gameObjectManager.getPathways().stream().filter(c -> c.getDirection() == Direction.LEFT).findFirst().ifPresent(Pathway::closeDoor);
            case LEFT -> gameObjectManager.getPathways().stream().filter(c -> c.getDirection() == Direction.RIGHT).findFirst().ifPresent(Pathway::closeDoor);
        }
    }

    private void destroyAllEntities(){
        for(int i=0;i<gameObjectManager.getRobots().size();i++){
            gameObjectManager.getRobots().get(i).destroy();
            i--;
        }

        for(int i=0;i<gameObjectManager.getAllBullets().size();i++){
            gameObjectManager.getAllBullets().get(i).destroy();
            i--;
        }
    }
}
