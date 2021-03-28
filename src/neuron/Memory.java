/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuron;

import entity.DemoObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class Memory {
    private List<Node> memory;
    private Node currentNode;
    
    public Memory() {
        memory = new ArrayList<>();
        currentNode = new Node(0, 0, DemoObject.BOT);
    }
    
    public void addAroundNode(short kindOfLeftObject, short kindOfRightObject, 
            short kindOfTopObject, short kindOfBottomObject) {
        int tmpX = currentNode.relativeX, tmpY = currentNode.relativeY;
        Node tmpNode;
        
        // new left node
        if((tmpNode = searchNode(tmpX - 1, tmpY)) == null) {
            currentNode.addLeft(kindOfLeftObject);
            memory.add(currentNode.left);
        } else {
            currentNode.addLeft(tmpNode);
        }
        
        // new right node
        if((tmpNode = searchNode(tmpX + 1, tmpY)) == null) {
            currentNode.addRight(kindOfLeftObject);
            memory.add(currentNode.right);
        } else {
            currentNode.addRight(tmpNode);
        }
        
        // new top node
        if((tmpNode = searchNode(tmpX, tmpY + 1)) == null) {
            currentNode.addTop(kindOfLeftObject);
            memory.add(currentNode.top);
        } else {
            currentNode.addTop(tmpNode);
        }
        
        // new bottom node
        if((tmpNode = searchNode(tmpX, tmpY - 1)) == null) {
            currentNode.addBottom(kindOfLeftObject);
            memory.add(currentNode.bottom);
        } else {
            currentNode.addBottom(tmpNode);
        }
    }
    
    private Node searchNode(int relativeX, int relativeY) {
        for(Node node : memory)
            if(node.relativeX == relativeX && node.relativeY == relativeY) {
                return node;
            }
        
        return null;
    }

    public final Node getCurrentNode() {
        return currentNode;
    }

    public final void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }
    
    public final void set(short kindOfObject) {
        currentNode.set(kindOfObject);
    }
    
    public final void moveLeft() {
        currentNode = currentNode.left;
    }
    
    public final void moveRight() {
        currentNode = currentNode.right;
    }
    
    public final void moveUp() {
        currentNode = currentNode.top;
    }
    
    public final void moveDown() {
        currentNode = currentNode.bottom;
    }
    
    public final short getKindOfCurrentNode() {
        return currentNode.kindOfObject;
    }
}