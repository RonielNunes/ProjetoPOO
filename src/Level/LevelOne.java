 package Level;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Roniel Nunes
 */
public class LevelOne {
    
    private Image fundo; //DECLARAÇÃO DO OBJETO QUE SE´RA O FUNDO
    
    //NO CONSTRUTOR DA FASE FAZEMOS A REFERENCIAÇÃO DAS IAMGENS QUE O COMPOE
    public LevelOne(){
        ImageIcon referencia = new ImageIcon();
        fundo = referencia.getImage();
    }
}
