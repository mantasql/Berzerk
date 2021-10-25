package Managers;

import GameObjects.Bullet;
import GameObjects.Player;
import GameObjects.Robot;
import GameObjects.Wall;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DisplayManager extends Application {
    public static final int scaling = 3;
    public static Scene mainScene;
    private final int WIDTH = 256,HEIGHT = 224;
    private final Stage mainStage;
    private final GraphicsContext graphicsContext;
    private final GameManager gameManager;
    private int highScore;

    public DisplayManager() {
        AnchorPane mainPane = new AnchorPane();
        Canvas canvas = new Canvas(WIDTH * scaling, HEIGHT * scaling);
        graphicsContext = canvas.getGraphicsContext2D();
        mainPane.getChildren().add(canvas);
        mainScene = new Scene(mainPane, canvas.getWidth(), canvas.getHeight());
        this.mainStage = new Stage();
        this.mainStage.setScene(mainScene);
        gameManager = new GameManager();
    }

    public static void main(String[] args) {
        launch();
    }

    private void run() {
        if(gameManager.getGameObjectManager() != null){
            drawGame();
            gameManager.runGame();
        }
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

    private void drawRoom(){
        fillBackground();
        displayWalls();
        displayRobots();
        displayPlayer();
        displayBullets();
        displayScore();
    }

    private void drawGame(){
        drawRoom();
        setHighScore();
        displayGameOverText();
    }

    private void displayWalls(){
        for(Wall wall : gameManager.getGameObjectManager().getWalls()) {
            graphicsContext.setFill(wall.getColor());
            graphicsContext.fillRect(wall.getXCoordinate(),wall.getYCoordinate(),wall.getObjectWidth(),wall.getObjectHeight());
        }
    }

    private void fillBackground() {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,WIDTH*scaling,HEIGHT*scaling);
    }

    private void displayRobots(){
        for(Robot robot : gameManager.getGameObjectManager().getRobots()){
            if(robot.isActive()){
                graphicsContext.drawImage(robot.getAiController().getCurrentSprite().getImage(),robot.getXCoordinate(), robot.getYCoordinate(),
                        robot.getAiController().getCurrentSprite().getWidth(), robot.getAiController().getCurrentSprite().getHeight());
            }
        }
    }
    private void displayPlayer(){
        Player player = gameManager.getGameObjectManager().getPlayer();
        if(player != null){
            if(player.isActive()){
                graphicsContext.drawImage(
                        player.getPlayerController().getCurrentSprite().getImage(),
                        player.getXCoordinate(), player.getYCoordinate(),
                        player.getPlayerController().getCurrentSprite().getWidth(),
                        player.getPlayerController().getCurrentSprite().getHeight()
                );
            }
        }
    }

    private void displayGameOverText(){
        if(gameManager.isGameOver()){
            graphicsContext.setStroke(Color.WHITE);
            graphicsContext.setTextAlign(TextAlignment.CENTER);
            graphicsContext.setTextBaseline(VPos.CENTER);
            graphicsContext.strokeText("Game Over!\n" +
                    " Press Enter to restart or q to quit",WIDTH*scaling/2,HEIGHT*scaling/2);
        }
    }

    private void displayBullets(){
        for(Bullet bullet:gameManager.getGameObjectManager().getAllBullets()){
            if(bullet.isActive()){
                graphicsContext.drawImage(bullet.getBulletController().getCurrentSprite().getImage(),bullet.getXCoordinate(), bullet.getYCoordinate(),
                        bullet.getBulletController().getCurrentSprite().getWidth(), bullet.getBulletController().getCurrentSprite().getHeight());
            }
        }
    }

    private void displayScore(){
        graphicsContext.setStroke(Color.WHITE);
        graphicsContext.setTextAlign(TextAlignment.CENTER);
        graphicsContext.setTextBaseline(VPos.CENTER);
        graphicsContext.strokeText(String.valueOf(gameManager.getScore()),WIDTH*scaling/4-100,HEIGHT*scaling-20);
        graphicsContext.strokeText("High score: " + String.valueOf(highScore),WIDTH*scaling-100,HEIGHT*scaling-20);
    }

    //TODO: Refactor high score.
    private void setHighScore(){
        if(gameManager.isGameOver()){
            if(gameManager.getScore() > highScore){
                highScore = gameManager.getScore();
            }
        }
    }
}
