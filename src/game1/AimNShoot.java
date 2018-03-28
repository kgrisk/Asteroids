package game1;

import utilities.Controller;

import java.util.Map;

/**
 * Created by zelda on 3/17/2017.
 */
public class AimNShoot implements Controller {
    Action action = new Action();
    Game game;
    double movingSpeed = 200;
    AimNShoot(Game game){

        this.game = game;
    }
    //more complex AI who follows you
    @Override
    public Action action() {
        action.shoot = false;
        try {
            double yDistance = (game.ship.position.y - game.scauter.position.y);
            double xDistance = (game.ship.position.x - game.scauter.position.x);
            double angle = Math.atan2(yDistance, xDistance);
            if (Math.abs(yDistance) > 250 || Math.abs(xDistance) > 250) {
                game.scauter.velocity.y = Math.sin(angle) * movingSpeed * ((game.level>4)? 4*3:game.level*0.3);
                game.scauter.velocity.x = Math.cos(angle) * movingSpeed * ((game.level>4)? 4*3:game.level*0.3);
            } else {
                action.shoot = true;
            }
            game.scauter.direction.x = Math.cos(angle);
            game.scauter.direction.y = Math.sin(angle);

        } catch (Exception e) {
            System.out.println(e);
        }
        return action;
    }
}