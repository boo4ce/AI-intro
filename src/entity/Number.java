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
public class Number extends DemoObject {
    private int count;
    
    /**
     * Creates new form Number
     */
    public Number(int xMaze, int yMaze, int width, int height, int count) {
        super(xMaze, yMaze, width, height);
        this.count = count;
        initComponents(width, height, count);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents(int width, int height, int count) {
        number = new javax.swing.JLabel();

        number.setBackground(new Color(101, 237, 217, 50));

        number.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        number.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        number.setText(Integer.toString(count));
        number.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, width, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, height, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private javax.swing.JLabel number;
    // End of variables declaration    
    
    public final void increase() {
        this.count++;
        number.setText(Integer.toString(count));
    }
}