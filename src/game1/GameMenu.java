package game1;

import java.awt.*;

/**
 * Created by zelda on 3/5/2017.
 */
public class GameMenu extends StateManager {
    private String options[] = {"start",  "quit"};
    GameMenu(){
        menuCtrl = new MenuController();
    }

    //updates buttons

    @Override
    public void update() {
        if(menuCtrl.start() == true && menuCtrl.buttonPressed() == 0) StateManager.game = new Game();
        if(menuCtrl.start() == true && menuCtrl.buttonPressed() == 1) System.exit(0);

    }
        // draws menu
    public void draw(Graphics g0){
        g0.drawImage(Constants.MILKYWAY,0,0,Constants.FRAME_WIDTH,Constants.FRAME_HEIGHT,null);
        g0.setColor(Color.BLACK);
        g0.fillRect(0,0,Constants.FRAME_WIDTH,250);
        g0.setColor(new Color((int)(250*Math.random()),50,250));
        g0.setFont(new Font("Arial",Font.ITALIC,250));
        g0.drawString("Asteroids",Constants.FRAME_WIDTH/2-1100,200);

        for(int i = 0;i < 2;i++){
            if(menuCtrl.buttonPressed()==i)g0.setColor(Color.GREEN);
            else g0.setColor(Color.yellow);
            g0.setFont(new Font("Arial",Font.BOLD,68));
            g0.drawString(options[i],Constants.FRAME_WIDTH/2-700,350 + i*150);

        }
    }
}
