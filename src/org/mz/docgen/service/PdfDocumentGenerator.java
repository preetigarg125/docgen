/*
 * Copyright (C) 2017 Metazone Infotech Pvt Ltd
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.mz.docgen.service;

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
