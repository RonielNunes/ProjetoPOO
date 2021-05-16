package Jogo;

import Entity.Entity;
import Entity.Player;
import Input.KeyInput;
import Tile.Wall;
import gfx.Sprite;
import gfx.SpriteSheet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * @author Roniel Nunes
 */
public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 320;//WIDTH = 270;
    public static final int HEIGHT = 180; //HEIGHT = WIDTH/14*10;
    public static final int SCALE = 4;
    public static final String TITLE = "Fabricio Adventures";
    
    private Thread thread;
    private boolean running = false;
    private BufferedImage image;
    
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Camera cam;
    
    
    public static Sprite grass;
    public static Sprite []player;//player;//[]= new Sprite[18];
    
    public Game(){
        Dimension size = new Dimension(WIDTH * SCALE, HEIGHT*SCALE);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);
    }
    
    private void init(){
        handler = new Handler();
        sheet = new SpriteSheet("src\\res\\sheet.png");
        cam = new Camera();
        
        addKeyListener(new KeyInput()); 
        
        grass = new Sprite (sheet,1,1); //coluna linha 
        player = new Sprite[12];//player= new Sprite (sheet,1,1);
        
        for(int i=0;i<player.length;i++){
            player[i] = new Sprite(sheet,i+1,16);//player[i] = new Sprite(i+1,16,sheet);//player[i] = new Sprite(sheet,i+1,16);//TODO: Colocar coordenada Y da spriteSheet 
        }
        
        try{
            image = ImageIO.read(new FileInputStream("src\\res\\mapa1.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        handler.createLevel(image);
        
        //handler.addEntity(new Player(300,100,64,64,true,Id.player,handler));
        
        
        //removendo bloco adversario handler.addTile(new Wall(200,200,64,64,true,Id.wall,handler));
        
    }
    
    private synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this,"Thread");
        thread.start();
    }
    
    private synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        
        try{
            thread.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
    public void run() {
        init();
        requestFocus();
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0.0;
        double ns = 1000000000.0/60.0;
        int frames = 0;
        int ticks = 0;

        while(running){
            long now = System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime = now;
            while(delta > 1){
                tick();
                ticks++;
                delta--;
            }
            render();
            frames++;
            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                //System.out.println(frames + " Frames per second " + ticks + " Updade per second");
                frames = 0;
                ticks = 0;
            }
        } 
        stop();
    }
    
    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(),getHeight());
       
        g.translate(cam.getX(),cam.getY());
        handler.render(g);
        //g.setColor(Color.yellow);
        //g.fillRect(200, 200,getWidth()-400, getHeight()-400);
        g.dispose();
        bs.show();
    }
    
    public void tick(){
        handler.tick(); //Adicionadno o player na tela
        
        for (Entity e: handler.entity) {
            if(e.getId() == Id.player){
                cam.tick(e);
            }
        }
        
    }
    
    public int getFrameWitdh(){
      return WIDTH*SCALE;
    }
    
    public int getFrameHeight(){
      return HEIGHT*SCALE;
    }
    
   
    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame(TITLE);
        frame.add(game);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        game.start();
         
    }
}
