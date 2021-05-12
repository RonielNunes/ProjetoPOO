/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Entity.Entity;
import Tile.Tile;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Roniel Nunes
 */
public class Handler {
    public LinkedList<Entity>entity = new LinkedList<Entity>();
    public LinkedList<Tile>tile = new LinkedList<Tile>();
    
    public void render(Graphics g){
        for (Entity en: entity) {
            en.render(g);
        }
        
        for (Tile ti:tile) {
            ti.render(g);
        }
    }
    
    public void tick(){
        for (Entity en: entity) {
            en.tick();
        }
        
        for (Tile ti:tile) {
            ti.tick();
        }
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
}
