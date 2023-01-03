/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.awt.Dimension;
import java.awt.Point;

/**
 *
 * @author berka
 */
public class LocationModel {
    private Point location;
    private Dimension diemention;
    
    public LocationModel(Point location, Dimension dimension){
        this.location = location;
        this.diemention = dimension;
        
    }
    
    public void setLocation(Point location){
        this.location = location;
    }
    
    public Point getLocation(){
        return location;
        
    }
    public void setDiemention(Dimension dimension){
        this.diemention = dimension;
        
    }
    public Dimension getDimension(){
        return this.diemention;
    }
    
   
}
