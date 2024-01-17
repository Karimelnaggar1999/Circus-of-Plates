/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Area;

/**
 *
 * @author AhmedAbdelwahed
 */
public class Text {
public String text;
    public Font font;
    public double x,y;
    
    public Text(String text,Font font,double x, double y){
        this.font=font;
        this.text=text;
        this.x=x;
        this.y=y;
    }
    public Text(int text,Font font,double x, double y){
        this.font=font;
        this.text=text+"";
        this.x=x;
        this.y=y;
    }
    public void draw(Graphics2D g2){
        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString(text, (float)x, (float)y);
    
    }
}
