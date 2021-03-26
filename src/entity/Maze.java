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
    private Bot bot;
    private Goal goal;
    protected short[][] maze;
    
    public Bot getBot() {
        return bot;
    }

    public void setBot(Bot bot) {
        this.bot = bot;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
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
}
