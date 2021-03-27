/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entity.*;
import java.util.Queue;
import java.util.Stack;
/**
 *
 * @author acer
 */
public class FindingProcess implements Runnable{
    private Maze maze;
    private Bot bot;
    private Goal goal;

    public FindingProcess() {
        maze = new Maze();
    }
    
    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

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
    
    public void findWayByDFS() {
        Stack<Pair<Integer, Integer> > stack = new Stack<>();
        stack.push(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        
        while(!stack.empty()) {
            Pair<Integer, Integer> current = stack.peek();
            stack.pop();
            
            
            
        }
    }

    @Override
    public void run() {
        
    }
}
