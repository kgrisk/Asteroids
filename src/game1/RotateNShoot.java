package game1;

import utilities.Controller;

/**
 * Created by zelda on 2/28/2017.
 */
public class RotateNShoot implements Controller {
    Action action = new Action();
    @Override
    public Action action() {
        action.shoot = true;
        action.turn = 1;
        return action;
    } }