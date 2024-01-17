package Model.obj;

import View.PanelGame;
import View.ScoreObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import static java.lang.Math.abs;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 *
 * @author eyada
 */
public class Po extends abstractPlayer {

//    public static final double PLAYER_SIZE = 64;
//    private double x;
//    private double y;
//    private final int widthOfImage;
//    private final int heightOfImage;
//    private final Image image;
//    private Area legshape;
//    private LinkedList<Droppable> platesStack;
//    private int counter = 0;
//    private int score = 0;
//    private double xShape = 0;
//    private double yShape = 0;
    private Path2D p = new Path2D.Double();
//    private int xOfLeg;

    public Po(ScoreObserver l) {
        super("/Model/image/Poo.jpg",l);
        
//        this.image = new ImageIcon(getClass().getResource("/game/image/Poo.jpg")).getImage();
//        widthOfImage = image.getWidth(null);
//        heightOfImage = image.getHeight(null);
        p.moveTo(this.getWidthOfImage() - 35, 5);
        p.lineTo(this.getWidthOfImage() - 35, this.getHeightOfImage() / 6);
        p.lineTo(3 * this.getWidthOfImage() / 4 - 13, this.getHeightOfImage() / 6);
        p.lineTo(3 * this.getWidthOfImage() / 4 - 13, 5);
        legshape = new Area(p);
        xOfLeg = this.getWidthOfImage() - 35;
        platesStack = new LinkedList<>();
    }
        public Path2D getP() {
        return p;
    }

//    public void changeLocation(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public void draw(Graphics2D g2) {
//        AffineTransform oldTransform = g2.getTransform();
//        g2.translate(x, y);
//        g2.drawImage(image, 0, 0, null);
//        Shape shape = getShape();
//        g2.setTransform(oldTransform);
//        g2.setColor(Color.red);
//        //g2.draw(shape);
//        g2.setTransform(oldTransform);
//        for (int i = 0; i < platesStack.size(); i++) {
//            platesStack.get(i).draw(g2);
//        }
//    }
//
//    public double getX() {
//        return x;
//    }
//
//    public double getY() {
//        return y;
//    }
//
//    public void setX(double x) {
//        this.x = x;
//    }
//
//    public int getWidthOfImage() {
//        return widthOfImage;
//    }
//
//    public int getHeightOfImage() {
//        return heightOfImage;
//    }
//
//    public Area getShape() {
//        AffineTransform afx = new AffineTransform();
//        if (xShape == 0) {
//            afx.translate(x, y);
//        } else {
//            afx.translate(xShape, yShape);
//        }
//        return new Area(afx.createTransformedShape(legshape));
//    }
//
//    public LinkedList<Droppable> getPlatesStack() {
//        return platesStack;
//    }
//
//    public void setPlatesStack(LinkedList<Droppable> platesStack) {
//        this.platesStack = platesStack;
//    }
//
//    public int getCounter() {
//        return counter;
//    }
//
//    public void setCounter(int counter) {
//        this.counter = counter;
//    }
//
//    public void counterIncreament() {
//        this.counter++;
//    }
//
//    public void changeAllLocation(double x) {
//        this.x += x;
//        for (int i = 0; i < platesStack.size(); i++) {
//            platesStack.get(i).changeXLocation(x);
//        }
//        if (xShape != 0) {
//            xShape += x;
//        }
//    }
//
//    public void addPlate(Droppable droppedObject) {
//        if (droppedObject instanceof Bomb) {
//            platesStack.clear();
//            counter = 0;
//            xShape = 0;
//            yShape = 0;
//            legshape = new Area(p);
//            return;
//        }
//        if (droppedObject instanceof NinjaKnife) {
//            score--;
//            return;
//        }
//        platesStack.add(droppedObject);
//        legshape = droppedObject.getPlateshape();
//        xShape = droppedObject.getX();
//        yShape = droppedObject.getY();
//    }
//
//    public void updateStack() {
//        if (counter >= 3) {
//            if (platesStack.get(counter - 1).getImage().equals(platesStack.get(counter - 2).getImage()) && platesStack.get(counter - 2).getImage().equals(platesStack.get(counter - 3).getImage())) {
//                for (int i = 0; i < 3; i++) {
//                    platesStack.pollLast();
//                    System.out.println("successfully reomved");
//                }
//                counter -= 3;
//                score++;
//                System.out.println("score " + score);
//                if (counter == 0) {
//                    xShape = 0;
//                    yShape = 0;
//                    legshape = new Area(p);
//                } else {
//                    xShape = platesStack.peekLast().getX();
//                    yShape = platesStack.peekLast().getY();
//                    legshape = platesStack.peekLast().getPlateshape();
//                }
//            }
//        }
//    }
//
//    public boolean checkPlates(Droppable template) {
//        if (template != null) {
//            Area area = new Area(getShape());
//            area.intersect(template.getShape());
//            if (!area.isEmpty()) {
//                counterIncreament();
//                addPlate(template);
//                System.out.println("remove to player...");
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public int getScore() {
//        return score;
//    }


    @Override
    public void notifyObserver(int x) {
        this.sc.updateScore(x);
    }
}
