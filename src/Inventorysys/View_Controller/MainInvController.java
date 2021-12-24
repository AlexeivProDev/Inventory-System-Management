/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.View_Controller;


import Inventorysys.Main.InventorySystem;
import Inventorysys.Model.Inventory;
import static Inventorysys.Model.Inventory.getAllParts;
import static Inventorysys.Model.Inventory.lookUpProduct;
import Inventorysys.Model.Part;
import Inventorysys.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
  * FXML Controller class
  *
  * @author Alexeiv Perez
  */
public class MainInvController implements Initializable {
     
    
    @FXML
    private Label theLabel;
    @FXML
    private Button addPartButton;
    @FXML
    private Button addProductButton;
    @FXML
    private TextField searchForProduct;
    @FXML
    private Button modifyPartButton;
    @FXML
    private TableView<Part> tableView;
    @FXML
    private TableColumn<Part, Integer> partsID;
    @FXML
    private TableColumn<Part,String > partsName;
    @FXML
    private TableColumn<Part, Integer> partsinvLevel;
    @FXML
    private TableColumn<Part, Double> partsPrice;
    @FXML
    private Button deletePartButton;
    @FXML
    private TextField searchForPart;
    @FXML
    private Button partsSearchButton;
    @FXML
    private TableColumn<Product, Integer> productID;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productInv;
    @FXML
    private TableColumn<Product, Double> productPrice;
    @FXML
    private Button modifyForProduct;
    @FXML
    private TableView<Product> productTableView;
    
   /**
     * Initializes the controller class--
     * For an update to the application, It would be good to eliminate the search boxes on two separate tables; Instead, implement a big search box in the middle and have either one of the table populate when the user enters the unique name or id of the part and product---Also, implement a view button to both tables ---When the user selects and clicks view on parts table for example, a new window would pop up and show the user the details of the part and which product is associated with the part--- The same thing should be done to the Products table. On the view scene the user would not be able to edit any parts of the product/part.
     * @param url initializes
     * @param rb initializes
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
     tableView.setItems(Inventory.getAllParts()); 
     
     ///PartsTable
     partsID.setCellValueFactory(new PropertyValueFactory<>("id"));
     partsName.setCellValueFactory(new PropertyValueFactory<>("name"));
     partsinvLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
     partsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
   
     ///ProductsTable
     productTableView.setItems(Inventory.getAllProducts());
     productID.setCellValueFactory(new PropertyValueFactory<>("id"));
     productName.setCellValueFactory(new PropertyValueFactory<>("name"));
     productInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
     productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
   
}
   /**
     * This method will take the user to the add part scene
     * @param event this is to add a part
     * @throws java.io.IOException exception
     */     
    @FXML
    public void addButton(ActionEvent event) throws IOException {
         

            Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/AddPart.fxml"));
            Scene viewScene = new Scene(viewParent);
            
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(viewScene);
            window.show();
            
        }
    
    
    /**
     * This method will take the user to the add product scene
     * @param event this is to add a product and take the user to the product scene
     * @throws java.io.IOException exception
     */
    @FXML
    public void addButton1(ActionEvent event) throws IOException {
        
            Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/AddProduct.fxml"));
            Scene viewScene = new Scene(viewParent);
            
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            //m_nState = 0;
            window.setScene(viewScene);
            window.show();
    }

    /**
     * This method will the information from the selected item and pass it onto the modify part controller
     * in order for the user to be able to update the information on a part
     * @param event this is to modify a part
     * @throws java.io.IOException exception
     */
    @FXML
    public void modifyButton(ActionEvent event) throws IOException {

        int nselect = tableView.getSelectionModel().getSelectedIndex();// .getSelectedItem();
        if(nselect < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please select a Part");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/Inventorysys/View_Controller/ModifyPart.fxml"));
        loader.load();
        ModifyPartController controller = (ModifyPartController)loader.getController();
        controller.receiveData(tableView.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent viewParent = loader.getRoot();
        window.setScene(new Scene(viewParent));
        window.show();
          
        }
    /**
     * This method deletes the selected part from the table and it also prevents the user from deleting a part if there are no parts selected
     * @param event this is to delete the selected item from the table
     */
    @FXML
    public void deleteAction(ActionEvent event) {
        int nselect = tableView.getSelectionModel().getSelectedIndex();
        if(nselect < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please select a Part");
            alert.showAndWait();
            return;
        }
        Part deletePart = tableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Delete Confirm");
            alert.setContentText("Are you sure delecte this Part?");
            
            alert.showAndWait();
            
        if(alert.getResult() == ButtonType.YES)
        {
           Inventory.removePart(deletePart);
        }
        
        
    }
    /**
     * This method has an action even that will exit the program when the user clicks on the exit button 
     * @param event this is to exit the program
     */
    @FXML
    public void exitAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Exit Confirm");
            alert.setContentText("Are you want to exit the Program?");
            
