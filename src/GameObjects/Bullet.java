package GameObjects;

import Controllers.BulletController;
import com.sq.*;
import javafx.geometry.Rectangle2D;

import static com.sq.DisplayManager.scaling;

public class Bullet extends GameObject implements IDestroyable {
    private int speed = 3;
    private Direction travelDirection;
    private BulletController bulletController;
    private Rectangle2D colliderBox;
    private GameObject parentGameObject;

    public Bullet(float xCoordinate, float yCoordinate, Direction direction, GameObjectManager gameObjectManager,GameObject parentGameObject) {
        super(xCoordinate, yCoordinate, 5*scaling, scaling, parentGameObject.getTag(), gameObjectManager);
        this.parentGameObject = parentGameObject;
        if(parentGameObject.getTag().equals("Player")){
            gameObjectManager.getPlayerBullets().add(this);
        } else {
            gameObjectManager.getEnemyBullets().add(this);
        }

        this.travelDirection = direction;
        this.colliderBox = new Rectangle2D(xCoordinate,yCoordinate,5*scaling,5*scaling);
        setObjectSprites();
        bulletController = new BulletController(this);
    }

    private void setObjectSprites() {
        Sprite[] sprites = new Sprite[2];
        sprites[0] = new Sprite("textures/ShootHorizontally.png",5*scaling,scaling);
        sprites[1] = new Sprite("textures/ShootVertically.png",scaling,5*scaling);
        setSprites(sprites);
    }

    public int getSpeed() {
        return speed;
    }

    public Direction getTravelDirection() {
        return travelDirection;
    }

    public Rectangle2D getColliderBox() {
        return colliderBox;
    }

    public void setColliderBox(Rectangle2D colliderBox) {
        this.colliderBox = colliderBox;
    }

    @Override
    public void destroy() {
        this.setActive(false);
        bulletController.getTimeline().stop();
        if(parentGameObject.getTag().equals("Player")){
            getGameObjectManager().getPlayerBullets().remove(this);
        }else{
            getGameObjectManager().getEnemyBullets().remove(this);
        }
    }
}
