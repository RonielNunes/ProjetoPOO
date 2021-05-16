/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Entity.Entity;

/**
 *
 * @author Roniel Nunes
 */
public class Camera {
    public int x,y;
    
    
    public void tick(Entity player){
        setX(-player.getX() + ((Game.WIDTH*Game.SCALE)/2));//usar o método criado
        setY(-player.getY() + ((Game.HEIGHT*Game.SCALE)/2));
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
}
