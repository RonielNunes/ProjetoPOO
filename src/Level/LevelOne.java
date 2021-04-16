 package Level;

import Model.Player;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
 
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Roniel Nunes
 */
 
//extends adicionando na hora que adicionamos level no container
//implements adiconando na hora que adicionamos o player na fase (instanciação)
//ActionListener é o método que vai atualizar a tela quando o player estive se movendo
public class LevelOne extends JPanel implements ActionListener{
   // private final Image fundo; //DECLARAÇÃO DO OBJETO QUE SERÁ O FUNDO VÁRIAVEIS GLOBAIS
    //private final Image piso;
    private final Image mapa;
    
    private ImageIcon referencia;
    private Player player;
    private Timer timerGame;
    
    public LevelOne(){ //NO CONSTRUTOR DA FASE FAZEMOS A REFERENCIAÇÃO DAS IAMGENS QUE O COMPOE
        setFocusable(true); //COMANDOS PARA DEIXAR MAIS EFICIENTE O CÓDIGO
        setDoubleBuffered(true);
        
        //referencia = new ImageIcon("src\\Image\\ceuLimpo.png"); //FAZ INSTÂNCIAÇÃO DO FUNDO
        //fundo = referencia.getImage(); //RECEBE A IMAGEM DO CÉU
        
        //referencia = new ImageIcon("src\\Image\\pisoGrama.png");
        //piso = referencia.getImage();
        
        referencia = new ImageIcon("src\\Image\\mapa1.png");
        mapa = referencia.getImage();
        
        player = new Player(); //INSTÂNCIANDO O PLAYER NA FASE(LEVELONE)
        player.loadImagemPlayer(); //CARREGA A IMAGEM DO PLAYER
        
        addKeyListener(new TecladoAdapter()); //INSTÂNCIAÇÃO DO TECLADO NA FASE
        
        timerGame = new Timer(5,this); //TIME UTILIZADO PARA MODIFICAR A VELOCIDADE DO JOGO
        timerGame.start(); //PODEMOS PARAR O TIMER TAMBÉM
    }
 
    @Override
    public void paint(Graphics g){//MÉTODO PARA CARREGAR A IMAGEM NA TELA, "PINTAR A TELA"//UTILIZASE DA CLASSE GRAPHICS PARA FAZER ISSO
        Graphics2D graficos = (Graphics2D)g;
        //graficos.drawImage(fundo, 0, 0, null); //INDICA IMAGEM, POSIÇÃO,POSIÇÃO E CENTRO DA TELA COMO REFERENCIA
        //graficos.drawImage(piso,0,0, null);
        graficos.drawImage(mapa,0,0, null);
        
        graficos.drawImage(player.getImagem(),player.getX(),player.getY(), this); //FAZ A PINTURA DO PLAYER NA TELA NAS POSIÇÕES ADICIONADAS this indica nesse plano
        
        g.dispose();
    }

    @Override 
    public void actionPerformed(ActionEvent e) { //Método de atuaçização de movimento player
        player.updateMovimento();
        repaint(); //UTILIZADO PARA NÃO DEIXAR IMAGEM FANTASMA NA TELA
    }
    
    //MÉTODO DO TECLADO NA FASE
    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent tecla) {
                player.KeyPressed(tecla);
        }//keyPressed

        @Override
        public void keyReleased(KeyEvent tecla) {
                player.KeyRelease(tecla);
        }//keyRelease

    }//class TecladoAdapter
}
 
