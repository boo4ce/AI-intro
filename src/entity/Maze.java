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
    private int row, column;
    
    public Maze() {
        
    }
    
    public Maze(int row, int column) {
        this.row = row;
        this.column = column;
        
        maze = new short[row][column];
        setDefault(row, column);
    }

    public short[][] getMaze() {
        return maze;
    }

    public void setMaze(int row, int column, short[][] maze) {
        this.maze = maze;
        this.row = row;
        this.column = column;
    }
    
    public void setMaze(int row, int column) {
        this.row = row;
        this.column = column;
        this.maze = new Maze(row, column).getMaze();
    }
    
    public void resize(int row, int column) {
        this.maze = new short[row][column];
    }
    
    public void change(int x, int y, short kindOfObject) {
        this.maze[y][x] = kindOfObject;
    }
    
    public short getKindOfObject(int x, int y) {
        return this.maze[y][x];
    }
    
    private void setDefault(int row, int column) {
        for(int i = 0; i < row; i++)
            for(int j = 0; j < column; j++)
                maze[i][j] = DemoObject.WAY;
    }
    
    public int getRow() {
        return this.row;
    }
    
    public int getColumn() {
        return this.column;
    }
    
    public static short[][] getMazeDefault() {
        return new short[][] {
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3},
            {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}
        };
    }
}
