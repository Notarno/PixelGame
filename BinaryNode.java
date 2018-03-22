/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
 */
public class BinaryNode {
    private BinaryNode l;
    private BinaryNode r;
    private BinaryNode p;
    private Pixel v;
    
    public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent){
        v = value;
        l = left;
        r = right;
        p = parent;
    }
    
    //Creates a leaf node with parent that is null
    public BinaryNode(){
        v = null;
        l = null;
        r = null;
        p = null;
    }
    
    //Returns parent of current node
    public BinaryNode getParent(){
        return p;
    }
    
    //Sets parent of current node
    public void setParent(BinaryNode parent){
        p = parent;
    }
    
    //Sets left child of current node
    public void setLeft(BinaryNode p){
        l = p;
    }
    
    //Sets right child of current node
    public void setRight(BinaryNode p){
        r = p;
    }
    
    //Sets the entry at current node
    public void setData(Pixel value){
        v = value;
    }
    
    //Returns the entry at current node
    public Pixel getData(){
        return v;
    }
    
    //Returns left child of current node
    public BinaryNode getLeft(){
        return l;
    }
    
    //Returns right child of current node
    public BinaryNode getRight(){
        return r;
    }
    
    //Checks if the current node is a leaf
    public boolean isLeaf(){
        if(this.getData() == null && this.getLeft() == null && this.getRight() == null){
            return true;
        }
        else{
            return false;
        }
    }
}
