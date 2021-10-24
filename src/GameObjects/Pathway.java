package GameObjects;

import com.sq.Direction;
import com.sq.GameObject;
import com.sq.GameObjectManager;
import com.sq.PointObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

import static com.sq.DisplayManager.scaling;

public class Pathway extends GameObject {
    private Direction direction;
    private boolean hasDoor;
    private Wall wall;
    private PointObject pointObject1;
    private PointObject pointObject2;
    private int thickness;

    public Pathway(Direction direction, PointObject pointObject1, PointObject pointObject2, int thickness, GameObjectManager gameObjectManager) {
        setGameObjectManager(gameObjectManager);
        gameObjectManager.getPathways().add(this);
        this.direction = direction;
        this.hasDoor = false;
        this.pointObject1 = pointObject1;
        this.pointObject2 = pointObject2;
        this.thickness = thickness;

        setObjectWidth(calculatePathwayWidth());
        setObjectHeight(calculatePathwayHeight());
        setXCoordinate(pointObject1.getXCoordinate());
        setYCoordinate(pointObject1.getYCoordinate());
        setBoxCollider(new Rectangle2D(getXCoordinate(),getYCoordinate(),getObjectWidth(),getObjectHeight()));
    }

    private int calculatePathwayWidth(){
        int width = (int)Math.abs(pointObject1.getXCoordinate() - pointObject2.getXCoordinate());
        return (width > 0) ? width : thickness;
    }
    private int calculatePathwayHeight(){
        int height = (int)Math.abs(pointObject1.getYCoordinate() - pointObject2.getYCoordinate());
        return (height > 0) ? height : thickness;
    }

    public void closeDoor(){
        hasDoor = true;
        wall = new Wall(pointObject1,pointObject2,4*scaling,getGameObjectManager());
        wall.setColor(Color.RED);
    }

    public void openDoor(){
        hasDoor = false;
        wall.destroy();
    }

    public boolean isHasDoor() {
        return hasDoor;
    }

    public Direction getDirection() {
        return direction;
    }
}
