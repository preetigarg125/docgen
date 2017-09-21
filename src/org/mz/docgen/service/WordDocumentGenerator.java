/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public void generateSingleDocument(File[] file, File path) {
        pathFile = file[0].getName() + counter + ".docx";
        counter++;
        CustomXWPFDocument document = new CustomXWPFDocument();
        try {
            fos = new FileOutputStream(new File(pathFile));
            for (int i = 0; i < file.length; i++) {

                picId = document.addPictureData(new FileInputStream(new File(file[i].getAbsolutePath())), org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG);
                document.createPicture(strArray[i], document.getNextPicNameNumber(org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG), 750, 600);
                strArray[i] = picId;
            }
            document.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException | InvalidFormatException ex) {
            Logger.getLogger(WordDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        save(pathFile, path);//To change body of generated methods, choose Tools | Templates.
    }

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
                fos.flush();
                fos.close();
                save(pathFile, path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WordDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException | IOException ex) {
                Logger.getLogger(WordDocumentGenerator.class.getName()).log(Level.SEVERE, null, ex);
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
