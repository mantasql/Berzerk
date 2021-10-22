package Controllers;

import GameObjects.Bullet;
import com.sq.Direction;
import com.sq.DisplayManager;
import com.sq.Sprite;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

public class BulletController {
    private int bulletSpeed;
    private Direction direction;
    private Bullet bullet;
    private Timeline timeline;

    private Sprite currentSprite;

    public BulletController(Bullet bullet) {
        this.bullet = bullet;
        bulletSpeed = bullet.getSpeed();
        direction = bullet.getTravelDirection();

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> move()));
        timeline.setCycleCount(500);
        timeline.play();
    }

    private void move() {
        if(direction == Direction.DOWN){
            currentSprite = bullet.getSprites()[1];
            bullet.setYCoordinate(bullet.getYCoordinate() + bulletSpeed);
        }
        else if(direction == Direction.UP){
            currentSprite = bullet.getSprites()[1];
            bullet.setYCoordinate(bullet.getYCoordinate() - bulletSpeed);
        }
        else if(direction == Direction.LEFT){
            currentSprite = bullet.getSprites()[0];
            bullet.setXCoordinate(bullet.getXCoordinate() - bulletSpeed);
        }
        else if(direction == Direction.RIGHT){
            currentSprite = bullet.getSprites()[0];
            bullet.setXCoordinate(bullet.getXCoordinate() + bulletSpeed);
        }

        bullet.setColliderBox(new Rectangle2D(bullet.getXCoordinate(),bullet.getYCoordinate(),bullet.getObjectWidth(),bullet.getObjectHeight()));
        display();
    }

    private void display(){
        if(bullet.isActive()){
            DisplayManager.graphicsContext.drawImage(currentSprite.getImage(),bullet.getXCoordinate(), bullet.getYCoordinate(),
                    currentSprite.getWidth(), currentSprite.getHeight());
        }
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
