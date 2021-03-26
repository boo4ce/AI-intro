/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.awt.Color;

/**
 *
 * @author acer
 */
public class Bot extends DemoObject{
    public Bot(int x, int y, int width, int height) {
        super(x, y, width, height);
        init();
    }
    
    private void init() {
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setBackground(Color.BLUE);
        this.setForeground(Color.BLUE);
    }
}
