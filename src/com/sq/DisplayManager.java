package com.sq;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DisplayManager extends Application {
    public static final int WIDTH = 256,HEIGHT = 224;
    public static final int scaling = 3;
    public static AnchorPane mainPane;
    public static Scene mainScene;
    public static Stage mainStage;
    public static Canvas canvas;
    public static GraphicsContext graphicsContext;

    public DisplayManager() {
        this.mainPane = new AnchorPane();
        canvas = new Canvas(WIDTH*scaling,HEIGHT*scaling);
        graphicsContext = canvas.getGraphicsContext2D();
        mainPane.getChildren().add(canvas);
        this.mainScene = new Scene(mainPane,canvas.getWidth(), canvas.getHeight());
        this.mainStage = new Stage();
        this.mainStage.setScene(mainScene);

    }

    public static void main(String[] args) {
        launch();
    }

    private void run() {
        GameManager.getInstance().startGame();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage = mainStage;
            primaryStage.setTitle("Berzerk");

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> run()));
            timeline.setCycleCount(Timeline.INDEFINITE);

            primaryStage.show();
            timeline.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
