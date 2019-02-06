package game;

import game.gameobjects.GameObject;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private boolean[] KeyDown = new boolean[8];
    private Handler handler;
    private Game game;
    private Spawn spawn;

    public KeyInput(Handler handler, Spawn spawn, Game game) {
        this.handler = handler;
        this.game = game;
        this.spawn = spawn;

        KeyDown[0] = false;
        KeyDown[1] = false;
        KeyDown[2] = false;
        KeyDown[3] = false;
        KeyDown[4] = false;
        KeyDown[5] = false;
        KeyDown[6] = false;
        KeyDown[7] = false;

    }

    public synchronized void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int keyTwo = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W || (key == KeyEvent.VK_UP && game.gameState == Game.STATE.Game)) {
                    tempObject.setVelY(-5);
                    KeyDown[0] = true;
                }
                if (key == KeyEvent.VK_A || (key == KeyEvent.VK_LEFT && game.gameState == Game.STATE.Game)) {
                    tempObject.setVelX(-5);
                    KeyDown[1] = true;
                }
                if (key == KeyEvent.VK_D || (key == KeyEvent.VK_RIGHT && game.gameState == Game.STATE.Game)) {
                    tempObject.setVelX(5);
                    KeyDown[2] = true;
                }
                if (key == KeyEvent.VK_S || (key == KeyEvent.VK_DOWN && game.gameState == Game.STATE.Game)) {
                    tempObject.setVelY(5);
                    KeyDown[3] = true;
                }
            }
        }
        for (int i = 0; i < handler.object.size(); i++) {

            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.PlayerTwo) {
                if (keyTwo == KeyEvent.VK_UP) {
                    tempObject.setVelY(-5);
                    KeyDown[4] = true;
                }
                if (keyTwo == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                    KeyDown[5] = true;
                }
                if (keyTwo == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                    KeyDown[6] = true;
                }
                if (keyTwo == KeyEvent.VK_DOWN) {
                    tempObject.setVelY(5);
                    KeyDown[7] = true;
                }

            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if (game.gameState == Game.STATE.Game || game.gameState == Game.STATE.twoPlayer) {
                spawn.resetGame();
                game.gameState = Game.STATE.Menu;
            } else if (game.gameState == Game.STATE.Menu) {
                System.exit(0);

            } else if (game.gameState == Game.STATE.howToPlay || game.gameState == Game.STATE.highscores) {
                game.gameState = Game.STATE.Menu;
            }
        }

        if (key == KeyEvent.VK_ENTER) {

            if (game.gameState == Game.STATE.Menu) {
                game.gameState = Game.STATE.Game;

                spawn.resetGame();
                spawn.startSpawner();
            }
            if (game.gameState == Game.STATE.endScreenOnePlayer || game.gameState == Game.STATE.endScreenTwoPlayer) {

                game.gameState = Game.STATE.Menu;

            }

        }
    }

    public synchronized void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        int keyTwo = e.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.id == ID.Player) {
                if (key == KeyEvent.VK_W || (key == KeyEvent.VK_UP && game.gameState == Game.STATE.Game)) {
                    //tempObject.setVelY(0);
                    KeyDown[0] = false;
                }
                if (key == KeyEvent.VK_A || (key == KeyEvent.VK_LEFT && game.gameState == Game.STATE.Game)) {
                    //tempObject.setVelX(0);
                    KeyDown[1] = false;
                }
                if (key == KeyEvent.VK_D || (key == KeyEvent.VK_RIGHT && game.gameState == Game.STATE.Game)) {
                    //tempObject.setVelX(0);
                    KeyDown[2] = false;
                }
                if (key == KeyEvent.VK_S || (key == KeyEvent.VK_DOWN && game.gameState == Game.STATE.Game)) {
                    //tempObject.setVelY(0);
                    KeyDown[3] = false;

                }

                if (!KeyDown[1] && !KeyDown[2]) {
                    tempObject.setVelX(0);
                }
                if (!KeyDown[0] && !KeyDown[3]) {
                    tempObject.setVelY(0);

                }

            }
        }
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.PlayerTwo) {
                if (keyTwo == KeyEvent.VK_UP) {
                    //tempObject.setVelY(0);
                    KeyDown[4] = false;
                }
                if (keyTwo == KeyEvent.VK_LEFT) {
                    //tempObject.setVelX(0);
                    KeyDown[5] = false;
                }
                if (keyTwo == KeyEvent.VK_RIGHT) {
                    //tempObject.setVelX(0);
                    KeyDown[6] = false;
                }
                if (keyTwo == KeyEvent.VK_DOWN) {
                    //tempObject.setVelY(0);
                    KeyDown[7] = false;

                }
                if (!KeyDown[4] && !KeyDown[7]) {
                    tempObject.setVelY(0);
                }
                if (!KeyDown[5] && !KeyDown[6]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
