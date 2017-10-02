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
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Payal
 */
public class WordDocumentGenerator implements DocumentGenerator {

    private static final Logger logger = LogManager.getLogger(WordDocumentGenerator.class.getName());

    @Override
    public int generateSingleDocument(File[] files, File destination) {
        FileOutputStream fos = null;
        File wordDoc = new File(destination, files[0].getName() + ".docx");
        CustomXWPFDocument document = new CustomXWPFDocument();
        try {
            String picId;
            fos = new FileOutputStream(wordDoc);
            for (File file : files) {
                picId = document.addPictureData(new FileInputStream(new File(file.getAbsolutePath())), org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG);
                document.createPicture(picId, document.getNextPicNameNumber(org.apache.poi.xwpf.usermodel.Document.PICTURE_TYPE_JPEG), 750, 600);
            }
            document.write(fos);
            logger.info("single WordDocument generated");
            return 1;
        } catch (IOException | InvalidFormatException ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            try {
                if(fos!=null){
                    fos.flush();
                    fos.close();
                }
            } catch (IOException ex) {
                logger.error(ex.getMessage(), ex);
            }
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
        logger.info("Multiple WordDocument generated");
        return result;
    }
}
