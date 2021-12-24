/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.Main;


import Inventorysys.Model.InHouse;
import Inventorysys.Model.Inventory;
import Inventorysys.Model.OutSourced;
import Inventorysys.Model.Product;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Alexeiv Perez
 */
public class InventorySystem extends Application {

    private static Inventory inv;
   
    private Stage stage;
/**
 * This will display the dummy data into the main screen
     * @param args main method
 */
     public static void main (String [] args){
         
        Inventory inv = new Inventory();
        InHouse fakePart1 = new InHouse ( "Computer", 599.99, 100, 1, 100, 123,0);
        InHouse fakePart3 = new InHouse ( "Laptop", 1300.99, 100, 1, 100, 12,0);
        OutSourced fakePart2 = new OutSourced (1, "vru", 11.10, 1,1,1, "tr");
        OutSourced fakePart4 = new OutSourced (1, "plc", 11.10, 1,1,1, "tr");
        InHouse fakePart5 = new InHouse ( "mac", 999.99, 100, 1, 100, 11,0);
        Inventory.addPart(fakePart1);
        Inventory.addPart(fakePart2);
        Inventory.addPart(fakePart3);
        Inventory.addPart(fakePart4);
        Inventory.addPart(fakePart5);
        
        Product product1 = new Product( 1, "poppet",1,12.0,11,11);
        Product product2 = new Product( 10, "printer",10,12.0,11,11);
        Product product3 = new Product( 100, "paper",15,12.0,11,11);
        Product product4 = new Product( 20, "pencil",17,12.0,11,11);
        Product product5 = new Product( 30, "pen",8,12.0,11,11);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        Inventory.addProduct(product5);
    
        
        launch(args);
        
    }
     /**
      *starts the main screen 
      */
    @Override
    public void start(Stage stage) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
    }
   

 }
      

