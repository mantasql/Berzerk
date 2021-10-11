package com.sq;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;


public class AnimationManager extends AnimationTimer {

    private CharacterController characterController;
    private AnimatedObject animatedObject;
    private Image currentImage;
    private int animationSequence = 0;
    private double animationTime = 0.5;

    public AnimationManager(CharacterController characterController, AnimatedObject animatedObject){
        this.characterController = characterController;
        this.animatedObject = animatedObject;
        currentImage = animatedObject.getSprites()[0].getImage();
    }

    @Override
    public void handle(long now) {
        move();
        //if(animatedObject.ge)
    }

    public void move() {
        if(characterController.isMoving()){
            switch (characterController.getCurrentMoveDirection()) {
                case EAST:
                    currentImage = animatedObject.getSprites()[0].getImage();
                    /*animationSequence = (animationSequence>2) ? 0 : animationSequence++;
                    if(animationSequence > 2) animationSequence = 0;
                    else animationSequence++;*/
                    break;
                case WEST:
                    currentImage = animatedObject.getSprites()[3].getImage();
                    break;
            }
        }
        DisplayManager.graphicsContext.drawImage(currentImage,animatedObject.xCoordinate,animatedObject.yCoordinate,
                currentImage.getWidth(),currentImage.getHeight());
    }

}
