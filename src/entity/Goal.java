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
public class Goal extends DemoObject{
    public Goal(int x, int y, int width, int height, int padding) {
        super(x + padding, y + padding, width - padding*2, height - padding*2);
        init();
    }
    
    private void init() {
        this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setBackground(Color.RED);
        this.setForeground(Color.RED);
    }
}
