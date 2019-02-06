package game.states;
import game.Game;
import game.Spawn;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Spawn spawn;
    private Game game;

    public Menu(Game game, Spawn spawn) {
        this.game = game;
        this.spawn = spawn;
    }

    public void mousePressed(MouseEvent e) {
        if (mouseOver(e.getY(), e.getX(), 170, 50, 250, 60)) {
            if (game.gameState == Game.STATE.Menu) {
                game.gameState = Game.STATE.Game;

                spawn.resetGame();
                spawn.startSpawner();
            }
        }

        if (mouseOver(e.getY(), e.getX(), 170, 125, 270, 60)) {
            if (game.gameState == Game.STATE.Menu) {
                game.gameState = Game.STATE.twoPlayer;

                spawn.resetGame();
                spawn.startSpawner();

            }
        }

        if (mouseOver(e.getY(), e.getX(), 170, 200, 270, 60)) {
            if (game.gameState == Game.STATE.Menu) {
                game.gameState = Game.STATE.howToPlay;

            }

        }
        if (mouseOver(e.getY(), e.getX(), 170, 350, 270, 60)) {
            if (game.gameState == Game.STATE.Menu) {
                System.exit(0);
            }
        }
        if (mouseOver(e.getY(), e.getX(), 170, 275, 270, 60)) {
            if (game.gameState == Game.STATE.Menu) {
                game.gameState = Game.STATE.highscores;
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public boolean mouseOver(int my, int mx, int x, int y, int witdh, int height) {

        if (mx > x && mx < x + witdh) {
            if (my > y && my < y + height) {
                return true;
            }
        }
        return false;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);

        Font fnt = new Font("ariel", 2, 40);
        g.setFont(fnt);

        g.drawString("One Player", 180, 100);
        g.drawRect(170, 50, 270, 60);
        g.drawString("Two Players", 180, 175);
        g.drawRect(170, 125, 270, 60);
        g.drawString("How to Play", 180, 250);
        g.drawRect(170, 200, 270, 60);
        g.drawString("Highscores", 180, 325);
        g.drawRect(170, 275, 270, 60);
        g.drawString("Exit", 180, 400);
        g.drawRect(170, 350, 270, 60);
    }

}
