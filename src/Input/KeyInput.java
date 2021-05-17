package Input;

import Entity.Entity;
import Game.Game;
import Game.Id;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Roniel Nunes
 */
public class KeyInput implements KeyListener{
 
    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        for(Entity en: Game.handler.entity){
            if(en.getId() == Id.player){
                switch(key){
                case KeyEvent.VK_W:
                    if(!en.jumping){
                        en.jumping = true;
                        en.gravity = 10.0;
                    }
                    break;
                case KeyEvent.VK_A:
                    en.setVelX(-5);
                    en.facing = 0; //troca movimento
                    break;
                case KeyEvent.VK_D:
                    en.setVelX(5);
                    en.facing = 1;//troca movimento
                    break;
            }
            }
        } 
         
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(Entity en: Game.handler.entity){
            if(en.getId() == Id.player){
                switch(key){
                    case KeyEvent.VK_W:
                        en.setVelY(0);
                        break;
                    case KeyEvent.VK_A:
                        en.setVelX(0);
                        break;
                    case KeyEvent.VK_D:
                        en.setVelX(0);
                        break;
                }
            }
        } 
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
