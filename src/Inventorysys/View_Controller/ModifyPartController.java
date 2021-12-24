/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.View_Controller;

import Inventorysys.Main.InventorySystem;
import Inventorysys.View_Controller.MainInvController;
import Inventorysys.Model.InHouse;
import Inventorysys.Model.Inventory;
import static Inventorysys.Model.Inventory.getAllParts;
import Inventorysys.Model.OutSourced;
import Inventorysys.Model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Alexeiv Perez
 */
public class ModifyPartController implements Initializable {
   
    Part m_pfromadd;
    Part fromadd2;
    Inventory inv;
    @FXML
    private RadioButton outSourcedButton;
    @FXML
    private RadioButton inHouseButton;
    @FXML
    private TextField id;
    @FXML
    private TextField Name;
    @FXML
    private TextField Inv;
    @FXML
    private TextField Price;
    @FXML
    private TextField Max;
    @FXML
    private TextField machineID;
    @FXML
    private TextField Min;
    @FXML
    private ToggleGroup preferSource;
    @FXML
    private Label machineId;
    @FXML
    private Button modifyPartCancelButton;
    @FXML
    private Button saveModification;
    
    

   
    
  /**
    * This method will take the user back to the main screen if the user clicks on the cancel button
     * @param event this is to cancel and go back tot he main screen
     * @throws java.io.IOException exception
    */
    @FXML
    public void modifyPartCancelButton(ActionEvent event) throws IOException {
        
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
     
   /**
     * These lines will be for the Radio Buttons
     */
        preferSource = new ToggleGroup();
        this.inHouseButton.setToggleGroup(preferSource);
        this.outSourcedButton.setToggleGroup(preferSource);
        id.setEditable(false); 
    } 
    
   /**
     *These lines of codes are to Modify when the user clicks any of the radio buttons
     */
    @FXML
    public void radioHouseChange(){
        if(this.preferSource.getSelectedToggle().equals(this.outSourcedButton))
            machineId.setText("Company Name");
        if(this.preferSource.getSelectedToggle().equals(this.inHouseButton))
            machineId.setText("Machine ID");
    }
            
    /**
     * This method won't allow the user to edit the field
     */
    @FXML
    private void clearText(MouseEvent event) {
         
         id.setEditable(false);
         
    }
    
   /**
    *This method is used to receive data into the modify scene
     * @param fromadd this whole method is to get the data to modify the part
    */
    public void receiveData(Part fromadd){
        
        
            if (fromadd instanceof InHouse){
                
                InHouse fromadd1 = (InHouse) fromadd;
                this.inHouseButton.setSelected(true);
                machineId.setText("Machine ID");
                id.setText(String.valueOf(fromadd1.getId()));
                Inv.setText(String.valueOf(fromadd1.getStock()));
                Name.setText(fromadd1.getName());
                Price.setText(String.valueOf(fromadd1.getPrice()));
                Max.setText(String.valueOf(fromadd1.getMax()));
                Min.setText(String.valueOf(fromadd1.getMin()));
                machineID.setText(String.valueOf(((InHouse)fromadd1).getMachineID()));  
             }
        
            if(fromadd instanceof OutSourced){
            
                OutSourced fromadd2 = (OutSourced) fromadd;
                this.outSourcedButton.setSelected(true);
                machineId.setText("Company ID");
                id.setText(String.valueOf(fromadd2.getId()));
                Inv.setText(String.valueOf(fromadd2.getStock()));
                Name.setText(fromadd2.getName());
                Price.setText(String.valueOf(fromadd2.getPrice()));
                Max.setText(String.valueOf(fromadd2.getMax()));
                Min.setText(String.valueOf(fromadd2.getMin()));
                this.machineID.setText(fromadd2.getCompanyName());          
        }
         m_pfromadd = fromadd;           
    }
    
  /**
    *This method is to save the part, but the user has to meet certain conditions before they can save the part------
    * I encountered an error while building this program "java.lang.NumberFormatException" When the user would try to put letters in to the machine id filed-- Also, when the user tried to put letters in the Text fields such as price, inv, max, min; I would get the same error-- I fixed this error by putting some exceptions into the program  for each field and i used the "try and catch" approach
    * @param event save item and back to the main screen
    * @throws java.io.IOException exception
    */
     @FXML
    public void saveModification(ActionEvent event) throws IOException{
               
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
            alert.setContentText("Inv and Min Only accepts numbers! Please make an adjustment");
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
        
        if( machineID.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("error dialogue");
            alert.setContentText("Please fill out all of the required fields");
            alert.showAndWait();
            return;
            
        } 
        
        //this will add the part to either InHouse or OutHouse depending on which radio button is picked  
        String strmachineID = machineID.getText();
        try{
            int ID = Integer.parseInt(strmachineID);
            if (this.inHouseButton.isSelected()){
                InHouseParts2();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error dialogue");
                alert.setContentText("Company Name only accepts letters! Please make an adjustment");
                alert.showAndWait();
                return;
            }
        }catch(Exception er)
        {
            if (this.outSourcedButton.isSelected()){
                OutSourcedParts2();
            }else            
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error dialogue");
                alert.setContentText("Machine ID only accepts numbers! Please make an adjustment");
                alert.showAndWait();
                return;
            }
            
        }
             
        Parent viewParent = FXMLLoader.load(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
            Scene viewScene = new Scene(viewParent);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(viewScene);
            window.show();
    }
    
  /**
    * This method is to override the current part in the main screen and when it is called to the save method it will send the data to the main screen and update the current Item
    */
     public void InHouseParts2 (){
       Inventory.updatePart(new InHouse (Name.getText(), Double.parseDouble(Price.getText()), Integer.parseInt(Inv.getText()), Integer.parseInt(Max.getText()), Integer.parseInt(Min.getText()), Integer.parseInt(id.getText()), Integer.parseInt(machineID.getText())));
    }
     
  /**
    * This method is to override the current part in the main screen and when it is called to the save method it will send the data to the main screen and update the current Item
    */
    public void OutSourcedParts2 () {
        Inventory.updatePart(new OutSourced(Integer.parseInt(id.getText()),Name.getText(),Double.parseDouble(Price.getText()),Integer.parseInt(Inv.getText()),Integer.parseInt(Max.getText()),Integer.parseInt(Min.getText()),machineID.getText()));
    }
    
}
