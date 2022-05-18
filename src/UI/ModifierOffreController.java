/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import entities.Offre;
import entities.produit;
import services.OffreService;
import utilities.datasource;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author lacht
 */
public class ModifierOffreController implements Initializable {

    @FXML
    private TextField txttype;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtcrid;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnMod;
    @FXML
    private Button btannuler;

    /**
     * Initializes the controller class.
     */
    
    Offre o = new Offre();
    OffreService os = new OffreService();
    private ComboBox<Integer> combo_id;
    private DatePicker dateexp;
    private DatePicker lsdate;
    @FXML
    private TableView<Offre> table_offre;
    @FXML
    private TableColumn<Offre, String> NomColmn;
    @FXML
    private TableColumn<Offre, String> typeColmn;
    @FXML
    private TableColumn<Offre, Integer> cridColmn;
    @FXML
    private TableColumn<Offre, Float> prixColmn;
    @FXML
    private TableColumn<Offre, String> dateclmn;
    
        int index = -1;
        ObservableList<Offre> oblist = FXCollections.observableArrayList() ; 
    @FXML
    private TextField txtdate;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            String req="select crid from offre";
//            PreparedStatement pst = Datasource.getInstance().getCnx().prepareStatement(req);
//            ResultSet rs=pst.executeQuery();
//            ObservableList<Integer> id = null;
//            List<Integer> list = new ArrayList<>();
//            while(rs.next()){
//                
//                list.add(rs.getInt("crid"));
//                
//            }
//            id = FXCollections.observableArrayList(list);
//            .setItems(id);
//        } catch (SQLException ex) {
//            Logger.getLogger(ModifierOffreController.class.getName()).log(Level.SEVERE, null, ex);
//        }
afficher();
    }    

    @FXML
    private void Modiffier(ActionEvent event) throws ParseException {
          try {
                Offre  p1 =table_offre.getSelectionModel().getSelectedItem();
        String nom,type,datefin;
      int crid;
      float prix;
      
      //Date datefin;
            nom = txtnom.getText();
            type = txttype.getText();
            crid = Integer.parseInt(txtcrid.getText());
            prix = Float.parseFloat(txtprix.getText());
            
            datefin = txtdate.getText();
            //datee// 
            p1.setNom(nom);
            p1.setType(type);
            p1.setPrix(prix);
            System.out.print(p1.getPrix());
             Date date=Date.valueOf(datefin);
            p1.setDatefin(date);
            
            OffreService os = new OffreService();
            os.modifier(crid, p1);
            
        
      
        //ps.modifier(new produit(combo_id.getSelectionModel().getSelectedItem(), txtnom.getText(), txttype.getText(),Integer.parseInt(txtcrid.getText()) Float.parseFloat(txtprix.getText())));       
        JOptionPane.showMessageDialog(null, "Offre modifié");
        
        //REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("FXMLOffre2.fxml"));
            Parent root = loader.load(); 
            txtnom.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger(ModifierProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {
             try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLOffre2.fxml"));
            Parent root=loader.load();
            FXMLOffre2Controller pc=loader.getController();
            txtnom.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(ModifierOffreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void getSelected(MouseEvent event) {
          
          index = table_offre.getSelectionModel().getSelectedIndex();
        if(index <=-1){
        return;
        }
        txtnom.setText(NomColmn.getCellData(index));
        txttype.setText(typeColmn.getCellData(index));
        txtcrid.setText(cridColmn.getCellData(index).toString());
        txtprix.setText(prixColmn.getCellData(index).toString());
        txtdate.setText(String.valueOf(dateclmn.getCellData(5)));
        //dateclmn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        }
       void afficher() {
                table_offre.getItems().clear();


        OffreService ps = new OffreService();
        List<Offre> lp = ps.afficher();
        
        lp.forEach(e->oblist.add(e));
        NomColmn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeColmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        cridColmn.setCellValueFactory(new PropertyValueFactory<>("crid"));
        prixColmn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        dateclmn.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        table_offre.setItems(oblist); 
}
}
