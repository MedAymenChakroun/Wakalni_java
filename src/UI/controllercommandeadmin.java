package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import entities.commande;
import services.commandeservice;
import utilities.datasource;

public class controllercommandeadmin implements Initializable{
    
    ObservableList<commande> oblist = FXCollections.observableArrayList();
    ObservableList<String> oblist1 = FXCollections.observableArrayList();
                              
    
    

    private Statement ste;
    private PreparedStatement pste;
    Connection conn = datasource.getInstance().getCnx();
    
    
    
    
    @FXML
    private TableView<commande> table_commande;

  /*  @FXML
    private TableColumn<commande, Integer> col_commandeid;*/

    @FXML
    private TableColumn<commande, Timestamp> col_datecreation;

    @FXML
    private TableColumn<commande, Timestamp> col_dateexpedition;

    @FXML
    private TableColumn<commande, Timestamp> col_datearrivee;

    @FXML
    private TableColumn<?, String> col_nomclient;

    @FXML
    private TableColumn<?, Float> col_total;

  /*  @FXML
    private TableColumn<?, String> col_nomresto;

    @FXML
    private TableColumn<commande, Integer> col_panierid;*/

    @FXML
    private Button btn_retour;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    afficher();
    //getnomclient()
    //commande c = table_commande.getSelectionModel().getSelectedItem();
    }    
    
    

    @FXML
    void afficher() {
        commandeservice cs = new commandeservice();
        List<commande> lc = cs.afficher();
        lc.forEach(e->oblist.add(e));
        lc.forEach(e->System.out.println("e==> "+e));
        System.out.print("oblist"+oblist);
     //   col_commandeid.setCellValueFactory(new PropertyValueFactory<>("commandeid"));
        col_datecreation.setCellValueFactory(new PropertyValueFactory<>("datecreation"));
        col_dateexpedition.setCellValueFactory(new PropertyValueFactory<>("dateexpedition"));
        col_datearrivee.setCellValueFactory(new PropertyValueFactory<>("datearrivee"));
        col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
       // col_nomresto.setCellValueFactory(new PropertyValueFactory<>("nomresto"));
      //  col_panierid.setCellValueFactory(new PropertyValueFactory<>("panierid"));

      System.out.print("oblist1"+oblist1); 
      //col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
      
      
     table_commande.setItems(oblist);
    }

    
        
  /*      @FXML
    void getnomclient(ActionEvent event) {
        
      commandeservice cs = new commandeservice();
      List<String> la = cs.getnomclient();
      la.forEach((String e)->oblist1.add(e));
      System.out.print("oblist1"+oblist1); 
      col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
    //  table_commande.setItems(oblist1);

    }*/

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("accueil.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    
    
    
    }
    
    
    
    
    
    
    
    

