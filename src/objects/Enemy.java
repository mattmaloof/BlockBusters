package objects;

import framework.GameObject;
import framework.ID;
import framework.SpriteSheet;
import window.Handler;
import window.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Enemy extends GameObject {

    private Handler handler;
    Random r = new Random();
    private BufferedImage senemy;

    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = 0;
        velY = 0;

        SpriteSheet enemy = new SpriteSheet(Main.enemies);

        senemy = enemy.grabImage(1, 1, 32, 32);

    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Main.HEIGHT - 32) velY *= -1;
        //if(x <= 0 || x >= Main.WIDTH - 16) velX *= -1;
        int spawn = r.nextInt(10);
        if (spawn == 0) {
            handler.addObject(new EnemyBullet((int) x + 8, (int) y + 8, ID.EBullet, handler));
        }

        //handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
    }

    public void render(Graphics g) {
        g.drawImage(senemy, (int)x, (int)y, null);
    }
}
