package GameObjects;

import com.sq.GameObject;
import com.sq.GameObjectManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class Wall extends GameObject {
    private int thickness;
    private Pillar pillar1;
    private Pillar pillar2;
    private Rectangle2D colliderBox;
    private Color color;

    public Wall(Pillar pillar1, Pillar pillar2, int thickness, GameObjectManager gameObjectManager) {
        gameObjectManager.getWalls().add(this);
        this.pillar1 = pillar1;
        this.pillar2 = pillar2;
        this.thickness = thickness;
        this.color = Color.rgb(0,0,108);
        setObjectWidth(calculateWallWidth());
        setObjectHeight(calculateWallHeight());
        colliderBox = new Rectangle2D(pillar1.getX(),pillar1.getY(),getObjectWidth(),getObjectHeight());

        setXCoordinate(pillar1.getX());
        setYCoordinate(pillar1.getY());

        System.out.println("Width: "+getObjectWidth()+" Height: "+getObjectHeight()+" xCoordinate: "+getXCoordinate() +" yCoordinate:" + getYCoordinate());

    }
    private int calculateWallWidth(){
        int width = Math.abs(pillar1.getX() - pillar2.getX());
        return (width > 0) ? width : thickness;
    }
    private int calculateWallHeight(){
        int height = Math.abs(pillar1.getY() - pillar2.getY());
        return (height > 0) ? height : thickness;
    }

    public Rectangle2D getColliderBox() {
        return colliderBox;
    }
}
