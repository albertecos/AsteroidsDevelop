package dk.sdu.mmmi.cbse.common.data;

public class GameData {
    private int displayWidth = 800;
    private int displayHeight = 800;
    private final GameKeys keys = new GameKeys();

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public GameKeys getKeys() {
        return keys;
    }
}
