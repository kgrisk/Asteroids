package game1;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by zelda on 1/24/2017.
 */
public class BasicView extends JComponent {
    // background colour

    //private StateManager game;

    public BasicView() {
        //this.game = game;


    }

    @Override
    public void paintComponent(Graphics g0) {

            StateManager.game.draw(g0);


    }


    @Override
    public  Dimension getPreferredSize() {
        return new Dimension(
                Constants.FRAME_SIZE);
    }

}