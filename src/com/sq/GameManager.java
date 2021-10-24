package com.sq;

import javafx.application.Platform;
import javafx.scene.input.KeyCode;

public class GameManager {

    private boolean gameOver = false;
    private RoomGenerator roomGenerator;
    private GameObjectManager gameObjectManager;

    public GameManager() {
        gameObjectManager = new GameObjectManager();
        roomGenerator = new RoomGenerator(gameObjectManager);
    }

    public void runGame(){
        checkGameCondition();
        if(gameOver){
            waitToResumeOrQuit();
        }
        gameObjectManager.managePlayerCollisions();
        gameObjectManager.manageEnemyCollisions();
        gameObjectManager.manageBulletCollisions();
        generateNewRoom();
    }

    private void generateNewRoom() {
        Direction pathwayDirection = gameObjectManager.returnPathwayDirectionIfCollided();
        if(pathwayDirection != null){
            roomGenerator.generateNextLevel(pathwayDirection);
        }
    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    private void checkGameCondition(){
        if(gameObjectManager.getPlayer() == null){
            gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    private void restart(){
        gameOver = false;
        gameObjectManager = new GameObjectManager();
        roomGenerator = new RoomGenerator(gameObjectManager);
    }

    private void waitToResumeOrQuit(){
        DisplayManager.mainScene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER) {
                restart();
            } else if (event.getCode() == KeyCode.Q){
                quit();
            }
        });
    }

    private void quit(){
        Platform.exit();
    }
}
