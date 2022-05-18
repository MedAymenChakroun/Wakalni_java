/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UI;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import entities.reclamation;
import services.ReclamationService;
import entities.reponse;
import services.ReponseService;
import utilities.Javamail;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.mail.MessagingException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollBar;
import org.controlsfx.control.Rating;
import entities.review;
import services.ReviewService;


public class ReviewUserController implements Initializable{
        private Stage stage;
    private Scene scene;
    private Parent root;
     int index= -1;
    ObservableList<String> oblist = FXCollections.observableArrayList();
        @FXML
    private Rating notes;

    
     @FXML
    private ChoiceBox<String> Plist;
     @FXML
    private ScrollBar Scroller;
    @FXML
    private TitledPane Reclamation;
    @FXML
    private Button Envoyer;

  @FXML
    private TextArea Contenu;


    @FXML
    private TextField Sujet;
    
    public void initialize(URL url, ResourceBundle rb) { 
     ReviewService rvs=new ReviewService();
     List<String> r = rvs.listeproduits();
      r.forEach(e->oblist.add(e));
              r.forEach(e -> System.out.println("res =>"+e));        

    Plist.setItems(oblist);
    }

    @FXML
    void EnvoyerReclamation(ActionEvent event) throws MessagingException {
        AccueilController ac = new AccueilController();
    ReviewService rs=new ReviewService();
        System.out.println(Plist.getValue());
        String x = Plist.getValue();
        int score = (int) notes.getRating();
        String Cont =Contenu.getText();  
      int prodid = rs.getidproduit(x);
       review r = new review (score,Cont,rs.getclientidtoken(),prodid);
       rs.ajouter(r);

    Javamail.sendMailrev("ahmed.rahal@esprit.tn");
    }
@FXML
void Retourner(ActionEvent event) throws IOException{
Stage primaryStage= new Stage();
    Parent root = FXMLLoader.load(getClass().getResource("EspaceReview.fxml"));
    stage = (Stage)((Node)event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);
    stage.show();    
}
}
