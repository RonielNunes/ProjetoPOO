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
import java.awt.Font;
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
    
    public static int lives = 2;
    public static int deathScreeanTime = 0;
    public static boolean showDeathScreen = true;
    public static boolean gameOver = false;
    public static boolean gameWins = false;
    
    public static Handler handler;
    public static SpriteSheet sheet;
    public static Camera cam;
    
    public static int provaNumero = 10;
    
    public static Sprite grass;
    public static Sprite prova;
    
    
    public static Sprite []player;//player;//[]= new Sprite[18];
    public static Sprite []student;
    
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
        prova = new Sprite (sheet,2,1);
        
        player = new Sprite[12];//player= new Sprite (sheet,1,1);
        student = new Sprite[12];
        
        for(int i=0;i<player.length;i++){//Linha 16
            player[i] = new Sprite(sheet,i+1,16);//player[i] = new Sprite(i+1,16,sheet);//player[i] = new Sprite(sheet,i+1,16);//TODO: Colocar coordenada Y da spriteSheet 
        }
        
        for(int i=0;i<student.length;i++){ //Linha 14
            student[i] = new Sprite(sheet,i+1,13);//player[i] = new Sprite(i+1,16,sheet);//player[i] = new Sprite(sheet,i+1,16);//TODO: Colocar coordenada Y da spriteSheet 
        }
        
        try{
            image = ImageIO.read(new FileInputStream("src\\res\\fase.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        //handler.createLevel(image);
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
        

        
        if(!showDeathScreen && !gameWins){
            g.drawImage(Game.prova.getBufferedImage(), 50, 20,100,100, null);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier",Font.BOLD,20));
            g.drawString("Falta Corrigir : " + provaNumero , 160, 80);
        }
        
        if(showDeathScreen){
            if(!gameOver){
                g.setColor(Color.WHITE);
                g.setFont(new Font("Courier",Font.BOLD,50));
                g.drawImage(Game.player[1].getBufferedImage(), 500, 300,100,100, null);
                g.drawString("x " + lives , 610, 400);
            }else{
                g.setColor(Color.WHITE);
                g.setFont(new Font("Courier",Font.BOLD,50));
                     
                g.drawString("GAME OVER!", 500, 400);
            }
            

        }
        if(gameWins){
            System.out.println("esasssssssssssssssssssssssssssssssssssssssssssssss");
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier",Font.BOLD,50));  
            g.drawString("WINS!", 570, 400);
        }
       
        g.translate(cam.getX(),cam.getY());
        
        if(!showDeathScreen && !gameWins){
            System.out.println("-------------------------------------------------");
            handler.render(g);
        }
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
        
        if(showDeathScreen &&!gameOver){
            deathScreeanTime++;
        }
        if(deathScreeanTime >=180){
            showDeathScreen = false;
            deathScreeanTime = 0;
            handler.clearLevel();
            handler.createLevel(image);
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