            alert.showAndWait();
        
        Platform.exit();
    }
    
    /**
     * This event allows the user to type either the ID or the name of the Part in the textfield to search for any Item
     * @param event this is to search an for an item
     */
    @FXML
    public void searchForPartsBox(ActionEvent event) {
        System.out.println("Search");// this command is just to see if the method is actually getting called
        String s = searchForPart.getText().toLowerCase().trim();
        if(s.isEmpty()) {
            tableView.setItems(Inventory.getAllParts());
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
           tableView.setItems(result);
        }                    
    }
    
    /**
     * This method will take the user to the modify product scene and it will also prevent the user from clicking the button without selecting an item first
     * @param event this is to modify the product
     * @throws java.io.IOException exception
     */
    @FXML
    public void modifyProductButton(ActionEvent event) throws IOException {
    
        int nselect = productTableView.getSelectionModel().getSelectedIndex();
        if(nselect < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please select a Product");
            alert.showAndWait();
            return;
        }
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/Inventorysys/View_Controller/modifyProduct.fxml"));
        loader.load();
        modifyProductController controller = (modifyProductController)loader.getController();
        controller.receiveModifyData(productTableView.getSelectionModel().getSelectedItem());
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Parent viewParent = loader.getRoot();
        window.setScene(new Scene(viewParent));
        window.show();
    }
    
    /**
     * This method will delete an item from the Products table, but the user has to select an item in order to delete
     * @param event this is to delete a product from the table
     * @throws java.io.IOException exception
     */
    @FXML
    public void deleteProductButton(ActionEvent event) throws IOException {
    int nselect = productTableView.getSelectionModel().getSelectedIndex();
        if(nselect < 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please select a Product");
            alert.showAndWait();
            return;
        }
        Product deleteProduct = productTableView.getSelectionModel().getSelectedItem();
        
        // This condition wont let the user delete a product if the product has an associated part to it
        if(deleteProduct.getPartsListSize() > 0)
        { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("You cannot delete a Product that has an associated part");
            alert.showAndWait();
            return;
            
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Delete Confirm");
            alert.setContentText("Are you sure delecte this Product?");
            
            alert.showAndWait();
             
       
        if( alert.getResult() == ButtonType.YES)
        {
            Inventory.removeProduct(deleteProduct);
        }        
        
    }
    
    /**
     * This event allows the user to type either the ID or the name of the Part in the textfield to search for any Item -- The program will warn the user if the part is not found
     * @param event this is to search through the table
     */
    @FXML
    public void searchBoxx(KeyEvent event) {
        System.out.println("type in Search box");
        String s = searchForPart.getText().toLowerCase().trim();
        
        if(s.isEmpty()) {
            tableView.setItems(Inventory.getAllParts());
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
            tableView.setItems(result); 
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
    
    /**
     * This event allows the user to type either the ID or the name of the Product in the textfield to search for any Item
     */
    @FXML
    private void SearchProductBoxx(KeyEvent event) {
        System.out.println("type in Search box");
        String s = searchForProduct.getText().toLowerCase().trim();
        if(s.isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
        else{
           
            ObservableList<Product> results = FXCollections.observableArrayList();
            for(Product pr : Inventory.getAllProducts()) {
                if(pr.getName().toLowerCase().contains(s)) {
                    results.add(pr);                
                    }
                 if(String.valueOf(pr.getId()).contains(s)) {
                    results.add(pr);
                }                  
            }
            productTableView.setItems(results);
            if(results.size() == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search");
                alert.setContentText("Product is not found. Please search for another product.");
                alert.showAndWait();
                return;
            }
         }                
     }
        
  }
    


