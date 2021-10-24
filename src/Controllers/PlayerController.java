package Controllers;

import GameObjects.Bullet;
import GameObjects.Player;
import com.sq.Direction;
import com.sq.DisplayManager;
import com.sq.Sprite;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PlayerController implements EventHandler<InputEvent> {

    private Player player;

    private boolean isShooting;
    private long timeOfLastProjectile = 0;

    private Sprite currentSprite;

    public PlayerController(Player playerObject) {
        this.player = playerObject;
        currentSprite = player.getSprites()[0];
        isShooting = false;
        DisplayManager.mainScene.addEventHandler(KeyEvent.KEY_PRESSED,this);
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
            float speed = player.getMovementSpeed();

            if(moveLeft && moveUp){
                player.setPosition(playerXCoordinate - speed,playerYCoordinate + speed);
                currentSprite = player.getSprites()[3];
            }
            else if(moveLeft && moveDown){
                player.setPosition(playerXCoordinate - speed, playerYCoordinate - speed);
                currentSprite = player.getSprites()[3];
            }
            else if(moveRight && moveUp){
                player.setPosition(playerXCoordinate + speed, playerYCoordinate + speed);
                currentSprite = player.getSprites()[0];
            }
            else if(moveRight && moveDown){
                player.setPosition(playerXCoordinate + speed,playerYCoordinate - speed);
                currentSprite = player.getSprites()[0];
            }
            else if(moveLeft){
                player.setPosition(playerXCoordinate - speed,playerYCoordinate);
                currentSprite = player.getSprites()[3];
            }
            else if(moveRight){
                player.setPosition(playerXCoordinate + speed,playerYCoordinate);
                currentSprite = player.getSprites()[0];
            }
            else if(moveUp){
                player.setPosition(playerXCoordinate,playerYCoordinate - speed);
            }
            else if(moveDown){
                player.setPosition(playerXCoordinate,playerYCoordinate + speed);
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

    public Sprite getCurrentSprite() {
        return currentSprite;
    }
}
