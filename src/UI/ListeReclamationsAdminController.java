package UI;

import utilities.Javamail;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import entities.reclamation;
import services.ReclamationService;
import entities.reponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ReponseService;
import services.ReviewService;

public class ListeReclamationsAdminController {
         private Connection conn;
    private Statement ste;
    private PreparedStatement pste;

    private Stage stage;
    private Scene scene;
    private Parent root;

    int index = -1;
    ObservableList<reclamation> oblist = FXCollections.observableArrayList();
    @FXML
    private Button Refresh;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Valider;
    @FXML
    private Button Refuser;

    @FXML
    private TableView<reclamation> table;

    @FXML
    private TableColumn<reclamation, String> col_sujet;

    @FXML
    private TableColumn<reclamation, String> col_contenu;

    @FXML
    private TableColumn<reclamation, Integer> col_nomclient;

    @FXML
    private TableColumn<reclamation, String> col_etat;

    @FXML
    void RefreshAction(ActionEvent event) {
        table.getItems().clear();
        ReclamationService rs = new ReclamationService();
        List<reclamation> r = rs.afficher();
        r.forEach(e -> oblist.add(e));
        r.forEach(e -> System.out.println("res =>" + e));
        col_sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        col_contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("reponse"));
        table.setItems(oblist);
        /*table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         table.refresh();
         System.out.println("TABLE ===> "+table);*/

    }

    @FXML
    void Retourner(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void SupprimerAction(ActionEvent event) {

        reclamation r = table.getSelectionModel().getSelectedItem();
        ReclamationService rs = new ReclamationService();
        rs.supprimer(r.getReclamationid());
        table.getItems().clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Delete");
        alert.setHeaderText(null);
        alert.setContentText("suppression avec succes!");
        alert.showAndWait();

    }

    @FXML
    private void ValiderAction(ActionEvent event) throws MessagingException {
        reclamation r = table.getSelectionModel().getSelectedItem();
        System.out.println(r.getReponse());
        ReclamationService rs = new ReclamationService();
           String req="UPDATE `reclamation` SET  `reponse`='Valide'   WHERE `reclamationid`='"+r.getReclamationid()+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
       //      ReponseService reps = new ReponseService();
       //  reps.repvalider(r.getReclamationid());
      //   reps.afficher();
      //   rs.update(r.getReclamationid());
        table.getItems().clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("validation avec succes!");
        alert.showAndWait();
        Javamail.sendMailvv("ahmed.rahal@esprit.tn");
    }

    @FXML
    private void RefuserAction(ActionEvent event) throws MessagingException {
        reclamation r = table.getSelectionModel().getSelectedItem();
        ReclamationService rs = new ReclamationService();
           String req="UPDATE `reclamation` SET  `reponse`='refuse'   WHERE `reclamationid`='"+r.getReclamationid()+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReviewService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        //ReponseService reps = new ReponseService();
      //  reps.reprefuser(r.getReclamationid());
      //  rs.update(r.getReclamationid());
        table.getItems().clear();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Refuser");
        alert.setHeaderText(null);
        alert.setContentText("refus avec succes!");
        alert.showAndWait();
        Javamail.sendMailrr("ahmed.rahal@esprit.tn");
    }
}
