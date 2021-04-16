 package Model;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *
 * @author Roniel Nunes
 */
public class Player {
    private int x; //USADO PARA MOVIMENTAÇÃO
    private int y; //USADO PARA MOVIMENTAÇÃO
    private int dx,dy; //USADO PARA MOVIMENTAÇÃO
    private Image imagem; //USADO PARA A IMAGEM DO PLAYER
    private int altura, largura; //POSTERIOMENTE PARA COLISAÇÕ
    ImageIcon referencia; //VARIÁVEL PARA REFERENCIAR AS IAMGENS DO PLAYER
    
    public Player() {
        this.x = 270; //COORDENADA DA TELA QUE O PLAYER VA COMEÇAR
        this.y = 560; //PIXEL DA TELA
 
    }
    
    public void loadImagemPlayer(){//DEFINIR IMAGEM DO PLAYER
         referencia = new ImageIcon("src\\Image\\player.png");
         imagem = referencia.getImage();
         
         altura = imagem.getHeight(null);//DEFININDO ALTURA PE LARGURA DE ACORDO COM A IMAGEM 
         largura = imagem.getWidth(null);//DO PLAYER
    }
    
    public void updateMovimento(){ //REALIZAR MOVEIMENTO
        x += dx; // MOVEIMENTO DO PLAYER TANTO NO EIXO X
        y += dy; //QUANTO EIXO Y
    }
    
    public void KeyPressed(KeyEvent tecla){ //MÉTODO PARA ENTRADA DO TECLADO QUANDO PRECIONADA
        int codigoTecla = tecla.getKeyCode(); //PEGA O CÓDIGO DA TECLA PARA REALIZAR O MOVIMENTO
        
       
        if(codigoTecla == KeyEvent.VK_UP || codigoTecla == KeyEvent.VK_W){
            dy = -3; //QUANDO FOR PRECIONADO A TECLA PARA CIMA, dy RECEBE 3 FAZENDO A POSIÇÃO DO PERSONAGEM SUBIR
        }
        if(codigoTecla == KeyEvent.VK_DOWN || codigoTecla == KeyEvent.VK_S){
            dy = 3;  //ADICIONAR AQUI METODO PARA AGACHAR 
        }
        if(codigoTecla == KeyEvent.VK_LEFT || codigoTecla == KeyEvent.VK_A){
            dx = -3;  
        }
        if(codigoTecla == KeyEvent.VK_RIGHT || codigoTecla == KeyEvent.VK_D){
            dx = 3; 
        }
    }
    
        public void KeyRelease(KeyEvent tecla){ //MÉTODO PARA PARAR O MOVIMENTO CONSTANTE
        int codigoTecla = tecla.getKeyCode();  //APÓS DEIXAR DE PRECIONAR UMA TECLA

        if(codigoTecla == KeyEvent.VK_UP || codigoTecla == KeyEvent.VK_W){
            dy = 0;  
        }
        if(codigoTecla == KeyEvent.VK_DOWN || codigoTecla == KeyEvent.VK_S){
            dy = 0;  //ADICIONAR AQUI METODO PARA AGACHAR 
        }
        if(codigoTecla == KeyEvent.VK_LEFT || codigoTecla == KeyEvent.VK_A){
            dx = 0;  
        }
        if(codigoTecla == KeyEvent.VK_RIGHT || codigoTecla == KeyEvent.VK_D){
            dx = 0; 
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
    
   
}
