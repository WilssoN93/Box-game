package game;

import game.ui.BufferedImageLoader;
import game.states.HowToPlay;
import game.states.Menu;
import game.ui.HUD;
import game.ui.Window;
import game.states.EndScreen;
import game.states.HighScores;
import java.awt.image.BufferStrategy;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Game extends Canvas implements Runnable {

    public static BufferedImage sprite_sheet;
    private static final long serialVersionUID = 1L;
    private Thread thread;
    private boolean running = false;
    public static final int WITDH = 640;
    public static final int HEIGHT = WITDH / 12 * 9;
    
    GameDAO gdao;    
    Handler handler;
    HUD hud;
    Spawn spawn;
    Menu menu;
    HowToPlay play;
    EndScreen endScreen;
    HighScores highscores;

    public enum STATE {
        Game,
        howToPlay,
        twoPlayer,
        endScreenOnePlayer,
        endScreenTwoPlayer,
        highscores,
        Menu;

    }
    public STATE gameState = STATE.Menu;

    public Game() {

        BufferedImageLoader loader = new BufferedImageLoader();

        try {
            sprite_sheet = loader.loadImage("res/SpriteSheet.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        hud = new HUD(this);
        handler = new Handler();
        spawn = new Spawn(handler, hud, this);
        this.addKeyListener(new KeyInput(handler, spawn, this));

        menu = new Menu(this,spawn);
        play = new HowToPlay(handler, hud, this);
        gdao = new GameDAO(hud);
        endScreen = new EndScreen(handler, hud, this, gdao);
        highscores = new HighScores(handler, hud, this, gdao);
        this.addMouseListener(menu);
        this.addMouseListener(play);
        this.addMouseListener(endScreen);
        this.addMouseListener(highscores);

        new Window(WITDH, HEIGHT, "Simple Game", this);

    }

    public synchronized void start() {

        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void tick() {

        handler.tick();
        if (gameState == STATE.Game) {
            hud.tick();
            spawn.tick();
            gdao.addScore();

        } else if (gameState == STATE.Menu) {
            menu.tick();

        } else if (gameState == STATE.howToPlay) {
            play.tick();

        } else if (gameState == STATE.twoPlayer) {
            spawn.tick();
            hud.tick();
        } else if (gameState == STATE.endScreenOnePlayer||gameState==STATE.endScreenTwoPlayer) {
            endScreen.tick();
            
        } else if (gameState == STATE.highscores) {

            highscores.tick();
        }
    }

    public void render() {

        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WITDH, HEIGHT);

        if (gameState == STATE.Game) {
            handler.render(g);
            hud.render(g);

        } else if (gameState == STATE.Menu) {

            menu.render(g);

        } else if (gameState == STATE.howToPlay) {
            play.render(g);

        } else if (gameState == STATE.twoPlayer) {
            handler.render(g);
            hud.render(g);

        } else if (gameState == STATE.endScreenOnePlayer||gameState==STATE.endScreenTwoPlayer) {
            endScreen.render(g);
        } else if (gameState == STATE.highscores) {
            highscores.render(g);
        }
        g.dispose();
        bs.show();

    }

    public static double clamp(double var, double min, double max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }

    }

    public static void main(String[] args) {

        new Game();
    }

}
