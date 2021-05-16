/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;

/**
 *
 * @author Roniel Nunes
 */
public class Sprite {
    public SpriteSheet sheet;
    public BufferedImage image;
    
    public Sprite(SpriteSheet sheet, int x ,int y){
        image = sheet.getSprite(x, y, 32, 32);
    }
    
    public BufferedImage getBufferedImage(){
        return image;
    }
}
