package game.ui;

import game.Game;
import game.Handler;
import java.awt.Graphics;
import java.awt.Color;

public class HUD {

    Game game;
    Handler handler;

    public int playerOneScore;
    public int playerTwoScore;
    private int score;
    private int level = 1;
    public int healthPlayerOne = 100;
    public int healthPlayerTwo = 100;
    private double aScore;

    public HUD(Game game) {
        this.game = game;

    }

    public void tick() {

        if (healthPlayerOne > 0) {
            scoreMenu();

        }

    }

    public void scoreMenu() {

        if (game.gameState == Game.STATE.Game) {
            if (score >= 500) {
                score = 0;
                level += 1;
            }
            score++;
            aScore += 0.1;
        } else if (game.gameState == Game.STATE.twoPlayer) {
            if (score >= 250) {
                score = 0;
                level += 1;
            }
            score++;

        }
    }

    public void render(Graphics g) {
        if (game.gameState == Game.STATE.Game) {
            g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 32);
            g.setColor(Color.GREEN);

            g.fillRect(15, 15, healthPlayerOne * 2, 32);
            g.drawRect(15, 15, 200, 32);
        } else if (game.gameState == Game.STATE.twoPlayer) {
            g.setColor(Color.gray);
            g.fillRect(15, 15, 200, 32);
            g.setColor(Color.GREEN);

            g.fillRect(15, 15, healthPlayerOne * 2, 32);
            g.drawRect(15, 15, 200, 32);

            g.setColor(Color.gray);
            g.fillRect(400, 15, 200, 32);
            g.setColor(Color.GREEN);

            g.fillRect(400, 15, healthPlayerTwo * 2, 32);
            g.drawRect(400, 15, 200, 32);

        }
        if (game.gameState == Game.STATE.Game) {
            g.setColor(Color.WHITE);
            g.drawString("Level: " + level, 15, 70);
            g.drawString("Score: " + (int) aScore, 15, 90);
        } else if (game.gameState == Game.STATE.twoPlayer) {
            g.setColor(Color.WHITE);
            g.drawString("Level: " + level, 285, 40);
            g.drawString("Player One Score: " + playerOneScore, 15, 70);
            g.drawString("Player Two Score: " + playerTwoScore, 490, 70);
        }
    }

    public int getLevel() {
        return level;

    }

    public void setLevel(int level) {
        this.level = level;

    }

    public int getScore() {

        return score;

    }

    public void setScore(int score) {
        this.score = score;

    }

    public double getaScore() {
        return aScore;

    }

    public void setaScore(double aScore) {
        this.aScore = aScore;
    }
}
