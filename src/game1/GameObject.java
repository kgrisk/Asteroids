package game1;

import utilities.Vector2D;
import java.awt.Shape;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.GregorianCalendar;


import static game1.Constants.*;

/**
 * Created by zelda on 2/17/2017.
 */
public abstract class GameObject {
    public Vector2D position; // on frame
    public Vector2D velocity; // per second
    public Boolean dead = false;

    //object radius
    public double RADIUS;

    //object tag
    public String source = "";

    //on collision
    public void hit(){
        dead = true;
    }

    //updates object
    public void update(){
        position.addScaled(velocity, DT);
        position.wrap(FRAME_WIDTH, FRAME_HEIGHT);
    }
    // checks if two objects overlaps
    public boolean overlap(GameObject other){
        Area area = new Area(ellipse2D());
        // TODO: simple overlap detection based on bounding circles
        area.intersect(new Area(other.ellipse2D()));
        return !area.isEmpty();
    }

    public void collisionHandling(GameObject other) {
        if (this.getClass() != other.getClass() && this.overlap(other)) {
            this.hit();
            other.hit();
        }
    }

    //paints boundaries
    public Ellipse2D ellipse2D(){
        return new Ellipse2D.Double(position.x-RADIUS/2,position.y-RADIUS/2,RADIUS,RADIUS);
    }

    public abstract void draw(Graphics2D g);

}
