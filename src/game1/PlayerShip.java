package game1;

import utilities.Controller;
import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.*;

/**
 * Created by zelda on 2/16/2017.
 */
public class PlayerShip extends Ship {

    public  long respawnInvinsibleTime;

    public int newLevel = 1;

    public static int lives = 3;
    public static boolean respawnShip = false;

    public PlayerShip(Controller ctrl) {
        this.ctrl = ctrl;
        position = new Vector2D(FRAME_WIDTH / 2, FRAME_HEIGHT / 2);
        velocity = new Vector2D();
        direction = new Vector2D(0, -1);
        shootTime = System.currentTimeMillis();
        respawnInvinsibleTime = System.currentTimeMillis();
        RADIUS = 40;
    }

    @Override
    public void hit() {
        if(respawnInvinsibleTime +3000 < System.currentTimeMillis() && newLevel == Game.level){
            lives--;
            if(lives == -1){
                StateManager.gameOver = true;
            }
            dead = true;
            respawnShip = true;
            SoundManager.play(SoundManager.extraShip);
    }}

    //updates ship
    public void update() {

        action = ctrl.action();

        if(newLevel != Game.level){
            newLevel = Game.level;
            respawnInvinsibleTime = System.currentTimeMillis();
        }

        direction.rotate(STEER_RATE * action.turn*DT);
        if (ctrl.action().thrust == 1)
            velocity.addScaled(direction, action.thrust * DT * MAG_ACC);
            position.addScaled(velocity,DT).wrap(FRAME_WIDTH, FRAME_HEIGHT);
            velocity.mult(1-DRAG);


        if (action.shoot && shootTime +shootInterval < System.currentTimeMillis()) {
            mkBullet();
            shootTime = System.currentTimeMillis();
            action.shoot = false;
        }
    }

    //makes a bullet
    private void mkBullet() {
        bullet = new Bullet(new Vector2D(new Vector2D(position).add(new Vector2D(direction).normalise().mult(14))), new Vector2D(direction).normalise().mult(400),"PlayerShip");
        SoundManager.fire();
        // TODO: add code to set bullet position and velocity properly
        // ...
    }

    public void draw(Graphics2D g) {


        Sprite sprite = new Sprite(Constants.SHIP,position,direction,RADIUS,RADIUS);
        if (action!=null && action.thrust == 1) {
            sprite = new Sprite(Constants.SHIP2,position,direction,RADIUS,RADIUS);
            if (soundPlayer + 200 < System.currentTimeMillis()) {
                soundPlayer = System.currentTimeMillis();
                SoundManager.play(SoundManager.thrust);

            }
        }
        sprite.draw(g);
        if(respawnInvinsibleTime +3000 > System.currentTimeMillis()){


            g.setColor(new Color((int)(250 * Math.random()),250,(int)(250 * Math.random())));
            g.setFont(new Font("Arial",Font.ITALIC,70));
            g.drawString( "NIAM NIAM NIAM!!! ", (int)(position.x-300),(int)(position.y+100- Constants.FRAME_SIZE.getHeight()/2));
    }
        g.draw(ellipse2D());

    }
}