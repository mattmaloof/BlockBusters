package objects;

import framework.GameObject;
import framework.ID;
import framework.SpriteSheet;
import window.Handler;
import window.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Finish extends GameObject {

    private Handler handler;
    private BufferedImage walls;

    public Finish(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        //SpriteSheet ss = new SpriteSheet(Main.environment);
        //walls = ss.grabImage(1, 1, 32, 64);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 20, 300);
    }

    public void tick() {

    }

    public void render(Graphics g) {
//        for (int i = 0; i < handler.object.size(); i++) {
//            framework.GameObject tempObject = handler.object.get(i);
//            if (tempObject.getID() == framework.ID.objects.Player){
        g.setColor(Color.green);
        g.fillRect(0,0,20,265);
    }
}
