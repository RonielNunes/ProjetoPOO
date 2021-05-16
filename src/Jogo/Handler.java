/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Entity.Entity;
import Entity.Player;
import Tile.Tile;
import Tile.Wall;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

/**
 *
 * @author Roniel Nunes
 */
public class Handler {
    public LinkedList<Entity>entity = new LinkedList<Entity>();
    public LinkedList<Tile>tile = new LinkedList<Tile>();

    //public Handler() {
    //    createLevel();
    //}

    public void render(Graphics g){
        for (int i = 0; i < entity.size(); i++) {
            Entity e = entity.get(i);
            e.render(g);
        }
        
        for (int i = 0; i < tile.size(); i++) {
            Tile t = tile.get(i);
            t.render(g);
        }
        //for (Entity en: entity) {//    en.render(g);//} //for (Tile ti:tile) {//    ti.render(g);//}
    }
    
    public void tick(){
        
        for (int i = 0; i < entity.size(); i++) {
            Entity e = entity.get(i);
            e.tick();
        }
        for (int i = 0; i < tile.size(); i++) {
            Tile t = tile.get(i);
            t.tick();
        }
        //for (Entity en: entity) {//    en.tick();//}//for (Tile ti:tile) {//    ti.tick();//}
    }
    
    public void addEntity(Entity en){
        entity.add(en);
    }
    
    public void removeEntity(Entity en){
        entity.remove(en);
    }
    public void addTile(Tile ti){
        tile.add(ti);
    }
    public void removeTile(Tile ti){
        tile.remove(ti);
    }
    
    public void createLevel(BufferedImage level){
        int width = level.getWidth();
        int height = level.getHeight();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = level.getRGB(x, y);
                int red = (pixel >>16)& 0xff;
                int green = (pixel >>8)& 0xff;
                int blue = (pixel)& 0xff;
                if(red == 0 && green == 0 && blue == 0){
                    addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                }
                
                if(red == 0 && green == 0 && blue == 255){
                    addEntity(new Player(x*64,y*64,64,64,false,Id.player,this));
                }
 
            }
        }
        //for (int i = 0; i <Game.WIDTH*Game.SCALE/64+1; i++) {
        //    addTile(new Wall(i*64,Game.HEIGHT*Game.SCALE-64,64,64,true,Id.wall,this));
        //    if(i != 0 &&i!=15 && i != 1 && i !=16 && i != 17){
        //        addTile(new Wall(i*64,300,64,64,true,Id.wall,this));
        //    }
        //}
    }
    
}
