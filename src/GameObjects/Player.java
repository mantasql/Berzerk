package GameObjects;

import com.sq.GameObject;
import Controllers.PlayerController;
import com.sq.GameObjectManager;
import com.sq.Sprite;
import javafx.geometry.Rectangle2D;

import static com.sq.DisplayManager.scaling;

public class Player extends GameObject {
    private int health = 3;
    private int movementSpeed = 10;
    private PlayerController playerController;
    private Rectangle2D colliderBox;

    public Player(GameObjectManager gameObjectManager) {
        super(49*scaling, 83*scaling,8*scaling,12*scaling,"Player",gameObjectManager);
        gameObjectManager.setPlayer(this);
        setObjectSprites();
        colliderBox = new Rectangle2D(getXCoordinate(),getYCoordinate(),getObjectWidth(),getObjectHeight());
        playerController = new PlayerController(this);
    }

    private void setObjectSprites() {
        Sprite[] sprites = new Sprite[8];
        sprites[0] = new Sprite("textures/BerzerkPlayerFaceRight.png",8*scaling,12*scaling);
        sprites[1] = new Sprite("textures/BerzerkPlayerRight.png",8*scaling,12*scaling);
        sprites[2] = new Sprite("textures/BerzerkPlayerRight2.png",8*scaling,12*scaling);
        sprites[3] = new Sprite("textures/BerzerkPlayerFaceLeft.png",8*scaling,12*scaling);
        sprites[4] = new Sprite("textures/BerzerkPlayerLeft.png",8*scaling,12*scaling);
        sprites[5] = new Sprite("textures/BerzerkPlayerLeft2.png",8*scaling,12*scaling);
        sprites[6] = new Sprite("textures/ShootRight1.png",5*scaling,12*scaling);
        sprites[7] = new Sprite("textures/ShootLeft2.png",5*scaling,12*scaling);
        setSprites(sprites);
    }
    public int getMovementSpeed() {
        return movementSpeed;
    }

    public Rectangle2D getColliderBox() {
        return colliderBox;
    }

    public void setColliderBox(Rectangle2D colliderBox) {
        this.colliderBox = colliderBox;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
