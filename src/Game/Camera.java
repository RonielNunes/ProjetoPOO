package Game;

import Entity.Entity;

/**
 *
 * @author Roniel Nunes
 */
public class Camera {
    public int x,y;
    
    public void tick(Entity player){
        setX(-player.getX() + (Game.WIDTH*2));
        setY(-player.getY() + (Game.HEIGHT*2));
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
