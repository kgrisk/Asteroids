package game1;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import utilities.Controller;
/**
 * Created by zelda on 2/16/2017.
 */
public class PlayerKeysController extends KeyAdapter implements Controller {
    Action action;
    static boolean pouse = false;
    public PlayerKeysController() {
        action = new Action();
    }

    public Action action() {
        // this is defined to comply with the standard interface
        return action;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 1;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = +1;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = true;
                break;
            case KeyEvent.VK_V:
                if(pouse) pouse = false;
                else pouse = true;
                break;
            case KeyEvent.VK_Y:
                if(StateManager.gameOver) {
                    StateManager.gameOver = false;
                    StateManager.game = new Game();
                    Game.level =1;
                    Game.score = 0;
                    PlayerShip.lives = 3;
                    Game.untilNextBonus = 0;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                action.thrust = 0;
                break;
            case KeyEvent.VK_LEFT:
                action.turn = 0;
                break;
            case KeyEvent.VK_RIGHT:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = false;
                break;
        }
    }
}