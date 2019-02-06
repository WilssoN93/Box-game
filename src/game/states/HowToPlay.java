package game.states;

import game.Game;
import game.Handler;
import game.ui.HUD;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HowToPlay extends MouseAdapter {

    Handler handler;
    HUD hud;
    Game game;

    public HowToPlay(Handler handler, HUD hud, Game game) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;

    }

    public void mousePressed(MouseEvent e) {
        if (mouseOver(e.getY(), e.getX(), 170, 350, 270, 60)) {
            if (game.gameState == Game.STATE.howToPlay) {
                game.gameState=Game.STATE.Menu;
                
            }

        }
    }

    public boolean mouseOver(int my, int mx, int x, int y, int witdh, int height) {

        if (mx > x && mx < x + witdh) {
            if (my > y && my < y + height) {
                return true;
            }
        }
        return false;
    }

    public void render(Graphics g) {
        
        Font fnt2 = new Font("ariel", 1,40);
        g.setColor(Color.WHITE);
        g.setFont(fnt2);
        g.drawString("One player: ", 25, 100);
        g.drawString("Two players: ", 275, 100);
        
        
        Font fnt1 = new Font("ariel", 4, 20);
        g.setFont(fnt1);
        g.setColor(Color.WHITE);

        
        g.drawString("Use WASD to move", 25, 150);
        g.drawString("Avoid moving squares", 25, 180);
        g.drawString("The Brown are healthpacks", 25, 210);
        g.drawString("*?* detroys enemys", 25, 240);
        g.drawString("Stay alive = higher score", 25, 270);
        
        g.drawString("Player One(Red) use WASD to move", 275, 150);
        g.drawString("Player two(Green) use Arrows to move", 275, 180);
        g.drawString("Avoid moving squares", 275, 210);
        g.drawString("The Brown are healthpacks", 275, 240);
        g.drawString("Catch X squares to get points", 275, 270);
        g.drawString("You loose 3 points if you die", 275, 300);
        g.drawString("Most Points Win!!", 275, 330);

        Font fnt = new Font("ariel", 2, 40);
        g.setFont(fnt);

        g.drawString("Back", 180, 400);
        g.drawRect(170, 350, 270, 60);

    }

    public void tick() {

    }

}
