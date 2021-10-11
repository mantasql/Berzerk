package com.sq;

public class CharacterController {
    protected MoveDirection currentMoveDirection;
    protected boolean isMoving;

    public MoveDirection getCurrentMoveDirection() {
        return currentMoveDirection;
    }

    public boolean isMoving() {
        return isMoving;
    }
}
