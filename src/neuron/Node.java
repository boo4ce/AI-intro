/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuron;

import entity.DemoObject;

/**
 *
 * @author acer
 */
public class Node {
    Node left;
    Node right;
    Node top;
    Node bottom;
    short kindOfObject;
    private int timeVisit;
    final int relativeX, relativeY;
    
    Node(int relativeX, int relativeY) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        
        kindOfObject = DemoObject.UNKNOWN;
        timeVisit = 0;
    }
    
    Node(int relativeX, int relativeY, short kindOfObject) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.kindOfObject = kindOfObject;
        timeVisit = 0;
    }
    
    void set(short kindOfObject) {
        this.kindOfObject = kindOfObject;
    }
    
    void addLeft(Node node) {
        left = node;
        node.right = this;
    }
    
    void addLeft(short kindOfObject) {
        left = new Node(this.relativeX-1, this.relativeY, kindOfObject);
        this.left.right = this;
    }
    
    void addRight(Node node) {
        right = node;
        node.left = this;
    }
    
    void addRight(short kindOfObject) {
        right = new Node(this.relativeX+1, this.relativeY, kindOfObject);
        this.right.left = this;
    }
    
    void addTop(Node node) {
        top = node;
        node.bottom = this;
    }
    
    void addTop(short kindOfObject) {
        top = new Node(this.relativeX, this.relativeY+1, kindOfObject);
        this.top.bottom = this;
    }
    
    void addBottom(Node node) {
        bottom = node;
        node.top = this;
    }
    
    void addBottom(short kindOfObject) {
        bottom = new Node(this.relativeX, this.relativeY-1, kindOfObject);
        this.bottom.top = this;
    }
    
    void visit() {
        this.timeVisit++;
    }
    
    int getTime() {
        return this.timeVisit;
    }
}
