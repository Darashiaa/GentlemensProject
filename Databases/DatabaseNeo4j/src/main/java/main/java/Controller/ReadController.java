/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main.java.Controller;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author sharelison
 */
public class ReadController extends DatabaseController{
    
    public final String seperator = ">>>";
    public ArrayList<String[]> readFile(String csvfile) {
        ArrayList<String[]> fieldsArray = new ArrayList<String[]>();
        try {
        File file = new File(csvfile);
        BufferedReader br = new BufferedReader(new InputStreamReader(
        new FileInputStream(file), "UTF-8"));
        String info;
        String text = "";
        while((info = br.readLine()) != null) {
              text = text + info;
           //   String[] fields = getFields(info);
           //   fieldsArray.add(fields);
            }
            //System.out.println(text);
            String[] fields2 = getLines(text);
            //System.out.println(fields2[0]);
            for (String fields21 : fields2) {
                String[] theFields = getFields(fields21);
                fieldsArray.add(theFields);
            }
            br.close();
        }       
        catch(Exception e) {
            System.out.print(":(");
           e.printStackTrace();
        }
        return fieldsArray;
    }
        
    public String[] getFields(String line) {
        String[] words = line.split(seperator);
        //System.out.println(words[2]);
        return words;
    }
    
    public String[] getLines(String text) {
        String[] productLines = text.split("~~~");
        return productLines;
    }
}
