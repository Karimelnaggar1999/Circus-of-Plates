/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Controller;

import Model.obj.Droppable;

/**
 *
 * @author AhmedAbdelwahed
 */
public interface DropObjectFactory {
    public Droppable generateDroppable(float n);
}
