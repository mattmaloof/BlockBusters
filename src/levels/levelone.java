package levels;

import framework.HUD;
import framework.ID;
import objects.Enemy;
import objects.Finish;
import objects.Wall;
import objects.WallH;
import window.Handler;
import window.Main;

import java.awt.*;
import java.util.Random;


public class levelone {

    private Handler handler;
    private HUD hud;
    private Main game;
    private Random r = new Random();
    private int i = 0;

    public levelone(Handler handler, HUD hud, Main game){
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        if (i == 0) {
            handler.addObject(new Wall(210, 808, ID.Wall, handler));
            handler.addObject(new Wall(210, 744, ID.Wall, handler));
            handler.addObject(new Wall(210, 682, ID.Wall, handler));
            handler.addObject(new WallH(178, 652, ID.Wall, handler));
            handler.addObject(new WallH(114, 652, ID.Wall, handler));
            handler.addObject(new Wall(114, 588, ID.Wall, handler));
            handler.addObject(new Wall(114, 524, ID.Wall, handler));
            handler.addObject(new Wall(114, 460, ID.Wall, handler));
            handler.addObject(new WallH(114, 428, ID.Wall, handler));
            handler.addObject(new WallH(178, 428, ID.Wall, handler));
            handler.addObject(new WallH(242, 428, ID.Wall, handler));
            handler.addObject(new WallH(306, 428, ID.Wall, handler));
            handler.addObject(new WallH(370, 428, ID.Wall, handler));
            handler.addObject(new WallH(434, 428, ID.Wall, handler));
            handler.addObject(new Enemy(230, 535, ID.Enemy, handler));
            handler.addObject(new WallH(0, 270, ID.Wall, handler));
            handler.addObject(new WallH(64, 270, ID.Wall, handler));
            handler.addObject(new WallH(128, 270, ID.Wall, handler));
            handler.addObject(new WallH(192, 270, ID.Wall, handler));
            handler.addObject(new WallH(256, 270, ID.Wall, handler));
            handler.addObject(new WallH(320, 270, ID.Wall, handler));
            handler.addObject(new WallH(384, 270, ID.Wall, handler));
            handler.addObject(new WallH(448, 270, ID.Wall, handler));
            handler.addObject(new WallH(512, 270, ID.Wall, handler));
            handler.addObject(new WallH(576, 270, ID.Wall, handler));
            handler.addObject(new WallH(640, 270, ID.Wall, handler));
            handler.addObject(new WallH(704, 270, ID.Wall, handler));
            handler.addObject(new Wall(736, 270, ID.Wall, handler));
            handler.addObject(new Wall(736, 334, ID.Wall, handler));
            handler.addObject(new Wall(736, 398, ID.Wall, handler));
            handler.addObject(new Wall(736, 462, ID.Wall, handler));
            handler.addObject(new Wall(736, 526, ID.Wall, handler));
            handler.addObject(new Wall(736, 590, ID.Wall, handler));
            handler.addObject(new WallH(736, 622, ID.Wall, handler));
            handler.addObject(new WallH(800, 622, ID.Wall, handler));
            handler.addObject(new WallH(864, 622, ID.Wall, handler));
            handler.addObject(new WallH(928, 622, ID.Wall, handler));
            handler.addObject(new Wall(960, 590, ID.Wall, handler));
            handler.addObject(new Wall(960, 536, ID.Wall, handler));
            handler.addObject(new Wall(960, 472, ID.Wall, handler));
            handler.addObject(new Wall(960, 408, ID.Wall, handler));
            handler.addObject(new WallH(960, 270, ID.Wall, handler));
            handler.addObject(new WallH(1024, 270, ID.Wall, handler));
            handler.addObject(new WallH(1088, 270, ID.Wall, handler));
            handler.addObject(new WallH(1152, 270, ID.Wall, handler));
            handler.addObject(new Wall(960, 206, ID.Wall, handler)); //
            handler.addObject(new Wall(960, 142, ID.Wall, handler));
            handler.addObject(new Wall(960, 78, ID.Wall, handler));
            handler.addObject(new Wall(448, 100, ID.Wall, handler));
            handler.addObject(new Wall(448, 36, ID.Wall, handler));
            handler.addObject(new Wall(448, 0, ID.Wall, handler));
            handler.addObject(new Finish(0, 0, ID.Finish, handler));


            i++;
        }

        //handler.addObject(new Wall(200, 232, ID.Wall, handler));
    }

    public void render(Graphics g) {

    }

}
