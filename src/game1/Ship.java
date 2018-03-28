package game1;

import utilities.Controller;
import utilities.Vector2D;

import java.awt.*;

/**
 * Created by zelda on 2/28/2017.
 */
public abstract class Ship extends GameObject {


    // rotation velocity in radians per second
    public static final double STEER_RATE = 2 * Math.PI;

    // acceleration when thrust is applied
    public static final double MAG_ACC = 200;

    // constant speed loss factor
    public static final double DRAG = 0.01;




    // direction in which the nose of the ship is pointing
    // this will be the direction in which thrust is applied
    // it is a unit vector representing the angle by which the ship has rotated
    public Vector2D direction;

    // controller which provides an Action object in each frame
    public Controller ctrl;
    public Action action;

    public Bullet bullet = null;


    public long shootTime;

    long soundPlayer = 0;
}
