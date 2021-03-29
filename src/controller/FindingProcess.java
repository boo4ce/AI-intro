/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import entity.Bot;
import entity.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.MazeView;
/**
 *
 * @author acer
 */
public class FindingProcess implements Runnable{
    private Maze maze;
    private Bot bot;
    private Goal goal;
    private final MazeView mazeView;
    
    private int firstBotX, firstBotY;
    private final String algoName;
    
    private boolean end = false;
    private boolean stop = false;
    
    private List<Pair<Integer, DemoObject>> list;
    
    public FindingProcess(MazeView mazeView, String algoName) {
        maze = new Maze();
        this.algoName = algoName;
        this.mazeView = mazeView;
        
        list = new ArrayList<>();
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
        directList.push((short)-1);
        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
        
        int current_x, current_y;
        short orient;
        
        while(!stack.empty()) {
            if(end) return;
            orient = -1;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();

            //left
            if(current_x == 0) left = DemoObject.WALL;
            else left = maze.getKindOfObject(current_x-1, current_y); 
            //top
            if(current_y == 0) top = DemoObject.WALL;
            else top = maze.getKindOfObject(current_x, current_y-1);
            //right
            if(current_x == maze.getColumn() - 1) right = DemoObject.WALL;
            else right = maze.getKindOfObject(current_x+1, current_y);
            //bottom
            if(current_y == maze.getRow() - 1) bottom = DemoObject.WALL;
            else bottom = maze.getKindOfObject(current_x, current_y+1);
            
            bot.see(left, top, right, bottom);
            
            this.track();
            
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
            if(bot.getKinfOfTopObject() == DemoObject.WAY 
                    && bot.getTimeVisited(Bot.UP) == 0) {
                stack.push(new Pair<>(current_x, current_y-1));
                orient = Bot.UP;
            } 
            else if(bot.getKinfOfLeftObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.LEFT) == 0) {
                stack.push(new Pair<>(current_x-1, current_y));
                orient = Bot.LEFT;
            } 
            else if(bot.getKinfOfRightObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.RIGHT) == 0) {
                stack.push(new Pair<>(current_x+1, current_y));
                orient = Bot.RIGHT;
            } 
            else if(bot.getKinfOfBottomObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.DOWN) == 0) {
                stack.push(new Pair<>(current_x, current_y+1));
                orient = Bot.DOWN;
            }
            
            if(orient != -1) {
                bot.move(orient);
                directList.push(orient);
            } else {
                stack.pop();
                bot.reverseMove(directList.peek());
                directList.pop();
            }
            
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
    }
    
    private void findWayByDFS_random() {
        Stack<Pair<Integer, Integer> > stack = new Stack<>();
        Stack<Short> directList = new Stack<>();
        
        stack.push(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        directList.push((short)-1);
        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
        
        int current_x, current_y;
        List<Short> orient = new ArrayList<>();
        Random random = new Random();
        
        while(!stack.empty()) {
            if(end) return;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();

            //left
            if(current_x == 0) left = DemoObject.WALL;
            else left = maze.getKindOfObject(current_x-1, current_y); 
            //top
            if(current_y == 0) top = DemoObject.WALL;
            else top = maze.getKindOfObject(current_x, current_y-1);
            //right
            if(current_x == maze.getColumn() - 1) right = DemoObject.WALL;
            else right = maze.getKindOfObject(current_x+1, current_y);
            //bottom
            if(current_y == maze.getRow() - 1) bottom = DemoObject.WALL;
            else bottom = maze.getKindOfObject(current_x, current_y+1);
            
            bot.see(left, top, right, bottom);
            
            this.track();
            
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
            if(bot.getKinfOfLeftObject() == DemoObject.WAY 
                    && bot.getTimeVisited(Bot.LEFT) == 0) {
//                stack.push(new Pair<>(current_x-1, current_y));
                orient.add(Bot.LEFT);
            } 
            if(bot.getKinfOfRightObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.RIGHT) == 0) {
//                stack.push(new Pair<>(current_x+1, current_y));
                orient.add(Bot.RIGHT);
            } 
            if(bot.getKinfOfTopObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.UP) == 0) {
//                stack.push(new Pair<>(current_x, current_y-1));
                orient.add(Bot.UP);
            } 
            if(bot.getKinfOfBottomObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.DOWN) == 0) {
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
    }
    
    private void findWayByBFS() {
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Queue<Short> directList = new LinkedList<>();
        
        queue.add(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        directList.add((short)-1);
        
        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
        
        int current_x, current_y;
        
        while(!queue.isEmpty()) {
            if(end) return;
            Pair<Integer, Integer> current = queue.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();
            
            //left
            if(current_x == 0) left = DemoObject.WALL;
            else left = maze.getKindOfObject(current_x-1, current_y); 
            //top
            if(current_y == 0) top = DemoObject.WALL;
            else top = maze.getKindOfObject(current_x, current_y-1);
            //right
            if(current_x == maze.getColumn() - 1) right = DemoObject.WALL;
            else right = maze.getKindOfObject(current_x+1, current_y);
            //bottom
            if(current_y == maze.getRow() - 1) bottom = DemoObject.WALL;
            else bottom = maze.getKindOfObject(current_x, current_y+1);
            
            bot.see(left, top, right, bottom);
            
            this.track();
            
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
            
            if(bot.getKinfOfLeftObject() == DemoObject.WAY) {
                queue.add(new Pair<>(current_x-1, current_y));
            } 
            if(bot.getKinfOfRightObject() == DemoObject.WAY) {
                queue.add(new Pair<>(current_x+1, current_y));
            } 
            if(bot.getKinfOfTopObject() == DemoObject.WAY) {
                queue.add(new Pair<>(current_x, current_y-1));
            } 
            if(bot.getKinfOfBottomObject() == DemoObject.WAY) {
                queue.add(new Pair<>(current_x, current_y+1));
            }
        }
        
        this.end = true;
    }
    
    @Override
    public void run() {
        switch(this.algoName) {
            case "DFS":
                this.findWayByDFS();
                break;
            case "DFS-random":
                this.findWayByDFS_random();
                break;
            case "BFS":
                this.findWayByBFS();
                break;
            default:
        }
    }
    
    public void pause() {
        this.stop = true;
    }
   
    private DemoObject search() {
        for(Pair<Integer, DemoObject> pp : list) {
            if(pp.getFirst() == getIndex()) {
                return pp.getSecond();
            }
        }
        
        return null;
    }
    private void track() {
        DemoObject tmp = search();
        if(tmp == null) {
            tmp = bot.track();
            list.add(new Pair<>(getIndex(), tmp));
            this.mazeView.getPane().setLayer(tmp, 1, this.getIndex());
            this.mazeView.getPane().add(tmp);
        }
        else ((entity.Number)tmp).increase();
    }
    
    private int getIndex() {
        return this.bot.getxMaze()*maze.getColumn() + this.bot.getyMaze();
    }
    
    public void clear() {
        this.list.clear();
        end = true;
    }
    
    public boolean isEnd() {
        return this.end;
    }
}
