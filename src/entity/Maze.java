/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author acer
 */
public class Maze {
    private short[][] maze;

    public short[][] getMaze() {
        return maze;
    }

    public void setMaze(short[][] maze) {
        this.maze = maze;
    }
    
    public void resize(int row, int column) {
        this.maze = new short[row][column];
    }
    
    public void change(int x, int y, short kindOfObject) {
        this.maze[x][y] = kindOfObject;
    }
    
    public short getKindOfObject(int x, int y) {
        return this.maze[x][y];
    }
}
