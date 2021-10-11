package com.sq;

import GameObjects.Player;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerController extends CharacterController implements EventHandler<InputEvent> {

    private Player player;
    private boolean isMoving;
    private boolean isShooting;
    private MoveDirection currentMoveDirection = MoveDirection.EAST;
    //private AnimationManager animationManager;

    public PlayerController(Player playerObject) {
        this.player = playerObject;
        DisplayManager.mainScene.addEventHandler(KeyEvent.KEY_PRESSED,this);
        //this.animationManager = new AnimationManager(this);
    }

    @Override
    public void handle(final InputEvent event) {
        if(event instanceof KeyEvent){
            boolean moveLeft = ((KeyEvent)event).getCode() == KeyCode.A;
            boolean moveUp = ((KeyEvent)event).getCode() == KeyCode.W;
            boolean moveDown = ((KeyEvent)event).getCode() == KeyCode.S;
            boolean moveRight = ((KeyEvent)event).getCode() == KeyCode.D;

            boolean shootRight = ((KeyEvent)event).getCode() == KeyCode.RIGHT;
            boolean shootLeft = ((KeyEvent)event).getCode() == KeyCode.LEFT;
            boolean shootUp = ((KeyEvent)event).getCode() == KeyCode.UP;
            boolean shootDown = ((KeyEvent)event).getCode() == KeyCode.DOWN;

            isMoving = false;
            if(moveLeft && moveUp){
                player.xCoordinate-=1 * player.getMovementSpeed();
                player.yCoordinate+=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.WEST;
                isMoving = true;
            }
            else if(moveLeft && moveDown){
                player.xCoordinate-=1 * player.getMovementSpeed();
                player.yCoordinate-=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.WEST;
                isMoving = true;
            }
            else if(moveRight && moveUp){
                player.xCoordinate+=1 * player.getMovementSpeed();
                player.yCoordinate+=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.EAST;
                isMoving = true;
            }
            else if(moveRight && moveDown){
                player.xCoordinate+=1 * player.getMovementSpeed();
                player.yCoordinate-=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.EAST;
                isMoving = true;
            }
            else if(moveLeft){
                player.xCoordinate-=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.WEST;
                isMoving = true;
            }
            else if(moveRight){
                player.xCoordinate+=1 * player.getMovementSpeed();
                currentMoveDirection = MoveDirection.EAST;
                isMoving = true;
            }
            else if(moveUp){
                player.yCoordinate-=1 * player.getMovementSpeed();
                isMoving = true;
            }
            else if(moveDown){
                player.yCoordinate+=1 * player.getMovementSpeed();
                isMoving = true;
            };
        }
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public MoveDirection getCurrentMoveDirection() {
        return currentMoveDirection;
    }

    @Override
    public boolean isMoving() {
        return isMoving;
    }
}
