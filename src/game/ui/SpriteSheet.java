
package game.ui;

import java.awt.image.BufferedImage;


public class SpriteSheet {
    
    private BufferedImage sprite;
    
    public SpriteSheet(BufferedImage ss){
    this.sprite = ss;
    }
    
    public BufferedImage getImage(int col,int row,int witdh,int height){
    BufferedImage img = sprite.getSubimage((row*42)-42, (col *42)-42, witdh, height);
    return img;
    } 

}
