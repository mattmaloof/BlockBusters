package window;

import framework.GameObject;
import framework.ID;
import objects.Player;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();

    public int spd = 5;

    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void clearPlayer(){
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            if(tempObject.getID() == ID.Player) {
                object.clear();
            }
        }
    }

    public void clearEnemies(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            if(tempObject.getID() == ID.Player) {
                object.clear();
                if(Main.gameState != STATE.End) {
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                }
            }
        }
    }

    public void addObject (GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
