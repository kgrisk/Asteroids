package game1;

import utilities.JEasyFrame;

import java.awt.*;
import java.util.List;

import static game1.Constants.DELAY;

/**
 * Created by zelda on 3/5/2017.
 */
public abstract class StateManager {
    public static boolean running = true;
    public PlayerKeysController ctrl;
    public  MenuController menuCtrl;
    static JEasyFrame frame;
    public List<GameObject> objects;
    public PlayerShip ship;
    public static StateManager game;
    public static boolean gameOver = false;

    public static void main(String[] args) throws Exception {
        game = new GameMenu();

        BasicView view = new BasicView();



        frame = new JEasyFrame(view, "Basic Game");
        frame.addKeyListener(game.menuCtrl);

        // run the game
        while (running) {
            synchronized (Game.class){
            if(game.getClass() == Game.class ){ frame.addKeyListener(game.ctrl);

            }
            }
            game.update();
            view.repaint();
            Thread.sleep(DELAY);
        }
    }
    public abstract void update();
    public abstract void draw(Graphics g0);
}
