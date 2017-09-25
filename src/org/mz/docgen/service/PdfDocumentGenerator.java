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
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.File;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Payal
 */
public class PdfDocumentGenerator implements DocumentGenerator {

    private int counter = 1;
    private String pathFile;
    private Image image;

    @Override
    public void generateSingleDocument(File[] files, File path) {
        Document document = new Document();
        pathFile = files[0].getName() + counter + ".pdf";
        counter++;
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pathFile));
            document.open();
            for (File file1 : files) {
                if (file1.exists()) {
                    image = com.itextpdf.text.Image.getInstance(file1.getAbsolutePath());
                    image.scaleToFit(400, 400);
                    image.setAbsolutePosition(130f, PageSize.A4.getHeight() - image.getScaledHeight() - 100f);
                    image.scaleAbsoluteHeight(300);
                    image.scaleAbsoluteWidth(300);
                    image.getAlignment();
                    document.add((Element) image);
                    document.newPage();
                }
            }
            
            save(pathFile, path);
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }finally{
            document.close();
        }
    }

    @Override
    public void generateMultipleDocument(File[] files, File destinationFilePath) {
        for (File file : files) {
            Document document = new Document();
            try {
                pathFile = file.getName() + ".pdf";
                PdfWriter.getInstance(document, new FileOutputStream(pathFile));
                document.open();
                if (file.exists()) {
                    image = Image.getInstance(file.getAbsolutePath());
                    image.scaleToFit(400, 400);
                    image.setAbsolutePosition(130f, PageSize.A4.getHeight() - image.getScaledHeight() - 100f);
                    image.scaleAbsoluteHeight(300);
                    image.scaleAbsoluteWidth(300);
                    image.getAlignment();
                    document.add(image);
                }
                document.newPage();
                save(pathFile, destinationFilePath);
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally{
                document.close();
            }
        }
    }

    public void save(String pathFile, File path) {
        File file1 = new File(pathFile);
        File gg = new File(path, file1.getName());
        boolean success = file1.renameTo(gg);
    }
}
