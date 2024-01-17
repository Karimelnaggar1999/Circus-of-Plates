/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Strategy.*;
import Model.obj.Bomb;
import Model.obj.Droppable;
import Model.obj.NinjaKnife;
import Model.obj.Noodles;
import Model.obj.Plates;
import Model.obj.Spaghetti;
import static java.lang.Math.random;
import java.util.Random;

/**
 *
 * @author AhmedAbdelwahed
 */
public class DropObjectConcreteFactory implements DropObjectFactory {

    private double E = 0.15;
    private double M = 0.5;
    private double H = 0.7;
    UpdateBehavior diff;

    @Override
    public Droppable generateDroppable(float threshold) {
        //7ot hena code estkhdam el strategy 3ala 7asab el threshold e3ml object mn update behavior w eb3ato fel initialization
        Random rd = new Random(); // creating Random object
        float r = rd.nextFloat(1);
        System.out.println("threshold : " + threshold + " E : "+ E + " M : "+M+" H : "+ H);
        if (threshold == 0.15) {
            diff = new EasyModeUpdateBehavior();
        }
        else if (threshold == 0.5) {
              System.out.println("ana dakhalt !!!!!!! w b 0.5");
            diff = new MedModeUpdateBehavior();
        }
        else if (threshold >= 0.6) {
            System.out.println("ana dakhalt !!!!!!! w b 0.7");
            diff = new HardModeUpdateBehavior();
        }
        else diff = new EasyModeUpdateBehavior();

        if (r < threshold) {
            Droppable bad;
            if (r < threshold - (threshold / 4)) {
                bad = new Bomb(diff);
                return bad;
            } else {
                bad = new NinjaKnife(diff);
                return bad;
            }
        } else {
            float goodRegion = 1 - threshold;
            if (r <= threshold + goodRegion / 3) {
                return new Plates(diff);
            }
            if (r <= threshold + 2 * (goodRegion / 3)) {
                return new Noodles(diff);
            }

        }
        return new Spaghetti(diff);

    }
}
