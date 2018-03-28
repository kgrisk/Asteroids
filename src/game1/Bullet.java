package game1;

import utilities.Vector2D;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zelda on 2/17/2017.
 */

public class Bullet extends GameObject{

    Timer timer = new Timer();

    public Bullet(Vector2D position, Vector2D direction, String buulletSource){
        RADIUS = 2;

        this.velocity = new Vector2D(direction);
        this.position = new Vector2D(position);
        source = buulletSource;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dead = true;
            }
        }, 3000);
    }





    public void draw(Graphics2D g){
        g.setColor(Color.CYAN);
        g.drawOval((int) (position.x), (int) (position.y), (int) (RADIUS),(int) (RADIUS));
    }

}
