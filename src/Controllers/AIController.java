package Controllers;

import GameObjects.Bullet;
import GameObjects.Robot;
import com.sq.*;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;

public class AIController extends AnimationTimer {
    private Robot robot;
    private GameObject target;
    private Sprite currentSprite;
    private boolean isShooting = false;
    private long timeOfLastProjectile = 0;

    public AIController(Robot robot,GameObject target) {
        this.robot = robot;
        currentSprite = robot.getSprites()[0];
        this.target= target;
        start();
    }


    private void move() {
        float newXVelocity = Math.signum(target.getXCoordinate() - robot.getXCoordinate());
        float newYVelocity = Math.signum(target.getYCoordinate() - robot.getYCoordinate());
        robot.setPosition(robot.getXCoordinate() + newXVelocity * robot.getMovementSpeed(), robot.getYCoordinate() + newYVelocity * robot.getMovementSpeed());
        robot.setBoxCollider(new Rectangle2D(robot.getXCoordinate(), robot.getYCoordinate(), robot.getObjectWidth(), robot.getObjectHeight()));
        //System.out.println("player: xCoordinate: "+target.getXCoordinate() + ": player yCoordinate: "+target.getYCoordinate());
        //System.out.println("robot: xCoordinate: "+robot.getXCoordinate() + ": robot yCoordinate: "+robot.getYCoordinate());
        //System.out.println("new: xCoordinate: "+newXVelocity + ": new yCoordinate: "+newYVelocity);
    }

    private void shoot(){
        Direction shootDirection = checkWhereToShoot();
        System.out.println("Player pos: "+ (int)target.getXCoordinate() +" "+ (int)target.getYCoordinate() + " Robot pos: "+(int)robot.getXCoordinate()+" "+(int)robot.getYCoordinate());
        if(shootDirection != null && !isShooting){
            isShooting = true;
            new Bullet(robot.getXCoordinate(), robot.getYCoordinate(), shootDirection, robot.getGameObjectManager(), robot);
        }
        delayTimeBetweenShots();
    }

    @Override
    public void handle(long now) {
        move();
        shoot();
        if(robot.isActive()){
            DisplayManager.graphicsContext.drawImage(currentSprite.getImage(),robot.getXCoordinate(), robot.getYCoordinate(),
                    currentSprite.getWidth(), currentSprite.getHeight());
        }
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
        if(isShooting){
            long timeNow = System.currentTimeMillis();
            long time = timeNow - timeOfLastProjectile;
            if (time < 0 || time > 1000) {
                timeOfLastProjectile = timeNow;
                isShooting = false;
            }
        }
    }
}
