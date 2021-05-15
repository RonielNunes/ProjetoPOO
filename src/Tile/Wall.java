package Tile;

import Jogo.Game;
import Jogo.Handler;
import Jogo.Id;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Roniel Nunes
 */
public class Wall extends Tile{

    public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
         super(x, y, width, height, solid, id, handler);
    }
 
 
    public void render(Graphics g) {
       //g.setColor(Color.RED);
       //g.fillRect(x, y, width, height);
        g.drawImage(Game.grass.getBufferedImage(),x,y,width,height,null);
    }

 
    public void tick() {
        
    }
    
}
