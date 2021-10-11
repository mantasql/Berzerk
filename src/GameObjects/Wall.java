package GameObjects;

import com.sq.GameObject;

public class Wall extends GameObject {
    private int thickness;

    public Wall (int thickness, int xCoordinate, int yCoordinate) {
        super(xCoordinate,yCoordinate,10,10,true,false);
        this.thickness = thickness;
    }

    public int getThickness() {
        return thickness;
    }

}
