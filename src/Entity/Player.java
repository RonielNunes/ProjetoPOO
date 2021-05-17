/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Jogo.Game;
import Jogo.Handler;
import Jogo.Id;
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
       //this.setVelX(5);
       state = PlayerState.ALIVE;
    }

    //Implemtação dos metodos abstratos
    public void render(Graphics g) {
        //g.drawImage(Game.player.getBufferedImage(), x, y, width,height,null);
        if(facing==0){
            g.drawImage(Game.player[frame+6].getBufferedImage(),x,y,width,height,null);
        }else if (facing ==1){
            g.drawImage(Game.player[frame].getBufferedImage(),x,y,width,height,null);
        }
        
    }

 
    public void tick() {
        x+=velX;
        y+=velY;
       // if(x <= 0){ //Colisão esquerda //     x = 0;//}
        //y+=velY;
        if(y <= 0){ //colisao cima
            y = 0;
        }
        
        //if(x + width>=1090){ //colisao direita 1080
        //    x = 1090 - width;
        //}
        if(y + height >=771){ //colisao baixo
            y = 771 - height;
        }
        //if(velX!=0){
         //   animate = true;
        //}else{
        //    animate = false;
       // }
        
        
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
                if(getBounds().intersects(t.getBounds())&& t.getId()==Id.prova){
                    System.out.println("TEstes");
                    Game.provaNumero--;//Game.provaNumero++;
                    if(Game.provaNumero <= 0){
                        System.out.println("asaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                        Game.gameWins = true;
                    }
                    t.die();
                }
            }
        }
        
        
        
       /* for (Tile t: handler.tile) {
            if(!t.solid){
                break;
            }
            if(t.getId() == Id.wall){
                if(getBoundsTop().intersects(t.getBounds()) && t.getId() != Id.prova){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }
                    //y = t.getY()+t.height;
                    //removido para implemntar pulo y = t.getY()+t.height; //se precimar muito ele muda para outra possicap
                }
                if(getBoundsBottom().intersects(t.getBounds())&& t.getId() != Id.prova){
                    setVelY(0); //se precimar muito ele muda para outra possicap
                    //y = t.getY()-t.height;
                    if(falling){
                        falling = false;
                    }else{
                        if(!falling && !jumping ){
                            gravity = 0.8;
                            falling = true;
                            
                        }
                    }
                    //removido para implemntar pulo y = t.getY()-t.height;
                }
                if(getBoundsLeft().intersects(t.getBounds()) && t.getId() != Id.prova){
                    setVelX(0);
                    x = t.getX()+t.width; 
                }
                if(getBoundsRight().intersects(t.getBounds()) && t.getId() != Id.prova){
                    setVelX(0);
                    x = t.getX()-t.width;
                }
                if(getBounds().intersects(t.getBounds())&& t.getId()==Id.prova){
                    System.out.println("Essdsdsaaaaaaaaaaaaaaaaaa");
                    Game.provaNumero++;
                    t.die();
                }
            }
        }*/
        
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
        
        if(velX!=0){ //Usado para parar a troca de movimentos
            frameDelay++;

            if(frameDelay>=3){ //10
                frame++;
                if(frame>=6){ //3
                    frame = 0;
                }
                frameDelay = 0;

            }
        }
        

    }
}
