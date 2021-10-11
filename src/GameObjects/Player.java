package GameObjects;

import com.sq.*;
import javafx.geometry.Rectangle2D;

import static com.sq.DisplayManager.scaling;

public class Player extends AnimatedObject {
    private int health = 3;
    private int movementSpeed = 10;
    private Sprite[] sprites = new Sprite[6];

    private CharacterController characterController;
    private AnimationManager animationManager;

    private Rectangle2D collider;

    public Player() {
        super(49*scaling, 83*scaling,25,36,true,true);
        setupObjectSprites();
        collider = new Rectangle2D(xCoordinate,yCoordinate,25,36);
        characterController = new PlayerController(this);
        animationManager = new AnimationManager(characterController,this);
        animationManager.start();
    }

    protected void setupObjectSprites() {
        sprites[0] = new Sprite("textures/BerzerkPlayerFaceRight.png",25,36);
        sprites[1] = new Sprite("textures/BerzerkPlayerRight.png",25,36);
        sprites[2] = new Sprite("textures/BerzerkPlayerRight2.png",25,36);
        sprites[3] = new Sprite("textures/BerzerkPlayerFaceLeft.png",25,36);
        sprites[4] = new Sprite("textures/BerzerkPlayerLeft.png",25,36);
        sprites[5] = new Sprite("textures/BerzerkPlayerLeft2.png",25,36);
        //sprites[6] = new Sprite("textures/ShootRight1.png",16,36);
        //sprites[7] = new Sprite("textures/ShootLeft2.png",16,36);
    }
    public int getMovementSpeed() {
        return movementSpeed;
    }

    @Override
    public Sprite[] getSprites() {
        return sprites;
    }
}
