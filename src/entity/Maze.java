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
    protected short[][] maze;
    
    public Maze() {
        
    }
    
    public Maze(int row, int column) {
        maze = new short[row][column];
        setDefault(row, column);
    }

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
    
    private void setDefault(int width, int height) {
        for(int i = 0; i < width; i++)
            for(int j = 0; j < height; j++)
                maze[i][j] = DemoObject.WAY;
    }
}
