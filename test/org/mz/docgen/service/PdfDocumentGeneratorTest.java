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
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author parii
 */
@RunWith(Parameterized.class)
public class PdfDocumentGeneratorTest {
    
    PdfDocumentGenerator pdfDocumentGenerator;
    File[] files;
    File destination;

    public PdfDocumentGeneratorTest(File[] files,File destination) {
        this.files=files;
        this.destination=destination;
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][]{{new File[]{new File("C:/Users/parii/Desktop/1.jpg"),new File("C:/Users/parii/Desktop/2.jpg"),new File("C:/Users/parii/Desktop/2(1).jpg"),new File("C:/Users/parii/Desktop/4.jpg")},new File("C:/Users/parii/Desktop/New Folder")},
            {new File[]{new File("C:/Users/parii/Desktop/1.jpg"),new File("D:/mz app images/51.png")},new File("C:/Users/parii/Desktop/New Folder")},
            {new File[]{new File("D:/DocumentService.java")},new File("C:/Users/parii/Desktop")},
            {new File[]{new File("D:/doc/resume.doc")},new File("C:/Users/parii/Desktop/New Folder")},
            {new File[]{new File("D:/doc/resume.pdf")},new File("C:/Users/parii/Desktop/New Folder")}
        });
    }
    
    @Before
    public void setup(){
        pdfDocumentGenerator=new PdfDocumentGenerator();
    }

    /**
     * Test of generateSingleDocument method, of class PdfDocumentGenerator.
     * Expected result 1
     * Actual result 1
     */
    @Test
    public void testGenerateSingleDocument() {
        assertEquals(1,pdfDocumentGenerator.generateSingleDocument(files,destination));
    }

    /**
     * Test of generateMultipleDocument method, of class PdfDocumentGenerator.
     * Expected result 1
     * Actual result 1
     */
    @Test
    public void testGenerateMultipleDocument() {
        assertEquals(1,pdfDocumentGenerator.generateMultipleDocument(files,destination));
    }
    
}
