package View;

 import javax.swing.JFrame;
/**
 *
 * @author Roniel Nunes
 */
 public class Container extends JFrame{
    
    
   public Container(){
         //add(new Fase());
    setTitle("NOME DO JOGO");
    setSize(1280,720);//setSize(1024,768); //setSize(800,600); //MODIFICANDO O TAMANHO DA JANELA PARA UM PADRÃO
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //HABILITANDO O BOTÃO DE FECHAR A JANELA
    setLocationRelativeTo(null); //MODIFICANDO A JANELA PARA APARECER NO CENTRO DA TELA
    this.setResizable(false); //NÃO PERMITIR O REDIMENSIONAMENTO DA JANELA
    setVisible(true); //MODIFICANDO O STATUS DA JANELA COMO VISIVÉL
        
  } 
    
    public static void main(String []args){
        new Container();   
    }
}
