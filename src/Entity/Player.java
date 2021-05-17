package Entity;

import Game.Game;
import Game.Handler;
import Game.Id;
import States.PlayerState;
import Tile.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Roniel Nunes
 */
public class Player extends Entity{
 
    private PlayerState state;
    
    public Player(int x, int y, int width, int height , Id id,Handler handler){
       super(x,y,width,height ,id,handler);
       state = PlayerState.ALIVE;
    }

    public void render(Graphics g) {
        if(facing==0){
            g.drawImage(Game.player[frame+6].getBufferedImage(),x,y,width,height,null);
        }else if (facing ==1){
            g.drawImage(Game.player[frame].getBufferedImage(),x,y,width,height,null);
        }
        
    }

 
    public void tick() {
        x+=velX;
        y+=velY;
 
        //colisao  
        for (int i = 0; i < handler.tile.size(); i++) {
            Tile t = handler.tile.get(i);
            
            if(t.isSolid()){
                if(getBoundsTop().intersects(t.getBounds()) && t.getId()!=Id.prova){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }
                }
                
                if(getBoundsBottom().intersects(t.getBounds()) && t.getId()!=Id.prova){
                     setVelY(0);
                     if(falling) falling = false;
                }else if(!falling && !jumping){
                    falling = true;
                    gravity = 0.8;
                }
                
                if(getBoundsLeft().intersects(t.getBounds()) && t.getId()!=Id.prova){
                    setVelX(0);
                    x = t.getX()+t.width;
                }
                if(getBoundsRight().intersects(t.getBounds()) && t.getId()!=Id.prova){
                    setVelX(0);
                    x = t.getX() - t.width;
                }
                if(getBounds().intersects(t.getBounds())&& t.getId()==Id.prova){;
                    Game.provaNumero--;
                    if(Game.provaNumero <= 0){
                        Game.gameWins = true;
                    }
                    t.die();
                }
            }
        }
        
        for (int i = 0; i < handler.entity.size(); i++) {
            Entity e = handler.entity.get(i);
            if(e.getId() == Id.student){
                if(getBoundsBottom().intersects(e.getBoundsTop())){
                    e.die();
                }else if(getBounds().intersects(e.getBounds())){
                    if(state==PlayerState.ALIVE){
                        die();
                    }

                }
            }
        }
    
        if(jumping){
            gravity -= 0.1;
            setVelY((int)-gravity);
            
            if(gravity<=0.0){
                jumping = false;
                falling = true;
            }
        }
        if(falling){
            gravity+=0.1;
            setVelY((int)gravity);
            
            
        }
        
        if(velX!=0){
            frameDelay++;

            if(frameDelay>=3){
                frame++;
                if(frame>=6){
                    frame = 0;
                }
                frameDelay = 0;

            }
        }
        

    }
}
