package window;

import Sound.MusicPlayer;
import framework.HUD;
import framework.KeyInput;
import framework.SpriteSheet;
import levels.levelone;
import levels.leveltwo;
import objects.Bullet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;


public class Main extends Canvas implements Runnable {

    public static final int WIDTH = 1200, HEIGHT = 900;
    public static final String filename = "TestOutput.sav";

    private Thread thread;
    private boolean running = false;

    public static boolean paused = false;
    public int diff = 0;
    public static int finalframes;

    private Handler handler;
    private HUD hud;
    private Menu menu;
    private Bullet bullet;
    private STATE state;
    private levelone lvlone;
    private leveltwo lvltwo;

    public static STATE gameState = STATE.Menu;
    public static String title = "BlockBusters";

    //public static BufferedImage player;
    public static BufferedImage start_background, level_two_background, menu_button1, menu_button2, sprite_sheet_2, sprite_sheet_3, sprite_sheet_4, sprite_sheet_5, ui, projectiles, level_select, environment, pause_background, help_background, enemies;
    public static MusicPlayer mplayer, level1, bpress, level_two, shoot;


    public Main() {

        BufferedImageLoader loader = new BufferedImageLoader();

        //player = loader.loadImage("/player.png");
        sprite_sheet_2 = loader.loadImage("/menu_buttons.png");
        sprite_sheet_3 = loader.loadImage("/menu_background.png");
        sprite_sheet_4 = loader.loadImage("/start_background.png");
        sprite_sheet_5 = loader.loadImage("/level_two_background.png");
        ui = loader.loadImage("/ui.png");
        projectiles = loader.loadImage("/projectiles.png");
        level_select = loader.loadImage("/level_select.png");
        environment = loader.loadImage("/environment.png");
        pause_background = loader.loadImage("/pause_background.png");
        help_background = loader.loadImage("/help_background.png");
        enemies = loader.loadImage("/enemies.png");



        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        this.addMouseMotionListener(menu);
        mplayer = new MusicPlayer("background");
        level1 = new MusicPlayer("start_music");
        bpress = new MusicPlayer("button_press");
        level_two = new MusicPlayer("level_two");
        shoot = new MusicPlayer("shoot");

        new Window(WIDTH, HEIGHT, "BlockBusters", this);
        mplayer.run();
        lvlone = new levelone(handler, hud, this);
        lvltwo = new leveltwo(handler, hud, this);

        SpriteSheet ss2 = new SpriteSheet(Main.sprite_sheet_2);
        SpriteSheet ss4 = new SpriteSheet(Main.sprite_sheet_4);
        SpriteSheet ls = new SpriteSheet(Main.level_select);
        SpriteSheet pb = new SpriteSheet(Main.pause_background);
        SpriteSheet ss5 = new SpriteSheet(Main.sprite_sheet_5);
        start_background = ss4.grabImage(1, 1, 1200, 900);
        level_select = ls.grabImage(1, 1, 1200, 900);
        pause_background = pb.grabImage(1, 1, 1200, 900);
        menu_button1 = ss2.grabImage(1, 1, 224, 64);
        menu_button2 = ss2.grabImage(3, 1, 224, 64);
        level_two_background = ss5.grabImage(1, 1, 1200, 900);
    }

    public static boolean checkFileExists() {
        return new File(filename).isFile();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        finalframes = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                finalframes = frames;
                frames = 0;
            }
        }
        stop();
    }

    public void tick() {
        if (gameState == STATE.Start) {
            if (!paused) {
                hud.tick();
                handler.tick();
                lvlone.tick();
            }
        }
        if (gameState == STATE.Level2) {
            if (!paused) {
                hud.tick();
                handler.tick();
                lvltwo.tick();
            }
        }
        if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select || gameState == STATE.Help) {
            menu.tick();
            handler.tick();
        }
    }


    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused) {
            g.drawImage(pause_background, 0, 0, null);
            Font fnt = new Font("arial", 1, 50);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Paused", 435, 300);
            g.drawString("Press Escape to go back in game.", 200, 400);
            Font fnt2 = new Font("arial", 1, 30);
            if (menu.back) {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button1, 485, 607, null);
                g.drawString("Menu", 563, 650);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button2, 485, 607, null);
                g.drawString("Menu", 563, 650);
            }
        }

        if (gameState == STATE.Start) {
            if (!paused) {
                g.drawImage(start_background, 0, 0, null);
                hud.render(g);
                handler.render(g);
                //} else if (gameState == window.STATE.Shop){
                //shop.render(g);
            }
        } else if (gameState == STATE.Level2) {
            if (!paused) {
                g.drawImage(level_two_background, 0, 0, null);
                hud.render(g);
                handler.render(g);
                //} else if (gameState == window.STATE.Shop){
                //shop.render(g);
            }
        } else if (gameState == STATE.Select) {
            g.drawImage(level_select, 0, 0, null);
            menu.render(g);
            handler.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
            menu.render(g);
            handler.render(g);
        }
        g.dispose();
        bs.show();
    }

        public static float clamp(float var, float min, float max){
            if (var >= max)
                return var = max;
            else if (var <= min)
                return var = min;
            else
                return var;
        }

        public static void main (String[]args){
            new Main();
        }
    }
