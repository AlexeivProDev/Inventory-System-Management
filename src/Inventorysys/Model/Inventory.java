/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Inventorysys.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Alexeiv Perez
 */
public class Inventory{
    
    private  static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private   static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
  /**
    * This method adds a part to the Observable list
     * @param part add parts
    */
    public static void addPart(Part part){

        allParts.add(part);
}
    /**
     * Updates part from the list
     * @param update update parts
     */
    public static void updatePart(Part update){    
        for(int i= 0; i < allParts.size(); i++){
            if(allParts.get(i).getId() == update.getId()){
                allParts.set(i,update);
                break;
            }
        }   
    }
    /**
     * return the parts when is called
     * @return  return parts
     */
    public static ObservableList<Part> getAllParts(){

    return allParts;
}
    /**
     * Removes part from the list
     * @param partRemove remove part
     */
     public static void removePart(Part partRemove){
         allParts.remove(partRemove);
         
}
   /**
    * I decided to not use this method
     * @param partToLookUp did not use
     * @return n/a
    */
     public static Part lookUpPart(int partToLookUp) {
        
        return null;
     }
   /**
    * I decided to not use this method
     * @param partNameToLookUp n/a
     * @return n/a
    */
      public static ObservableList<Part> lookUpPart(String partNameToLookUp) {
       
        return null;
    }
   /**
     * It deletes the parts from the list
     * @param partToDelete delete part
     * @return delete part
     */
      public static boolean deletePart(Part partToDelete) {
          allParts.remove(partToDelete);
          
        return true;
    }
   /**
     * Updates Product from the list
     * @param ID look for and update id
     * @return return id
     */
      public static Product getcurrentProduct(int ID)
      {
          for(int i = 0 ; i < allProducts.size() ; i++)
          {
              if(allProducts.get(i).getId() == ID)
                  return allProducts.get(i);
          }
          return null;
      }
   /**
     * This adds a product to the list
     * @param product add product
     */
      public static void addProduct(Product product){

            allProducts.add(product);
}
   /**
     * Observable list that returns the products
     * @return return products
     */
       public static ObservableList<Product> getAllProducts(){

            return allProducts;
}
   /**
     * remove the product from the list
     * @param productToRemove n/a
     * @return n/a
     */ 
      
     public static boolean removeProduct(Product productToRemove) {
         allProducts.remove(productToRemove);
       return true;
    }
  /**
    * I decided to not use this method
     * @param productNameToLookUp n/a
     * @return n/a
    */
     public static ObservableList<Product> lookUpProduct(String productNameToLookUp) {
         
        return null;
    }
  /**
    * I decided to not use this method
     * @param productToSearch search product
     * @return n/a
    */
     public static Product lookUpProduct(int productToSearch) {
   
        return null;
    }
  /**
    * Looks for the Product that needs to be updated and updates the product
     * @param product update product
    */
     public static void updateProduct(Product product) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == product.getId()) {
                allProducts.set(i, product);
                break;
            }
        }
        return;
    }

    
    }

         
         
     



    
   