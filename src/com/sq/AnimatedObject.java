package com.sq;

public class AnimatedObject extends GameObject{

    protected CharacterController characterController;
    protected AnimationManager animationManager;

    public AnimatedObject(int xCoordinate, int yCoordinate, int objectWidth, int objectHeight, boolean isSolid, boolean isDynamic) {
        super(xCoordinate, yCoordinate, objectWidth, objectHeight, isSolid, isDynamic);
    }
}
