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
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

/**
 *
 * @author acer
 */
public class Bot extends DemoObject{
    public static final short LEFT = 1;
    public static final short UP = 2;
    public static final short RIGHT = 3;
    public static final short DOWN = 4;
    
    private BufferedImage image;
    private short[][] memory;
    
    public Bot(int x, int y, int width, int height, int padding) {
        super(x, y, width, height, padding);
        
        memory = new short[100][100];
        
        init();
    }
    
    private void init() {
        this.setBackground(new Color(0, 0, 0, 0));
        this.setForeground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        
        try {
            image = ImageIO.read(new File("src/image/robot.png"));
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
    }
    private void moveDown() {
        this.yMaze += 1;
    }
    private void moveLeft() {
        this.xMaze -= 1;
    }
    private void moveRight() {
        this.xMaze += 1;
    }
    
    public void move(short direction) {
        memory[yMaze][xMaze] = DemoObject.WAY;
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
        memory[yMaze][xMaze] = DemoObject.BOT;
        this.display();
    }
    
    public void see(short left, short top, short right, short bottom) {
        memory[yMaze][xMaze-1] = left;
        memory[yMaze][xMaze+1] = right;
        memory[yMaze-1][xMaze] = top;
        memory[yMaze+1][xMaze-1] = bottom;
    }
    
}
