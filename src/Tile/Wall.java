package Tile;

import Game.Game;
import Game.Handler;
import Game.Id;
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
        g.drawImage(Game.grass.getBufferedImage(),x,y,width,height,null);
    }

 
    public void tick() {
        
    }
    
}
