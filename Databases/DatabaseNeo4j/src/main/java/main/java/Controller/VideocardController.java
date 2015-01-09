/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import static main.java.Controller.DatabaseController.graphdb;
import main.java.Model.Videocard;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author UR mama
 */
public class VideocardController extends ReadController{
    
    private String price;
    private String name;
    private String photo;
    private String desc;
    private String link;
    private String site;
    private String memoryType;
    final private String seperatorData = "<<<";
    HashMap<String, String> hashMap = new HashMap<String, String>();
       
    public VideocardController() {
           hashMap.put(priceProperty, "-");
           hashMap.put(nameProperty,"-");
           hashMap.put(photoProperty, "-");
           hashMap.put(descProperty, "-");
           hashMap.put(linkProperty, "-");
           hashMap.put(siteProperty,"-");
           hashMap.put(memoryTypeProperty, "-");

           assignValuesToHashMap();
       }  
    public void fillData(DatabaseController db,String csvFile, String label){
        ArrayList <String[]> fieldsArrays = readFile(csvFile);
        for(int i = 0; i < fieldsArrays.size(); i++) {
        String[] fields = fieldsArrays.get(i);    
        try {    
        assignValuesToAttributes(fields);  
        assignValuesToHashMap();
        fill(new Videocard(price,name,desc,photo,memoryType,link, site, label));
        updateMySQLDatabase(price, name, link, photo, site);
        }
        catch(Exception eobe) {
            eobe.printStackTrace();
        }       
      }
    }
    
    public void fill(Videocard pcPart) {
             
        Transaction transaction = graphdb.beginTx();
        try {
          Label label = DynamicLabel.label(pcPart.getLabel());
          nodeIndex = graphdb.index().forNodes("exact");
          Node node = graphdb.createNode(label);
          node.setProperty(nameProperty, pcPart.getName());
          node.setProperty(priceProperty, pcPart.getPrice());
          node.setProperty(linkProperty, pcPart.getLink());
          node.setProperty(photoProperty, pcPart.getPhoto());
          node.setProperty(descProperty, pcPart.getDesc());
          node.setProperty(memoryTypeProperty, pcPart.getMemoryType());
          node.setProperty(siteProperty, pcPart.getSite());
          nodeIndex.add(node,nameProperty, pcPart.getName());
          transaction.success();
        } finally {
          transaction.finish();
        }
    }
    
        
    private void assignValuesToAttributes(String[] fields) {
        for(int t = 0; t < fields.length; t++) {                    
            String[] specifications = fields[t].split(seperatorData);
            hashMap.put(specifications[0], specifications[1]);
        }
    }
    
    private void assignValuesToHashMap() {                  
           price = hashMap.get(priceProperty);
           name = hashMap.get(nameProperty);
           photo = hashMap.get(photoProperty);
           desc = hashMap.get(descProperty);
           link = hashMap.get(linkProperty);
           site = hashMap.get(siteProperty);
           memoryType = hashMap.get(memoryTypeProperty);
    }
}
