 
package Entity;

import Jogo.Game;
import Jogo.Handler;
import Jogo.Id;
import Tile.Tile;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Roniel Nunes
 */
public class Student extends Entity{
    private int frame = 0;
    private int frameDelay = 0;
    private Random random = new Random();    
    
    public Student(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
        
        int dir = random.nextInt(2);
        
        switch(dir){
            case 0:
                setVelX(-2);
                facing = 1;
                break;
            case 1:
                setVelX(2);
                facing = 3;
                break;
        }
    }
    

 
    public void render(Graphics g) {
        if(facing==0){
            g.drawImage(Game.student[frame+6].getBufferedImage(),x,y,width,height,null);
        }else if (facing ==1){
            g.drawImage(Game.student[frame].getBufferedImage(),x,y,width,height,null);
        }
    }

 
    public void tick() {
        x+=velX;
        y+=velY;
        
        for (int i = 0; i < handler.tile.size();i++) {
            Tile t = handler.tile.get(i);
            if(t.isSolid()){
                if(getBoundsBottom().intersects(t.getBounds())){
                    setVelY(0);
                    if(falling){
                        falling = false;
                        //facing = 3;
                    }
                }else if(!falling){
                    falling = true;
                    gravity = 0.8;
                }
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelX(2);
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelX(-2);
                }
            }   
        }
        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
        }
        if(velX!=0){ //Usado para parar a troca de movimentos
            frameDelay++;

            if(frameDelay>=4){ //10
                frame++;
                if(frame>=6){ //3
                    frame = 0;
                }
                frameDelay = 0;

            }
        }
        
    }
    
}
