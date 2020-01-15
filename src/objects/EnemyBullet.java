package objects;

import framework.GameObject;
import framework.ID;
import framework.SpriteSheet;
import window.Handler;
import window.Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBullet extends GameObject {

    private Handler handler;
    Random r = new Random();
    private BufferedImage EBullet;

    public EnemyBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;

        velX = (r.nextInt( 10 + 20));
        velY = 0;

        SpriteSheet enemy = new SpriteSheet(Main.enemies);

        EBullet = enemy.grabImage(1, 2, 21, 21);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 21, 21);
    }

    public void tick() {
        x += velX;
        y += velY;

        //if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        //if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        if(y >= Main.HEIGHT) handler.removeObject(this);

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
