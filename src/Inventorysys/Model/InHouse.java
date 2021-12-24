/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.Model;

/**
 *
 * @author Alexeiv Perez
 */
public class InHouse extends Part{
    
    private int machineID;  
   /**
     * @param name this is a parameter for InHouse
     * @param price this is a parameter for InHouse
     * @param stock this is a parameter for InHouse
     * @param max this is a parameter for InHouse
     * @param min this is a parameter for InHouse
     * @param machineID this is a parameter for InHouse
     * @param id this is a parameter for InHouse
     */
    public InHouse (String name, double price, int stock, int max, int min, int id, int machineID){
        
        super(id,name, price, stock, min, max);
        this.machineID = machineID;
        
    
        
    }

    
/**
 *This method return the machine ID
     * @return return machineID
 */
    public int getMachineID(){
        return machineID;
    }
    
 /**
  *This method sets the id
     * @param id set id
  */
    public void setMachineID(int id){
        this.machineID = id;
    }
    
}
