/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import static UI.AccueilController.ID_mod;
import com.itextpdf.text.DocumentException;
import entities.user;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.userService;
import utilities.NotificationUtils;
import utilities.PDFUtils;
import utilities.UserUtils;

/**
 * FXML Controller class
 *
 * @author malek guemri
 */
public class AccueilController implements Initializable {
    @FXML
    private Button btn_leftovers;
    @FXML
    private Button btn_produit;
    @FXML
    private ImageView Pr;
    @FXML
    private Button btn_panier;
    @FXML
    private Button btn_reviews;
    @FXML
    private Button btn_user;
    @FXML
    private Button btn_logout;
    @FXML
    private Tab comptes_tab;
    @FXML
    private TableView<user> aff_table;
    @FXML
    private TableColumn<user, Integer> id;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user, String> tel;
    @FXML
    private TableColumn<user, String> adr;
    @FXML
    private TableColumn<user, String> email;
    @FXML
    private TableColumn<user, String> role;
    @FXML
    private TableColumn<user, Void> modifier;
    @FXML
    private TableColumn<user, Void> supprimer;
    @FXML
    private TextField search;
    @FXML
    private Button pdf;
    @FXML
    private Button ajouter;
    @FXML
    private TextField fld_nom;
    @FXML
    private TextField fld_prenom;
    @FXML
    private TextField fld_email;
    @FXML
    private TextField fld_num;
    @FXML
    private TextField fld_adr;
    @FXML
    private PasswordField fld_pwd;
    @FXML
    private PasswordField fld_pwd2;
    @FXML
    private ComboBox<String> cmb_role;
    private final ObservableList<user> tvObservableList = FXCollections.observableArrayList();
    public static int ID_mod;
    public int idpdf;
    @FXML
    private Button btn_reclamation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmb_role.getItems().add("Admin");
        cmb_role.getItems().add("Client");
        cmb_role.getItems().add("Chef");
        cmb_role.getItems().add("Livreur");
        affichageTabUser();
        addButtonModifierToTable();
        addButtonSuprimmerToTable();
    }    

    @FXML
    private void produit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("FXMLproduit.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void leftovers(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("Main.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void panier(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("interfacecommandeadmin.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void reviews(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("ListeReviewAdmin.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void user(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
         userService us = new userService();
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("login.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    private void search(KeyEvent event) {
        userService us = new userService();
        if ("".equals(search.getText())) {
            affichageTabUser();
        } else {
            List<user> list = us.search(search.getText());
            ObservableList<user> ListUser = FXCollections.observableArrayList();
            for (user u : list) {
                ListUser.add(u);
            }
            aff_table.setItems(ListUser);
        }
    }

    @FXML
    private void gen_pdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        NotificationUtils Notification= new NotificationUtils();
        UserUtils uUtiles = new UserUtils();
        boolean test = uUtiles.check_Box("", "Vous etes sur le point de generer un fichier pdf\n Vous voulez continuez ?");
        if (test) {
            PDFUtils scpdf = new PDFUtils();
            user user;
            userService us = new userService();
            user = us.loadDataUser(this.idpdf);           

            scpdf.liste_users(user);
            Notification.Notification("Pdf Generé ", "Verifier votre repertoire");
        }
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
         userService uService= new userService();
        UserUtils uUtiles = new UserUtils();
        String nom = fld_nom.getText();
        String prenom = fld_prenom.getText();
        String mail = fld_email.getText();
        String password = fld_pwd.getText();
        String cpassword = fld_pwd2.getText();
        String adresse = fld_adr.getText();
        String tel = fld_num.getText();
        String role="";
        
        if("Admin".equals(cmb_role.getValue())){
            role="0";
        }else if("Client".equals(cmb_role.getValue())){
            role="1";
        }else if ("Chef".equals(cmb_role.getValue())){
            role="2";
        }else if ("Livreur".equals(cmb_role.getValue())){
            role="3";
        }

        //controle de saisie
        if (nom.isEmpty()) {
            uUtiles.alert_Box("Verifier votre nom", "Votre nom ne doit pas être vide");
        }else if (prenom.isEmpty()) {
            uUtiles.alert_Box("Verifier votre prenom", "Votre prenom ne doit pas être vide");
        }else if (adresse.isEmpty()) {
            uUtiles.alert_Box("Verifier votre adresse", "Votre adresse ne doit pas être vide");
        }else if (!uUtiles.testEmail(mail)) {
            uUtiles.alert_Box("Verifier votre mail", "veillez saisir une adresse mail valide");
        } else if (!password.equals(cpassword)) {
            uUtiles.alert_Box("Verifier mot de passe", "Veillez verifier votre mot de passe ");
        } else if (!uUtiles.testTel(tel)) {
            uUtiles.alert_Box("Verifier votre numero telephone", "Veillez mettre un numero de telephone valide");
        } else if(uService.checkUniqueEmail(fld_email.getText())){
            uUtiles.alert_Box("Email existant", "Cet Email existe dèja, veuillez utiliser un autre email");
        } else {
            user u = new user(nom,prenom, mail,tel,role,adresse, password);
            uService.ajouter(u);
            uService.update_roles();
            uService.validerCompte(mail);
            
        }
        affichageTabUser();
    }
    public void affichageTabUser() {
        userService us = new userService();
        ObservableList<user> list = FXCollections.observableArrayList(us.afficher());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        role.setCellValueFactory(new PropertyValueFactory<>("role"));
        System.out.print(us.afficher());
        aff_table.setItems(list);

    }

    private void addButtonModifierToTable() {
       
        Callback<TableColumn<user, Void>, TableCell<user, Void>> cellFactory = new Callback<TableColumn<user, Void>, TableCell<user, Void>>() {
            @Override
            public TableCell<user, Void> call(final TableColumn<user, Void> param) {
                final TableCell<user, Void> cell = new TableCell<user, Void>() {

                    private final Button btn = new Button("Modifier");

                    {
                        btn.setOnAction((ActionEvent event)  -> {
                            try {
                                user data = getTableView().getItems().get(getIndex());
                                ID_mod = data.getId();
                                FXMLLoader loader = new FXMLLoader(getClass()
                                        .getResource("ModUser_backend.fxml"));
                                Stage primaryStage = new Stage();
                                Parent root = loader.load();
                                Scene homescene = new Scene(root);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(homescene);
                                window.show();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        modifier.setCellFactory(cellFactory);
    }

    private void addButtonSuprimmerToTable() {

        userService us = new userService();
        Callback<TableColumn<user, Void>, TableCell<user, Void>> cellFactory = new Callback<TableColumn<user, Void>, TableCell<user, Void>>() {
            @Override
            public TableCell<user, Void> call(final TableColumn<user, Void> param) {
                final TableCell<user, Void> cell = new TableCell<user, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            user u = getTableView().getItems().get(getIndex());
                            us.supprimer(u);
                            loadData();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }

        };

        supprimer.setCellFactory(cellFactory);

    }
    public void loadData(){
        userService us = new userService();
        ArrayList<user> list = new ArrayList();
        ObservableList<user> listuser = FXCollections.observableArrayList(us.afficher());
        aff_table.setItems(listuser);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("ListeReclamationsAdmin.fxml"));
        Stage primaryStage = new Stage();
        Parent root = loader.load();
        Scene homescene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
        
    }
    
}
