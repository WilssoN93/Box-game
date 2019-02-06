package game.gameobjects;

import game.Game;
import game.ID;
import game.ui.SpriteSheet;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PlayerTwo extends GameObject {

    private BufferedImage playerImage;
    
    public PlayerTwo(int x, int y, ID id) {
        super(x, y, id);
        
        SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
        
        playerImage = ss.getImage(1, 1, 42, 42);
        
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH+601);
        y = Game.clamp(y, 0, Game.HEIGHT-70);

    }

    @Override
    public void render(Graphics g) {


        g.drawImage(playerImage, (int)x, (int)y, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,42,42);
        
    }
}
