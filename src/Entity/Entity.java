/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Jogo.Handler;
import Jogo.Id;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Roniel Nunes
 */
public abstract class Entity {
    public int x,y;
    public int width, height;
    public int velX,velY;
    public boolean solid;
    public Id id;
    
    public Handler handler;
    
    public Entity(int x, int y, int width, int height, boolean solid, Id id,Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.solid = solid;
        this.id = id;
        this.handler = handler;
    }
 
    
    public abstract void render(Graphics g);
    public abstract void tick();
    
    public void die(){
        handler.removeEntity(this);
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

    public boolean isSolid() {
        return solid;
    }
    public Id getId(){
        return id;
    }
    
    public void setSolid(boolean solid) {
        this.solid = solid;
    }
 
    public void setVelX(int velX) {
        this.velX = velX;
    }
 
    public void setVelY(int velY) {
        this.velY = velY;
    }
    //colisão
    public Rectangle getBounds(){
        return new Rectangle(getX(),getY(),width,height);
    }
    
    public Rectangle getBoundsTop(){
        return new Rectangle(getX()+10,getY(),width-20,5);
    }
    public Rectangle getBoundsBottom(){
        return new Rectangle(getX()+10,getY()+width-5,width-20,5);
    }
    
    public Rectangle getBoundsLeft(){
        return new Rectangle(getX(),getY()+10,5,height-20);
    }
    
    public Rectangle getBoundsRight(){
        return new Rectangle(getX()+width-5,getY()+10,5,height-20);
    }
}
