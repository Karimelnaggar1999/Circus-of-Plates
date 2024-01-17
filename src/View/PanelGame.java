package View;

import Model.obj.Monkey;
import View.MainMenu;
import Model.component.Key;
import Model.component.Sound;
import Model.component.Text;
import Model.obj.Bomb;
import Controller.DropObjectConcreteFactory;
import Controller.Main;
import java.util.Random;
import Model.obj.Droppable;
import Model.obj.NinjaKnife;
import Model.obj.Plates;
import Model.obj.Noodles;
import Model.obj.abstractPlayer;
import Model.obj.Po;
import Model.obj.Spaghetti;
import Model.obj.Tiger;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.util.List;
import java.util.ArrayList;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import Model.component.MenuMouseInput;

public class PanelGame extends JComponent implements ScoreObserver{

    
    private Graphics2D g2;
    private Graphics g;
    private BufferedImage image;
    private int width;
    private int height;
    private Thread thread;
    private Thread threadTimer;
    private boolean start = true;
    //Game FPS
    private final int FPS = 60;
    private final int TARGET_TIME = 1000000000 / FPS;
    // Game Object
    private abstractPlayer player;
    private Key key;
    private List<Droppable> plates;
    private final float EASY = (float) 0.15;
    private final float MEDIUM = (float) 0.5;
    private final float HARD = (float) 0.7;
    private float Probability = EASY;
    private int counter;
    java.util.Timer timer = new java.util.Timer();
    // private MainMenu mm;
     private GameOver gameOver;
   // private Timer timer = new Timer();
    private MainMenu mm;
    private int Cflag = 0;
    public static int gameState;
     public static final int gameMenu=1;
     public static final int gamePlay=2;
     public static final int gameEnd=3;
     private String level;
     private int Score;
    /*public static enum STATE{
         MENU,
         GAME
     };*/
     
     //public static STATE State=STATE.MENU;

