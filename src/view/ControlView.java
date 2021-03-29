/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entity.Bot;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class ControlView extends javax.swing.JFrame  {
    private final short[][] mazeDetail = {
        {3, 5, 2, 2, 2, 2, 2, 3, 3, 3},
        {3, 3, 2, 3, 3, 3, 3, 3, 2, 2},
        {2, 3, 2, 3, 2, 2, 2, 3, 3, 3},
        {2, 3, 2, 3, 3, 3, 3, 3, 2, 3},
        {2, 3, 2, 2, 2, 2, 2, 3, 2, 3},
        {2, 3, 3, 3, 3, 3, 3, 3, 3, 3},
        {2, 3, 2, 2, 2, 2, 2, 2, 2, 3},
        {2, 3, 3, 3, 2, 3, 3, 3, 3, 3},
        {2, 3, 2, 3, 2, 3, 2, 2, 2, 3},
        {2, 2, 2, 4, 2, 3, 2, 3, 3, 3}
    };
    
    private List<MazeView> mazes;
    protected boolean running = false;
    /**
     * Creates new form ControlView
     */
    public ControlView() {
        initComponents();
        this.setLocation(MazeView.right + 100, MazeView.top + 100);
        mazes = new ArrayList<>();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();
        resumeButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        algo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control");
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("mainFrame"); // NOI18N
        setResizable(false);

        newButton.setText("New");
        newButton.setFocusable(false);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        stopButton.setText("Stop");
        stopButton.setFocusable(false);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        resumeButton.setText("Resume");
        resumeButton.setFocusable(false);
        resumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resumeButtonActionPerformed(evt);
            }
        });

        resetButton.setText("Reset");
        resetButton.setFocusable(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        algo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DFS", "BFS" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(resumeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(stopButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(newButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(startButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(algo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(196, Short.MAX_VALUE)
                .addComponent(newButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stopButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resumeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(resetButton)
                .addGap(18, 18, 18)
                .addComponent(algo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        // TODO add your handling code here:
        short[][] tmp = mazeDetail.clone();
        mazes.add(new MazeView(400, 400, 10, 10, tmp, algo.getSelectedItem().toString()));
        mazes.get(mazes.size()-1).findGoal();
    }//GEN-LAST:event_newButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        running = true;
        for(MazeView mv : mazes) {
            new Thread(mv.getProcess()).start();
        }
        
    }//GEN-LAST:event_startButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        // TODO add your handling code here:
        for(MazeView mv : mazes) 
            mv.getProcess().pause();
    }//GEN-LAST:event_stopButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
       mazes.clear();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void resumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resumeButtonActionPerformed
        // TODO add your handling code here:
        for(MazeView mv : mazes) {
            synchronized(mv.getProcess()) {
                mv.getProcess().notifyAll();
            }
        }
    }//GEN-LAST:event_resumeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public void run() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControlView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ControlView.this.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> algo;
    private javax.swing.JButton newButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton resumeButton;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables
}
