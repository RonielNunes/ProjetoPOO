/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gfx;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Roniel Nunes
 */
public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(String path) {
        try{
            sheet = ImageIO.read(new FileInputStream(path)); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public BufferedImage getSprite(int x,int y){
        return sheet.getSubimage(sheet.getWidth()-32, sheet.getHeight()-32, 32, 32);
    }
     
}
