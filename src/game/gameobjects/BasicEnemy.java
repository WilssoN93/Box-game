package game.gameobjects;

import game.Game;
import game.Handler;
import game.ID;
import game.ui.HUD;
import game.ui.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {

    private Handler handler;
    private BufferedImage basicEnemyImage;
    private HUD hud;

    public BasicEnemy(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        velocity = 2;
        velX = velocity;
        velY = velocity;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);

        basicEnemyImage = ss.getImage(1, 3, 20, 20);

    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if (x >= Game.WITDH - 24 || x <= 0) {
            velX *= -1;
        }
        if (y >= Game.HEIGHT - 43 || y <= 0) {
            velY *= -1;
        }

        x = Game.clamp(x, 0, Game.WIDTH + 613);
        y = Game.clamp(y, 0, Game.HEIGHT - 45);

        collision();

    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player || tempObject.getId() == ID.FasterEnemy || tempObject.getId() == ID.PlayerTwo || tempObject.getId() == ID.BasicEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (tempObject != this) {
                        if (tempObject.velX <= 0 && tempObject.velY <= 0) {
                            this.velY = velocity;
                            this.velX = velocity;
                        }

                        if (tempObject.velX >= 0 && tempObject.velY <= 0) {
                            this.velY = velocity;

                            this.velX = -velocity;

                        }
                        if (tempObject.velY >= 0 && tempObject.velX <= 0) {
                            this.velY = -velocity;
                            this.velX = velocity;

                        }
                        if (tempObject.velY >= 0 && tempObject.velX >= 0) {

                            this.velX = -velocity;
                            this.velY = -velocity;
                        }
                        if (tempObject.velX == 0 && tempObject.velY == 0) {
                            if (this.velX == -velocity && this.velY == -velocity) {
                                this.velX = velocity;
                                this.velY = velocity;
                            } else if (this.velX == velocity && this.velY == velocity) {
                                this.velX = -velocity;
                                this.velY = -velocity;
                            } else if (this.velY == velocity && this.velX == -velocity) {
                                this.velY = -velocity;
                                this.velX = velocity;
                            } else if (this.velY == -velocity && this.velX == velocity) {
                                this.velY = velocity;
                                this.velX = -velocity;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hud.healthPlayerOne -= 2;
                }
            }
        }
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.PlayerTwo) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    hud.healthPlayerTwo -= 2;

                }
            }

        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(basicEnemyImage, (int)x,(int) y, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int) y, 20, 20);

    }
}
