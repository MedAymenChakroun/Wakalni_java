/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import static com.itextpdf.text.BaseColor.ORANGE;
import static com.itextpdf.text.BaseColor.WHITE;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.user;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javafx.scene.text.TextAlignment;
import static javafx.scene.text.TextAlignment.CENTER;
import javax.swing.text.StyleConstants.ColorConstants;
import services.userService;


/**
 *
 * @author malek guemri
 */
public class PDFUtils {
    public void liste_users(user u) throws FileNotFoundException, DocumentException {
        userService sa = new userService();
        String message = "List des Utilisateurs ";
        

        String file_name = "PDF/liste_users.pdf";
        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        para.setAlignment(1);
        document.add(para);
        List<user> users = sa.afficher();
        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("Nom"));
        c1.setBackgroundColor(ORANGE);
        table.addCell(c1);
        
        PdfPCell c2 = new PdfPCell(new Phrase("Prenom"));
        c2.setBackgroundColor(ORANGE);
        table.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase("Email"));
        c3.setBackgroundColor(ORANGE);
        table.addCell(c3);
        
        PdfPCell c4 = new PdfPCell(new Phrase("Adresse"));
        c4.setBackgroundColor(ORANGE);
        table.addCell(c4);
        
        PdfPCell c5 = new PdfPCell(new Phrase("Num Telephone"));
        c5.setBackgroundColor(ORANGE);
        table.addCell(c5);
        PdfPCell c6 = new PdfPCell(new Phrase("Role"));
        c6.setBackgroundColor(ORANGE);
        table.addCell(c6);

        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < users.size(); i++) {
            table.addCell(users.get(i).getNom());
            table.addCell(users.get(i).getPrenom());
            table.addCell(users.get(i).getEmail());
            table.addCell(users.get(i).getAge());
            table.addCell(users.get(i).getTel());
            table.addCell(users.get(i).getRole());

        }
        document.add(table);
        document.close();

    }
    
}
