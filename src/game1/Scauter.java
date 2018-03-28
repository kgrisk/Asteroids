package game1;

import utilities.Controller;
import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.*;

/**
 * Created by zelda on 3/1/2017.
 */
public class Scauter extends Ship   {

    Scauter(Controller ctrl){
        this.ctrl = ctrl;
        RADIUS =40;
        position = new Vector2D(0,Constants.FRAME_HEIGHT*Math.random());
        velocity = new Vector2D(200,200);
        direction = new Vector2D(1, 1);
    }
    public void update() {

        action = ctrl.action();

        // parameters for RotateNShoot saucer

        if(ctrl.getClass() == RotateNShoot.class) {
            direction.rotate(STEER_RATE * action.turn * DT);
            source = "RotateNShoot";
            if (soundPlayer + 200 < System.currentTimeMillis()) {
                soundPlayer = System.currentTimeMillis();
                SoundManager.play(SoundManager.saucerBig);

            }
        }     //parameters for AimNShoot saucer

            else{ source = "AimNShoot";
            if (soundPlayer + 200 < System.currentTimeMillis()) {
                soundPlayer = System.currentTimeMillis();
                SoundManager.play(SoundManager.saucerSmall);

            }
            velocity.addScaled(new Vector2D(direction).mult(2), action.thrust * DT * MAG_ACC);
        }
        position.addScaled(velocity,DT);

        if (action!=null &&action.shoot && shootTime +shootInterval < System.currentTimeMillis()) {
            mkBullet();
            shootTime = System.currentTimeMillis();
            action.shoot = false;
        }
        if(FRAME_HEIGHT < position.y || FRAME_WIDTH < position.x) dead = true;

    }

    private void mkBullet() {
        bullet = new Bullet(new Vector2D(new Vector2D(position).add(new Vector2D(direction).normalise().mult(14))), new Vector2D(direction).normalise().mult(400),"ScouterShip");
        SoundManager.fire();
        // TODO: add code to set bullet position and velocity properly
        // ...
    }
    @Override
    public void draw(Graphics2D g) {
        Sprite sprite = new Sprite(Constants.SCAUTER,position,direction,RADIUS*2,RADIUS*2);
        g.draw(ellipse2D());
        sprite.draw(g);
    }
}
