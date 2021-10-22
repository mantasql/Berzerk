package com.sq;

import GameObjects.Player;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

import static com.sq.DisplayManager.scaling;

public class GameManager {

    private boolean gameOver = false;
    private RoomGenerator roomGenerator;
    private GameObjectManager gameObjectManager;

    private static GameManager instance;

    private GameManager() {
        gameObjectManager = new GameObjectManager(null);
        roomGenerator = new RoomGenerator(gameObjectManager);
    }

    public static GameManager getInstance() {
        if(instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(){
        roomGenerator.fillBackground(Color.BLACK);
        roomGenerator.drawBoarders();
        if(gameObjectManager.didPlayerCollide()){
            onPlayerCollision();
        }
        gameObjectManager.destroyEnemyOnCollision();
        gameObjectManager.destroyBulletOnCollision();
    }

    private void onPlayerCollision() {
        int playerHealth = gameObjectManager.getPlayer().getHealth() - 1;
        Player player = gameObjectManager.getPlayer();
        if(playerHealth > 0) {
            gameObjectManager.getPlayer().setHealth(playerHealth);
            gameObjectManager.getPlayer().setPosition(65*scaling,83*scaling);
            player.setColliderBox(new Rectangle2D(player.getXCoordinate(), player.getYCoordinate(), player.getObjectWidth(), player.getObjectHeight()));
        } else {
            gameObjectManager.getPlayer().setActive(false);
            gameObjectManager.setPlayer(null);
            gameOver = true;
        }
        System.out.println(playerHealth);
    }

    private void onBulletCollision() {
        //gameObjectManager.getPlayerBullets().get()
    }

}
