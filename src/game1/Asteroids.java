package game1;

import utilities.Vector2D;

import java.awt.*;

import static game1.Constants.FRAME_HEIGHT;
import static  game1.Constants.*;
/**
 * Created by zelda on 1/24/2017.
 */

public class Asteroids extends GameObject {


    Asteroids(){}

    public Asteroids(double x, double y, double vx, double vy) {
        RADIUS =50;
        position = new Vector2D(x,y);
        velocity = new Vector2D(vx,vy);
    }



    public static Asteroids makeRandomAsteroid() {
        Asteroids obj;
        obj = new Asteroids(Math.random()* FRAME_HEIGHT, Math.random() * FRAME_HEIGHT,Math.random() * MAX_SPEED,Math.random()*MAX_SPEED);
        return obj;
    }




        //draws asteroids
    public void draw(Graphics2D g) {
        Sprite sprite = new Sprite(Constants.ASTEROID1,position,velocity,RADIUS*2,RADIUS*2);
        sprite.draw(g);
        g.draw(ellipse2D());
//
    }
}