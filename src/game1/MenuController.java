package game1;

import utilities.Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by zelda on 3/9/2017.
 */
public class MenuController extends KeyAdapter {
    private int buttonPressed = 0;
    private boolean back = false;
    private boolean enter = false;
    public MenuController() {

    }

    public int buttonPressed() {
        // this is defined to comply with the standard interface
        return buttonPressed;
    }

    public boolean start() {
        // this is defined to comply with the standard interface
        return enter;
    }
    //scroll up down and enter
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                buttonPressed = (buttonPressed() == 0)? 1: buttonPressed()-1;
                break;
            case KeyEvent.VK_DOWN:
                buttonPressed = (buttonPressed() == 1)? 0: buttonPressed()+1;
                break;
            case KeyEvent.VK_ENTER:
                enter = true;

                break;

        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {


            case KeyEvent.VK_ENTER:
                enter = false;
                break;
        }
    }

}