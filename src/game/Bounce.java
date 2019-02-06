package game;

import game.gameobjects.GameObject;

public class Bounce implements Runnable {

    GameObject object;
    public Bounce(GameObject object) {
        this.object=object;
        
        run();
    }

    @Override
    public synchronized void run() {
        int cooldown = 0;

        if (object.velX == -2) {
            if (cooldown == 0) {
                object.velX = 2;
                ++cooldown;
            }
        } else if (object.velX == 2) {
            if (cooldown == 0) {
                object.velX = -2;
                ++cooldown;
            }

        }

        if (object.velY == -2) {
            if (cooldown == 0) {
                object.velY = 2;
                ++cooldown;
            }
        } else if (object.velY == 2) {
            if (cooldown == 0) {
                object.velY = -2;
                ++cooldown;
            }
        }

    }

}
