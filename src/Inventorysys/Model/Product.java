/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Alexeiv Perez
 */
public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private int inStock;
    private double price;
    private int max;
    private int min;
    /**
     * @param ID Parameter for Product
     * @param Name Parameter for Product
     * @param Stock Parameter for Product
     * @param Price Parameter for Product
     * @param Max Parameter for Product
     * @param Min Parameter for Product
     */
    public Product(int ID, String Name, int Stock, double Price, int Max, int Min) {
        this.id = ID;
        this.name = Name;
        this.inStock = Stock;
        this.price = Price;
        this.max = Max;
        this.min = Min;     
    }
    
    /**
     * This get the Name of the product
     * @return return name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * This sets the Name of the product
     * @param name set name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This get the Price of the product
     * @return get price
     */
    public double getPrice() {
        return this.price;
    }
    
    /**
     * This sets the Price of the Product 
     * @param price set price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * This gets the Inventory levels of the Product 
     * @return get stock
     */
    public int getStock() {
        return this.inStock;
    }
    
    /**
     * This sets the Inventory levels of the Product 
     * @param quantity return quantity
     */
    public void setStock(int quantity) {
        this.inStock = quantity;
    }
    
    /**
     * This gets the Minimum of the Product 
     * @return return min
     */
    public int getMin() {
        return this.min;
    }
    
    /**
     * This sets the Minimum amount of the Product 
     * @param min set min
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * This gets the Maximum amount of the Product 
     * @return set max
     */
    public int getMax() {
        return this.max;
    }
    
    /**
     * This sets the Maximum amount of the Product 
     * @param max set max
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * This sets the Price of the Product 
     * @param partToAdd this is to an associated part
     */
    public void addAssociatedPart(Part partToAdd) {
        associatedParts.add(partToAdd);
    }
    
    /**
     * This removes the associated part from the list 
     * @param partToRemove to remove an associated part
     * @return an associated part
     */
    public boolean removeAssociatedPart(int partToRemove) {
        int i;
        for (i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getId() == partToRemove) {
                associatedParts.remove(i);
                return true;
            }
        }

        return false;
    }
    
    /**
     * This sets the Price of the Product 
     * @param partToSearch n/a
     * @return n/a
     */
    public Part lookupAssociatedPart(int partToSearch) {
        for (int i = 0; i < associatedParts.size(); i++) {
            if (associatedParts.get(i).getId() == partToSearch) {
                return associatedParts.get(i);
            }
        }
        return null;
    }
    
    /**
     * This gets the ID of the Product 
     * @return n/a
     */
    public int getId() {
        return this.id;
    }
    /**
     * This sets the ID of the Product 
     * @param id n/a
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * This gets the size of the List 
     * @return n/a
     */
    public int getPartsListSize() {
        return associatedParts.size();
    }
    /**
     * This sets an observable list to return the associatedParts 
     * @return n/a
     */
    public ObservableList<Part> getassociatedPart(){
        return associatedParts;
    }

}
    

