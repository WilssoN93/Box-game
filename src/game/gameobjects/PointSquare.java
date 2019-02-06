package game.gameobjects;

import game.Game;
import game.ui.HUD;
import game.Handler;
import game.ID;
import game.ui.SpriteSheet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PointSquare extends GameObject {


    Handler handler;
    HUD hud;
    
    private BufferedImage pointImage;

    public PointSquare(double x, double y, ID id, Handler handler,HUD hud) {
        super(x, y, id);
        this.hud=hud;
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);

        pointImage = ss.getImage(2, 3, 16, 16);
        
    }

    @Override
    public void tick() {

        x += velX;
        y += velY;

        if (x >= Game.WITDH - 32 || x <= 0) {
            velX *= -1;
        }
        if (y >= Game.HEIGHT - 45 || y <= 0) {
            velY *= -1;
        }

        x = Game.clamp(x, 0, Game.WIDTH + 617);
        y = Game.clamp(y, 0, Game.HEIGHT - 45);

        collision();

    }

    public void collision() {

        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.Player) {
                if (getBounds().intersects(tempObject.getBounds())) {
                           hud.playerOneScore +=1;
                   handler.removeObject(this);
                }
            }

        }
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ID.PlayerTwo) {
                if (getBounds().intersects(tempObject.getBounds())) {
                           hud.playerTwoScore +=1;
                   handler.removeObject(this);
                }
            }

        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(pointImage,(int)x,(int)y,null);  
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 16, 16);

    }
}
