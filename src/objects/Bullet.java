package objects;

import framework.GameObject;
import framework.ID;
import framework.SpriteSheet;
import window.Handler;
import window.Main;
import window.STATE;
import window.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static javafx.scene.input.KeyCode.M;
import static window.Main.gameState;


public class Bullet extends GameObject{
    public boolean shoot;
    private BufferedImage bullet;
    Handler handler;
    private Vector2f pos = new Vector2f(0, 0);
    private float x,y, xVel, yVel;
    private float moveSpeed = -10;
    JFrame frame;
    Menu menu;

    public Bullet(Vector2f pos, Handler handler) {
        super(0, 0, ID.Bullet);
        float mx = MouseInfo.getPointerInfo().getLocation().x;
        float my = MouseInfo.getPointerInfo().getLocation().y;
        System.out.println(mx + " " + my);
        if (gameState == STATE.Start || gameState == STATE.Level2) {
            shoot = false;
            this.handler = handler;
            SpriteSheet ss = new SpriteSheet(Main.projectiles);
            bullet = ss.grabImage(1, 1, 21, 21);
            VectorMath.copyVector(this.pos, pos);
            VectorMath.printVector(this.pos);
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tempObject = handler.object.get(i);
                if (tempObject.getID() == ID.Player) {
                    x = tempObject.getX();
                    y = tempObject.getY();
                }
            }
            float tX = x - mx;
            float tY = y - my;
            float mag = (float) java.lang.Math.hypot(tX, tY);
            tX /= mag;
            tY /= mag;
            tX *= moveSpeed;
            tY *= moveSpeed;
            xVel = tX;
            yVel = tY;
            shoot = true;
        }
    }

    public void tick() {
        x += xVel;
        y += yVel;
    }

    public void render(Graphics g) {
        g.drawImage(bullet, (int) x, (int) y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 21, 21);
    }
}
