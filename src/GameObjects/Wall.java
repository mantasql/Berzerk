package GameObjects;

import com.sq.GameObject;
import com.sq.GameObjectManager;
import com.sq.IDestroyable;
import com.sq.PointObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class Wall extends GameObject implements IDestroyable {
    private int thickness;
    private PointObject pointObject1;
    private PointObject pointObject2;
    private Color color;

    public Wall(PointObject pointObject1, PointObject pointObject2, int thickness, GameObjectManager gameObjectManager) {
        setGameObjectManager(gameObjectManager);
        gameObjectManager.getWalls().add(this);
        this.pointObject1 = pointObject1;
        this.pointObject2 = pointObject2;
        this.thickness = thickness;
        this.color = Color.rgb(0,0,108);
        setObjectWidth(calculateWallWidth());
        setObjectHeight(calculateWallHeight());
        setBoxCollider(new Rectangle2D(pointObject1.getXCoordinate(), pointObject1.getYCoordinate(), getObjectWidth(),getObjectHeight()));

        setXCoordinate(pointObject1.getXCoordinate());
        setYCoordinate(pointObject1.getYCoordinate());
    }
    private int calculateWallWidth(){
        int width = (int)Math.abs(pointObject1.getXCoordinate() - pointObject2.getXCoordinate());
        return (width > 0) ? width : thickness;
    }
    private int calculateWallHeight(){
        int height = (int)Math.abs(pointObject1.getYCoordinate() - pointObject2.getYCoordinate());
        return (height > 0) ? height : thickness;
    }

    @Override
    public void destroy() {
        setActive(false);
        getGameObjectManager().getWalls().remove(this);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
