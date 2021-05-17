package Game;

import Entity.Entity;
import Entity.Player;
import Entity.Student;
import Tile.Prova;
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
 
    public void render(Graphics g){
        for (int i = 0; i < entity.size(); i++) {
            Entity e = entity.get(i);
            e.render(g);
        }
        
        for (int i = 0; i < tile.size(); i++) {
            Tile t = tile.get(i);
            t.render(g);
        }
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
        int flag = 0,flag2 = 0,flag3 = 0;
        int numeroDeProva = 10;
        int numeroDePlayer = 1;
        int numeroDeEstudantes = 2;
        
        int width = level.getWidth();
        int height = level.getHeight();
        int pixel;
        int red;
        int green;
        int blue;
       
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = level.getRGB(x, y);
                red = (pixel >>16)& 0xff;
                green = (pixel >>8)& 0xff;
                blue = (pixel)& 0xff;
                
                if(red == 0 && green == 0 && blue == 0){
                    addTile(new Wall(x*64,y*64,64,64,true,Id.wall,this));
                }
                
                if(red == 0 && green == 0 && blue == 255 && flag != numeroDePlayer ){
                    flag++;
                    addEntity(new Player(x*64,y*64,64,64, Id.player,this));
                }
                
                if(red == 255 && green == 0 && blue == 0){
                    addEntity(new Student(x*64,y*64,64,64 ,Id.student,this));
                }
                
                if(red == 0 && green == 255 && blue == 0 ){
                    addTile(new Prova(x*64,y*64,64,64,true ,Id.prova,this));
                }
 
            }
        }
    }
    
    public void clearLevel(){
            entity.clear();
            tile.clear();
        }
}
