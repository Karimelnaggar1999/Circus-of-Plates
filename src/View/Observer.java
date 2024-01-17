/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author AhmedAbdelwahed
 */
public abstract class Observer {
    protected PanelGame panel;
    public Observer (PanelGame panel)
    {
        this.panel = panel;
    }
    abstract void updateScore (int x);
}
