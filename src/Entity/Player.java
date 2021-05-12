/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Jogo.Handler;
import Jogo.Id;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Roniel Nunes
 */
public class Player extends Entity{
    public Player(int x, int y, int width, int height, boolean solid, Id id,Handler handler){
       super(x,y,width,height,solid,id,handler);
       //this.setVelX(5);
    }

    //Implemtação dos metodos abstratos
    public void render(Graphics g) {
      g.setColor(Color.BLUE);
      g.fillRect(x, y,width , height);
    }

 
    public void tick() {
        x+=velX;
        y+=velY;
        if(x <= 0){ //Colisão esquerda
            x = 0;
        }
        if(y <= 0){ //colisao cima
            y = 0;
        }
        
        if(x + width>=1090){ //colisao direita 1080
            x = 1090 - width;
        }
        if(y + height >=771){ //colisao baixo
            y = 771 - height;
        }
    }
}
