/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
 */
public class GraphicalObject implements GraphicalObjectADT{
    private int identity;
    private int w;
    private int h;
    private String t;
    private Location p;
    private BinarySearchTree bsTree;
    
    public GraphicalObject(int id, int width, int height, String type, Location pos){
        identity = id;
        w = width;
        h = height;
        t = type;
        p = pos;
        bsTree = new BinarySearchTree();
    }
    
    //Sets the type for the graphical object
    @Override
    public void setType(String type){
        t = type;
    }
    
    //Sets the width of the graphical object
    @Override
    public int getWidth(){
        return w;
    }
    
    //Sets the height of the graphical object
    @Override
    public int getHeight(){
        return h;
    }
    
    //Returns the type of the graphical object
    @Override
    public String getType(){
        return t;
    }
    
    //Returns the identity of the graphical object
    @Override
    public int getId(){
        return identity;
    }
    
    //Returns the offset of the graphical object
    @Override
    public Location getOffset(){
        return p;
    }
    
    //Sets the offset of the graphical object
    @Override
    public void setOffset(Location value){
        p = value;
    }
    
    //Adds a pixel to the BST of the graphical object
    @Override
    public void addPixel(Pixel pix) throws DuplicatedKeyException{
        try{
            bsTree.put(bsTree.getRoot(), pix);
        }catch(DuplicatedKeyException e){
            System.out.println(e);
        }
    }
    
    //Checks if the current graphical object intersects with graphical object gobj
    @Override
    public boolean intersects(GraphicalObject gobj){
        int size = this.getHeight()*this.getWidth();
        Pixel temp = new Pixel(null, 0);
        
        try{
            temp = this.getBST().smallest(this.getBST().getRoot());
        }catch(EmptyTreeException e){
            System.out.println(e);
        }
        
        int x, y;
        
        for(int i = 0; i < size; i++){
            x = temp.getLocation().xCoord() + this.getOffset().xCoord() - gobj.getOffset().xCoord();
            y = temp.getLocation().yCoord() + this.getOffset().yCoord() - gobj.getOffset().yCoord();
            if(gobj.findPixel(new Location(x, y))){
                return true;
            }
            else{
                temp = this.getBST().successor(this.getBST().getRoot(), temp.getLocation());
            }
        }
        return false;
    }
    
    //Returns the BST
    private BinarySearchTree getBST(){
        return bsTree;
    }
    
    //Returns true if the pixel is found in the graphical object
    private boolean findPixel(Location p){
        if(this.getBST().get(this.bsTree.getRoot(), p) != null){
            return true;
        }
        else{
            return false;
        }
    }
}
