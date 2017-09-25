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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Payal
 */
public class WordDocumentGenerator implements DocumentGenerator {

    int counter = 1;
    String picId, pathFile;
    String[] strArray = new String[10];
    FileOutputStream fos;

    @Override
    public void generateSingleDocument(File[] files, File path) {
        pathFile = files[0].getName() + counter + ".docx";
        counter++;
        CustomXWPFDocument document = new CustomXWPFDocument();
        try {
            fos = new FileOutputStream(new File(pathFile));
            for (int i = 0; i < files.length; i++) {

                picId = document.addPictureData(new FileInputStream(new File(files[i].getAbsolutePath())), org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG);
                document.createPicture(strArray[i], document.getNextPicNameNumber(org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG), 750, 600);
                strArray[i] = picId;
            }
            document.write(fos);
            save(pathFile, path);//To change body of generated methods, choose Tools | Templates.
        } catch (IOException | InvalidFormatException ex) {
            ex.printStackTrace();
        }finally{
            try {
                fos.flush();
            
            fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
    }

    @Override
    public void generateMultipleDocument(File[] file, File path) {
        for (File file2 : file) {
            pathFile = file2.getName() + ".docx";
            CustomXWPFDocument document = new CustomXWPFDocument();
            try {
                fos = new FileOutputStream(new File(pathFile));

                picId = document.addPictureData(new FileInputStream(new File(file2.getAbsolutePath())), org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG);

                System.out.println(document.getNextPicNameNumber(org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG));
                document.createPicture(picId, document.getNextPicNameNumber(org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG), 700, 600);
                document.write(fos);
                save(pathFile, path);
            } catch (FileNotFoundException ex) {
               ex.printStackTrace();
            } catch (InvalidFormatException | IOException ex) {
              ex.printStackTrace();
            }finally{
                try {
                    fos.flush();
                    fos.close();
                    } catch (IOException ex) {
                    ex.printStackTrace();
                } 
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
