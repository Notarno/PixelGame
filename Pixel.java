/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
 */
public class Pixel {
    private Location q;
    private int c;
    
    public Pixel(Location p, int color){
        q = p;
        c = color;
    }
    
    //returns the location of current pixel
    public Location getLocation(){
        return q;
    }
    
    //returns the color of current pixel
    public int getColor(){
        return c;
    }
}
