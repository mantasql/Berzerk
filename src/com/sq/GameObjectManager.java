package com.sq;

import GameObjects.Bullet;
import GameObjects.Player;
import GameObjects.Robot;
import GameObjects.Wall;

import java.util.ArrayList;

public class GameObjectManager {
    private ArrayList<Wall> walls;
    private Player player;
    private ArrayList<Bullet> enemyBullets;
    private ArrayList<Bullet> playerBullets;
    private ArrayList<Robot> robots;

    public GameObjectManager(Player player) {
        this.walls = new ArrayList<Wall>();
        this.player = player;
        this.enemyBullets = new ArrayList<Bullet>();
        this.playerBullets = new ArrayList<Bullet>();
        this.robots = new ArrayList<Robot>();
    }

    public boolean didPlayerCollide() {
        for(Wall wall : walls) {
            if(player != null){
                if(player.getColliderBox().intersects(wall.getColliderBox()))
                    return true;
            }
        }
        for(Bullet bullet : enemyBullets){
            if(player != null){
                if(player.getColliderBox().intersects(bullet.getColliderBox()))
                    return true;
            }
        }
        return false;
    }

    public void destroyBulletOnCollision() {
        for(Wall wall: walls){
            Bullet playerBullet = playerBullets.stream().filter(c -> c.getColliderBox().intersects(wall.getColliderBox())).findFirst().orElse(null);
            if(playerBullet != null){
                playerBullet.setActive(false);
                playerBullets.remove(playerBullet);
                break;
            }
        }
    }

    public void destroyEnemyOnCollision() {
        for(Wall wall:walls){
            Robot robot = robots.stream().filter(c -> c.getBoxCollider().intersects(wall.getColliderBox())).findFirst().orElse(null);
            if(robot != null){
                robot.setActive(false);
                robots.remove(robot);
                break;
            }
        }

        for(Bullet bullet:playerBullets){
            Robot robot = robots.stream().filter(c -> c.getBoxCollider().intersects(bullet.getColliderBox())).findFirst().orElse(null);
            if(robot != null){
                robot.destroy();
                bullet.destroy();
                break;
            }
        }
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

    public ArrayList<Bullet> getEnemyBullets() {
        return enemyBullets;
    }

    public void setEnemyBullets(ArrayList<Bullet> enemyBullets) {
        this.enemyBullets = enemyBullets;
    }

    public ArrayList<Bullet> getPlayerBullets() {
        return playerBullets;
    }

    public void setPlayerBullets(ArrayList<Bullet> playerBullets) {
        this.playerBullets = playerBullets;
    }

    public ArrayList<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ArrayList<Robot> robots) {
        this.robots = robots;
    }
}
