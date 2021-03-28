/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entity.Bot;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author acer
 */
public class FindingProcess implements Runnable{
    private Maze maze;
    private Bot bot;
    private Goal goal;

    private int firstBotX, firstBotY;
    private final String algoName;
    
    private boolean end = false;
    private boolean stop = false;
    
    public FindingProcess(String algoName) {
        maze = new Maze();
        this.algoName = algoName;
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
        this.firstBotX = bot.getxMaze();
        this.firstBotY = bot.getyMaze();
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
    
    private void findWayByDFS() {
        Stack<Pair<Integer, Integer> > stack = new Stack<>();
        Stack<Short> directList = new Stack<>();
        
        stack.push(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
        
        int current_x, current_y;
        List<Short> orient = new ArrayList<>();
        Random random = new Random();
        
        while(!stack.empty()) {
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();
            
            maze.change(current_x, current_y, DemoObject.UNKNOWN);

            //left
            if(current.getFirst() == 0) left = DemoObject.WALL;
            else left = maze.getKindOfObject(current_x-1, current_y); 
            //top
            if(current.getSecond() == 0) top = DemoObject.WALL;
            else top = maze.getKindOfObject(current_x, current_y-1);
            //right
            if(current.getFirst() == maze.getColumn() - 1) right = DemoObject.WALL;
            else right = maze.getKindOfObject(current_x+1, current_y);
            //bottom
            if(current.getSecond() == maze.getRow() - 1) bottom = DemoObject.WALL;
            else bottom = maze.getKindOfObject(current_x, current_y+1);
            
            bot.see(left, top, right, bottom);
            
            // goal in 4 direction
            if(left == DemoObject.GOAL) {
                bot.move(Bot.LEFT);
                break;
            }
            if(right == DemoObject.GOAL) {
                bot.move(Bot.RIGHT);
                break;
            }
            if(top == DemoObject.GOAL) {
                bot.move(Bot.UP);
                break;
            }
            if(bottom == DemoObject.GOAL) {
                bot.move(Bot.DOWN);
                break;
            }
            
            //
            if(left == DemoObject.WAY) {
//                stack.push(new Pair<>(current_x-1, current_y));
                orient.add(Bot.LEFT);
            } 
            if(right == DemoObject.WAY) {
//                stack.push(new Pair<>(current_x+1, current_y));
                orient.add(Bot.RIGHT);
            } 
            if(top == DemoObject.WAY) {
//                stack.push(new Pair<>(current_x, current_y-1));
                orient.add(Bot.UP);
            } 
            if(bottom == DemoObject.WAY) {
//                stack.push(new Pair<>(current_x, current_y+1));
                orient.add(Bot.DOWN);
            }
            
            if(!orient.isEmpty()) {
                short direction = orient.get(Math.abs(random.nextInt())%orient.size());
                
                switch(direction) {
                    case Bot.UP:
                        stack.push(new Pair<>(current_x, current_y-1));
                        break;
                    case Bot.DOWN:
                        stack.push(new Pair<>(current_x, current_y+1));
                        break;
                    case Bot.RIGHT:
                        stack.push(new Pair<>(current_x+1, current_y));
                        break;
                    case Bot.LEFT:
                        stack.push(new Pair<>(current_x-1, current_y));
                        break;
                }
                
                bot.move(direction);
                directList.push(direction);
            } else {
                stack.pop();
                bot.reverseMove(directList.peek());
                directList.pop();
            }
            
            orient.clear();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FindingProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(stop) {
                synchronized(this) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FindingProcess.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.stop = false;
                }
            }
        }
        
        this.end = true;


//        this.bot.see((short)3, (short)3, (short)3, (short)3);
//        this.bot.move(Bot.UP);
    }
    
    public void reset() {
        this.bot.setCoor(firstBotX, firstBotY);
    }
    
    @Override
    public void run() {
        switch(this.algoName) {
            case "DFS":
                this.findWayByDFS();
                break;
            case "BFS":
                
            default:
        }
    }
    
    public void pause() {
        this.stop = true;
    }
   
}
