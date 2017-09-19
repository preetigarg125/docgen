/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

// javax.swing.text.Document;
import com.itextpdf.text.BadElementException;
import java.io.File;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.*;
import java.io.IOException;

/**
 *
 * @author Payal
 */
public class PdfDocumentGenerator implements DocumentGenerator {

    int counter = 1, counter1 = 1;
    File[] file;
    String pathFile;
    Image image1;

    public void generateSingleDocument(File[] file, File path) {
        Document document = new Document();
        pathFile = file[0].getName() + counter + ".pdf";
        counter++;
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pathFile));
            document.open();
            for (File file1 : file) {
                if (file1.exists()) {

                    image1 = com.itextpdf.text.Image.getInstance(file1.getAbsolutePath());
                    image1.scaleToFit(400, 400);
                    image1.setAbsolutePosition(130f, PageSize.A4.getHeight() - image1.getScaledHeight() - 100f);
                    image1.scaleAbsoluteHeight(300);
                    image1.scaleAbsoluteWidth(300);
                    image1.getAlignment();
                    document.add((Element) image1);
                    document.newPage();
                }
            }
                document.close();
            save(pathFile, path);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(PdfDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void generateMultipleDocument(File[] file, File path) {
        for (File file1 : file) {
            Document document = new Document();
            try {
                pathFile = file1.getName() + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pathFile));
                document.open();
                if (file1.exists()) {
                    try {
                        image1 = com.itextpdf.text.Image.getInstance(file1.getAbsolutePath());
                        image1.scaleToFit(400, 400);
                        image1.setAbsolutePosition(130f, PageSize.A4.getHeight() - image1.getScaledHeight() - 100f);
                        image1.scaleAbsoluteHeight(300);
                        image1.scaleAbsoluteWidth(300);
                        image1.getAlignment();
                    } catch (BadElementException | IOException ex) {
                        Logger.getLogger(PdfDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    document.add(image1);
                }
                document.newPage();
                 document.close();
                save(pathFile, path);
            } catch (DocumentException | FileNotFoundException ex) {
                Logger.getLogger(PdfDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void save(String pathFile, File path) {
        File file1 = new File(pathFile);

        File gg = new File(path, file1.getName());
        boolean success = file1.renameTo(gg);
        System.out.print(success);

    }
}
