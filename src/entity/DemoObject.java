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
    public final static short UNKNOWN = 6;
    
    protected int xMaze, yMaze, width, height;
    private final int padding;
    
    protected DemoObject(int xMaze, int yMaze, int width, int height) {
        super.setSize(width, height);
     
        this.xMaze = xMaze;
        this.yMaze = yMaze;
        
        padding = 0;
        
        this.width = width;
        this.height = height;
        
        this.display(); 
    }
    
    protected DemoObject(int xMaze, int yMaze, int width, int height, int padding) {
        this.xMaze = xMaze;
        this.yMaze = yMaze;
        
        this.padding = padding;
        
        this.width = width - 2*padding;
        this.height = height - 2*padding;
        
        super.setSize(this.width, this.height);
        
        this.display();
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    
    public int getxMaze() {
        return this.xMaze;
    }

    public int getyMaze() {
        return this.yMaze;
    }
    
    public final void display() {
        this.setLocation(this.xMaze*(width + 2*padding) + padding, 
                this.yMaze*(height + 2*padding) + padding);
    }
}
