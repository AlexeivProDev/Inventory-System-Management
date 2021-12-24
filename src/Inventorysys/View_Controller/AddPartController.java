/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventorysys.View_Controller;

import Inventorysys.Model.InHouse;
import Inventorysys.Model.Inventory;
import Inventorysys.Model.OutSourced;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Alexeiv Perez
 */
public class AddPartController implements Initializable {
     @FXML
    private TextField partId;
    @FXML
    private Button addPartCancelButton;
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
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourceRadio;
    @FXML
    private ToggleGroup addPreferSource;
    @FXML
    private Label machineId;
    @FXML
    private Button addPartSaveButton;
    @FXML
    private AnchorPane tablleview;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      /**
       * This code will make the radio buttons work and the Label for machine Code or Company name will remain invisible until a radio button is selected
       */  
        machineId.setText("Machine ID");
        addPreferSource = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(addPreferSource);
        this.outSourceRadio.setToggleGroup(addPreferSource);
    
    }    

    /**     
     * @throws IOException
     * These lines of code will make the cancel work and when clicked, it will
     * return the user to the main screen.
     */
    
    @FXML
    public void addPartCancelButton() throws  Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"",ButtonType.YES,ButtonType.NO);
            alert.setTitle("Cancellation Confirm");
            alert.setContentText("Are you sure you want to cancel?");
            alert.showAndWait();
        returnMain();
    }
    
    
    /**
     *These lines of codes are to Modify when the user clicks any of the radio buttons
     */
    @FXML
    public void radioHouseChange(){
        if(this.addPreferSource.getSelectedToggle().equals(this.outSourceRadio))
            machineId.setText("Company Name");
        
           
        if(this.addPreferSource.getSelectedToggle().equals(this.inHouseRadio))
            machineId.setText("Machine ID");       
    }
    /**
     *This is the button to "save" the parts.once the user presses this button and saves the part it will take the user back to the main screen----
     * I encountered an error while building this program "java.lang.NumberFormatException" When the user would try to put letters in to the machine id filed-- Also, when the user tried to put letters in the Text fields such as price, inv, max, min; I would get the same error-- I fixed this error by putting some exceptions into the program  for each field and i used the "try and catch" approach
     * @param event this is to save a part
     * @throws java.lang.Exception exception
     */
    @FXML
    public void addPartSaveButton(ActionEvent event) throws Exception {
        
        //these set of conditions will stop the user if there are any errors within the conditions given. The user will have to revise and fix the issues
        
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
            alert.setContentText("Name field only accepts numbers! Please make an adjustment");
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
            alert.setContentText("Max field is number format");
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
            if (this.inHouseRadio.isSelected()){
                InHouseParts();
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
            if (this.outSourceRadio.isSelected()){
                OutSourcedParts();
            }else            
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error dialogue");
                alert.setContentText("Machine ID only accepts numbers! Please make an adjustment");
                alert.showAndWait();
                return;
            }
            
        }
            
       returnMain();                                                    
    }
    /**
     * This method sets the id generator for the text field and it does not let the user edit the ID
     */
    @FXML
    private void clearText(MouseEvent event) {
       partId.setEditable(false);
       iDGenerator();
    }
    
    /**
     * This method adds a new part to InHouse 
     */
    public void InHouseParts (){   
       Inventory.addPart(new InHouse(Name.getText(), Double.parseDouble(Price.getText()), Integer.parseInt(Inv.getText()), Integer.parseInt(Max.getText()), Integer.parseInt(Min.getText()), Integer.parseInt(partId.getText()), Integer.parseInt(machineID.getText())));       
    }
    
    /**
     * This method add a new part to Outsourced
     */
    public void OutSourcedParts () {
        Inventory.addPart(new OutSourced(Integer.parseInt(partId.getText()),Name.getText(),Double.parseDouble(Price.getText()),Integer.parseInt(Inv.getText()),Integer.parseInt(Max.getText()),Integer.parseInt(Min.getText()),machineID.getText()));
    }
    //This is the generator for IDs. The user must click the textfield to generate the ID
    public void iDGenerator(){
        
        /*
        This will generate the ID when the user clicks on the text field
        */
        Random idgen = new Random();
        int gen = idgen.nextInt(1000)+1;
        String generator = String.valueOf(gen);
        partId.setText(generator);
    } 
    /**
     * This will return to the main screen
     * @throws java.lang.Exception exception
     */
    public void returnMain() throws Exception {
        
       Stage stage;
       Parent root;
       stage=(Stage)addPartCancelButton.getScene().getWindow();
       FXMLLoader loader= new FXMLLoader(getClass().getResource("/Inventorysys/View_Controller/mainInv.fxml"));
       root= loader.load();
       Scene scene = new Scene (root);
       stage.setScene(scene);
       stage.show();
       
    }

    
}
