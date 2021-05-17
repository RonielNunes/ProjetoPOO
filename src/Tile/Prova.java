/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tile;

import Jogo.Game;
import Jogo.Handler;
import Jogo.Id;
import java.awt.Graphics;

/**
 *
 * @author Roniel Nunes
 */
public class Prova  extends Tile{

    public Prova(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
        super(x, y, width, height, solid, id, handler);
    }

 
    public void render(Graphics g) {
      g.drawImage(Game.prova.getBufferedImage(), x, y,width,height,null);
    }

 
    public void tick() {
       
    }
    
}
