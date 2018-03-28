package game1;

import utilities.ImageManager;

import java.awt.*;
import java.io.IOException;

/**
 * Created by zelda on 1/24/2017.
 */
public class Constants {
    public static final int FRAME_HEIGHT = 1280;
    public static final int FRAME_WIDTH = 2560;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Dimension FRAME_SIZE = new Dimension(
            Constants.FRAME_WIDTH/2, Constants.FRAME_HEIGHT/2);
    public static final int DELAY = 20;  // in milliseconds
    public static final double DT = DELAY / 1000.0;  // in seconds
    public static final Color BG_COLOR = Color.black;
    public static final double MAX_SPEED = 100;
    public static final int N_INITIAL_ASTEROIDS = 5;
    public static Image ASTEROID1, MILKYWAY, SHIP, SHIP2,SCAUTER;
    public static int shootInterval = 500;
    static {
        try {
            ASTEROID1 = ImageManager.loadImage("asteroid");
            MILKYWAY = ImageManager.loadImage("Background");
            SHIP = ImageManager.loadImage("Ship");
            SHIP2 = ImageManager.loadImage("Ship2");
            SCAUTER = ImageManager.loadImage("Scauter");
        } catch (IOException e) { e.printStackTrace(); }
    }
}