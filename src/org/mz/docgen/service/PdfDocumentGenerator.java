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
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Payal
 */
public class PdfDocumentGenerator implements DocumentGenerator {
     private static final Logger logger = LogManager.getLogger(PdfDocumentGenerator.class.getName());
    @Override
    public int generateSingleDocument(File[] files, File destination) {
        File pdf = new File(destination, files[0].getName() + ".pdf");
        Document pdfDoc = new Document();
        try {
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(pdf));
            pdfDoc.open();
            Image image;
            for (File file : files) {
                if (file.exists()) {
                    image = Image.getInstance(file.getAbsolutePath());
                    image.scaleToFit(400, 400);
                    image.setAbsolutePosition(130f, PageSize.A4.getHeight() - image.getScaledHeight() - 100f);
                   image.scaleAbsoluteHeight(300);
                   image.scaleAbsoluteWidth(300);
                    pdfDoc.add((Element) image);
                    pdfDoc.newPage();
                }
            }
             logger.info("files generated");
            return 1;
        } catch (DocumentException | IOException ex) {
            logger.error(ex.getMessage(),ex);
        }finally{
            pdfDoc.close();
        }
        return 0;
    }

    @Override
    public int generateMultipleDocument(File[] files, File destinationFilePath) {
        int result = 1;
        File[] singleFileArray = new File[1];
        for (File file : files) {
            singleFileArray[0] = file;
            result = result & generateSingleDocument(singleFileArray, destinationFilePath);
            
        }
        logger.info("multiple Files generated");
        return result;
    }
}
