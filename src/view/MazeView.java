/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import entity.Bot;
import controller.FindingProcess;
import entity.*;
import javax.swing.JLayeredPane;
/**
 *
 * @author acer
 */
public class MazeView extends javax.swing.JFrame{
//    public static int left = 300, top = 10;
    public static int right, bottom;
    
    private final int box_width, box_height;
    private final int rows, columns;
    
    private javax.swing.JLayeredPane mazePane;
    private FindingProcess process;
    private boolean dispose = false;
    
    public MazeView(int width, int height, int row, int column, String name) {
        rows = row;
        columns = column;
        box_width = width/column;
        box_height = height/row;
        
//        right = left + width;
//        bottom = top + height;
        
        initComponents(width, height, name);
        process = new FindingProcess(this, name);
        process.setMaze(new Maze(row, column));
    }
    
    public MazeView(int width, int height, int row, int column, short[][] mazeDetail, String name) {
        rows = row;
        columns = column;
        box_width = width/column;
        box_height = height/row;
        
//        right = left + width;
//        bottom = top + height;
        
        initComponents(width, height, name);
        process = new FindingProcess(this, name);
        process.getMaze().setMaze(row, column, mazeDetail);
    }
    
    private void initComponents(int width, int height, String name) {
        this.setResizable(false);
//        this.setLocation(left, top);
        this.setLocation((ControlView.screen_width - width)/2, 
                (ControlView.screen_height - height)/2);
        this.setTitle(name);
        
        mazePane = new javax.swing.JLayeredPane();
        
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
    
    protected final void showMaze() {
        short[][] mazeInfo = this.process.getMaze().getMaze();
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                switch(mazeInfo[i][j]) {
                    case DemoObject.WALL: 
                        mazePane.add(new Wall(j, i, box_width, box_height));
                        break;                          
                    case DemoObject.BOT:
                        mazePane.add(new Way(j, i, box_width, box_height));
                        process.setBot(new Bot(j, i, box_width, box_height, 5));
                        break;
                    case DemoObject.GOAL:
                        mazePane.add(new Way(j, i, box_width, box_height));
                        process.setGoal(new Goal(j, i, box_width, box_height, 5));
                        break;
                    case DemoObject.WAY: 
                        mazePane.add(new Way(j, i, box_width, box_height));
                        break;
                    default:
                }
            }
        }

        try {
            mazePane.setLayer(process.getBot(), 2, -1);
            mazePane.add(process.getBot());
            mazePane.setLayer(process.getGoal(), 1, -1);
            mazePane.add(process.getGoal());  
        } catch(Exception e) {
            System.out.println(this.getClass().toString() + " create bot and flag " + e);
        }
    }
    
    public final void findGoal() {
       this.setVisible(true);
       showMaze();
    }
    
    public final FindingProcess getProcess() {
        return this.process;
    }
    
//    public final void reset() {
//        this.process.reset();
//    }
    
    public final JLayeredPane getPane() {
        return this.mazePane;
    }

    @Override
    public void dispose() {
        dispose = true;
        super.dispose(); //To change body of generated methods, choose Tools | Templates.
        process.clear();
        this.mazePane.removeAll();
    }

    public boolean isDispose() {
        return dispose;
    }
    
    public final boolean isFound() {
        return process.isEnd();
    }
}
