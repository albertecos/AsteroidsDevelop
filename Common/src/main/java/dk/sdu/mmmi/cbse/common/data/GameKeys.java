package dk.sdu.mmmi.cbse.common.data;

public class GameKeys {
    private static boolean[] keys;
    private static boolean[] pkeys;

    private static final int NUM_KEYS =  4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;

    public GameKeys() {
        keys = new boolean[NUM_KEYS];
        pkeys = new boolean[NUM_KEYS];
    }

    public void update(){
        for (int i = 0; i < NUM_KEYS; i++) {
            pkeys[i] = keys[i];
        }
    }

    public static void setKeys(int k,  boolean b) {
        keys[k] = b;
    }

    public static boolean isDown(int k) {return keys[k];}

    public boolean inPressed(int k) {return keys[k] && !pkeys[k];}
}
