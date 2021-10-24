package GameObjects;

import Controllers.BulletController;
import com.sq.*;
import javafx.geometry.Rectangle2D;

import static com.sq.DisplayManager.scaling;

public class Bullet extends GameObject implements IDestroyable {
    private int speed = 3;
    private Direction travelDirection;
    private BulletController bulletController;
    private GameObject parentGameObject;

    public Bullet(float xCoordinate, float yCoordinate, Direction direction, GameObjectManager gameObjectManager,GameObject parentGameObject) {
        super(xCoordinate, yCoordinate, 5*scaling, scaling, parentGameObject.getTag(), gameObjectManager);
        this.parentGameObject = parentGameObject;
        if(parentGameObject.getTag().equals("Player")){
            gameObjectManager.addPlayerBullets(this);
        } else {
            gameObjectManager.addEnemyBullets(this);
        }

        this.travelDirection = direction;
        this.setBoxCollider(new Rectangle2D(xCoordinate,yCoordinate,5*scaling,5*scaling));
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

    @Override
    public void destroy() {
        this.setActive(false);
        bulletController.stop();
        if(parentGameObject.getTag().equals("Player")){
            getGameObjectManager().removePlayerBullets(this);
        }else{
            getGameObjectManager().removeEnemyBullets(this);
        }
    }

    public GameObject getParentGameObject() {
        return parentGameObject;
    }

    public BulletController getBulletController() {
        return bulletController;
    }
}
