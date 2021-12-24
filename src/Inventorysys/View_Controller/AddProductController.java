/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.View_Controller;

import Inventorysys.Model.Inventory;
import Inventorysys.Model.Part;
import Inventorysys.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author Alexeiv Perez
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField ID;
    @FXML
    private TextField Name;
    @FXML
    private TextField Inv;
    @FXML
    private TextField Price;
    @FXML
    private TextField Max;
    @FXML
    private TextField Min;
    @FXML
    private TableColumn<Part, Integer> columnPartID;
    @FXML
    private TableColumn<Part, String> columnPartName;
    @FXML
    private TableColumn<Part, Integer> columnInv;
    @FXML
    private TableColumn<Part, Double> columnPrice;
    @FXML
    private TableColumn<Product,Integer > columnParttw;
    @FXML
    private TableColumn<Product, String> columnPartN;
    @FXML
    private TableColumn<Product, Integer> columnInvLev;
    @FXML
    private TableColumn<Product, Double> columnPriceUn;
    @FXML
    private TableView<Part> partsView;
    @FXML
    private TableView<Part> productsView;
    @FXML
    private TextField searchForPartss;
    
    ObservableList<Part> associatedPart = FXCollections.observableArrayList();
  
    /**
     * Initializes the controller class.
     * @param url initializes
     * @param rb initializes
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        partsView.setItems(Inventory.getAllParts());
        //parts
        columnPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //associated parts
        columnParttw.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnPartN.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnInvLev.setCellValueFactory(new PropertyValueFactory<>("stock"));
        columnPriceUn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
       
        
    }    
    /**
     * This is to add Items to the Products table
     * @param event this is to add an associated part to the associated part table
     */
    @FXML
    public void addProd(ActionEvent event) {
       
         Part selection = partsView.getSelectionModel().getSelectedItem();
         associatedPart.add(selection);
         productsView.setItems(associatedPart);
    }
/**
  * This is to save a Product to the Products table back to the Main Screen ----- 
  * I encountered an error while building this program "java.lang.NumberFormatException" When the user would try to put letters in to the machine id filed-- Also, when the user tried to put letters in the Text fields such as price, inv, max, min; I would get the same error-- I fixed this error by putting some exceptions into the program  for each field and i used the "try and catch" approach
  * @param event this is to save product
  * @throws java.io.IOException exception
  */
    @FXML
    public void saveProduct(ActionEvent event) throws IOException {
        if (Name.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please fill out all of the required fields");
            alert.showAndWait();
            return;
        }
        try
        {
            int nName = Integer.parseInt(Name.getText().trim());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Name field only accepts letters! Please make a correction");
            alert.showAndWait();
            return;
        }catch(Exception er)
        {
            
        }
        try{
            if(Integer.parseInt(Inv.getText()) < Integer.parseInt(Min.getText())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Minimum number cannot be greater than the Items in the Inventory");
            alert.showAndWait();
            return;  
        }
        }catch(Exception er){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setContentText("Inv and Min fields only accepts numbers! Please make a correction");
            alert.showAndWait();
            return;
        }
       try{
           if(Integer.parseInt(Inv.getText()) > Integer.parseInt(Max.getText()) ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Number of Items in Inventory cannot be greater than the Maximum amount of Items allowed");
            alert.showAndWait();
            return;
        }
       }catch(Exception er){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setContentText("Max field only accept numbers! Please make a correction");
            alert.showAndWait();
            return;
       }
       
       if(Price.getText().isEmpty() ){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please fill out all of the required fields");
            alert.showAndWait();
            return;
       }
       try{
           double dprice = Double.valueOf(Price.getText());
       }catch(Exception er){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning");
            alert.setContentText("Price field is double format");
            alert.showAndWait();
            return;
       }
        
        
        // this will add the product to the table
        Product product = new Product (Integer.parseInt(ID.getText()), Name.getText(),  (int) Double.parseDouble(Price.getText()), Integer.parseInt(Inv.getText()), Integer.parseInt(Max.getText()), Integer.parseInt(Min.getText()));
        for(int i= 0; i < associatedPart.size(); i++){
            product.addAssociatedPart(associatedPart.get(i));
        }
        Inventory.addProduct(product);
        //calling back to the main screen
        Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(viewScene);
            window.show();                  
    }
    /**
     * This is to remove any associated parts, but the user has to select an item first. If the user does not select an Item from the table, the program will ask the user to select a Part. In case the user wants to remove a part, the program will throw a warning and ask the user if they really want to delete the part.
     * @param event  this is to remove associated part
     */
    @FXML
    public void removeAssociated(ActionEvent event) {
        int nselect = productsView.getSelectionModel().getSelectedIndex();
        if(nselect < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please select a Product");
            alert.showAndWait();
            return;
        }
        Part deleteProduct = productsView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Delete Confirm");
            alert.setContentText("Are you sure delecte this Product?");
            
            alert.showAndWait();
        if(alert.getResult() == ButtonType.YES)
        {
            associatedPart.remove(deleteProduct);
        }
    }
    
  /**
    *This method will take the user back to the main screen when the user clicks on the cancel button
     * @param event this is to go back to the main screen
     * @throws java.io.IOException exception
    */
    @FXML
    public void cancelMain(ActionEvent event) throws IOException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Cancellation Confirm");
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait();
        
         Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(viewScene);
            window.show();
    }

  /**
   * This method will generate the ID automatically once the user clicks on the ID TextField  
     * @param event this is to automatically generate an ID
   */
    @FXML
    public void iDauto(MouseEvent event) {
        Random idgen = new Random();
        int gen = idgen.nextInt(1000)+1;
        String generator = String.valueOf(gen);
        ID.setText(generator);
    }
    
    /**
     * This method is for searching the item through the table when the user types in the ID or the Name of the item-- The program will warn the user if the part is not found
     * @param event this is to search for an item
     */
    @FXML
    public void searchBoxPart(KeyEvent event) {
        System.out.println("type in Search box");
        String s = searchForPartss.getText().toLowerCase().trim();
        if(s.isEmpty()) {
            partsView.setItems(Inventory.getAllParts());
        }
        else{
            ObservableList<Part> result = FXCollections.observableArrayList();
            for(Part p : Inventory.getAllParts()) {
                if(p.getName().toLowerCase().contains(s)) {
                    result.add(p);
                }
                if(String.valueOf(p.getId()).contains(s)) {
                    result.add(p);
                }
            }
            partsView.setItems(result);
            if(result.size() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search");
                alert.setContentText("Part is not found. Please search for another part");
                alert.showAndWait();
                return;
            }
        }
    }
    
}
