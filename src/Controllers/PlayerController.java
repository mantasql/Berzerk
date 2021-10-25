package Controllers;

import Commands.PlayerControlls.PlayerCommandFactory;
import GameObjects.Bullet;
import GameObjects.Player;
import com.sq.Direction;
import Managers.DisplayManager;
import com.sq.Sprite;
import javafx.event.EventHandler;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;

public class PlayerController implements EventHandler<InputEvent> {

    private Player player;

    private boolean isShooting;
    private long timeOfLastProjectile = 0;
    private int fireRate = 500;

    private Sprite currentSprite;

    public PlayerController(Player playerObject) {
        this.player = playerObject;
        isShooting = false;
        currentSprite = player.getSprites()[0];
        DisplayManager.mainScene.addEventHandler(KeyEvent.KEY_PRESSED,this);
    }

    public void moveLeft(){
        player.setPosition(player.getXCoordinate() - player.getMovementSpeed(),player.getYCoordinate());
        currentSprite = player.getSprites()[3];
    }

    public void moveRight(){
        player.setPosition(player.getXCoordinate() + player.getMovementSpeed(), player.getYCoordinate());
        currentSprite = player.getSprites()[0];
    }

    public void moveUp(){
        player.setPosition(player.getXCoordinate(), player.getYCoordinate() - player.getMovementSpeed());
    }

    public void moveDown(){
        player.setPosition(player.getXCoordinate(), player.getYCoordinate() + player.getMovementSpeed());
    }

    public void shootLeft(){
        if(!isShooting){
            isShooting = true;
            currentSprite = player.getSprites()[7];
            new Bullet(player.getXCoordinate(), player.getYCoordinate(), Direction.LEFT,player.getGameObjectManager(),player);
        }
    }

    public void shootRight(){
        if(!isShooting) {
            isShooting = true;
            currentSprite = player.getSprites()[6];
            new Bullet(player.getXCoordinate(), player.getYCoordinate(), Direction.RIGHT,player.getGameObjectManager(),player);
        }
    }

    public void shootUp(){
        if(!isShooting){
            isShooting = true;
            currentSprite = player.getSprites()[6];
            new Bullet(player.getXCoordinate(), player.getYCoordinate(), Direction.UP,player.getGameObjectManager(),player);
        }
    }

    public void shootDown(){
        if(!isShooting){
            isShooting = true;
            currentSprite = player.getSprites()[6];
            new Bullet(player.getXCoordinate(), player.getYCoordinate(), Direction.DOWN,player.getGameObjectManager(),player);
        }
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    @Override
    public void handle(InputEvent event) {
        delayTimeBetweenShots();
        PlayerCommandFactory.getCommand(event,this).execute();
    }

    private void delayTimeBetweenShots(){
        if(isShooting){
            long timeNow = System.currentTimeMillis();
            long time = timeNow - timeOfLastProjectile;
            if (time < 0 || time > fireRate) {
                timeOfLastProjectile = timeNow;
                isShooting = false;
            }
        }
    }
}
