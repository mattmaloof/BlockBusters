package objects;

import framework.GameObject;
import framework.ID;
import framework.SpriteSheet;
import window.Handler;
import window.Main;

import java.awt.*;
import java.awt.image.BufferedImage;


public class WallH extends GameObject {

    private Handler handler;
    private BufferedImage walls;

    public WallH(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        SpriteSheet ss = new SpriteSheet(Main.environment);
        walls = ss.grabImage(1, 3, 64, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 32);
    }

    public void tick() {

    }

    public void render(Graphics g) {
//        for (int i = 0; i < handler.object.size(); i++) {
//            framework.GameObject tempObject = handler.object.get(i);
//            if (tempObject.getID() == framework.ID.objects.Player){
        g.drawImage(walls, (int)x, (int)y, null);
    }
}
