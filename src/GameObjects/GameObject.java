package GameObjects;

import Managers.GameObjectManager;
import com.sq.Sprite;
import javafx.geometry.Rectangle2D;

public abstract class GameObject extends PointObject {
    private int objectWidth;
    private int objectHeight;
    private Sprite[] sprites;
    private boolean isActive;
    private GameObjectManager gameObjectManager;
    private String tag;
    private Rectangle2D boxCollider;

    public GameObject(float xCoordinate,float yCoordinate, int objectWidth, int objectHeight,String tag, GameObjectManager gameObjectManager) {
        super(xCoordinate,yCoordinate);
        this.objectWidth = objectWidth;
        this.objectHeight = objectHeight;
        this.tag = tag;
        isActive = true;
        this.gameObjectManager = gameObjectManager;
    }

    public GameObject() {
        super(0,0);
        isActive = true;
    }

    public Sprite[] getSprites() {
        return sprites;
    }

    public void setSprites(Sprite[] sprites) {
        this.sprites = sprites;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public GameObjectManager getGameObjectManager() {
        return gameObjectManager;
    }

    public String getTag() {
        return tag;
    }

    public Rectangle2D getBoxCollider() {
        return boxCollider;
    }

    public void setBoxCollider(Rectangle2D boxCollider) {
        this.boxCollider = boxCollider;
    }

    public void setPosition(float xCoordinate, float yCoordinate) {
        setXCoordinate(xCoordinate);
        setYCoordinate(yCoordinate);
        boxCollider = new Rectangle2D(xCoordinate,yCoordinate,objectWidth,objectHeight);
    }

    public void setGameObjectManager(GameObjectManager gameObjectManager) {
        this.gameObjectManager = gameObjectManager;
    }
}
