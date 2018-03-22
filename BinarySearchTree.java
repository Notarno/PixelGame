





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
 */
public class BinarySearchTree implements BinarySearchTreeADT{
    private BinaryNode root;
    private Pixel successor = new Pixel(null, 0);
    private Pixel predecessor = new Pixel(null, 0);
    
    public BinarySearchTree(){
        root = new BinaryNode();
    }
    
    @Override
    public Pixel get(BinaryNode r, Location Key){
        //If the r is a leaf we assume the key is not in the tree and return null
        if (r.isLeaf()){
           return null;
        }
        else{
            Location w = r.getData().getLocation();
            switch (Key.compareTo(w)) {
                //if the key is less than the key of node r then visit left child of r
                case -1:
                    return get(r.getLeft(), Key);
                //if the key is  equal to the key of node r, then return the entry at r
                case 0:
                    return r.getData();
                //if the key is greater than the key of node r then visit right child of r
                default:
                    return get(r.getRight(), Key);
            }
        }
    }
    
    @Override
    public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException{
        //Checking if a node already has this pixel
        BinaryNode z = treeSearch(r, data.getLocation());
        
        //If tree does have this pixel, we must throw an exception and can not insert it
        if(!z.isLeaf()){
            throw new DuplicatedKeyException("This Key is a duplicate");
        }
        //If tree does not have this pixel, insert it
        else{
            insertAtExternal(z, data);
        }
    }
    
    @Override
    public void remove(BinaryNode r, Location Key) throws InexistentKeyException{
        //Searching for the key to be removed
        BinaryNode z = treeSearch(r, Key);
        BinaryNode w = new BinaryNode();
        
        //if key is not found, return an exception
        if(z.isLeaf()){
            throw new InexistentKeyException("This key does not exist in the tree");
        }
        //Else if key is found do the following
        //case 1: node v has a leaf child
        if((z.getLeft()).isLeaf()){
            removeAtExternal(z.getLeft());
        }
        else if((z.getRight()).isLeaf()){
            removeAtExternal(z.getRight());
        }
        //case 2: node z storing entry to be removed has no leaf child
        else{
            try {
                //we find the smallest node in the right subtree of z
                w = smallestNode(z.getRight());
            } catch (EmptyTreeException e) {
               System.out.println(e);
            }
                //set the entry of node z equal to node w and remove node w
                z.setData(w.getData());
                removeAtExternal(w.getLeft());
        }
    }
    
    @Override
    public Pixel successor(BinaryNode r, Location Key){
        //if the root is a leaf, we know there is no successor for the key and return null
        if(r.isLeaf()){
            return null;
        }
        //otherwise we search the tree for successor
        else if(Key.compareTo(root.getData().getLocation()) == 0){
            successorPredecessor(root, Key);
        }
        else{
            successorPredecessor(r, Key);
        }
       return successor;
   }
   
    //Similar to successor function
   @Override
   public Pixel predecessor(BinaryNode r, Location Key){
       if(r.isLeaf()){
           return null;
       }
       else if(Key.compareTo(root.getData().getLocation()) == 0){
           successorPredecessor(root, Key);
       }
       else{
           successorPredecessor(r, Key);
       }
       return predecessor;             
    }
    
   //This function allows us to traverse the tree to find smallest node
    @Override
    public Pixel smallest(BinaryNode r) throws EmptyTreeException{
        if(r.isLeaf() && r.getParent() == null){
            throw new EmptyTreeException("This tree is empty");
        }
        else{
            //In a BST the smallest entry is the farthest left of the tree
            if(r.getLeft().isLeaf()){
                return r.getData();
            }
            else{
                return smallest(r.getLeft());
            }
        }
    }
    
    //This function allows us to traverse the tree to find largest node
    @Override
    public Pixel largest(BinaryNode r) throws EmptyTreeException{
        if(r.isLeaf() && r.getParent() == null){
            throw new EmptyTreeException("This tree is empty");
        }
        else{
            //In a BST the largest entry is the farthest right of the tree
            if(r.getRight().isLeaf()){
                return r.getData();
            }
            else{
                return largest(r.getRight());
            }
        }
    }
    
