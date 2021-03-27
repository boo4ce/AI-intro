/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import view.*;

/**
 *
 * @author acer
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        short[][] mazeDetail = {
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, 
            {2, 2, 2, 2, 2, 2, 4, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 5, 2, 2, 2, 2, 2, 2, 2, 2},
            {2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
        };
        
        
        MazeView mazeView = new MazeView(600, 600, 10, 10, mazeDetail);
        mazeView.findGoal();
        
        ControlView control = new ControlView();
        control.init(mazeView);
        control.run();
    }
    
}
