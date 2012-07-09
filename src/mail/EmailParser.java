/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.io.*;


/**
 * Class responsible for parsing a HTML message.
 * @author Alex Hughes
 */
public class EmailParser {
            
    /**
     * Reads a file's contents into a String.
     * @param aFile
     * @return
     * @throws IOException 
     */
    public static String parseContent(File aFile) throws IOException {
        String content = "";
        StringBuilder fileData = new StringBuilder(1000);
        BufferedReader reader = new BufferedReader(new FileReader(aFile));
        
        char[] buf = new char[1024]; //hard-coded buffer
        int numRead=0;
        
        while((numRead = reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
            buf = new char[1024];
        }
        reader.close();
        content = fileData.toString();
        return content;
    }
    
}
