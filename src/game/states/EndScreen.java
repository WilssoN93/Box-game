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

public class EndScreen extends MouseAdapter {

    Handler handler;
    HUD hud;
    Game game;
    GameDAO gdao;
Scanner sc;
    public EndScreen(Handler handler, HUD hud, Game game,GameDAO gdao) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        this.gdao=gdao;

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (mouseOver(e.getY(), e.getX(), 170, 350, 270, 60)) {
            if (game.gameState == Game.STATE.endScreenOnePlayer) {
                game.gameState = Game.STATE.Menu;

            } else if (game.gameState == Game.STATE.endScreenTwoPlayer) {
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
        
        Font fnt1 = new Font("ariel", 2, 30);
        g.setFont(fnt1);
        g.setColor(Color.WHITE);

        if (game.gameState == Game.STATE.endScreenOnePlayer) {
            g.drawString("Your Score was: " + (int) hud.getaScore(), 200, 60);
            g.drawString("You got to Level: " + hud.getLevel(), 200, 100);
            
            g.drawString("1. " + gdao.printHighScores(1) , 200, 180);
            g.drawString("2. " + gdao.printHighScores(2), 200, 220);
            g.drawString("3. " + gdao.printHighScores(3), 200, 260);
            g.drawString("4. " + gdao.printHighScores(4), 200, 300);
            g.drawString("5. " + gdao.printHighScores(5), 200, 340);

        } else if (game.gameState == Game.STATE.endScreenTwoPlayer) {
            g.drawString("Player One got a score of: " + hud.playerOneScore, 150, 200);
            g.drawString("Player Two got a score of: " + hud.playerTwoScore, 150, 250);

        }

        Font fnt = new Font("ariel", 2, 40);
        g.setFont(fnt);

        g.drawString("Back To Menu", 180, 400);
        g.drawRect(170, 350, 270, 60);

    }

    public void tick() {

    }

}
