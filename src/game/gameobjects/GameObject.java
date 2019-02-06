package game.gameobjects;

import game.ID;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    
    public double x,y;
    public ID id;
    public double velX, velY,velocity;

    public GameObject(double x, double y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public void setId(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

}
