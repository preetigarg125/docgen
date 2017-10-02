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

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * @author parii
 */
public class DocGeneratorFactoryTest extends TestCase {

    /**
     * If in getGenerator method pass 0 than return object of PdfDocumentGenerator class
     * Test of getGenerator method, of class DocGeneratorFactory.
     * Expected result object of PdfDocumentGenerator class
     * Actual result object of PdfDocumentGenerator class
     */
    @Test
    public void testGetPdfDocumentGeneratorObj() {
        assertNotNull(DocGeneratorFactory.getGenerator(0));
    }
    
    /**
     * If in getGenerator method pass 1 than return object of WordDocumentGenerator class
     * Test of getGenerator method, of class DocGeneratorFactory.
     * Expected result object of WordDocumentGenerator class
     * Actual result object of WordDocumentGenerator class
     */
    @Test
    public void testWordDocumentGeneratorObj() {
        assertNotNull(DocGeneratorFactory.getGenerator(1));
    }
    
    /**
     * If in getGenerator method pass any value except 0 and 1 than return null
     * Test of getGenerator method, of class DocGeneratorFactory.
     * Expected result null
     * Actual result null
     */
    @Test
    public void testGetObjNull() {
        assertNull(DocGeneratorFactory.getGenerator(-10));
    }
    
}
