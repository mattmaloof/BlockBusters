package objects;

import framework.GameObject;
import framework.HUD;
import framework.ID;
import framework.Sprite;
import window.Animation;
import window.Handler;
import window.Main;
import window.STATE;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Player extends GameObject implements Serializable {
    Handler handler;

    private BufferedImage[] startPlayer = {Sprite.getSprite(0, 0), Sprite.getSprite(0, 1), Sprite.getSprite(0, 2), Sprite.getSprite(0, 3), Sprite.getSprite(0, 4), Sprite.getSprite(0, 5)};
    private Animation startPlr = new Animation(startPlayer, 8);
    private Animation animation = startPlr;
    private float oldX, oldY;
    private static int dmg;

    //private BufferedImage player_image;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        animation.start();
        this.dmg = 0;
        //framework.SpriteSheet ss = new framework.SpriteSheet(window.Main.player);
        //player_image = ss.grabImage(1, 1, 64, 64);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 64, 64);
    }

    public void tick() {
        oldX = getX();
        oldY = getY();
        x += velX;
        y += velY;
        if (velX < 0) direction = -1;
        else if (velX > 0) direction = 1;

        x = Main.clamp((int) x, 0, Main.WIDTH - 70);
        y = Main.clamp((int) y, 0, Main.HEIGHT - 93);

        animation.update();

        //handler.addObject(new Trail((int)x, (int)y, framework.ID.Trail, Color.white, 32, 32, 0.05f, handler));

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Wall) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handleCollision();
                }
                for (int j = 0; j < handler.object.size(); j++) {
                    GameObject tempObject1 = handler.object.get(j);
                    if (tempObject1.getID() == ID.Bullet) {
                        if (tempObject.getBounds().intersects(tempObject1.getBounds())) {
                            handler.removeObject(tempObject);
                            handler.removeObject(tempObject1);
                        }
                    }
                }
                for (int k = 0; k < handler.object.size(); k++) {
                    GameObject tempObject2 = handler.object.get(k);
                    if (tempObject2.getID() == ID.EBullet) {
                        if (tempObject.getBounds().intersects(tempObject2.getBounds())){
                            handler.removeObject(tempObject2);
                        }
                    }
                }
            }
            if (tempObject.getID() == ID.Finish) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.clearEnemies();
                    handler.clearPlayer();
                    Main.level1.stop();
                    Main.mplayer.run();
                    Main.gameState = STATE.Select;
                }
            }
            if (tempObject.getID() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.DMG++;
                    handleCollision();

                }
            }
            if (tempObject.getID() == ID.EBullet) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.DMG++;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void handleCollision() {
        setX(oldX);
        setY(oldY);
    }

    public void render(Graphics g) {
        //g.setColor(Color.white);
        g.drawImage(animation.getSprite(), (int) x, (int) y, null);
        //g.drawImage(player_image, (int) x, (int) y, null);
    }
}
