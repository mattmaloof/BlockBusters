package framework;

import objects.Player;
import window.Handler;
import window.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    Handler handler;

    private BufferedImage ui_start, ui_start_half, ui_start_full, ui_armored, ui_armored_halfe, ui_armored_half;
    public static int DMG = 0;



    public void tick(){
    }

    public void render(Graphics g){
        SpriteSheet ui = new SpriteSheet(Main.ui);
        ui_start = ui.grabImage(1, 5, 64, 64);
        ui_start_half = ui.grabImage(1, 3, 64, 64);
        ui_start_full = ui.grabImage(1, 1, 64, 64);
        ui_armored = ui.grabImage(3, 1, 64, 64);
        ui_armored_halfe = ui.grabImage(3, 3, 64, 64);
        ui_armored_half = ui.grabImage(3, 5, 64, 64);

//        g.setColor(Color.gray);
//        g.fillRect(15, 15, 200 + bounds, 32);
//        g.setColor(new Color(75, (int)greenValue, 0));
//        g.fillRect(15, 15, (int)HEALTH * 2, 32);
//        g.setColor(Color.white);
//        g.drawRect(15, 15, 200 + bounds, 32);
//        g.drawString( HEALTH + "%", 100, 36);
        if (DMG == 0) {
            g.drawImage(ui_start_full, 30, 30, null);
            g.drawImage(ui_start_full, 90, 30, null);
            g.drawImage(ui_start_full, 150, 30, null);
        } else if (DMG == 1) {
            g.drawImage(ui_start_full, 30, 30, null);
            g.drawImage(ui_start_full, 90, 30, null);
            g.drawImage(ui_start_half, 150, 30, null);
        }else if (DMG == 2) {
            g.drawImage(ui_start_full, 30, 30, null);
            g.drawImage(ui_start_full, 90, 30, null);
            g.drawImage(ui_start, 150, 30, null);
        }else if (DMG == 3) {
            g.drawImage(ui_start_full, 30, 30, null);
            g.drawImage(ui_start_half, 90, 30, null);
            g.drawImage(ui_start, 150, 30, null);
        }else if (DMG == 4) {
            g.drawImage(ui_start_full, 30, 30, null);
            g.drawImage(ui_start, 90, 30, null);
            g.drawImage(ui_start, 150, 30, null);
        }else if (DMG == 5) {
            g.drawImage(ui_start_half, 30, 30, null);
            g.drawImage(ui_start, 90, 30, null);
            g.drawImage(ui_start, 150, 30, null);
        }else if (DMG >=6) {
            g.drawImage(ui_start, 30, 30, null);
            g.drawImage(ui_start, 90, 30, null);
            g.drawImage(ui_start, 150, 30, null);
        }

//        g.drawString("Score: " + score, 15, 70);
//        g.drawString("Level: " + level, 15, 86);
//        g.drawString("Spacebar for Shop", 15, 102);
    }


}