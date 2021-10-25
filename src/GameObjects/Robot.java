package GameObjects;

import Controllers.AIController;
import Managers.GameObjectManager;
import com.sq.Destroyable;
import com.sq.Sprite;
import javafx.geometry.Rectangle2D;

import static Managers.DisplayManager.scaling;

public class Robot extends GameObject implements Destroyable {
    private float movementSpeed = 0.2f;
    private AIController aiController;
    public Robot(int xCoordinate,int yCoordinate,GameObject target,GameObjectManager gameObjectManager){
        super(xCoordinate,yCoordinate,8*scaling,12*scaling,"Robot",gameObjectManager);
        gameObjectManager.getRobots().add(this);
        setObjectSprites();
        setBoxCollider(new Rectangle2D(xCoordinate,yCoordinate,getObjectWidth(),getObjectHeight()));
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

    @Override
    public void destroy() {
        this.setActive(false);
        aiController.stop();
        aiController = null;
        getGameObjectManager().getRobots().remove(this);
    }

    public AIController getAiController() {
        return aiController;
    }
}
