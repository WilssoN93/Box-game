package game.states;

import game.Game;
import game.GameDAO;
import game.Handler;
import game.ui.HUD;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class HighScores extends MouseAdapter {

    Handler handler;
    HUD hud;
    Game game;
    GameDAO gdao;
    Scanner sc;

    public HighScores(Handler handler, HUD hud, Game game, GameDAO gdao) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.gdao = gdao;

    }

    
    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseOver(e.getY(), e.getX(), 170, 350, 270, 60)) {
            if (game.gameState == Game.STATE.highscores) {
                game.gameState = Game.STATE.Menu;

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

        Font fnt1 = new Font("ariel", 2, 50);
        g.setFont(fnt1);
        g.setColor(Color.WHITE);

        if (game.gameState == Game.STATE.highscores) {
            g.drawString("1. " + gdao.printHighScores(1), 200, 70);
            g.drawString("2. " + gdao.printHighScores(2), 200, 130);
            g.drawString("3. " + gdao.printHighScores(3), 200, 190);
            g.drawString("4. " + gdao.printHighScores(4), 200, 250);
            g.drawString("5. " + gdao.printHighScores(5), 200, 310);
        }

        Font fnt = new Font("ariel", 2, 40);
        g.setFont(fnt);

        g.drawString("Back To Menu", 180, 400);
        g.drawRect(170, 350, 270, 60);

    }

    public void tick() {

    }

}
