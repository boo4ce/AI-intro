/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Bot;
import entity.DemoObject;
import entity.Goal;
import entity.Wall;
import entity.Way;
import javax.swing.JLayeredPane;
import static view.MazeView.left;
import static view.MazeView.top;

/**
 *
 * @author abc
 */
public class EditView extends javax.swing.JFrame {
    private int box_width = 40, box_height = 40;
    private JLayeredPane mazePane;
    private short[][] mazeInfo;
    private final int rows, columns;
    private short kindOfSelectedObject = DemoObject.WALL;
    private int pre_x_bot = -5, pre_y_bot, pre_x_goal = -5, pre_y_goal;
    
    public EditView(int width, int height, int row, int column, String name) {
        initComponents(width, height, name);
        mazeInfo = new short[row][column];
        
        box_width = width/column;
        box_height = height/row;
        
        this.rows = row;
        this.columns = column;
    }
    
    private void initComponents(int width, int height, String name) {
        this.setResizable(false);
        this.setLocation(left, top);
        this.setTitle(name);
        
        mazePane = new javax.swing.JLayeredPane();
        mazePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mazePane1MouseClicked(evt);
            }
        });
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(mazePane);
        mazePane.setLayout(jPanel2Layout);
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
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePane, 0, width, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mazePane, 0, height, Short.MAX_VALUE)
        );
        
        pack();
    }
    
    private void mazePane1MouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        int coor_x = evt.getX(), coor_y = evt.getY();
        DemoObject object = (DemoObject) mazePane.getComponentAt(coor_x, coor_y);
        if(object instanceof Way)
            change(coor_x/box_width, coor_y/box_height, this.kindOfSelectedObject);
        else 
            change(coor_x/box_width, coor_y/box_height, DemoObject.WAY);
    }    
    
    protected final void showMaze() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                mazeInfo[i][j] = DemoObject.WAY;
                mazePane.add(new Way(j, i, box_width, box_height), rows*i + j);
            }
        }
    }
    
    private void change(int x, int y, short kindOfObject) {
        if(kindOfObject == DemoObject.BOT) {
            if(pre_x_bot != -5) {
                mazePane.remove(rows*pre_y_bot + pre_x_bot);
                mazePane.add(new Way(pre_x_bot, pre_y_bot, box_width, box_height), rows*pre_y_bot+ pre_x_bot);
            }
            pre_x_bot = x; pre_y_bot = y;
        }
        if(kindOfObject == DemoObject.GOAL) {
            if(pre_x_goal != -5) {
                mazePane.remove(rows*pre_y_goal + pre_x_goal);
                mazePane.add(new Way(pre_x_goal, pre_y_goal, box_width, box_height), rows*pre_y_goal+ pre_x_goal);
            }
            pre_x_goal = x; pre_y_goal = y;
        }
        
        mazeInfo[y][x] = kindOfObject;
        mazePane.remove(rows*y + x);
        switch(kindOfObject) {
            case DemoObject.WALL:
                mazePane.add(new Wall(x, y, box_width, box_height), rows*y + x);
                break;
            case DemoObject.BOT:
                mazePane.add(new Bot(x, y, box_width, box_height, 5), rows*y + x);
                break;
            case DemoObject.GOAL:
                mazePane.add(new Goal(x, y, box_width, box_height, 5), rows*y + x);
                break;
            case DemoObject.WAY:
                mazePane.add(new Way(x, y, box_width, box_height), rows*y + x);
                break;
        }
    }

    public void setKindOfSelectedObject(short kindOfSelectedObject) {
        this.kindOfSelectedObject = kindOfSelectedObject;
    }
    
    public void display() {
        this.setVisible(true);
        this.showMaze();
    }

    public short[][] getMazeInfo() {
        return mazeInfo;
    }

    @Override
    public void dispose() {
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
        this.mazePane.removeAll();
    }
    
    
}
