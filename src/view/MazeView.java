/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entity.*;
import java.awt.BorderLayout;
/**
 *
 * @author acer
 */
public class MazeView extends javax.swing.JFrame{
    private final int box_width, box_height;
    private final int rows, columns;
    
    private javax.swing.JPanel mazePanel;
    private Maze maze;
    
    public MazeView(int width, int height, int row, int column, short[][] mazeDetail) {
        rows = row;
        columns = column;
        box_width = width/column;
        box_height = height/row;
        
        initComponents(width, height);
        maze = new Maze();
        maze.setMaze(mazeDetail);
    }
    
    private void initComponents(int width, int height) {
        mazePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(mazePanel);
        mazePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, width, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, height, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    public final void showMaze() {
        short[][] mazeInfo;
        mazeInfo = this.maze.getMaze();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; i++) {
                switch(mazeInfo[i][j]) {
                    case DemoObject.WALL -> mazePanel.add(new Wall(box_height*i, box_width*j, box_width, box_height));
                    case DemoObject.WAY -> mazePanel.add(new Way(box_height*i, box_width*j, box_width, box_height));
                    default -> {
                    }
                }
            }
        }
    }
    
    public final void findGoal() {
        new Thread(new Runnable(){
            @Override
            public void run() {
                MazeView.this.setVisible(true);
            }
        }).start();
    }
}
