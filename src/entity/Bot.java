/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import neuron.Memory;
import image.Nothing;

/**
 *
 * @author acer
 */
public class Bot extends DemoObject{
    public static final short LEFT = 1;
    public static final short UP = 2;
    public static final short RIGHT = 3;
    public static final short DOWN = 4;
    public static final short VISITED = 5;
    
    private BufferedImage image;
    private final Memory memory;
    
    public Bot(int x, int y, int width, int height, int padding) {
        super(x, y, width, height, padding);
        
        memory = new Memory();
        
        init();
    }
    
    private void init() {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setForeground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        
        try {
            image = ImageIO.read(Nothing.class.getResource("robot.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this); // see javadoc for more info on the parameters     
    }
    
    private void moveUp() {
        this.yMaze -= 1;
        memory.moveUp();
    }
    private void moveDown() {
        this.yMaze += 1;
        memory.moveDown();
    }
    private void moveLeft() {
        this.xMaze -= 1;
        memory.moveLeft();
    }
    private void moveRight() {
        this.xMaze += 1;
        memory.moveRight();
    }
    
    public final void reverseMove(short direction) {
        switch(direction) {
            case LEFT: 
                moveRight();
                break;
            case RIGHT:
                moveLeft();
                break;
            case UP:
                moveDown();
                break;
            case DOWN:
                moveUp();
                break;
            default:
        }
        this.display();
    }
    
    public final void move(short direction) {
        switch(direction) {
            case LEFT: 
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
        }
        this.display();
    }
    
    public final void see(short left, short top, short right, short bottom) {
        memory.addAroundNode(left, right, top, bottom);    
    }
    
    public final void setCoor(int x, int y) {
        this.xMaze = x;
        this.yMaze = y;
    }
    
    public void getKind() {
        System.out.println(memory.getKindOfCurrentNode());
    }
    
    public final short getKinfOfLeftObject() {
        return memory.getKindOfLeftNode();
    }
    
    public final short getKinfOfRightObject() {
        return memory.getKindOfRightNode();
    }
    
    public final short getKinfOfTopObject() {
        return memory.getKindOfTopNode();
    }
    
    public final short getKinfOfBottomObject() {
        return memory.getKindOfBottomNode();
    }
    
    public Number track() {
        Number res = new Number(xMaze, yMaze, width+10, height+10, this.memory.getTimeVisited()) {
        };
        return res;
    }
    
    public final int getTimeVisited(short direction) {
        return this.memory.getTimeVisited(direction);
    }
}
