package UI;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utilities.datasource;
import entities.commande;
import services.commandeservice;
import entities.panier;
import javafx.scene.Node;
import javafx.scene.control.Label;
import services.panierservice;
import entities.produit;


import services.ProduitPanierservice;
import utilities.NotificationUtils;

public class controllerinterfacepanier implements Initializable{
    ObservableList<produit> oblist = FXCollections.observableArrayList();
    Parent root;
    private Statement ste;
    private PreparedStatement pste;
    Connection conn = datasource.getInstance().getCnx();
        


    @FXML
    private Button Refresh;

    @FXML
    private TableView<produit> table;

    @FXML
    private TableColumn<produit, String> col_nomproduit;

    @FXML
    private TableColumn<produit, String> col_type;

    @FXML
    private TableColumn<produit, Float> col_prix;

    @FXML
    private TableColumn<produit, Integer> col_quantite;

  /*  @FXML
    private TableColumn<produitpanier, button> col_plus;

    @FXML
    private TableColumn<produitpanier, button> col_minus;*/

    @FXML
    private Button Payement;


    @FXML
    private Button Supprimer;
    @FXML
    private TableColumn<?, ?> col_plus;
    @FXML
    private TableColumn<?, ?> col_minus;
    @FXML
    private Button retour;
    @FXML
    private Label total;
    
    
    
    
      public void initialize(URL url, ResourceBundle rb) {
    
    RefreshAction();
    }    
    
    
    
    

    @FXML
    void RefreshAction() {
    ProduitPanierservice ps = new ProduitPanierservice();
    panierservice pf = new panierservice();
          table.getItems().clear();
         
        List<produit> lpa = ps.jointure();
        lpa.forEach(e->oblist.add(e));
        lpa.forEach(e->System.out.println("e==> "+e));
        System.out.print("oblist"+oblist);
        col_nomproduit.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
       // col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
       // col_nomlivreur.setCellValueFactory(new PropertyValueFactory<>("nomlivreur"));

     table.setItems(oblist);
     total.setText(String.valueOf(pf.somme())); 
    }

    @FXML
    void payment(ActionEvent event) throws IOException {
        NotificationUtils nu = new NotificationUtils();
       java.util.Date date = new java.util.Date();
       java.sql.Timestamp sqlTimeStamp = new java.sql.Timestamp(date.getTime());
       java.sql.Timestamp sqlTimeStampf = new java.sql.Timestamp(date.getTime()+(1000 * 60 * 60 *1));
       commandeservice cs = new commandeservice();
           panierservice ps = new panierservice();
            List<panier> pp = ps.afficher(); 
      commande c=new commande(pp.get(1).getPanierid(),pp.get(1).getClienid(),sqlTimeStamp, sqlTimeStamp ,sqlTimeStampf);
        cs.ajouter(c);
       nu.Notification("felicitation", "Vous venez de passer une commande d'une somme de"+total.getText());
       Stage primaryStage= new Stage();
       Parent root = FXMLLoader.load(getClass().getResource("Stripe.fxml"));
       primaryStage.setScene(new Scene(root,500,500));
       primaryStage.setTitle("payer");
       primaryStage.show();
    }


    @FXML
    void SupprimerAction(ActionEvent event) {
        produit r=  table.getSelectionModel().getSelectedItem();
      panierservice ps = new panierservice();
      ps.supprimer(r.getNom(),r.getType(),r.getPrix());
      table.getItems().clear();
      Alert alert = new Alert(Alert.AlertType.INFORMATION);     
      alert.setTitle("Delete");
      alert.setHeaderText(null);
      alert.setContentText("suppression avec succes!");
      alert.showAndWait();

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("accueil_user.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

}
