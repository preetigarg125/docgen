/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mz.docgen.service;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Payal
 */
public interface DocumentGenerator {
    public abstract  void generateSingleDocument(File[] file,File path);
    public abstract void generateMultipleDocument(File[] file,File path);
    
}
