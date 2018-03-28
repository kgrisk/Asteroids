package game1;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import static game1.Constants.*;

/**
 * Created by zelda on 1/24/2017.
 */
public class Game extends StateManager {




    public Scauter scauter;
    public static int level = 1;
    public static double score = 0;
    public static double untilNextBonus = 0;
    Timer rotationEnemyTimer = new Timer();


    //***draw method things********
    Image im = Constants.MILKYWAY;
    AffineTransform bgTransf;
    double stretchx, stretchy;


    public Game() {

        //***draw method things*********///
        double imWidth = im.getWidth(null);
        double imHeight = im.getHeight(null);
        stretchx = (imWidth > Constants.FRAME_WIDTH? 1 :
                Constants.FRAME_WIDTH/imWidth);
        stretchy = (imHeight > Constants.FRAME_HEIGHT? 1 :
                Constants.FRAME_HEIGHT/imHeight);
        bgTransf = new AffineTransform();
        bgTransf.scale(stretchx, stretchy);
        //*************************************////

        objects = new ArrayList<GameObject>();
        MakeBigAsteroids();
        ctrl = new PlayerKeysController();
        ship = new PlayerShip(ctrl );
        objects.add(ship);
        AddEnemies(this);

    }

    //adds enemy
    void AddEnemies(Game game){
        rotationEnemyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(scauter == null || scauter.dead) {
                    if (level < 2)
                        scauter = new Scauter(new RotateNShoot());
                    else
                        scauter = new Scauter(new AimNShoot(game));

                    synchronized (Game.class) {
                        objects.add(scauter);
                    }
                }

            }
        },300, 20000);
    }

    //updates all objects
    public void update() {
        synchronized (Game.class) {
            if(!PlayerKeysController.pouse){
                NoMoreAsteroidsLeft();

                ShipRespowning();

                NewBullets();

                collisions();
                List<GameObject> alive = new ArrayList<>();
                alive = NewAsteroidsAfterCollision(alive);

                SortingObjectList(alive);
            }

        }

    }
        //checks if level ir cleared
    void NoMoreAsteroidsLeft(){
        int cointains = 0;
        for (GameObject object : objects) {
            if (Asteroids.class.isInstance(object)) cointains++;
        }
        if (cointains == 0) {
            level++;
            MakeBigAsteroids();
        }
    }
    //makes new asteroids at the end of level
    void MakeBigAsteroids() {
        for (int i = 0; i < level; i++) {
            objects.add(Asteroids.makeRandomAsteroid());
        }
    }
    //respows the ship after collision
    void ShipRespowning(){
        if (PlayerShip.respawnShip && !gameOver) {
            ship = new PlayerShip(ctrl);
            objects.add(ship);
            PlayerShip.respawnShip = false;
        }
    }

    //makes new bullets
    void NewBullets(){
            if (ship.bullet != null) {
                objects.add(ship.bullet);
                ship.bullet = null;
            }
            if(scauter != null)
            if (scauter.bullet != null) {
                objects.add(scauter.bullet);
                scauter.bullet = null;
            }

    }
    //handles all the collisions
    void collisions() {
        int i = 0;
            for (GameObject a : objects) {
                i++;
                for (int second = i; second < objects.size(); second++) {

                    if (!a.equals(objects.get(second)) &&
                            (!(PlayerShip.class.isInstance(a) && objects.get(second).source.equals("PlayerShip")) &&
                                    !(PlayerShip.class.isInstance(objects.get(second)) && a.source.equals("PlayerShip")) &&
                                    !(Scauter.class.isInstance(a) && Asteroids.class.isInstance(objects.get(second))) &&
                                    !(Scauter.class.isInstance(objects.get(second)) && Asteroids.class.isInstance(a)) &&
                                    !(Scauter.class.isInstance(a) && objects.get(second).source.equals("ScouterShip")) &&
                                    !(Scauter.class.isInstance(objects.get(second)) && a.source.equals("ScouterShip")) &&
                                    !(a.source.equals("ScouterShip") && Asteroids.class.isInstance(objects.get(second))) &&
                                    !(objects.get(second).source.equals("ScouterShip") && Asteroids.class.isInstance(a)))) {
                        a.collisionHandling(objects.get(second));
                    }
            }
        }
    }
    // divides asteroids after collision and calls for method to add points
    List<GameObject> NewAsteroidsAfterCollision(List<GameObject> alive) {
        Asteroids asteroid = new Asteroids();
        for (GameObject c : objects) {
            if (c.getClass() == asteroid.getClass() && c.dead) {
                incScore(c.RADIUS);
                if (c.RADIUS > 13) {
                    asteroid = (Asteroids) c;
                    double newRadius = c.RADIUS / 2;
                    for (int g = 0; g < N_INITIAL_ASTEROIDS; g++) {
                        asteroid = new Asteroids(c.position.x, c.position.y, Math.random() * MAX_SPEED, Math.random() * MAX_SPEED);
                        asteroid.RADIUS = newRadius;
                        alive.add(asteroid);
                    }
                }
            }if(c.getClass() == Scauter.class && c.dead){
                if(c.source == "RotateNShoot")incScore(200);
                else incScore(1000);
            }
        }
        return alive;
    }
    //adds score
    void incScore(double radius) {
        double beforeScore = score;
        if (radius == 50) {
            score += 20;
            SoundManager.play(SoundManager.bangLarge);
        }
        if (radius == 25) {
            SoundManager.play(SoundManager.bangMedium);
            score += 50;
        }
        if (radius == 12.5) {
            SoundManager.play(SoundManager.bangSmall);
            score += 100;
        }
        if (radius == 200) {
            SoundManager.play(SoundManager.bangMedium);
            score += 200;
        }
        if (radius == 1000) {

            score += 1000;
        }
        addScore(score - beforeScore);

    }
    //add live then the score is reached
    void addScore(double lScore) {
        untilNextBonus += lScore;
        if (untilNextBonus >= 1000) {
            PlayerShip.lives++;
            untilNextBonus = untilNextBonus - 1000;
        }

    }
    //sorts objects leaves just alive
    void SortingObjectList(List<GameObject> alive){
        for (GameObject a : objects) {
            a.update();
            if (!a.dead) {
                alive.add(a);
            }
        }
            objects.clear();
            objects.addAll(alive);
    }

        //draws game objects
    public void draw(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.translate(-ship.position.x + Constants.FRAME_SIZE.getWidth()/2 ,-ship.position.y + Constants.FRAME_SIZE.getHeight()/2);
        // paint the background
        g.drawImage(im,0,0, (int)(stretchx*im.getWidth(null)),(int)(stretchy*im.getHeight(null)),null);


        g.drawImage(im, Constants.FRAME_WIDTH, 0, (int) (stretchx * im.getWidth(null)), (int) (stretchy * im.getHeight(null)), null);
        g.setColor(Color.red);
        g.drawImage(im,0,0, (int)(-stretchx*im.getWidth(null)),(int)(stretchy*im.getHeight(null)),null);
        g.drawImage(im,0,Constants.FRAME_HEIGHT, (int)(stretchx*im.getWidth(null)),(int)(stretchy*im.getHeight(null)),null);
        g.drawImage(im,0,0, (int)(stretchx*im.getWidth(null)),(int)(-stretchy*im.getHeight(null)),null);
        g.draw3DRect(0, 0, (int)( stretchx * im.getWidth(null)), (int) (stretchy * im.getHeight(null)),false);        //g.setBackground(Color.BLACK);

        BufferedImage image = new BufferedImage(Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT,BufferedImage.TYPE_INT_ARGB_PRE);
        Graphics2D g3 =(Graphics2D) image.getGraphics();
        synchronized (Game.class) {
            for (GameObject object : objects){
                object.draw(g);
                if(object instanceof Ship)g3.setColor(Color.red);
                else if(object instanceof Bullet)g3.setColor(Color.GREEN);
                else g3.setColor(Color.CYAN);
                g3.fillOval((int)object.position.x,(int)object.position.y,20,20);
            }
        }
            g.draw3DRect((int)ship.position.x+390,(int)ship.position.y+200,Constants.FRAME_WIDTH/10,Constants.FRAME_HEIGHT/10,true);
            g.drawImage(image,(int)ship.position.x+390,(int)ship.position.y+200,Constants.FRAME_WIDTH/10,Constants.FRAME_HEIGHT/10,null);

            ResultBar(g);
            if(PlayerKeysController.pouse) g.drawString("POUSE!!! ", (int)(ship.position.x-70),(int)(ship.position.y));
            if(gameOver)g.drawString("GAME OVER!! PLAY AGAIN? PRESS Y!!! ", (int)(ship.position.x-270),(int)(ship.position.y));

        g.translate(ship.position.x + Constants.FRAME_SIZE.getWidth()/2 ,ship.position.y + Constants.FRAME_SIZE.getHeight()/2);

    }
    // result bar
    void ResultBar(Graphics2D g){
        g.setFont(new Font("Arial",Font.PLAIN,40));
        //g.draw3DRect((int)(ship.position.x - Constants.FRAME_SIZE.getWidth()/2), (int)(ship.position.y - Constants.FRAME_SIZE.getHeight()/2),(int)Constants.FRAME_SIZE.getWidth(),30,true);
        g.drawString( "Level: " + level, (int)(ship.position.x-250+Constants.FRAME_SIZE.getWidth()/2),(int)(ship.position.y+30- Constants.FRAME_SIZE.getHeight()/2));
        for(int i = 0; i < PlayerShip.lives;i++)
        g.drawImage(Constants.SHIP,(int)(ship.position.x + i * 30 - Constants.FRAME_SIZE.getWidth()/2),(int)(ship.position.y - Constants.FRAME_SIZE.getHeight()/2),30,30,null);
        g.drawString("Score: " + score,(int)(ship.position.x-250+  Constants.FRAME_SIZE.getWidth()/2),(int)(ship.position.y+70- Constants.FRAME_SIZE.getHeight()/2));
    }
}