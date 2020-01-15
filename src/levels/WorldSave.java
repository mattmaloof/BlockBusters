package levels;

import java.io.Serializable;

public class WorldSave implements Serializable {

    private static final long serialVersionUID = 6314938481976536946L;
    private int currentLevelID;


    public WorldSave(int currentLevelID) {
        this.currentLevelID = currentLevelID;
    }

    public int getCurrentLevelID() {
        return currentLevelID;
    }


}
