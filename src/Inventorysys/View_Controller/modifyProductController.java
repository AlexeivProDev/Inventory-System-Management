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
import javafx.stage.Stage;

/**
 *FXML
 * @author Alexeiv Perez
 */
public class modifyProductController implements Initializable{

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
    private TableColumn<Part, Integer > columnPartID;
    @FXML
    private TableColumn<Part, String> columnPartName;
    @FXML
    private TableColumn<Part, Integer> columnInv;
    @FXML
    private TableColumn<Part, Double> columnPrice;
    @FXML
    private TableColumn<Product, Integer> columnParttw;
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
    ObservableList<Part> associated = FXCollections.observableArrayList();
    
    
     /**
     * Initializes the controller class.
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
        ID.setEditable(false);
        
        
    }    
    /**
     *This method will add the selected part to the associated part table which is labeled as productView 
     * @param event add associated part to the associated parts table which is labeled as productView
     */
    @FXML
    public void addPart(ActionEvent event) {
         Part selection = partsView.getSelectionModel().getSelectedItem();
         associatedPart.add(selection);
         productsView.setItems(associatedPart);
    }
    /**
     * This method will remove the selected associated part from the  associated parts table which is labeled as productView
     * @param event remove associated part
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
        Part deleteProduct = productsView.getSelectionModel().getSelectedItem(); // this is to delete the selected part
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Delete Confirm");
            alert.setContentText("Are you sure delete this Product?");
            
            alert.showAndWait();
        if(alert.getResult() == ButtonType.YES)
        {
            associatedPart.remove(deleteProduct);
        }      
    }
    /**
     * This method is to save the product and take the user back to the main screen once he is done filling out the information which includes some of the conditions to allow the user to proceed---
     * I encountered an error while building this program "java.lang.NumberFormatException" When the user would try to put letters in to the machine id filed-- Also, when the user tried to put letters in the Text fields such as price, inv, max, min; I would get the same error-- I fixed this error by putting some exceptions into the program  for each field and i used the "try and catch" approach
     * @param event save product and back to main screen
     * @throws IOException exception
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
            alert.setContentText("Name field only accepts letters! Please make an adjustment");
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
            alert.setContentText("Inv and Min fields only accept numbers! Please make an adjustment");
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
            alert.setContentText("Max field only accepts numbers! Please make an adjustment");
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
            alert.setContentText("Price field only accepts numbers! Please make an adjustment");
            alert.showAndWait();
            return;
       }
        
        Product product = new Product (Integer.parseInt(ID.getText()), Name.getText(),  Integer.parseInt(Inv.getText()),(int) Double.parseDouble(Price.getText()),  Integer.parseInt(Max.getText()), Integer.parseInt(Min.getText()));
        for(int i= 0; i < associatedPart.size(); i++){
            product.addAssociatedPart(associatedPart.get(i));
        }
        Inventory.updateProduct(product);
        
        Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(viewScene);
            window.show();                
    }
    
    /**
     * This method is for the cancel button and it will take the user back to the main screen if the user decides to cancel the process
     * @param event back to main screen
     * @throws IOException exception
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
     * This is a method to receive the data from the main screen
     * @param product add product
     */
    public void receiveModifyData (Product product){
            //productView.setItems()
            ID.setText(String.valueOf(product.getId()));
            Inv.setText(String.valueOf(product.getStock()));
            Name.setText(product.getName());
            Price.setText(String.valueOf(product.getPrice()));
            Max.setText(String.valueOf(product.getMax()));
            Min.setText(String.valueOf(product.getMin()));
            associatedPart = product.getassociatedPart();
            productsView.setItems(associatedPart);
            
        
    }
    /**
     * This is a method to search through the table list and allow the user to search with the ID or the Name of the item-- The program will warn the user if the part is not included in the list
     * @param event search for part
     */
    @FXML
    public void searchBoxModify(KeyEvent event) {
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
