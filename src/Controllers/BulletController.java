package Controllers;

import GameObjects.Bullet;
import com.sq.Direction;
import com.sq.Sprite;
import javafx.animation.AnimationTimer;

public class BulletController extends AnimationTimer {
    private int bulletSpeed;
    private Direction direction;
    private Bullet bullet;

    private Sprite currentSprite;

    public BulletController(Bullet bullet) {
        this.bullet = bullet;
        bulletSpeed = bullet.getSpeed();
        direction = bullet.getTravelDirection();
        currentSprite = bullet.getSprites()[1];
        start();
    }

    private void move() {
        if(direction == Direction.DOWN){
            currentSprite = bullet.getSprites()[1];
            bullet.setPosition(bullet.getXCoordinate(),bullet.getYCoordinate() + bulletSpeed);
        }
        else if(direction == Direction.UP){
            currentSprite = bullet.getSprites()[1];
            bullet.setPosition(bullet.getXCoordinate(),bullet.getYCoordinate() - bulletSpeed);
        }
        else if(direction == Direction.LEFT){
            currentSprite = bullet.getSprites()[0];
            bullet.setPosition(bullet.getXCoordinate() - bulletSpeed,bullet.getYCoordinate());
        }
        else if(direction == Direction.RIGHT){
            currentSprite = bullet.getSprites()[0];
            bullet.setPosition(bullet.getXCoordinate() + bulletSpeed,bullet.getYCoordinate());
        }

    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }

    @Override
    public void handle(long now) {
        move();
    }
}
