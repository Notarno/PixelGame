/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
 */
public class Location {
    private int xCoor;
    private int yCoor;
    
    public Location(int x, int y){
        xCoor = x;
        yCoor = y;
    }
    
    //returns x coordinate
    public int xCoord(){
        return xCoor;
    }
    
    //Returns y coordinate
    public int yCoord(){
        return yCoor;
    }
    
    //Comparing function
    public int compareTo(Location p){
        //return -1 if current location is greater than location p
        if (this.xCoord() < p.xCoord() || (this.xCoord() == p.xCoord() && this.yCoord() < p.yCoord())){
            return -1;
        }
        //return 0 if current location is equal to location p
        else if (this.xCoord() == p.xCoord() && this.yCoord() == p.yCoord()){
            return 0;
        }
        //If previous cases above are false, it is assumed current location is greater than locatation p so return 1
        else{
            return 1;
        }
        
    }
}