    //Returns the root of the tree object
    @Override
    public BinaryNode getRoot(){
        return root;
    }
    
    //Similar to the get function, but returns a node instead of pixel
    private BinaryNode treeSearch(BinaryNode r, Location Key){
        //if key is nowhere to be found, return the last node visisted
         if (r.isLeaf()){
            return r;
        }
        
        Location w = r.getData().getLocation();
        switch (Key.compareTo(w)) {
            case -1:
                return treeSearch(r.getLeft(), Key);
            case 0:
                return r;
            default:
                return treeSearch(r.getRight(), Key);
        }
    }
    
    //This function allows us to remove external entries from the tree and recover Binary Search Tree Order
    private void removeAtExternal(BinaryNode r){
        
            BinaryNode p = r.getParent();
            BinaryNode nodeToRelink;
            //check if r is leaf node and assign relinking node accordingly
            if (p.getLeft().isLeaf() && r.isLeaf()){
                nodeToRelink = p.getRight();
            }
            else if(p.getRight().isLeaf() && r.isLeaf()){
                nodeToRelink = p.getLeft();
            }
            //check if r is the right child or left child of its parent node and assign relinking node accordingly
            else if(p.getLeft().getData().getLocation().compareTo(r.getData().getLocation()) == 0){
                nodeToRelink = p.getRight();
            }
            else{
                nodeToRelink = p.getLeft();
            }
            //If the parent of node r is the root of the tree, we assign a new root and remove old one
            if (p.getParent() == null && p.getData().getLocation().compareTo(root.getData().getLocation()) == 0){
                root = nodeToRelink;
                root.setParent(null);
            }
            else{
                if (p.getData().getLocation().compareTo(p.getParent().getLeft().getData().getLocation()) == 0){
                    p.getParent().setLeft(nodeToRelink);
                }
                else{
                    p.getParent().setRight(nodeToRelink);
                }
                nodeToRelink.setParent(p.getParent());
            }
    }
    
    //Similar to the smallest function but instead returns a Binary Node instead of a Pixel
    private BinaryNode smallestNode(BinaryNode r) throws EmptyTreeException{
       if(r.isLeaf() && r.getParent() == null){
           throw new EmptyTreeException("This tree is empty");
       }
       else{
           if(r.isLeaf()){
                return r.getParent();
           }
           else{
                return smallestNode(r.getLeft());
           }
       }
    }
    
    private void successorPredecessor(BinaryNode r, Location Key){
        BinaryNode z;
        if(!r.isLeaf()){
            //If key is equal to r key
            //Then we traverse farthest left in  right subtree for successor
            //And farthest right in left subtree for predecessor
            if(Key.compareTo(r.getData().getLocation()) == 0){
                if(!r.getLeft().isLeaf()){
                    z = r.getLeft();
                    while(!(z.getRight().isLeaf())){
                        z = z.getRight();
                    }
                    predecessor = z.getData();
                }
                if(!r.getRight().isLeaf()){
                    z = r.getRight();
                    while(!(z.getLeft().isLeaf())){
                        z = z.getLeft();
                    }
                    successor = z.getData();
                }
            }
            //if key is less than r key, than we make root the successor
            //and recursively call the function but now starting at the left subtree of r
            else if(Key.compareTo(r.getData().getLocation()) == -1){
                successor = r.getData();
                successorPredecessor(r.getLeft(), Key);
            }
            //if key is greater than r key, than we make root the predecessor
            //and recursively call the function but now starting at the right subtree of r
            else if(Key.compareTo(r.getData().getLocation()) == 1){
                predecessor = r.getData();
                successorPredecessor(r.getRight(), Key);
            }
        }
    }
    
    //This function allows us to insert an entry at a leaf child
    private void insertAtExternal(BinaryNode r, Pixel data){
        r.setData(data);
        r.setLeft(new BinaryNode(null, null, null, r));
        r.setRight(new BinaryNode(null, null, null, r));
    }
}
