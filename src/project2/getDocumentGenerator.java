/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author Payal
 */
public class getDocumentGenerator {

    public static DocumentGenerator getDocument(String doctype)
    {
        if(doctype.equals("pdf"))
            return(new PdfDocumentGenerator());
        else if(doctype.equals("doc"))
            return(new WordDocumentGenerator());
       return(new WordDocumentGenerator());
    }
}

