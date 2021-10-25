package Managers;

import GameObjects.*;
import com.sq.Direction;

import java.util.ArrayList;

import static Managers.DisplayManager.scaling;

public class GameObjectManager{
    private ArrayList<Wall> walls;
    private Player player;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Robot> robots;
    private ArrayList<Pathway> pathways;
    private ArrayList<Bullet> allBullets;
    private int gameScore;

    public GameObjectManager() {
        this.walls = new ArrayList<Wall>();
        this.player = null;
        this.enemyBullets = new ArrayList<Bullet>();
        this.playerBullets = new ArrayList<Bullet>();
        this.robots = new ArrayList<Robot>();
        this.pathways = new ArrayList<Pathway>();
        this.allBullets = new ArrayList<Bullet>();
    }

    public void managePlayerCollisions() {
        if(didPlayerCollideWithWall())
            onPlayerCollision();

        if(didPlayerCollideWithBullet())
            onPlayerCollision();
    }

    public void manageBulletCollisions(){
        destroyBulletOnCollision();
    }
    public void manageEnemyCollisions(){
        destroyEnemyOnCollision();
    }

    private boolean didPlayerCollideWithWall(){
        for(Wall wall : walls) {
            if(player != null){
                if(player.getBoxCollider().intersects(wall.getBoxCollider()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean didPlayerCollideWithBullet(){
        for(Bullet bullet : enemyBullets){
            if(player != null){
                if(player.getBoxCollider().intersects(bullet.getBoxCollider()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public Direction returnPathwayDirectionIfCollided(){
        for(Pathway pathway : pathways){
            if(player != null){
                if(player.getBoxCollider().intersects(pathway.getBoxCollider())){
                    return pathway.getDirection();
                }
            }
        }
        return null;
    }

    private void destroyBulletOnCollision() {
        for(Wall wall: walls){
            Bullet playerBullet = playerBullets.stream().filter(c -> c.getBoxCollider().intersects(wall.getBoxCollider())).findFirst().orElse(null);
            Bullet enemyBullet = enemyBullets.stream().filter(c -> c.getBoxCollider().intersects(wall.getBoxCollider())).findFirst().orElse(null);
            if(playerBullet != null){
                playerBullet.destroy();
                break;
            }
            if(enemyBullet != null){
                enemyBullet.destroy();
                break;
            }
        }
    }

    public void destroyEnemyOnCollision() {
        for(Wall wall:walls){
            Robot robot = robots.stream().filter(c -> c.getBoxCollider().intersects(wall.getBoxCollider())).findFirst().orElse(null);
            if(robot != null){
                robot.destroy();
                gameScore += 2;
                break;
            }
        }

        for(Bullet bullet:playerBullets){
            Robot robot = robots.stream().filter(c -> c.getBoxCollider().intersects(bullet.getBoxCollider())).findFirst().orElse(null);
            if(robot != null){
                robot.destroy();
                bullet.destroy();
                gameScore += 5;
                break;
            }
        }

        for(Bullet bullet:enemyBullets){
            Robot robot = robots.stream().filter(c -> c.getBoxCollider().intersects(bullet.getBoxCollider())).findFirst().orElse(null);
            if(robot != null && bullet.getParentGameObject() != robot){
                bullet.destroy();
                robot.destroy();
                gameScore += 10;
                break;
            }
        }
    }

    private void onPlayerCollision() {
        int playerHealth = player.getHealth() - 1;
        if(playerHealth > 0) {
            player.setHealth(playerHealth);
            player.setPosition(65*scaling,83*scaling);
        } else {
            player.setActive(false);
            player.destroy();
        }
        System.out.println("Player health: " + playerHealth);
    }


    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEnemyBullets(Bullet bullet) {
        allBullets.add(bullet);
        enemyBullets.add(bullet);
    }

    public void addPlayerBullets(Bullet bullet) {
        allBullets.add(bullet);
        playerBullets.add(bullet);
    }

    public void removePlayerBullets(Bullet bullet){
        allBullets.remove(bullet);
        playerBullets.remove(bullet);
    }
    public void removeEnemyBullets(Bullet bullet){
        allBullets.remove(bullet);
        enemyBullets.remove(bullet);
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }

    public ArrayList<Pathway> getPathways() {
        return pathways;
    }

    public void setPathways(ArrayList<Pathway> pathways) {
        this.pathways = pathways;
    }

    public ArrayList<Bullet> getAllBullets() {
        return allBullets;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }
}
