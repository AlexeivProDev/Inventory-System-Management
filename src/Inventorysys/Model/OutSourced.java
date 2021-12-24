/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.Model;

/**
 * @author Alexeiv Perez
 */
public class OutSourced extends Part {
    
    private String companyName;
    
 /**
   * This sets all of the parameters for OutSourced
     * @param partID Parameter for OutSourced
     * @param name Parameter for OutSourced
     * @param price Parameter for OutSourced
     * @param stock Parameter for OutSourced
     * @param max Parameter for OutSourced
     * @param min Parameter for OutSourced
     * @param companyName Parameter for OutSourced
   */
    public OutSourced(int partID,String name, double price, int stock, int max, int min,String companyName ){
        
        super( partID, name, price, stock, min, max);
        this.companyName = companyName;
        
    }
    /**
     * This method gets the name of the Company
     * @return return the name of the company
     */
  
       public String getCompanyName(){
        return companyName;
    }
   /**
     * This method sets the name of the Company
     * @param name set company name
     */ 
    public void setCompanyName(String name){
        this.companyName = name;
        
    }
    
}

    
    
    

