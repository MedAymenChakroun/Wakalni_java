/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entities.Leftovers;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class FXBarChartController implements Initializable {
    
    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;;
    @FXML
    private BarChart<String, Integer> LeftoversChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Get an array with the FRENCH month names.
        String[] months = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        xAxis.setLabel("Mois");
        yAxis.setLabel("Nombre de leftovers");
    }    
    
    public void setLeftoversData(List<Leftovers> leftover) {
    	// Count the number of people having their birthday in a specific month.
        int[] monthCounter = new int[12];
        for (Leftovers l : leftover) {
            int month = l.getDateexpiration().getMonth();
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        
        LeftoversChart.getData().add(series);
    }



    
}
