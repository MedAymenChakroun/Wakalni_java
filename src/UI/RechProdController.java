/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class RechProdController implements Initializable {

    @FXML
    private Button Retour;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcrid;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnajou;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retour(ActionEvent event) {
    }

    @FXML
    private void Ajouter(ActionEvent event) {
    }
    
}
