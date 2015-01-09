/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import static main.java.Controller.DatabaseController.graphdb;
import main.java.Model.Harddisk;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

/**
 *
 * @author UR mama
 */
public class HarddiskController extends ReadController {
    
    private String price;
    private String name;
    private String photo;
    private String desc;
    private String link;
    private String site;
    final private String seperatorData = "<<<";
    HashMap<String, String> hashMap = new HashMap<String, String>();
       
    public HarddiskController() {
           hashMap.put(priceProperty, "-");
           hashMap.put(nameProperty,"-");
           hashMap.put(photoProperty, "-");
           hashMap.put(descProperty, "-");
           hashMap.put(linkProperty, "-");
           hashMap.put(siteProperty,"-");

           assignValuesToHashMap();
       } 
    
    public void fillData(DatabaseController db,String csvFile, String label){
        ArrayList <String[]> fieldsArrays = readFile(csvFile);
        for (String[] fields : fieldsArrays) {
            try {
                assignValuesToAttributes(fields);
                assignValuesToHashMap();
                fill(new Harddisk(price,name,desc,photo,link,site, label));
                updateMySQLDatabase(price, name, link, photo, site);
            }
            catch(Exception eobe) {
                eobe.printStackTrace();
            }
        }
    }
    
    public void fill(Harddisk harddisk) {
             
        Transaction transaction = graphdb.beginTx();
        try {
          Label label = DynamicLabel.label(harddisk.getLabel());
          nodeIndex = graphdb.index().forNodes("exact");
          Node node = graphdb.createNode(label);
          node.setProperty(nameProperty, harddisk.getName());
          node.setProperty(priceProperty, harddisk.getPrice());
          node.setProperty(linkProperty, harddisk.getLink());
          node.setProperty(photoProperty, harddisk.getPhoto());
          node.setProperty(descProperty, harddisk.getDesc());
          node.setProperty(siteProperty, harddisk.getSite());
          nodeIndex.add(node,nameProperty, harddisk.getName());
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
    }
}
