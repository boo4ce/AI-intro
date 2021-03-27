/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.swing.JPanel;

/**
 *
 * @author acer
 */
public abstract class DemoObject extends JPanel {
    public final static short WALL = 2;
    public final static short WAY = 3;
    public final static short BOT = 4;
    public final static short GOAL = 5;
    
    int width, height;
    
    DemoObject(int x, int y, int width, int height) {
        super.setLocation(x, y);      
        super.setSize(width, height);
     
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
}
