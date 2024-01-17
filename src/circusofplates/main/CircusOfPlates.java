/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package circusofplates.main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import Model.Strategy.EasyModeUpdateBehavior;
import com.sun.tools.javac.Main;
import View.MainMenu;
import Model.component.MenuMouseInput;
import View.PanelGame;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author AhmedAbdelwahed
 */
public class CircusOfPlates extends JFrame {

 private  MenuMouseInput mmi;
    PanelGame panelGame ;
public static CircusOfPlates main;
//PanelGame blela ;

    public CircusOfPlates() {
        init();
    }
    private void init()
    {
        
        setTitle("Circus Of Plates");
        setSize(1366,768);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        panelGame= new PanelGame();
        mmi=new MenuMouseInput(panelGame);
        this.addMouseListener(mmi);
      // this.addMouseListener(new MenuMouseInput());
        add(panelGame);
       PanelGame.gameState=PanelGame.gameMenu;
        /*if(PanelGame.gameState==PanelGame.gameEnd){
           // 
            dispose();
            new CircusOfPlates().setVisible(true);
            
            
        
        
           //System.exit(0);
           this.remove(panelGame);
           panelGame = new PanelGame(EASY);
           this.add(panelGame);
           SwingUtilities.updateComponentTreeUI(this);
           
           
         
       }*/
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowOpened(WindowEvent e)
           {
                try {  
                    
                    panelGame.start();
                    
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(CircusOfPlates.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(CircusOfPlates.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(CircusOfPlates.class.getName()).log(Level.SEVERE, null, ex);
                }
           } 
        });
       //this.dispose();
        
    }
    

    public static void main(String[] args) {
        // TODO code application logic here

         main = new CircusOfPlates();
        main.setVisible(true);
    }

}

