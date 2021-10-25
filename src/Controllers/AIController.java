package Controllers;

import GameObjects.Bullet;
import GameObjects.GameObject;
import GameObjects.Robot;
import com.sq.Direction;
import com.sq.Sprite;
import javafx.animation.AnimationTimer;

public class AIController extends AnimationTimer {
    private Robot robot;
    private GameObject target;
    private Sprite currentSprite;
    private boolean isShooting = false;
    private long timeOfLastProjectile = 0;
    private int fireRate = 5000;

    public AIController(Robot robot,GameObject target) {
        this.robot = robot;
        currentSprite = robot.getSprites()[0];
        this.target = target;
        start();
    }


    private void move() {
        float newXVelocity = Math.signum(target.getXCoordinate() - robot.getXCoordinate());
        float newYVelocity = Math.signum(target.getYCoordinate() - robot.getYCoordinate());
        robot.setPosition(robot.getXCoordinate() + newXVelocity * robot.getMovementSpeed(), robot.getYCoordinate() + newYVelocity * robot.getMovementSpeed());
    }

    private void shoot(){
        delayTimeBetweenShots();
        Direction shootDirection = checkWhereToShoot();
        if(shootDirection != null && !isShooting){
            isShooting = true;
            new Bullet(robot.getXCoordinate(), robot.getYCoordinate(), shootDirection, robot.getGameObjectManager(), robot);
        }
    }

    @Override
    public void handle(long now) {
        move();
        shoot();
    }

    private Direction checkWhereToShoot(){
        if((int)target.getYCoordinate() == (int)robot.getYCoordinate()){
            if((int)target.getXCoordinate() < (int)robot.getXCoordinate())
                return Direction.LEFT;
            else return Direction.RIGHT;
        }
        else if((int)target.getXCoordinate() == (int)robot.getXCoordinate()){
            if((int)target.getYCoordinate() < (int)robot.getYCoordinate())
                return Direction.UP;
            else return Direction.DOWN;
        }
        return null;
    }

    private void delayTimeBetweenShots(){
            long timeNow = System.currentTimeMillis();
            long time = timeNow - timeOfLastProjectile;
            if (time < 0 || time > fireRate) {
                timeOfLastProjectile = timeNow;
                isShooting = false;
        }
    }

    public Sprite getCurrentSprite() {
        return currentSprite;
    }
}
