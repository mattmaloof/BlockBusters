package framework;

import levels.WorldSave;
import objects.Bullet;
import window.Handler;
import window.Main;
import window.STATE;
import window.SaveGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private int currentLevelID = 1;
    private boolean[] keyDown = new boolean[4];
    boolean keySave, keyLoad;
    private int currentLevel;
    Level level;

    Main game;

    public KeyInput(Handler handler, Main game) {
        this.handler = handler;
        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-handler.spd);
                    keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(handler.spd);
                    keyDown[1] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(handler.spd);
                    keyDown[2] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-handler.spd);
                    keyDown[3] = true;
                }
                if (key == KeyEvent.VK_ESCAPE){
                    if (Main.gameState == STATE.Start || Main.gameState == STATE.Level2) {
                        if (Main.paused) Main.paused = false;
                        else Main.paused = true;
                    }
                }
                if (key == KeyEvent.VK_J) {
                    keySave = true;
                }
                if (key == KeyEvent.VK_K) {
                    keyLoad = true;
                }
                if (keySave) {

                    WorldSave levelToSave = new WorldSave(currentLevelID);
                    SaveGame.save(levelToSave);

                } else if (keyLoad) {

                    WorldSave levelFromFile = SaveGame.load();
                    //game(LevelFactory.buildLevel(levelFromFile.getCurrentLevelID()));
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getID() == ID.Player) {
                //key events for player 1

                if (key == KeyEvent.VK_W) keyDown[0] = false; //tempObject.setVelY(0);
                if (key == KeyEvent.VK_S) keyDown[1] = false; //tempObject.setVelY(0);
                if (key == KeyEvent.VK_D) keyDown[2] = false; //tempObject.setVelX(0);
                if (key == KeyEvent.VK_A) keyDown[3] = false; //tempObject.setVelX(0);
                if (key == KeyEvent.VK_J) {
                    keySave = false;
                }
                if (key == KeyEvent.VK_K) {
                    keyLoad = false;
                }

                //vertical movement
                if (!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

            }
        }


    }
}