    @Override
    public void updateScore(int x) {
           
        this.setScore(getScore()+x);
  
    }
    public void startPanelGame(String level, String character) {
        
        switch (character) {
            case "PO" -> player = new Po(this);
            case "Tigress" -> player = new Tiger(this);
            case "Monkey" -> player = new Monkey(this);
            default -> throw new AssertionError(this);
        }
        player.changeLocation(0, height - player.getHeightOfImage());
        switch (level) {
            case "EASY" -> Probability = EASY;
            case "MEDIUM" -> Probability = MEDIUM;
            case "HARD" -> Probability = HARD;
            default -> throw new AssertionError();
        }
        
        System.out.println(Probability);
        this.level = level;
    }
public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }
    public void start() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Sound.RunMusic("res/music.wav");
        counter =5;
        mm=new MainMenu();
         gameOver= new GameOver();
        width = getWidth();
        System.out.println("width in abstract "+ width);
        height = getHeight();
        System.out.println("width component "+width);
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2 = image.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        threadTimer = new Thread(new Runnable() {
            
            @Override
            public void run() {
                
                TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                if(gameState==gamePlay){
                for(;counter>=0;counter--){
                
                sleep(1000);if(counter <=0 || player.getHealth()==0){
                   gameState=gameEnd;
                   Main add=new Main();
                   
                   add.addItemInDB(MenuMouseInput.start.getText(), player.getScore(), MenuMouseInput.start.getLevel());
                //timer.cancel();
               
                       
                Cflag++;
                
                
                }
            }
                //counter--;
                
            
           
                } }};//System.out.println("State:"+Blela);
        if(Cflag == 0){
        timer.scheduleAtFixedRate(task,0,1000);
        }
            
        else{
            System.out.println("ana hena");
            //threadTimer.stop();
        }
            }

                  
            
            
        });
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (start) {  
                    long startTime = System.nanoTime();
                    if(gameState==gamePlay){
                   //initPlayer();
                   //counter =5;
                    drawBackground(); 
                    drawGame();
                    render();
                    
                    }else if(gameState==gameMenu){
                        mm.render(g2);render();
                        
                    
                    } else if (gameState==gameEnd){
                        //g2.dispose();
                        
                        gameOver.render(g2);
                        render();
                        //sleep(5000);
                        counter=5;
                        player.setHealth(3);
                        thread.stop();
                        /*player.setScore(0);
                        
                        player.clearStack();
                        /*
                        player.setCounter(0);
                        player.setxShape(0);
                        player.setyShape(0);
                        player.set
                        
                        //legshape = new Area(getP());
                        player.setLegshape(new Area(player.getP()));//new Area(player.getP())*/
                        //thread.stop();
                        
                    
                    }
                    long time = System.nanoTime() - startTime;
                    //System.out.println("time : " + time);
                    if (time < TARGET_TIME) {
                        long sleep = (TARGET_TIME - time) / 1000000;
                        sleep(sleep);
                        //System.out.println("sleep : "+sleep);
                    }
                     

                }
            }
        });
        initObjectGame();
        initKeyboard();
        thread.start();
        threadTimer.start();
    }

    private void addPlates() {
        int location = 85;
        Random rand = new Random();
        //System.out.println("random " + rand.nextInt(200));
        Droppable plate;
        DropObjectConcreteFactory factory = new DropObjectConcreteFactory();
        plate = factory.generateDroppable(Probability);
        //if ((rand.nextInt(4) % 2) == 0) {
        //    plate = new Spaghetti();
        //} else {
        //    plate = new Noodles();
        //}
        plate.setX(0);
        plate.setY(location);
        plates.add(plate);
        Droppable plate2 = factory.generateDroppable(Probability);
        plate2.setX(width);
        //System.out.println("width: " + width);
        plate2.setY(location);
        plates.add(plate2);
        
        Droppable plate3 = factory.generateDroppable(Probability);
        plate3.setX(width/2);
        //System.out.println("width: " + width);
        plate3.setY(0);
        plates.add(plate3);

    }

    private void initObjectGame() {
        //choosing character of player
        player = new Monkey(this);
        player.changeLocation(0, height - player.getHeightOfImage());
        plates = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (start) {
                    addPlates();
                    sleep(3000);//Speed of creating the plates
                }
            }
        }).start();
    }

    private void initKeyboard() {
        key = new Key();
        requestFocus();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    key.setKey_left(true);
                } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    key.setKey_right(true);
                }
            }

            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
                    key.setKey_left(false);
                } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    key.setKey_right(false);
                }
            }

        });
        new Thread(new Runnable() {
            public void run() {
                while (start) {
                    double deltax = player.getX();
                    double deltaWidth = player.getWidthOfImage();
                    if (key.isKey_left()) {
                        if (deltax > 0) {
                            player.changeAllLocation(-2);//motion of player 
                        }
                    } else if (key.isKey_right()) {
                        if (deltax < width - deltaWidth) {
                            player.changeAllLocation(2);//motion of player                   
                        }
                    }
                    sleep(1);//speed  character
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (start) {
                   
                    for (int i = 0; i < plates.size(); i++) {
                        Droppable plate = plates.get(i);
                        if (plate != null) {
                            plate.update();
                            if (player.checkPlates(plate) == true) {
                                plates.remove(plate);
                                player.updateStack();
                            }
                            if (!plate.check(width, height)) {
                                plates.remove(plate);
                                System.out.println("Removed from panel..");
                            }
                        }
                    }
                    sleep(1);//speed of plates 
                }
            }
        }).start();
    }

    private void drawBackground() {
        if(gameState==gamePlay){
        g2.drawImage(new ImageIcon(getClass().getResource("/Model/image/Background.jpg")).getImage(), 0, 0, null);
        g2.drawImage(new ImageIcon(getClass().getResource("/Model/image/Bamboo2.png")).getImage(), 1100, 25, null);
        g2.drawImage(new ImageIcon(getClass().getResource("/Model/image/Bamboo.png")).getImage(), 0, 5, null);
        Font font = new Font("Times New Roman",Font.BOLD, 20);
        Text text = new Text("Score : " + player.getScore(), font, 20, 20);
        text.draw(g2);
        Text timerText = new Text("Time : "+counter,font,170,20);
                timerText.draw(g2); 
       for(int i = 0; i < player.getHealth(); i++)
       {
           g2.drawImage(new ImageIcon(getClass().getResource("/Model/image/Heart.png")).getImage(), 200+10*i, 25, null);
       }
        
        /*TimerTask task = new TimerTask() {
            
            @Override
            public void run() {
                for(;counter>0;counter--){
                
                sleep(1000);
            }
                //counter--;
                if(counter <=0){
                System.out.println("timer check condition !!!");    
                timer.cancel();
                Cflag++;
                System.out.println("after timer cancel !!!");
                }
            }
           
        };
        if(Cflag == 0)
        timer.scheduleAtFixedRate(task,0,1000);
        else{
            this.thread.stop();
        }*/
        
        }
    }

    private void drawGame() {
        if(gameState==gamePlay){
        player.draw(g2);
        for (int i = 0; i < plates.size(); i++) {
            Droppable plate = plates.get(i);
            if (plate != null) {
                plate.draw(g2);
            }
        }
        }
    }

    private void render() {
        
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
    }

    private void sleep(long speed) {
        try {
            Thread.sleep(speed);
        } catch (InterruptedException ex) {
            System.err.println(ex);
        }
    }
}
