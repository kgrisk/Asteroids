package utilities;
// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        x = y = 0;
    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        x = v.x;
        y = v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;
    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        Vector2D s = new Vector2D((Vector2D)o);
        return x == s.x && y == s.y;
    }

    // String for displaying vector as text
    public String toString() {
        return("x: " + x + " y: " + y);
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.sqrt((Math.pow(x,2) + Math.pow(y,2)));

    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(y,x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        double rez =(Math.atan2(y,x) - Math.atan2(other.y,other.x));
        if (rez < -Math.PI)
            rez += 2 * Math.PI;
        if (rez > Math.PI)
            rez -= 2 * Math.PI;
        return -rez;
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        x = this.x + v.x;
        y = this.y + v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x =this.x + x;
        this.y =this.y + y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x =this.x + v.x * fac;
        this.y =this.y + v.y * fac;
        return this;

    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        this.x =this.x - v.x;
        this.y = this.y - v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {

        this.x =this.x - x;
        this.y = this.y - y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        x = x * fac;
        y = y * fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double rotateX = (this.x * Math.cos(angle)) - (this.y * Math.sin(angle));
        double rotateY = (this.x * Math.sin(angle)) + (this.y * Math.cos(angle));
        x = rotateX;
        y = rotateY;
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {
        return (x*v.x+y*v.y);
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return (Math.sqrt((Math.pow(Math.abs(x-v.x),2)+Math.pow(Math.abs(y-v.y),2))));
    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        double normX = x/mag();
        double normY = y/mag();
        x = normX;
        y = normY;
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
    public Vector2D wrap(double w, double h) {
        this.x = (this.x + w)%w;
        this.y = (this.y + h)%h;
        return this;

    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        return new Vector2D(Math.cos(angle)*mag, Math.sin(angle)*mag);
    }

}