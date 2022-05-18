package UI;

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
import entities.reclamation;
import services.ReclamationService;
import entities.reponse;
import entities.review;
import services.ReviewService;

public class ListeReviewAdminController {
        private Stage stage;
    private Scene scene;
    private Parent root;
    
    int index= -1;
    ObservableList<review> oblist = FXCollections.observableArrayList();
    @FXML
    private Button Refresh;
     @FXML
    private Button Supprimer;
    
    @FXML
     private TableView<review> table;
  
    @FXML
    private TableColumn<review, String> col_nomclient;

    @FXML
    private TableColumn<review, String> col_nomproduit;

    @FXML
    private TableColumn<review, Integer> col_note;

    @FXML
    private TableColumn<review, String> col_comm;

    

    @FXML
    void RefreshAction(ActionEvent event) {
        ReviewService rs = new ReviewService();
        List<review> r = rs.afficher();
        r.forEach(e->oblist.add(e));
        r.forEach(e -> System.out.println("res =>"+e));        
        col_nomclient.setCellValueFactory(new PropertyValueFactory<>("nomclient"));
        col_nomproduit.setCellValueFactory(new PropertyValueFactory<>("nomproduit"));
        col_note.setCellValueFactory(new PropertyValueFactory<>("note"));
        col_comm.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        table.setItems(oblist);
      /*table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
         table.refresh();
        System.out.println("TABLE ===> "+table);*/
                                   
    }
      @FXML 
    void Retourner(ActionEvent event) throws IOException{
    Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("accueil.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    
    }
     @FXML
    private void SupprimerAction(ActionEvent event) {
     
      review r=  table.getSelectionModel().getSelectedItem();
      ReviewService rs = new ReviewService();
      rs.supprimer(r.getReviewid());
      table.getItems().clear();
      Alert alert = new Alert(AlertType.INFORMATION);     
      alert.setTitle("Delete");
      alert.setHeaderText(null);
      alert.setContentText("suppression avec succes!");
      alert.showAndWait();

    }
    
}

