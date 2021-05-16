/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Jogo.Game;
import Jogo.Handler;
import Jogo.Id;
import Tile.Tile;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Roniel Nunes
 */
public class Player extends Entity{
    
    private int frame = 0;
    private int frameDelay = 0;
    
    private boolean animate = false;
    
    
    public Player(int x, int y, int width, int height, boolean solid, Id id,Handler handler){
       super(x,y,width,height,solid,id,handler);
       //this.setVelX(5);
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
       // if(x <= 0){ //Colisão esquerda
       //     x = 0;
        //}
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
        if(velX!=0){
            animate = true;
        }else{
            animate = false;
        }
        
        
        //colisao  
        for (Tile t: handler.tile) {
            if(!t.solid){
                break;
            }
            if(t.getId() == Id.wall){
                if(getBoundsTop().intersects(t.getBounds())){
                    setVelY(0);
                    if(jumping){
                        jumping = false;
                        gravity = 0.8;
                        falling = true;
                    }
                    //y = t.getY()+t.height;
                    //removido para implemntar pulo y = t.getY()+t.height; //se precimar muito ele muda para outra possicap
                }
                if(getBoundsBottom().intersects(t.getBounds())){
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
                if(getBoundsLeft().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX()+t.width; 
                }
                if(getBoundsRight().intersects(t.getBounds())){
                    setVelX(0);
                    x = t.getX()-t.width;
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
        
        if(animate){ //Usado para parar a troca de movimentos
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
