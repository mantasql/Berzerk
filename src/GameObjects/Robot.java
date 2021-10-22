package GameObjects;

import Controllers.AIController;
import com.sq.GameObject;
import com.sq.GameObjectManager;
import com.sq.IDestroyable;
import com.sq.Sprite;
import javafx.geometry.Rectangle2D;

import static com.sq.DisplayManager.scaling;

public class Robot extends GameObject implements IDestroyable {
    private float movementSpeed = 0.2f;
    private AIController aiController;
    private Rectangle2D boxCollider;
    public Robot(int xCoordinate,int yCoordinate,GameObject target,GameObjectManager gameObjectManager){
        super(xCoordinate,yCoordinate,8*scaling,12*scaling,"Robot",gameObjectManager);
        gameObjectManager.getRobots().add(this);
        setObjectSprites();
        boxCollider = new Rectangle2D(xCoordinate,yCoordinate,getObjectWidth(),getObjectHeight());
        aiController = new AIController(this,target);
    }

    private void setObjectSprites(){
        Sprite[] sprites = new Sprite[1];
        sprites[0] = new Sprite("textures/BerzerkEnemyStill1.png",8*scaling,12*scaling);
        setSprites(sprites);
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public Rectangle2D getBoxCollider() {
        return boxCollider;
    }

    public void setBoxCollider(Rectangle2D boxCollider) {
        this.boxCollider = boxCollider;
    }

    @Override
    public void destroy() {
        this.setActive(false);
        aiController.stop();
        getGameObjectManager().getRobots().remove(this);
    }
}
