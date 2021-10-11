package com.sq;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
    private GameManager gameManager;
    private RoomGenerator roomGenerator;

    public DisplayManager() {
        this.mainPane = new AnchorPane();
        canvas = new Canvas(WIDTH*scaling,HEIGHT*scaling);
        graphicsContext = canvas.getGraphicsContext2D();
        mainPane.getChildren().add(canvas);
        this.mainScene = new Scene(mainPane,canvas.getWidth(), canvas.getHeight());
        this.mainStage = new Stage();
        this.mainStage.setScene(mainScene);

        roomGenerator = new RoomGenerator();
        gameManager = new GameManager();

    }

    public static void main(String[] args) {
        launch();
    }

    private void run(GraphicsContext graphicsContext) {

        fillBackground(Color.BLACK);
        drawBoarders();
        drawMazePoints();
    }

    private void fillBackground(Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(0,0,WIDTH*scaling,HEIGHT*scaling);
    }

    private void drawBoarders() {
        graphicsContext.setStroke(Color.rgb(0,0,108));
        graphicsContext.setLineWidth(roomGenerator.getBoarders().get(0).getThickness());

        for(int i=0;i<12;i+=3) {
            graphicsContext.strokeLine(roomGenerator.getBoarders().get(i).getX(),roomGenerator.getBoarders().get(i).getY(),
                    roomGenerator.getBoarders().get(i+1).getX(),roomGenerator.getBoarders().get(i+1).getY());
            graphicsContext.strokeLine(roomGenerator.getBoarders().get(i).getX(),roomGenerator.getBoarders().get(i).getY(),
                    roomGenerator.getBoarders().get(i+2).getX(),roomGenerator.getBoarders().get(i+2).getY());
        }

    }

    private void drawMazePoints() {
        graphicsContext.setFill(Color.rgb(0,0,108));
        for(int i=0;i<8;i++) {
            graphicsContext.fillRect(roomGenerator.getMazePoints().get(i).getX(),roomGenerator.getMazePoints().get(i).getY(),12,12);
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("Berzerk");
            primaryStage = mainStage;

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> run(graphicsContext)));
            timeline.setCycleCount(Timeline.INDEFINITE);

            primaryStage.show();
            timeline.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
