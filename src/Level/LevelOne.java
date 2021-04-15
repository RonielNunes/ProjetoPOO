 package Level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
 
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Roniel Nunes
 */
 
public class LevelOne extends JPanel{
    private final Image fundo; //DECLARAÇÃO DO OBJETO QUE SERÁ O FUNDO
    private final Image piso;
    private ImageIcon referencia;
    
    public LevelOne(){ //NO CONSTRUTOR DA FASE FAZEMOS A REFERENCIAÇÃO DAS IAMGENS QUE O COMPOE
        referencia = new ImageIcon("src\\Image\\ceuLimpo.png"); //FAZ INSTÂNCIAÇÃO DO FUNDO
        fundo = referencia.getImage(); //RECEBE A IMAGEM DO CÉU
        
        referencia = new ImageIcon("src\\Image\\pisoGrama.png");
        piso = referencia.getImage();
    }
 
    @Override
    public void paint(Graphics g){//MÉTODO PARA CARREGAR A IMAGEM NA TELA, "PINTAR A TELA"//UTILIZASE DA CLASSE GRAPHICS PARA FAZER ISSO
        Graphics2D graficos = (Graphics2D)g;
        graficos.drawImage(fundo, 0, 0, null); //INDICA IMAGEM, POSIÇÃO,POSIÇÃO E CENTRO DA TELA COMO REFERENCIA
        graficos.drawImage(piso,0,0, null);
        g.dispose();
    }

}
