package window;

import framework.HUD;
import framework.ID;
import framework.SpriteSheet;
import objects.Bullet;
import objects.Player;
import objects.Vector2f;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Menu extends MouseAdapter {

    private Main game;
    private Handler handler;
    private HUD hud;
    private BufferedImage menu_button1;
    private BufferedImage menu_button2;
    private BufferedImage menu_button3;
    private BufferedImage menu_background;
    private BufferedImage help_background;
    private MouseListener mouseListener;
    private Vector2f pos = new Vector2f(0, 0);
    boolean play, help, quit, back, l1, l2, clicked;
    private int num;
    public int mx, my;
    float mouseAngle;
    Bullet bullet;
    public Player plrStart;


    public Menu(Main game, Handler handler, HUD hud) {
        this.game = game;
        this.hud = hud;
        this.handler = handler;

        SpriteSheet ss2 = new SpriteSheet(Main.sprite_sheet_2);
        SpriteSheet ss3 = new SpriteSheet(Main.sprite_sheet_3);
        SpriteSheet help = new SpriteSheet(Main.help_background);

        menu_button1 = ss2.grabImage(1, 1, 224, 64);
        menu_button2 = ss2.grabImage(3, 1, 224, 64);
        menu_button3 = ss2.grabImage(5, 1, 224, 64);
        menu_background = ss3.grabImage(1, 1, 1200, 900);
        help_background = help.grabImage(1, 1, 1200, 900);
        plrStart = new Player(75, 750, ID.Player, handler);

    }

    public void mouseReleased(MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        if (game.gameState == STATE.Menu) {

            //play button
            if (mouseOver(mx, my, 468, 250, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                game.gameState = STATE.Select;

                //handler.addObject(new objects.Player(window.Main.WIDTH / 2 - 32, window.Main.HEIGHT / 2 - 32, framework.ID.objects.Player, handler));
                //AudioPlayer.getSound("menu_sound").play();
                return;
            }

            //help button
            if (mouseOver(mx, my, 468, 350, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                game.gameState = STATE.Help;


                //AudioPlayer.getSound("menu_sound").play();
            }

            //quit button
            if (mouseOver(mx, my, 468, 450, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                System.exit(1);

                //AudioPlayer.getSound("menu_sound").play();
            }
        }
        //back button
        if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 485, 507, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                game.gameState = STATE.Menu;

                //AudioPlayer.getSound("menu_sound").play();
                return;
            }
        }
        if (game.gameState == STATE.Select) {

            //Level 1
            if (mouseOver(mx, my, 100, 250, 200, 64)) {
                clicked = false;
                handler.addObject(plrStart);
                game.bpress.run1();
                game.gameState = STATE.Start;
                Main.mplayer.stop();
                Main.level1.run();
            }

            //Level 2
            if (mouseOver(mx, my, 350, 250, 200, 64)) {
                //handler.addObject(plr);
                game.bpress.run1();
                game.gameState = STATE.Level2;
                Main.mplayer.stop();
                Main.level_two.run();
            }

            //Back
            if (mouseOver(mx, my, 485, 607, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                game.gameState = STATE.Menu;
            }

        }
        if (Main.paused) {
            if (game.gameState == STATE.Start) {
                if (mouseOver(mx, my, 485, 607, 200, 64)) {
                    clicked = false;
                    game.bpress.run1();
                    handler.clearEnemies();
                    handler.clearPlayer();
                    Main.level1.stop();
                    Main.mplayer.run();
                    game.gameState = STATE.Menu;
                    Main.paused = false;
                }
            }
        }
        if (Main.paused) { if (game.gameState == STATE.Level2) {
            if (mouseOver(mx, my, 485, 607, 200, 64)) {
                clicked = false;
                game.bpress.run1();
                handler.clearEnemies();
                handler.clearPlayer();
                Main.level_two.stop();
                Main.mplayer.run();
                game.gameState = STATE.Menu;
                Main.paused = false;
            }
        }
        }
    }


    public void mousePressed(MouseEvent e) {
        if (game.gameState == STATE.Start || game.gameState == STATE.Level2) {
            if (!Main.paused) {
                game.shoot.run1();
                handler.addObject(new Bullet(pos, handler));
                // mouseAngle = (float) Math.toDegrees((float) Math.atan2(e.getY() - plr.getY(), e.getX() - plr.getX()));
            }
        }
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        if (game.gameState == STATE.Menu) {
            //play button
            if (e.getX() >= 468 && e.getX() <= 692 && e.getY() >= 250 && e.getY() <= 314) {
                play = false;
            } else {
                play = true;
            }

            //help button
            if (e.getX() >= 468 && e.getX() <= 692 && e.getY() >= 350 && e.getY() <= 414) {
                help = false;
            } else {
                help = true;
            }

            //quit button
            if (e.getX() >= 468 && e.getX() <= 692 && e.getY() >= 450 && e.getY() <= 514) {
                quit = false;
            } else {
                quit = true;
            }

            //back button
            if (e.getX() >= 485 && e.getX() <= 709 && e.getY() >= 507 && e.getY() <= 571) {
                back = false;
            } else {
                back = true;
            }
        } else if (game.gameState == STATE.Help) {
            //back button
            if (e.getX() >= 485 && e.getX() <= 709 && e.getY() >= 507 && e.getY() <= 571) {
                back = false;
            } else {
                back = true;
            }
        } else if (game.gameState == STATE.Select) {

            //l1 button
            if (e.getX() >= 100 && e.getX() <= 324 && e.getY() >= 250 && e.getY() <= 314) {
                l1 = false;
            } else {
                l1 = true;
            }

            //l2 button
            if (e.getX() >= 350 && e.getX() <= 574 && e.getY() >= 250 && e.getY() <= 314) {
                l2 = false;
            } else {
                l2 = true;
            }

            //back button
            if (e.getX() >= 485 && e.getX() <= 709 && e.getY() >= 607 && e.getY() <= 671) {
                back = false;
            } else {
                back = true;
            }

        } else if (game.gameState == STATE.Start || game.gameState == STATE.Level2) {
            if (Main.paused) {
                if (e.getX() >= 485 && e.getX() <= 709 && e.getY() >= 607 && e.getY() <= 671) {
                    back = false;
                } else {
                    back = true;
                }
            }
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public void tick() {

    }

    public void render(Graphics g) {
        if (game.gameState == STATE.Menu) {
            g.drawImage(menu_background, 0, 0, null);
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);

            g.setFont(fnt);
            g.setColor(Color.cyan);
            g.drawString(Main.title, 420, 170);

            //play button
            if (play == true) {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button1, 468, 250, null);
                g.drawString("Play", 550, 293);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button2, 468, 250, null);
                g.drawString("Play", 550, 293);
            }
            //help button
            if (help == true) {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button1, 468, 350, null);
                g.drawString("Help", 550, 393);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button2, 468, 350, null);
                g.drawString("Help", 550, 393);
            }

            //quit button
            if (quit == true) {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button1, 468, 450, null);
                g.drawString("Quit", 550, 493);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button2, 468, 450, null);
                g.drawString("Quit", 550, 493);
            }


        } else if (game.gameState == STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.drawImage(help_background, 0, 0, null);
            g.setFont(fnt);
            g.setColor(Color.orange);
            g.drawString("Help", 550, 170);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies.", 360, 280);
            g.drawString("Press Spacebar to use the shop.", 445, 320);
            g.drawString("2 Powerups: Fast-moving purple = health bonus, ", 360, 360);
            g.drawString("Fast-moving yellow = score bonus.", 435, 400);

            //back button
            if (back == true) {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button1, 485, 507, null);
                g.drawString("Back", 563, 550);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button2, 485, 507, null);
                g.drawString("Back", 563, 550);
            }
        } else if (game.gameState == STATE.Select) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.RED);
            g.drawString("Level Select", 450, 100);

            //Level 1
            if (l1 == true) {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button1, 100, 250, null); //y43
                g.drawString("Level 1", 160, 293);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button2, 100, 250, null);
                g.drawString("Level 1", 160, 293);
            }
            //Level 2
            if (l2 == true) {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button1, 350, 250, null);
                g.drawString("Level 2", 410, 293);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawImage(menu_button2, 350, 250, null);
                g.drawString("Level 2", 410, 293);
            }

            //Back button
            if (back == true) {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button1, 485, 607, null);
                g.drawString("Back", 563, 650);
            } else {
                g.setFont(fnt2);
                g.setColor(Color.pink);
                g.drawImage(menu_button2, 485, 607, null);
                g.drawString("Back", 563, 650);
            }
        }
    }
    public float getMouseAngle() {
        return this.mouseAngle;
    }

}