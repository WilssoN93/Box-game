package game;

import game.ui.HUD;
import game.gameobjects.FasterEnemy;
import game.gameobjects.ClearBox;
import game.gameobjects.BasicEnemy;
import game.gameobjects.HealthPack;
import static game.Game.HEIGHT;
import static game.Game.WITDH;
import game.gameobjects.Player;
import game.gameobjects.PlayerTwo;

import game.gameobjects.PointSquare;
import java.awt.Graphics;
import java.util.Random;

public class Spawn {

    private Random r = new Random();
    private Game game;
    private Handler handler;
    private HUD hud;
    private int fasterEnemyCounter = 0;
    private int healthPackCounter = 0;
    private int clearBoxCounter = r.nextInt(10) + 10;
    private int counter = 0;
    private int spawnCount = 400;
    private int levelCounter = 10;
    private int i;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;

    }

    public void spawn() {
        healthPackCounter++;
        fasterEnemyCounter++;

        spawnObjects();
        if (game.gameState == Game.STATE.twoPlayer) {
            if (hud.getLevel() == clearBoxCounter) {

                handler.addObject(new ClearBox(r.nextInt(WITDH - 32), r.nextInt(HEIGHT - 40), ID.ClearBox, handler));
                clearBoxCounter += r.nextInt(10) + 15;

            }
        }
        if (fasterEnemyCounter >= 1500) {
            handler.addObject(new FasterEnemy(r.nextInt(WITDH - 32), r.nextInt(HEIGHT - 40), ID.FasterEnemy, handler, hud));
            fasterEnemyCounter = 0;

        }

        if (healthPackCounter >= 700) {
            handler.addObject(new HealthPack(r.nextInt(WITDH - 32), r.nextInt(HEIGHT - 40), ID.HealthPack, handler, hud));
            healthPackCounter = 0;

        }

        if (game.gameState == Game.STATE.twoPlayer) {

            i++;
            if (i >= 40) {
                handler.addObject(new PointSquare(r.nextInt(WITDH - 32), r.nextInt(HEIGHT - 40), ID.PointSquare, handler, hud));
                i = 0;
            }

        }
    }

    private void spawnObjects() {
        
        counter++;
        
        if (counter >= spawnCount) {

            handler.addObject(new BasicEnemy(r.nextInt(WITDH - 32), r.nextInt(HEIGHT - 40), ID.BasicEnemy, handler, hud));
            counter = 0;
            
            if (hud.getLevel() == levelCounter) {

                levelCounter += 10;
                spawnCount -= 50;

            }
        }
    }

    public void resetGame() {

        for (int i = handler.object.size() - 1; i >= 0; i--) {
            handler.removeObject(handler.object.get(i));
            if (handler.object.isEmpty()) {
                break;
            }
        }
        hud.playerOneScore = 0;
        hud.playerTwoScore = 0;
        hud.healthPlayerTwo = 100;
        hud.healthPlayerOne = 100;
        hud.setLevel(1);
        hud.setaScore(0);
        hud.setScore(0);
        fasterEnemyCounter = 0;
        healthPackCounter = 0;
        clearBoxCounter = r.nextInt(10) + 10;
        counter = 0;
        spawnCount = 400;
        levelCounter = 10;
        i = 0;
    }

    public void tick() {
        if (hud.healthPlayerOne <= 0 && game.gameState == Game.STATE.Game) {
            for (int i = handler.object.size() - 1; i >= 0; i--) {

                handler.removeObject(handler.object.get(i));
                game.gameState = Game.STATE.endScreenOnePlayer;
            }
        } else if (hud.healthPlayerOne <= 0 || hud.healthPlayerTwo <= 0 && game.gameState == Game.STATE.twoPlayer) {
            for (int i = handler.object.size() - 1; i >= 0; i--) {

                handler.removeObject(handler.object.get(i));
                game.gameState = Game.STATE.endScreenTwoPlayer;
            }
            if (hud.healthPlayerOne <= 0) {

                if (hud.playerOneScore <= 3) {
                    hud.playerOneScore = 0;
                } else if (hud.playerOneScore > 3) {
                    hud.playerOneScore -= 3;
                }
            } else if (hud.healthPlayerTwo <= 0) {
                if (hud.playerTwoScore <= 3) {
                    hud.playerTwoScore = 0;
                } else if (hud.playerTwoScore > 3) {
                    hud.playerTwoScore -= 3;
                }
            }
        } else if (hud.healthPlayerOne > 100) {
            hud.healthPlayerOne = 100;
        } else if (hud.healthPlayerTwo > 100) {
            hud.healthPlayerTwo = 100;
        } else if (hud.healthPlayerOne <= 100) {
            spawn();
        }

    }

    public void startSpawner() {

        if (game.gameState == Game.STATE.Game) {
            handler.addObject(new BasicEnemy(r.nextInt(WITDH - 32), r.nextInt(game.HEIGHT - 40), ID.BasicEnemy, handler, hud));
            handler.addObject(new Player(100, 100, ID.Player));

        } else if (game.gameState == Game.STATE.twoPlayer) {

            handler.addObject(new BasicEnemy(r.nextInt(WITDH - 32), r.nextInt(game.HEIGHT - 40), ID.BasicEnemy, handler, hud));
            handler.addObject(new Player(200, 200, ID.Player));
            handler.addObject(new PlayerTwo(400, 200, ID.PlayerTwo));

        }

    }

    public void render(Graphics g) {

    }

}
