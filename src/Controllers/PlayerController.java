package Controllers;

import GameObjects.Bullet;
import GameObjects.Player;
import com.sq.*;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerController extends AnimationTimer implements EventHandler<InputEvent> {

    private Player player;
    private MoveDirection currentMoveDirection = MoveDirection.EAST;

    private boolean isShooting;
    private long timeOfLastProjectile = 0;

    private Sprite currentSprite;

    public PlayerController(Player playerObject) {
        this.player = playerObject;
        currentSprite = player.getSprites()[0];
        isShooting = false;
        DisplayManager.mainScene.addEventHandler(KeyEvent.KEY_PRESSED,this);
        start();
    }

    @Override
    public void handle(final InputEvent event) {
        if(event instanceof KeyEvent && player.isActive()){
            boolean moveLeft = ((KeyEvent)event).getCode() == KeyCode.A;
            boolean moveUp = ((KeyEvent)event).getCode() == KeyCode.W;
            boolean moveDown = ((KeyEvent)event).getCode() == KeyCode.S;
            boolean moveRight = ((KeyEvent)event).getCode() == KeyCode.D;

            boolean shootRight = ((KeyEvent)event).getCode() == KeyCode.RIGHT;
            boolean shootLeft = ((KeyEvent)event).getCode() == KeyCode.LEFT;
            boolean shootUp = ((KeyEvent)event).getCode() == KeyCode.UP;
            boolean shootDown = ((KeyEvent)event).getCode() == KeyCode.DOWN;

            float playerXCoordinate = player.getXCoordinate();
            float playerYCoordinate = player.getYCoordinate();

            if(moveLeft && moveUp){
                player.setXCoordinate(playerXCoordinate - player.getMovementSpeed());
                player.setYCoordinate(playerYCoordinate + player.getMovementSpeed());
                currentMoveDirection = MoveDirection.WEST;
                currentSprite = player.getSprites()[3];
            }
            else if(moveLeft && moveDown){
                player.setXCoordinate(playerXCoordinate - player.getMovementSpeed());
                player.setYCoordinate(playerYCoordinate - player.getMovementSpeed());
                currentMoveDirection = MoveDirection.WEST;
                currentSprite = player.getSprites()[3];
            }
            else if(moveRight && moveUp){
                player.setXCoordinate(playerXCoordinate + player.getMovementSpeed());
                player.setYCoordinate(playerYCoordinate + player.getMovementSpeed());
                currentMoveDirection = MoveDirection.EAST;
                currentSprite = player.getSprites()[0];
            }
            else if(moveRight && moveDown){
                player.setXCoordinate(playerXCoordinate + player.getMovementSpeed());
                player.setYCoordinate(playerYCoordinate - player.getMovementSpeed());
                currentMoveDirection = MoveDirection.EAST;
                currentSprite = player.getSprites()[0];
            }
            else if(moveLeft){
                player.setXCoordinate(playerXCoordinate - player.getMovementSpeed());
                currentMoveDirection = MoveDirection.WEST;
                currentSprite = player.getSprites()[3];
            }
            else if(moveRight){
                player.setXCoordinate(playerXCoordinate + player.getMovementSpeed());
                currentMoveDirection = MoveDirection.EAST;
                currentSprite = player.getSprites()[0];
            }
            else if(moveUp){
                player.setYCoordinate(playerYCoordinate - player.getMovementSpeed());
            }
            else if(moveDown){
                player.setYCoordinate(playerYCoordinate + player.getMovementSpeed());
            }

            if(shootRight){
                if(!isShooting) {
                    isShooting = true;
                    currentSprite = player.getSprites()[6];
                    new Bullet(playerXCoordinate,playerYCoordinate,Direction.RIGHT,player.getGameObjectManager(),player);
                }
            }
            else if(shootLeft){
                if(!isShooting){
                    isShooting = true;
                    currentSprite = player.getSprites()[7];
                    new Bullet(playerXCoordinate,playerYCoordinate,Direction.LEFT,player.getGameObjectManager(),player);
                }
            }
            else if(shootUp){
                if(!isShooting){
                    isShooting = true;
                    currentSprite = player.getSprites()[6];
                    new Bullet(playerXCoordinate,playerYCoordinate,Direction.UP,player.getGameObjectManager(),player);
                }

            }
            else if(shootDown){
                if(!isShooting){
                    isShooting = true;
                    currentSprite = player.getSprites()[6];
                    new Bullet(playerXCoordinate,playerYCoordinate,Direction.DOWN,player.getGameObjectManager(),player);
                }
            }

            delayTimeBetweenShots();

            player.setColliderBox(new Rectangle2D(player.getXCoordinate(), player.getYCoordinate(), player.getObjectWidth(), player.getObjectHeight()));
        }
    }

    private void delayTimeBetweenShots(){
        if(isShooting){
            long timeNow = System.currentTimeMillis();
            long time = timeNow - timeOfLastProjectile;
            if (time < 0 || time > 500) {
                timeOfLastProjectile = timeNow;
                isShooting = false;
            }
        }
    }

    @Override
    public void handle(long now) {
        if(player.isActive()){
            DisplayManager.graphicsContext.drawImage(currentSprite.getImage(),player.getXCoordinate(), player.getYCoordinate(),
                    currentSprite.getWidth(), currentSprite.getHeight());
        }
    }
}
