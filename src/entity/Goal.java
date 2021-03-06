/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import image.Nothing;
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
public class Goal extends DemoObject{
    private BufferedImage image;
    
    public Goal(int x, int y, int width, int height, int padding) {
        super(x, y, width, height, padding);
        init();
    }
    
    private void init() {  
//        this.setBackground(new Color(0, 0, 0, 0));
//        this.setForeground(new Color(0, 0, 0, 0));
        this.setBorder(BorderFactory.createEmptyBorder());
        
        try {
            image = ImageIO.read(Nothing.class.getResource("flag.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH), 0, 0, this); // see javadoc for more info on the parameters     
    }
}
