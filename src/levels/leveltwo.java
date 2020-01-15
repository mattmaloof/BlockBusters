package levels;

import framework.HUD;
import framework.ID;
import objects.Wall;
import window.Handler;
import window.Main;

import java.awt.*;

public class leveltwo {
    Handler handler;
    HUD hud;
    Main game;

    public leveltwo(Handler handler, HUD hud, Main game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        //handler.addObject(new Wall(200, 200, ID.Wall, handler));
        //handler.addObject(new Wall(200, 232, ID.Wall, handler));

    }

    public void render(Graphics g) {
    }
}
