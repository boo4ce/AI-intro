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
    
    private final String algoName;
    
    private boolean end = false;
    private boolean stop = false;
    
    private List<Pair<Integer, DemoObject>> list;
    private int timePerStep = 500;
    
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
        
        int current_x, current_y;
        short orient;
        
        while(!stack.empty()) {
            if(end) return;
            orient = -1;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();
            
            getArround(current_x, current_y);
            this.track();
            
            // goal in 4 direction
            if(bot.seeGoal()) break;
            
            //
            if(bot.getKindOfTopObject() == DemoObject.WAY 
                    && bot.getTimeVisited(Bot.UP) == 0) {
                stack.push(new Pair<>(current_x, current_y-1));
                orient = Bot.UP;
            } 
            else if(bot.getKindOfLeftObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.LEFT) == 0) {
                stack.push(new Pair<>(current_x-1, current_y));
                orient = Bot.LEFT;
            } 
            else if(bot.getKindOfRightObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.RIGHT) == 0) {
                stack.push(new Pair<>(current_x+1, current_y));
                orient = Bot.RIGHT;
            } 
            else if(bot.getKindOfBottomObject() == DemoObject.WAY
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
                Thread.sleep(timePerStep);
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
        
        int current_x, current_y;
        List<Short> orient = new ArrayList<>();
        Random random = new Random();
        
        while(!stack.empty()) {
            if(end) return;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();

            getArround(current_x, current_y);
            
            this.track();
            // goal in 4 direction
            if(bot.seeGoal()) break;
            
            //
            if(bot.getKindOfLeftObject() == DemoObject.WAY 
                    && bot.getTimeVisited(Bot.LEFT) == 0) {
//                stack.push(new Pair<>(current_x-1, current_y));
                orient.add(Bot.LEFT);
            } 
            if(bot.getKindOfRightObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.RIGHT) == 0) {
//                stack.push(new Pair<>(current_x+1, current_y));
                orient.add(Bot.RIGHT);
            } 
            if(bot.getKindOfTopObject() == DemoObject.WAY
                    && bot.getTimeVisited(Bot.UP) == 0) {
//                stack.push(new Pair<>(current_x, current_y-1));
                orient.add(Bot.UP);
            } 
            if(bot.getKindOfBottomObject() == DemoObject.WAY
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
                Thread.sleep(timePerStep);
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
   
    
    private void findWayByTremaux() {
        Stack<Pair<Integer, Integer> > stack = new Stack<>();
        Stack<Short> directList = new Stack<>();
        
        stack.push(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        directList.push((short)-1);
        
        int current_x, current_y;
        short orient = Bot.DOWN;
        
        while(!stack.empty()) {
            if(end) return;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();
            
            getArround(current_x, current_y);
            
            this.track();

            // goal in 4 direction
            if(bot.seeGoal()) break;
            
            //algorithm
            if(isMultiWay()) orient = fixedGetWay();
                
            if(orient != -1) {
                switch(orient) {
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
               
                bot.move(orient);
                directList.push(orient);
            } else {
                stack.pop();
                bot.reverseMove(directList.peek());
                directList.pop();
            }

            try {
                Thread.sleep(timePerStep);
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
    private void findWayByTremaux_random() {
        Stack<Pair<Integer, Integer> > stack = new Stack<>();
        Stack<Short> directList = new Stack<>();
        
        stack.push(new Pair<>(bot.getxMaze(), bot.getyMaze()));
        directList.push((short)-1);
        
        int current_x, current_y;
        short orient = Bot.DOWN;
        
        while(!stack.empty()) {
            if(end) return;
            Pair<Integer, Integer> current = stack.peek();
            
            current_x = current.getFirst();
            current_y = current.getSecond();
            
            getArround(current_x, current_y);
            
            this.track();

            // goal in 4 direction
            if(bot.seeGoal()) break;
            
            //algorithm
            if(isMultiWay()) orient = randomGetWay();
                
            if(orient != -1) {
                switch(orient) {
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
               
                bot.move(orient);
                directList.push(orient);
            } else {
                stack.pop();
                bot.reverseMove(directList.peek());
                directList.pop();
            }

            try {
                Thread.sleep(timePerStep);
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
    private short fixedGetWay() {
        int _min = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        int count_way = 0;
        int count_equal = 0;
        
        if(bot.getKindOfLeftObject() != DemoObject.WALL) {
            left = bot.getTimeVisited(Bot.LEFT);
            _min = Math.min(left, _min);
            System.out.println("Left " + left);
            count_way++;
        }
        
        if(bot.getKindOfRightObject() != DemoObject.WALL) {
            right = bot.getTimeVisited(Bot.RIGHT);
            _min = Math.min(right, _min);
            System.out.println("Right " + right);
            count_way++;
        } 
        
        if(bot.getKindOfBottomObject() != DemoObject.WALL) {
            bottom = bot.getTimeVisited(Bot.DOWN);
            _min = Math.min(bottom, _min);
            System.out.println("Bottom " + bottom);
            count_way++;
        }
        
        if(bot.getKindOfTopObject() != DemoObject.WALL) {
            top = bot.getTimeVisited(Bot.UP);
            _min = Math.min(top, _min);
            System.out.println("Top " + top);
            count_way++;
        }
        
        short orient = -1;
        if(left == _min) {
            orient = Bot.LEFT;
            count_equal++;
        }
        if(right == _min) {
            orient = Bot.RIGHT;
            count_equal++;
        }
        if(bottom == _min) {
            orient = Bot.DOWN;
            count_equal++;
        }
        if(top == _min) {
            orient = Bot.UP;
            count_equal++;
        }
        
        System.out.println("-------------------------------------");
        
        if(count_way == count_equal && count_way != 1) return -1;
        return orient;

    }
    private short randomGetWay() {
        int _min = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int top = Integer.MAX_VALUE;
        int bottom = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        int count_way = 0;
        int count_equal = 0;
        
        if(bot.getKindOfLeftObject() != DemoObject.WALL) {
            left = bot.getTimeVisited(Bot.LEFT);
            _min = Math.min(left, _min);
            System.out.println("Left " + left);
            count_way++;
        }
        
        if(bot.getKindOfRightObject() != DemoObject.WALL) {
            right = bot.getTimeVisited(Bot.RIGHT);
            _min = Math.min(right, _min);
            System.out.println("Right " + right);
            count_way++;
        } 
        
        if(bot.getKindOfBottomObject() != DemoObject.WALL) {
            bottom = bot.getTimeVisited(Bot.DOWN);
            _min = Math.min(bottom, _min);
            System.out.println("Bottom " + bottom);
            count_way++;
        }
        
        if(bot.getKindOfTopObject() != DemoObject.WALL) {
            top = bot.getTimeVisited(Bot.UP);
            _min = Math.min(top, _min);
            System.out.println("Top " + top);
            count_way++;
        }
        
        List<Short> orient = new ArrayList<>();
        Random random = new Random();
        
        if(left == _min) {
            orient.add(Bot.LEFT);
            count_equal++;
        }
        if(right == _min) {
            orient.add(Bot.RIGHT);
            count_equal++;
        }
        if(bottom == _min) {
            orient.add(Bot.DOWN);
            count_equal++;
        }
        if(top == _min) {
            orient.add(Bot.UP);
            count_equal++;
        }
        
        if(count_way == count_equal && count_way != 1) return -1;
        return orient.get(Math.abs(random.nextInt())%orient.size());
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
//                this.findWayByBFS();
                break;
            case "Tremaux":
                this.findWayByTremaux();
                break;
            case "Tremaux-random":
                this.findWayByTremaux_random();
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
    
    // --------------- track -----------------
    private void track() {
        bot.track();
        
        DemoObject tmp = search();
        if(tmp == null) {
            tmp = bot.getTrackTime();
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
    
    private void getArround(int current_x, int current_y) {
        System.out.println("Get arround");
        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
        
        if(bot.getTimeVisited() == 0) 
        {
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
            
        }
    }
    
    private boolean isMultiWay() {
        if(bot.getKindOfBottomObject() == DemoObject.WAY
                && bot.getKindOfTopObject()== DemoObject.WAY
                && bot.getKindOfLeftObject() == DemoObject.WALL
                && bot.getKindOfRightObject() == DemoObject.WALL) {
            return false;
        }
        
        return !(bot.getKindOfBottomObject() == DemoObject.WALL
                && bot.getKindOfTopObject()== DemoObject.WALL
                && bot.getKindOfLeftObject() == DemoObject.WAY
                && bot.getKindOfRightObject() == DemoObject.WAY);
    }
    
    
//    private void findWayByBFS() {
//        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
//        Queue<Short> directList = new LinkedList<>();
//        
//        queue.add(new Pair<>(bot.getxMaze(), bot.getyMaze()));
//        directList.add((short)-1);
//        
//        short left = DemoObject.UNKNOWN, right = DemoObject.UNKNOWN, 
//                top = DemoObject.UNKNOWN, bottom = DemoObject.UNKNOWN;
//        
//        int current_x, current_y;
//        
//        while(!queue.isEmpty()) {
//            if(end) return;
//            Pair<Integer, Integer> current = queue.peek();
//            
//            current_x = current.getFirst();
//            current_y = current.getSecond();
//            
//            //left
//            if(current_x == 0) left = DemoObject.WALL;
//            else left = maze.getKindOfObject(current_x-1, current_y); 
//            //top
//            if(current_y == 0) top = DemoObject.WALL;
//            else top = maze.getKindOfObject(current_x, current_y-1);
//            //right
//            if(current_x == maze.getColumn() - 1) right = DemoObject.WALL;
//            else right = maze.getKindOfObject(current_x+1, current_y);
//            //bottom
//            if(current_y == maze.getRow() - 1) bottom = DemoObject.WALL;
//            else bottom = maze.getKindOfObject(current_x, current_y+1);
//            
//            bot.see(left, top, right, bottom);
//            
//            this.track();
//            
//            // goal in 4 direction
//            if(left == DemoObject.GOAL) {
//                bot.move(Bot.LEFT);
//                break;
//            }
//            if(right == DemoObject.GOAL) {
//                bot.move(Bot.RIGHT);
//                break;
//            }
//            if(top == DemoObject.GOAL) {
//                bot.move(Bot.UP);
//                break;
//            }
//            if(bottom == DemoObject.GOAL) {
//                bot.move(Bot.DOWN);
//                break;
//            }
//            
//            if(bot.getKindOfLeftObject() == DemoObject.WAY) {
//                queue.add(new Pair<>(current_x-1, current_y));
//            } 
//            if(bot.getKindOfRightObject() == DemoObject.WAY) {
//                queue.add(new Pair<>(current_x+1, current_y));
//            } 
//            if(bot.getKindOfTopObject() == DemoObject.WAY) {
//                queue.add(new Pair<>(current_x, current_y-1));
//            } 
//            if(bot.getKindOfBottomObject() == DemoObject.WAY) {
//                queue.add(new Pair<>(current_x, current_y+1));
//            }
//        }
//        
//        this.end = true;
//    }
}
